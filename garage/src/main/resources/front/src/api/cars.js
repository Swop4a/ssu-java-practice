
import config from '../config';

import request from '../utils/request';

export const addCar = carData =>
  request(`//localhost:${config.PORT}/api/addCar`, {
    headers: {
      'content-type': 'application/json',
    },
    method: 'post',
    body: JSON.stringify(carData),
  }, {
    json: false,
  }).then(json => json.data)

export const deleteCar = carID =>
  request(`//localhost:${config.PORT}/api/removeCar?id=${carID}`, null, {
    json: false,
  }).then(json => json.data)
