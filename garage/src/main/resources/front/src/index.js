import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

import './index.css';
import Main from './containers/Main';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render((
  <BrowserRouter>
    <MuiThemeProvider>
      <Main />
    </MuiThemeProvider>
  </BrowserRouter>
), document.getElementById('root'))

registerServiceWorker();
