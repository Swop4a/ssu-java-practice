import React from 'react';
import {
  FontIcon,
  IconMenu,
  IconButton,
  MenuItem,
  DropDownMenu,
  RaisedButton,
} from 'material-ui';
import {
  Toolbar,
  ToolbarGroup,
  ToolbarSeparator,
  ToolbarTitle,
} from 'material-ui/Toolbar';
import NavigationExpandMoreIcon from 'material-ui/svg-icons/navigation/expand-more';

import Dialog from '../../components/Dialog';

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
      <ToolbarSeparator />
      <Dialog/>
    </ToolbarGroup>
  </Toolbar>
);

export default MyToolbar;
