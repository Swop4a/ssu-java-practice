import React from 'react';

import { getOr, get } from 'lodash/fp';

import Table from '../Table';
import Toolbar from '../Toolbar';

import { getGarages, getGarageInfo } from '../../api/garages';

export default class Main extends React.Component {
  state = {
    currentGarageID: '',
    garages: [],
    garage: null,
  };

  handleToolbarChange = (event, index, currentGarageID) => {
    getGarageInfo(currentGarageID)
      .then(garage => this.setState({ garage, currentGarageID }))
      .catch(err => console.error(err));
  }

  componentDidMount() {
    getGarages()
      .then(garages =>
        this.setState({ garages, currentGarageID: getOr(0, '[0].id', garages) }))
      .catch(err => console.error(err));
  }

  render() {
    const { garages, currentGarageID, garage } = this.state;

    return (
      <div>
        <Toolbar
          garages={garages}
          currentGarageID={currentGarageID}
          handleChange={this.handleToolbarChange}
          currentGarage={garage}
        />
        <Table cars={get('cars', garage)} />
      </div>
    );
  }
}
