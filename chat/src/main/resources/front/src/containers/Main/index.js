import React from 'react';

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

export default class Main extends React.Component {
  state = { messages: [] }

  connect = event => {
    event.preventDefault();

    if (this.username) {
      this.usernamePage.classList.add('hidden');
      this.chatPage.classList.remove('hidden');

      this.socket = new SockJS('http://localhost:8088/ws');
      this.stompClient = Stomp.over(this.socket);

      this.stompClient.connect({}, this.onConnected, this.onError);
    }
  }

  onError = error => {
    this.connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    this.connectingElement.style.color = 'red';
  }

  onConnected = () => {
    this.stompClient.subscribe('/topic/public', this.onMessageReceived);

    this.stompClient.send(
      "/app/chat.addUser",
      {},
      JSON.stringify({ sender: this.username, type: 'JOIN' }),
    );

    this.connectingElement.classList.add('hidden');
  }

  sendMessage = event => {
    event.preventDefault();
    const messageContent = this.messageInput.value.trim();

    if(messageContent && this.stompClient) {
        const chatMessage = {
          sender: this.username,
          content: messageContent,
          type: 'CHAT'
        };

        this.stompClient.send(
          "/app/chat.sendMessage",
          {},
          JSON.stringify(chatMessage),
        );
        this.messageInput.value = '';
    }
  }

  colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
  ];

  getAvatarColor(messageSender) {
    let hash = 0;
    for (let i = 0; i < messageSender.length; i++) {
      hash = 31 * hash + messageSender.charCodeAt(i);
    }
    const index = Math.abs(hash % this.colors.length);
    return this.colors[index];
  }

  onMessageReceived = payload => {
    const message = JSON.parse(payload.body);
    message.className = '';

    if(message.type === 'JOIN') {
      message.className += 'event-message';
      message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
      message.classList += 'event-message';
      message.content = message.sender + ' left!';
    } else {
      message.className += 'chat-message';

      message.avatarText = message.sender[0];
      message.avatarColor = this.getAvatarColor(message.sender);
      message.usernameText = message.sender;
    }

    // messageArea.scrollTop = messageArea.scrollHeight;
    this.setState(prevState => { messages: [...prevState, message] });
  }


  render() {
    return (
      [
        <div key={1} id="username-page" ref={ref => this.usernamePage = ref}>
            <div className="username-page-container">
                <h1 className="title">Type your username</h1>
                <form id="usernameForm" ref={ref => this.usernameForm = ref} name="usernameForm" onSubmit={this.connect}>
                    <div className="form-group">
                        <input type="text" id="name" ref={ref => this.username = ref} placeholder="Username" autoComplete="off" className="form-control" />
                    </div>
                    <div className="form-group">
                        <button type="submit" className="accent username-submit">Start Chatting</button>
                    </div>
                </form>
            </div>
        </div>,

        <div key={2} id="chat-page" ref={ref => this.chatPage = ref} className="hidden">
            <div className="chat-container">
                <div className="chat-header">
                    <h2>Spring WebSocket Chat Demo</h2>
                </div>
                <div className="connecting" ref={ref => this.connectingElement = ref}>
                    Connecting...
                </div>

                <ul id="messageArea" ref={ref => this.messageArea = ref}>
                  {this.state.messages.map(message => (
                    <li className={message.className}>
                      {message.avatartext && (
                        <i style={{ backgroundColor: message.avatarColor }}>
                          {message.avatartext}
                        </i>)}
                        {message.usernameText && <span>{message.usernameText}</span>}
                      <p>{message.content}</p>
                    </li>
                  ))}
                </ul>

                <form id="messageForm" ref={ref => this.chatPage = ref} name="messageForm" onSubmit={this.sendMessage}>
                    <div className="form-group">
                        <div className="input-group clearfix">
                            <input
                              type="text"
                              id="message"
                              placeholder="Type a message..."
                              autoComplete="off"
                              className="form-control"
                              ref={ref => this.messageInput = ref}
                            />
                            <button type="submit" className="primary">Send</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
      ]
    );
  }
}
