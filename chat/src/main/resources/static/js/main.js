import { getAvatarColor } from './utils/getAvatarColor.js';
import { chat as createChat } from './chat.js';

const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');

const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const roomForm = document.querySelector('#roomForm');

const messageInput = document.querySelector('#message');
const messageArea = document.querySelector('#messageArea');

const roomInput = document.getElementById('room-input');
const roomSelect = document.getElementById('room-select');

const connectingElement = document.querySelector('.connecting');
const roomTitleElement = document.getElementById('room-title');
const firstPageTitleElement = document.getElementById('username-page__title');

const leftRoomButton = document.getElementById('left-room-button');


let chat = null;

const wipeNode = node => {
  while (node.firstChild) {
    node.removeChild(node.firstChild);
  }
};

const getRooms = () => {
  chat.getRooms(rooms => {
    wipeNode(roomSelect);
    rooms.forEach(room => {
      const option = document.createElement('option');
      option.innerHTML = room;
      option.value = room;

      roomSelect.append(option);
    });
  });
};

function connect(event) {
  event.preventDefault();

  const user = document.querySelector('#name').value.trim();

  if (user) {
    chat = createChat({
      user,
      socketUrl: '/ws',
      onConnect: settings => {
        getRooms();

        firstPageTitleElement.innerHTML = 'Type room title or choose from existing';

        roomForm.addEventListener('submit', join, true)
        leftRoomButton.addEventListener('click', () => {
          chat.leaveRoom();
          getRooms();

          chatPage.classList.toggle('hidden');
          connectingElement.classList.add('hidden');


          wipeNode(messageArea);
        }, true);

        usernameForm.classList.toggle('hidden');
        roomForm.classList.toggle('hidden');
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
        // connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        // connectingElement.style.color = 'red';
      },
    });

  }
}

function join(event) {
  event.preventDefault();

  const room = roomInput.value.trim() || roomSelect.value;

  if (room) {
    chatPage.classList.toggle('hidden');

    chat.joinRoom(
      room,
      () => {
        roomInput.value = '';

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

        roomTitleElement.innerHTML = `Room: ${room}`;

        connectingElement.classList.add('hidden');
      }
    );
  }
}

usernameForm.addEventListener('submit', connect, true)
