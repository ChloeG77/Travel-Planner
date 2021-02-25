import React  from "react";
import { Form, DatePicker, Button, Select, Input } from 'antd';
import { useHistory } from 'react-router-dom';

const { RangePicker } = DatePicker;

const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 8,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 3,
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

const HomePage = () => {
  const history = useHistory();

  const onFinish = (fieldsValue) => {
    const destination = fieldsValue.destination[0];
    history.push(`planner/${destination}`);
    
    // Should format date value before submit.
    const rangeValue = fieldsValue['traveldate'];
    console.log(fieldsValue)
    const values = {
      ...fieldsValue,
      'traveldate': [rangeValue[0].format('YYYY-MM-DD'), rangeValue[1].format('YYYY-MM-DD')],
    };
    console.log('Received values of form: ', values);
  };

 
    return (
      // <h1>HomePage</h1>
      <Form name="time_related_controls" {...formItemLayout} onFinish={onFinish} initialValues={{
        destination: ['Beijing']
      }}>
        <Form.Item name="tripname" label="Trip Name" rules={[{ required: true, message: 'Please enter a trip name!' }]}>
          <Input />
        </Form.Item>
        <Form.Item name="traveldate" label="Travel Date" {...rangeConfig}>
          <RangePicker />
        </Form.Item>
        <Form.Item label="Destination City" name="destination"
            rules={[{ required: true, message: 'Please select your Desination City!' }]}>
          <Select options={cities} />
        </Form.Item>
        <Form.Item
          wrapperCol={{
            xs: { span: 24, offset: 0 },
            sm: { span: 16, offset: 8 },
          }}
        > 
            <Button type="primary" htmlType="submit">
              Start Planning
            </Button>
        </Form.Item>
      </Form>
    ) 
  }


export default HomePage