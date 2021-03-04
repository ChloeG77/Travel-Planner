import { Button, Form, Input, message, Modal, DatePicker, Select } from 'antd';
import React from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { register } from '../utils/auth';
import { useHistory } from 'react-router';
import { useState } from 'react';


const { Option } = Select;

 
const SignUp = () => {
    const [gender, setGender] = useState("NULL");
    const history = useHistory();

    const onFinish = (data) => {
        const dateOfBirth = data['dateOfBirth'].format('YYYY-MM-DD');
        const values = {
            ...data,
            dateOfBirth: dateOfBirth,
            gender: gender
        }
        register(values)
        .then(() => {
            message.success(`Successfully signed up`);
            history.push('/');  
        }).catch((err) => {
            message.error(err.message);
        })
    }

    const handleGenderChange = (value) => {
        console.log(`selected ${value}`);
        setGender(value);
      }

    return (
        <div className='signup'>
        <h1>Sign Up</h1>
        <Form
            name="normal_register"
            initialValues={{ remember: true }}
            onFinish={onFinish}
            preserve={false}
            >
            <Form.Item
                name="username"
                rules={[{ required: true, message: 'Please input your Username!' }]}
            >
            <Input prefix={<UserOutlined />} placeholder="Username" />
            </Form.Item>

            <Form.Item
                name="email"
                rules={[{ required: true, message: 'Please input your Email !' }]}
            >
                <Input prefix={<UserOutlined />} placeholder="Email" />
            </Form.Item>
            <Form.Item
                name="password"
                rules={[{ required: true, message: 'Please input your Password!' }]}
            >
                <Input
                prefix={<LockOutlined />}
                placeholder="Password"
                />
            </Form.Item>
            <Form.Item
                name="gender"
                rules={[{ required: true, message: 'Please input your Gender!' }]}
            >
                <Select defaultValue="Gender" style={{width: "100%", textAlign: "left"}} onChange={handleGenderChange} >
                <Option value="MALE">MALE</Option>
                <Option value="FEMALE">FEMALE</Option>
                <Option value="NULL">NULL</Option>

                </Select>
            </Form.Item>
            <Form.Item
                name="dateOfBirth"
                rules={[{ required: true, message: 'Please input your date of birth!' }]}
            >
                <DatePicker style={{width: "100%" }} />
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit">
                Register</Button>
            </Form.Item>
            </Form>
        </div>
    )
  
}
 
export default SignUp;
