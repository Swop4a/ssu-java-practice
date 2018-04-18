import { getAvatarColor } from './utils/getAvatarColor.js';
import { chat as createChat } from './chat.js';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
const roomTitleElement = document.getElementById('room-title');

let chat = null;

function connect(event) {
  event.preventDefault();

  const user = document.querySelector('#name').value.trim();
  const room = document.querySelector('#room').value.trim();

  if (user && room) {
    chat = createChat({
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
