import React from 'react';

import { get } from 'lodash/fp';

import Table from '../Table';
import Toolbar from '../Toolbar';

import { getGarages } from '../../api/garages';
import { addCar as addCarAPI, deleteCar as deleteCarAPI } from '../../api/cars';

const loadStyles = {
  color: 'rgba(0, 0, 0, 0.87)',
  paddingLeft: '24px',
  fontSize: '20px',
  margin: '15px 0',
  display: 'block',
};

export default class Main extends React.Component {
  state = {
    currentGarageID: null,
    garages: [],
  };

  handleToolbarChange = (event, index, currentGarageID) => {
    if (currentGarageID !== 'default') {
      this.setState(prevState => ({
        garage: prevState.garages.filter(garage => garage.id === currentGarageID)[0],
        currentGarageID,
      }));
    }
  }

  componentDidMount() {
    getGarages()
      .then(garages => this.setState({ garages }))
      .catch(err => console.error(err));
  }

  addCar = data => {
    addCarAPI(data)
      .then(updatedGarage => { this.updateGarages(updatedGarage); })
      .catch(err => console.error(err));
  }

  deleteCar = id => {
    deleteCarAPI(id)
      .then(() => {
        getGarages()
          .then(garages => this.setState({ garages }))
          .catch(err => console.error(err));
       })
      .catch(err => console.error(err));
  }

  updateGarages = newGarage => {
    this.setState(prevState => ({
      garages: [
        ...prevState.garages.filter(garage => garage.id !== newGarage.id),
        newGarage,
      ],
    }));
  };

  render() {
    const { garages, currentGarageID } = this.state;
    const currentGarage = garages.filter(garage => garage.id === currentGarageID)[0];

    return (
      <div>
        <Toolbar
          garages={garages}
          currentGarageID={currentGarageID}
          currentGarage={currentGarage}
          handleChange={this.handleToolbarChange}
          addCar={this.addCar}
        />
        <span style={loadStyles}>
          {currentGarage && `Load: ${currentGarage.currentLoad}`}
        </span>
        <Table
          cars={get('cars', currentGarage)}
          deleteCar={this.deleteCar}
        />
      </div>
    );
  }
}
