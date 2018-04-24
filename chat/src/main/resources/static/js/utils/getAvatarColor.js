const colors = [
  '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

export const getAvatarColor = userName => {
  let hash = 0;

  for (let i = 0; i < userName.length; i++) {
    hash = 31 * hash + userName.charCodeAt(i);
  }

  return colors[Math.abs(hash % colors.length)];
}
