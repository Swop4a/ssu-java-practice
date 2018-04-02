import React from 'react';
import FontIcon from 'material-ui/FontIcon';
import MenuItem from 'material-ui/MenuItem';
import DropDownMenu from 'material-ui/DropDownMenu';
import { Toolbar, ToolbarGroup, ToolbarTitle } from 'material-ui/Toolbar';

const MyToolbar = ({
  garages,
  handleChange,
  currentGarage,
  currentGarageID,
}) => (
  <Toolbar>
    <ToolbarGroup firstChild={true}>
      <DropDownMenu value={currentGarageID} onChange={handleChange}>
        {garages.length > 0 && garages.map(garage => (
          <MenuItem value={garage.id} key={garage.id} primaryText={garage.title} />
        ))}
      </DropDownMenu>
    </ToolbarGroup>
    <ToolbarGroup>
      <ToolbarTitle text={currentGarage ? `Capacity: ${currentGarage.capacity}` : 'Garages'} />
      <FontIcon className="muidocs-icon-custom-sort" />
    </ToolbarGroup>
  </Toolbar>
);

export default MyToolbar;
