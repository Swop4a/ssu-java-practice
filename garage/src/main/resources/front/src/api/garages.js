import config from '../config';

import request from '../utils/request';

export const getGarages = () =>
  request(`//localhost:${config.PORT}/api/findAllGarages`)
    .then(json => json.data)

export const getGarageInfo = id =>
    request(`//localhost:${config.PORT}/api/getGarageInfo?id=${id}`)
      .then(json => json.data)
