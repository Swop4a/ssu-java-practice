
import config from '../config';

import request from '../utils/request';

export const addCar = carData =>
  request(`//localhost:${config.PORT}/api/addCar`, {
    headers: {
      'content-type': 'application/json',
    },
    method: 'post',
    body: JSON.stringify(carData),
  }).then(json => json.data)
