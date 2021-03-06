export const parseJSON = response => response.json();

export const checkStatus = response => {
  if (response.status >= 200 && response.status < 300) {
    return response;
  }

  const error = new Error(response.status || response.statusText);
  throw error;
};

export default (url, options, reqOptions = {}) =>
  fetch(url, options)
    .then(checkStatus)
    .then(reqOptions.json === false ? arg => arg : parseJSON)
    .then(data => ({ data }));
