import React, { Component } from 'react';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import { RaisedButton } from 'material-ui';

export default class MyTable extends Component {
  render() {
    const { cars } = this.props;

    return (
      <Table onRowSelection={this.handleRowSelection}>
        <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
          <TableRow>
            <TableHeaderColumn>id</TableHeaderColumn>
            <TableHeaderColumn>make</TableHeaderColumn>
            <TableHeaderColumn>model</TableHeaderColumn>
            <TableHeaderColumn>color</TableHeaderColumn>
            <TableHeaderColumn>running</TableHeaderColumn>
            <TableHeaderColumn>actions</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false}>
          {cars && cars.map(car => (
            <TableRow key={car.id}>
              <TableRowColumn>{car.id}</TableRowColumn>
              <TableRowColumn>{car.make}</TableRowColumn>
              <TableRowColumn>{car.model}</TableRowColumn>
              <TableRowColumn>{car.color}</TableRowColumn>
              <TableRowColumn>{car.running ? 'yes' : 'no'}</TableRowColumn>
              <TableRowColumn>
                <RaisedButton label="Delete" onClick={() => {}} />
              </TableRowColumn>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    );
  }
}
