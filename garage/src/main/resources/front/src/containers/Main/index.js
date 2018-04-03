import React from 'react';

import { get } from 'lodash/fp';

import Table from '../Table';
import Toolbar from '../Toolbar';

import { getGarages, getGarageInfo } from '../../api/garages';
import { addCar as addCarAPI } from '../../api/cars';

export default class Main extends React.Component {
  state = {
    currentGarageID: null,
    garages: [],
    garage: null,
  };

  handleToolbarChange = (event, index, currentGarageID) => {
    if (currentGarageID !== 'default') {
      getGarageInfo(currentGarageID)
        .then(garage => this.setState({ garage, currentGarageID }))
        .catch(err => console.error(err));
    }
  }

  componentDidMount() {
    getGarages()
      .then(garages => this.setState({ garages }))
      .catch(err => console.error(err));
  }

  addCar = data => {
    addCarAPI(data).catch(err => console.error(err));
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
          addCar={this.addCar}
        />
        <Table cars={get('cars', garage)} />
      </div>
    );
  }
}
