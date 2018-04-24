// TODO: fix docs

const handleMessage = message => {
  try {
    return JSON.parse(message.body);
  } catch (error) {
    console.error('Can not parse payload body:', payload);
    return null;
  }
};

/**
 * Handle connection to the webscoket
 * @param  {object} client           socket client, that actually performs all the
 *                                   client-server communication actions
 * @param  {{ user: string, room: string }} settings        connect settings
 * @param  {{ onMessageReceive: function, onConnect: function }} handlers onMessageReceive callback
 * @return {function}                  actual connection handler
 */
const handleConnect = (client, settings, onConnect) => () => {
  client.send(
    `/chat.connectUser`,
    {},
    JSON.stringify({ type: 'CONNECT', sender: settings.user }),
  );

  if (typeof onConnect === 'function') {
    onConnect(settings);
  }
}

/**
 * Handle websocket error
 * @param  {function} onError on error callback
 * @return {function}         actual error handler
 */
const handleError = onError => error => {
  // Here we can perform some error handling

  if (typeof onError === 'function') {
    onError();
  }
}


export const chat = ({
  user,
  socketUrl,
  onConnect,
  onMessageReceive,
  onError,
}) => {
  const socket = new SockJS(socketUrl);
  const stompClient = Stomp.over(socket);

  stompClient.connect(
    {},
    handleConnect(
      stompClient,
      { user, },
      onConnect,
    ),
    handleError(onError),
  );

  return {
    _user: user,
    _room: null,

    /**
     * Send message to the server
     * @param  {object} client web client, that actually performs all the
     *                         client-server communication actions
     * @param  {string} user   name of the user
     * @param  {string} room   name of the room
     * @return {function}      function that actually performs
     *                                            message sending
     */
    sendMessage(message) {
      stompClient.send(
        `/chat.sendMessage/${this._room}`,
        {},
        JSON.stringify({
          type: 'CHAT',
          sender: this._user,
          content: message,
        }),
      );
    },

    /**
     * Handle new message received from the server
     * @param  {function} onMessageReceive callback that will be called after successful
     *                                     message receiving
     * @return {function}                actual new message handler
     */
    _handleNewMessage(payload) {
      const message = handleMessage(payload);

      if (
        typeof onMessageReceive === 'function'
        && message !== null
      ) {
        onMessageReceive(message);
      }
    },

    joinRoom(room, onJoin) {
      this._room = room;

      stompClient.send(
        `/chat.joinRoom/${room}`,
        {},
        JSON.stringify({ type: 'JOIN', sender: this._user }),
      );

      this._messagesSubsciption = stompClient.subscribe(
        `/topic/rooms/${room}`,
        this._handleNewMessage,
      );

      if (typeof onJoin === 'function') {
        onJoin();
      }
    },

    getRooms(onReceive) {
      this._roomSubscription = stompClient.subscribe(
        `/topic/users/${this._user}`,
        payload => {
          const message = handleMessage(payload);
          onReceive(message);
        },
      );

      stompClient.send(`/chat.getRooms/${this._user}`);
    },

    leaveRoom() {
      this._roomSubscription.unsubscribe();
      this._messagesSubsciption.unsubscribe();

      stompClient.send(
        `/chat.leaveRoom/${this._room}`,
        {},
        JSON.stringify({ type: "LEAVE", sender: this._user }),
      );
    },
  };
};
