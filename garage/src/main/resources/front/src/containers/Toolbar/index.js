import React from 'react';
import {
  MenuItem,
  DropDownMenu,
} from 'material-ui';
import {
  Toolbar,
  ToolbarGroup,
  ToolbarSeparator,
  ToolbarTitle,
} from 'material-ui/Toolbar';

import Dialog from '../../components/Dialog';

const MyToolbar = ({
  garages,
  handleChange,
  currentGarage,
  currentGarageID,
}) => (
  <Toolbar>
    <ToolbarGroup firstChild={true}>
      <DropDownMenu
        maxHeight={300}
        value={currentGarageID || 'default'}
        onChange={handleChange}
      >
        <MenuItem value="default" primaryText="Choose garage" />
        {garages.length > 0 && garages.map(garage => (
          <MenuItem value={garage.id} key={garage.id} primaryText={garage.title} />
        ))}
        {console.log(garages)}
      </DropDownMenu>
    </ToolbarGroup>
    <ToolbarGroup>
      <ToolbarTitle text={currentGarage ? `Capacity: ${currentGarage.capacity}` : 'Garages'} />
      <ToolbarSeparator />
      {currentGarageID && <Dialog currentGarageID={currentGarageID} />}
    </ToolbarGroup>
  </Toolbar>
);

export default MyToolbar;
