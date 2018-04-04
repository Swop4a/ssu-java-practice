import config from '../config';

import request from '../utils/request';

export const getGarages = () =>
  request(`//localhost:${config.PORT}/api/findAllGarages`)
    .then(json => json.data)
