import { Button, Form, Input, message, Modal } from 'antd';
import React from 'react';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { login } from '../utils/auth';
 
class Login extends React.Component {
  // state = {
  //   displayModal: false,
  //     accessToken : null,
  //     tokenType : null
  // }
  onFinish = (data) => {
    login(data)
      .then((data) => {
        // this.setState({
        //     displayModal: false,
        //     accessToken : data.accessToken,
        //     tokenType : data.tokenType
        // })
        // console.log("data", data);
        message.success(`Welcome back`);
        this.props.onSuccess(true, data.accessToken);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  }
 
  render = () => {
    return (
        <div className="login">
          <h1>Login</h1>
          <Form
            name="normal_login"
            onFinish={this.onFinish}
            preserve={false}
          >
            <Form.Item
              name="username"
              rules={[{ required: true, message: 'Please input your Username!' }]}
            >
              <Input prefix={<UserOutlined />} placeholder="Username" />
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
 
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Login</Button>
            </Form.Item>
          </Form>
        </div>
    )
  }
}
 
export default Login;
