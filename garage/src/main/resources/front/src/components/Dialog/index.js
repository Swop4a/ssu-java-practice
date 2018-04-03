import React from 'react';
import {
  Dialog,
  FlatButton,
  RaisedButton,
} from 'material-ui';

/**
 * Dialogs can be nested. This example opens a Date Picker from within a Dialog.
 */
export default class MyDialog extends React.Component {
  state = { open: false };

  handleOpen = () => {
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  render() {
    const actions = [
      <FlatButton
        label="Create"
        primary={true}
        keyboardFocused={true}
        onClick={this.handleClose}
      />,
    ];

    return (
      <div>
        <RaisedButton label="Add car" onClick={this.handleOpen} />
        <Dialog
          title="Type data for a new car"
          actions={actions}
          modal={false}
          open={this.state.open}
          onRequestClose={this.handleClose}
        >
          We are right here
        </Dialog>
      </div>
    );
  }
}
