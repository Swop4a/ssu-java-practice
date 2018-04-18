/**
 * Handle new message received from the server
 * @param  {function} onMessageReceive callback that will be called after successful
 *                                     message receiving
 * @return {function}                actual new message handler
 */
const handleNewMessage = onMessageReceive => payload => {
  let message = null

  try {
    message = JSON.parse(payload.body);
  } catch (error) {
    console.error('Can not parse payload body:', payload);
  }

  if (typeof onMessageReceive === 'function' && message !== null) {
    onMessageReceive(message)
  }
}

/**
 * Handle connection to the webscoket
 * @param  {object} client           socket client, that actually performs all the
 *                                   client-server communication actions
 * @param  {{ user: string, room: string }} settings        connect settings
 * @param  {{ onMessageReceive: function, onConnect: function }} handlers onMessageReceive callback
 * @return {function}                  actual connection handler
 */
const handleConnect = (client, settings, handlers) => () => {
  // client.subscribe(`/topic/public/${settings.room}`, handlers.onMessageReceive);
  client.subscribe(`/topic/public`, handlers.onMessageReceive);

  client.send(
    `/app/chat.addUser/${settings.room}`,
    {},
    JSON.stringify({ type: 'JOIN', sender: settings.user }),
  );

  if (typeof handlers.onConnect === 'function') {
    handlers.onConnect(settings);
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

/**
 * Send message to the server
 * @param  {object} client web client, that actually performs all the
 *                         client-server communication actions
 * @param  {string} user   name of the user
 * @param  {string} room   name of the room
 * @return {function}      function that actually performs
 *                                            message sending
 */
const sendMessage = (client, user, room) => message => {
  client.send(
    `/app/chat.sendMessage/${room}`,
    {},
    JSON.stringify({ type: 'CHAT', sender: user, content: message }),
  )
}


export const chat = ({
  user,
  room,
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
      {
        user,
        room,
      },
      {
        onConnect,
        onMessageReceive: handleNewMessage(onMessageReceive),
      },
    ),
    handleError(onError),
  );

  return {
    sendMessage: sendMessage(stompClient, user, room),
  };
};
