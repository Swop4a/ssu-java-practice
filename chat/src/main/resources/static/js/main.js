import { getAvatarColor } from './utils/getAvatarColor.js';
import { chat as createChat } from './chat.js';

const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const messageArea = document.querySelector('#messageArea');
const connectingElement = document.querySelector('.connecting');
const roomTitleElement = document.getElementById('room-title');

function connect(event) {
  event.preventDefault();

  const user = document.querySelector('#name').value.trim();
  const room = document.querySelector('#room').value.trim();

  if (user && room) {
    let chat = createChat({
      user,
      room,
      socketUrl: '/ws',
      onConnect: (settings) => {
        roomTitleElement.innerHTML = `Room: ${settings.room}`;

        connectingElement.classList.add('hidden');
      },
      onMessageReceive: message => {
        const messageElement = document.createElement('li');

        if (message.type === 'JOIN') {
          messageElement.classList.add('event-message');
          message.content = message.sender + ' joined!';
        } else if (message.type === 'LEAVE') {
          messageElement.classList.add('event-message');
          message.content = message.sender + ' left!';
        } else {
          messageElement.classList.add('chat-message');

          var avatarElement = document.createElement('i');
          var avatarText = document.createTextNode(message.sender[0]);
          avatarElement.appendChild(avatarText);
          avatarElement.style['background-color'] = getAvatarColor(message.sender);

          messageElement.appendChild(avatarElement);

          const usernameElement = document.createElement('span');
          const usernameText = document.createTextNode(message.sender);
          usernameElement.appendChild(usernameText);
          messageElement.appendChild(usernameElement);
        }

        const textElement = document.createElement('p');
        const messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);

        messageArea.appendChild(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
      },
      onError: () => {
        connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        connectingElement.style.color = 'red';
      },
    });

    messageForm.addEventListener(
      'submit',
      event => {
        event.preventDefault();

        const messageContent = event.target[0].value.trim();

        if (messageContent) {
          chat.sendMessage(messageContent),

          event.target[0].value = '';
        }
      },
      true,
    );

    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');
  }
}

usernameForm.addEventListener('submit', connect, true)
