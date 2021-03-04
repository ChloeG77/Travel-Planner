import React  from "react";
import { Form, DatePicker, Button, Select, Input, message, InputNumber } from 'antd';
import { useHistory } from 'react-router-dom';
import { newTrip, getAllCities } from '../utils/auth';
import { useState, useEffect } from 'react';

const { Option } = Select;

// const { RangePicker } = DatePicker;

const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 11 }
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 13 }
  }
};


const NewTrip = (props) => {
  const history = useHistory();
  
  const [tripType, setTripType] = useState('leisure');
  const [cities, setCities] = useState(null);
  const [city, setCity] = useState(null);
  const [cityOptions, setCityOptions] = useState(null);

  const {onSuccess, token, onCurTrip} = props;
  
  useEffect(() => {
    console.log(props.token)
    if (props.token !== null) {
      getAllCities(props.token)
      .then((data) => {
        console.log("get city", data);
        const tempCities = data.cities;
        setCities(cities);
        setCityOptions(tempCities.map(city => <Option key={city.name}>{city.name}</Option>)); 
     
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      }); 
    }

  }, [props.token]);

  const onFinish = (fieldsValue) => {

    // Should format date value before submit.
    const startDate = fieldsValue['startDate'];
    const values = {
      ...fieldsValue,
      'startDate': startDate.format('YYYY-MM-DD'),
      'type' : tripType
    };
    console.log('Received values of form: ', values);

    newTrip(values, token)
      .then((data) => {
        message.success(`add trip`);
        const destination = values.destination;
        console.log("de",destination);

        const newData = {
            accessToken: token,
            trips: data.trips
        }
        const newTrip = {
            ...data.newTrip,
            startCity: destination[0]
        }
        // console.log(newTrip);
        onCurTrip(newTrip);
        onSuccess(true, newData);
        history.push(`planner`);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  };


  const handleCityChange = (value) => {
    console.log(`selected ${value}`);
    setCity(value);
  }

  // const onChange = (value, dateString) => {
  //   console.log('Selected Time: ', value);
  //   console.log('Formatted Selected Time: ', dateString);
  // }
  const onCityOptions = () => {
    
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
          <Input style={{width: "100%", textAlign: "left"}} />
        </Form.Item>

        <Form.Item name="startDate" label="Travel Date">
        <DatePicker style={{width: "100%" }} />
        </Form.Item>

        <Form.Item name="numDays" label="Number of Days" rules={[{ required: true, message: 'Please enter number of trip days!' }]}>
          <InputNumber min={1} max={15} style={{width: "100%", textAlign: "left"}} placeholder="number of travelling days"/>
        </Form.Item>

        <Form.Item name="destination" label="Destination City" rules={[{ required: true, message: 'Please select your Desination City!' }]}>
        <Select defaultValue="" style={{width: "100%", textAlign: "left"}} onChange={handleCityChange} >
            {cityOptions}
          </Select>
        </Form.Item>

        <Form.Item name="type" label="Trip Type" rules={[{ required: false, message: 'Please select your trip type!' }]}>
          <Select defaultValue="leisure" style={{width: "100%", textAlign: "left"}} onChange={handleChange} >
            <Option value="business">business</Option>
            <Option value="leisure">leisure</Option>
          </Select>

        </Form.Item>

        <Form.Item
         style={{width: "100%"}}
        >
            <Button className="planning-btn" type="primary" style={{width: "80%"}} htmlType="submit">
              Start Planning
            </Button>
        </Form.Item>
      </Form>
    </div>
    )
  }


export default NewTrip;