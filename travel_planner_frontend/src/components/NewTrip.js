import React  from "react";
import { Form, DatePicker, Button, Select, Input, message } from 'antd';
import { useHistory } from 'react-router-dom';
import { newTrip } from '../utils/auth';
import { useState } from 'react';

const { Option } = Select;

// const { RangePicker } = DatePicker;

const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 10,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 4,
    },
  },
};


const NewTrip = (props) => {
  const history = useHistory();

  const [tripType, setTripType] = useState('leisure');

  const onFinish = (fieldsValue) => {
    
    
    // Should format date value before submit.
    const startDate = fieldsValue['startDate'];
    const values = {
      ...fieldsValue,
      'startDate': startDate.format('YYYY-MM-DD'),
      'type' : tripType
    };
    console.log('Received values of form: ', values);

    newTrip(values, props.token)
      .then((data) => {
        message.success(`add trip`);
        const destination = values.destination[0];
        history.push(`planner/${destination}`);
        const newData = {
            accessToken: props.token,
            trips: data.trips
        }
        props.onSuccess(true, newData);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  };

  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
  }

  const handleChange = (value) => {
    console.log(`selected ${value}`);
    setTripType(value);
  }
 
    return (
      <div className="newtrip">
      <h1> New Trip</h1>
      <Form name="time_related_controls" {...formItemLayout} onFinish={onFinish} initialValues={{
        destination: ['Beijing']
      }}>
        <Form.Item name="name" label="Trip Name" rules={[{ required: true, message: 'Please enter a trip name!' }]}>
          <Input />
        </Form.Item>

        <Form.Item name="startDate" label="Travel Date">
        <DatePicker onChange={onChange} />
        </Form.Item>

        <Form.Item name="numDays" label="Number of Days" rules={[{ required: true, message: 'Please enter number of trip days!' }]}>
          <Input />
        </Form.Item>

        <Form.Item name="destination" label="Destination City" rules={[{ required: true, message: 'Please select your Desination City!' }]}>
          <Input />        
        </Form.Item>

        <Form.Item name="type" label="Trip Type" rules={[{ required: false, message: 'Please select your trip type!' }]}>
          <Select defaultValue="leisure" onChange={handleChange} >
            <Option value="business">business</Option>
            <Option value="leisure">leisure</Option>
          </Select>

        </Form.Item>

        <Form.Item
          wrapperCol={{
            xs: { span: 24, offset: 0 },
            sm: { span: 16, offset: 4 },
          }}
        > 
            <Button type="primary" htmlType="submit">
              Start Planning
            </Button>
        </Form.Item>
      </Form>
    </div>
    ) 
  }


export default NewTrip;