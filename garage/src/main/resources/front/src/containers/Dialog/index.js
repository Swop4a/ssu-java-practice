import React from 'react';
import {
  Dialog,
  FlatButton,
  RaisedButton,
  TextField,
  Checkbox,
  DatePicker,
} from 'material-ui';
import { Form, Field } from 'react-final-form'

export default class MyDialog extends React.Component {
  state = { open: false };

  toggleDialog = (value) => {
    if (typeof value !== 'undefined') {
      this.setState({ open: value });
    } else {
      this.setState(prevState => ({ open: !prevState.open }));
    }
  }

  handleOpen = () => {
    this.toggleDialog(true);
  };

  handleClose = () => {
    this.toggleDialog(false);
  };

  onSubmit = formValues => {
    this.toggleDialog(false);

    formValues.running = typeof formValues.running !== 'undefined'
      ? formValues.running
      : false;

    this.props.addCar({
      garageId: this.props.currentGarageID,
      car: formValues,
    });
  }

  render() {
    return (
      <div>
        <RaisedButton primary label="Add car" onClick={this.handleOpen} />

        <Dialog
          title="Type data for a new car"
          modal={false}
          open={this.state.open}
          onRequestClose={this.handleClose}
        >
        <Form
            onSubmit={this.onSubmit}
            validate={validate}
            render={({ handleSubmit, pristine, invalid }) => (
              <form onSubmit={handleSubmit}>
                <Field
                  name="make"
                  render={({ input, meta }) => (
                    <TextField
                      {...input}
                      autoFocus
                      floatingLabelText="Make"
                      errorText={meta.error}
                     />
                  )}
                />

                <br/>

                <Field
                  name="model"
                  render={({ input, meta }) => (
                    <TextField
                      {...input}
                      floatingLabelText="Model"
                      errorText={meta.error}
                     />
                  )}
                />

                <br/>

                <Field
                  name="color"
                  render={({ input, meta }) => (
                    <TextField
                      {...input}
                      floatingLabelText="Color"
                      errorText={meta.error}
                     />
                  )}
                />

                <br/>

                <Field
                  name="mileage"
                  render={({ input, meta }) => (
                    <TextField
                      {...input}
                      type="number"
                      floatingLabelText="Mile age"
                      errorText={meta.error}
                     />
                  )}
                />

                <br/>
                <Field
                  name="running"
                  type="checkbox"
                  render={({ input, meta }) => (
                    <Checkbox
                      style={{ marginTop: '20px' }}
                      label="Running"
                      {...input}
                      onCheck={input.onChange}
                    />
                  )}
                />

                <br/>
                <Field
                  name="creationDay"
                  type="date"
                  render={({ input }) => (
                    <DatePicker
                      {...input}
                      value={input.value || null}
                      onChange={(event, date) => {
                        input.onChange(date);
                      }}
                      hintText="Creation day"
                    />
                  )}
                />

                <br/>

                <FlatButton
                  label="Cancel"
                  primary={true}
                  onClick={this.handleClose}
                />

                <FlatButton
                  label="Submit"
                  primary={true}
                  keyboardFocused={true}
                  type="submit"
                  disabled={pristine || invalid}
                />
              </form>
            )}
          />
        </Dialog>
      </div>
    );
  }
}

const validate = (defaultError => values => {
  const errors = {}

  if (!values.make) errors.make = defaultError;
  if (!values.model) errors.model = defaultError;
  if (!values.color) errors.color = defaultError;
  if (!values.mileage) errors.mileage = defaultError;

  return errors;
})('Required');
