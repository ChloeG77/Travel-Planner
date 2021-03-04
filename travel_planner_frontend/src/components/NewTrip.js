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
  const [city, setCity] = useState(null);
  // const [cities, setCities] = useState(null);
  // const [cityOptions, setCityOptions] = useState(null);

  const {onSuccess, token, onCurTrip} = props;
  
  // useEffect(() => {
  //   console.log(props.token)
  //   if (props.token !== null) {
  //     getAllCities(props.token)
  //     .then((data) => {
  //       console.log("get city", data);
  //       const tempCities = data.cities;
  //       setCities(cities);
  //       setCityOptions(tempCities.map(city => <Option key={city.name}>{city.name}</Option>)); 
     
  //     }).catch((err) => {
  //       console.log(err);
  //       message.error(err.message);
  //     }); 
  //   }

  // }, [props.token]);
  
  const cities = [{cityId: "10000", name: "Houston", state: "TX", country: "US", longitude: -95.3698028, latitude: 29.7604267},
                  {cityId: "10001", name: "New York", state: "NY", country: "US", longitude: -95.3698028, latitude: 40.7127753}, 
                  {cityId: "10002", name: "Atlanta", state: "GA", country: "US", longitude: -84.3879824, latitude: 33.7489954},
                  {cityId: "10003", name: "Winnipeg", state: "MB", country: "CA", longitude: -97.1383744, latitude: 49.895136},
                  {cityId: "10004", name: "Ottawa", state: "ON", country: "CA", longitude: -75.6971931, latitude: 45.4215296},
                  {cityId: "10005", name: "Seattle", state: "WA", country: "US", longitude: -122.3320708, latitude: 47.6062095},
                  {cityId: "10006", name: "Los Angeles", state: "CA", country: "US", longitude: -118.2436849, latitude: 34.0522342},
                  {cityId: "10007", name: "San Francisco", state: "CA", country: "US", longitude: -122.4194155, latitude: 37.7749295},  
                ]
  const cityOptions = cities.map(city => <Option key={city.cityId}>{city.name}</Option>);

  const onFinish = (fieldsValue) => {

    // Should format date value before submit.
    const startDate = fieldsValue['startDate'];
    const des = cities.filter(item => item.cityId === fieldsValue.destination);

    const values = {
      ...fieldsValue,
      'startDate': startDate.format('YYYY-MM-DD'),
      'type' : tripType,
      cities: des
    };
    console.log('Received values of form: ', values);

    newTrip(values, token)
      .then((data) => {
        message.success(`add trip`);
        console.log("val,de", values.destination);
        // const destination = cities.filter(item => item.cityId === values.destination);
        // console.log(cities);
        // console.log("de", destination);

        const newData = {
            accessToken: token,
            trips: data.trips
        }
        // const newTrip = {
        //     ...data.newTrip,
        //     startCity: destination[0],
        //     cities: destination
        // }


        console.log("newtrip", data.newTrip);
        onCurTrip(data.newTrip);
        onSuccess(true, newData);
        history.push(`planner`);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  };


  const handleCityChange = (value) => {
    console.log(`selected city ${value}`);
    setCity(value);
  }

  // const onChange = (value, dateString) => {
  //   console.log('Selected Time: ', value);
  //   console.log('Formatted Selected Time: ', dateString);
  // }



  const handleChange = (value) => {
    console.log(`selected ${value}`);
    setTripType(value);
  }

    return (
      <div className="newtrip">
      <h1> New Trip</h1>
      <Form name="time_related_controls" {...formItemLayout} onFinish={onFinish} initialValues={{
       
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