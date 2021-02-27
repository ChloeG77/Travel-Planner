import React  from "react";
import { Form, DatePicker, Button, Select, Input, message } from 'antd';
import { useHistory } from 'react-router-dom';
import { newTrip } from '../utils/auth';

const { RangePicker } = DatePicker;

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

const cities = [
  { label: 'Beijing', value: 'Beijing' },

];


const rangeConfig = {
  rules: [
    {
      type: 'array',
      required: true,
      message: 'Please select time!',
    },
  ],
};

const NewTrip = (props) => {
  const history = useHistory();

  const onFinish = (fieldsValue) => {
    
    
    // Should format date value before submit.
    const rangeValue = fieldsValue['traveldate'];
    console.log(fieldsValue)
    const values = {
      ...fieldsValue,
      'traveldate': [rangeValue[0].format('YYYY-MM-DD'), rangeValue[1].format('YYYY-MM-DD')],
    };
    console.log('Received values of form: ', values);

    newTrip(fieldsValue, props.token)
      .then((data) => {
        message.success(`add trip`);
        const destination = fieldsValue.destination[0];
        history.push(`planner/${destination}`);

      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  };

  const onChange = (value, dateString) => {
    console.log('Selected Time: ', value);
    console.log('Formatted Selected Time: ', dateString);
  }

  const onOk = (value) => {
    console.log('onOk: ', value);
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

        <Form.Item name="traveldate" label="Travel Date" {...rangeConfig}>
        <DatePicker disabledTime onChange={onChange} onOk={onOk} />
        </Form.Item>

        <Form.Item name="numOfDays" label="Number of Days" rules={[{ required: true, message: 'Please enter a trip name!' }]}>
          <Input />
        </Form.Item>

        {/* <Form.Item name="traveldate" label="Travel Date" {...rangeConfig}>
          <RangePicker />
        </Form.Item> */}

        <Form.Item label="Destination City" name="destination"
            rules={[{ required: true, message: 'Please select your Desination City!' }]}>
          <Select options={cities} />
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