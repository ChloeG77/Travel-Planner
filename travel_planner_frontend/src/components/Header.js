import React, { Component } from "react";
import { Button, Col, Layout, message, Row } from 'antd';
import { logout } from '../utils/auth';
import Login from './Login';
import Register from './Register'

const { Header, Content, Sider } = Layout;

export default class TravelHeader extends Component {
  state = {
    loggedIn: false
    }

  signoutOnClick = () => {
    logout()
      .then(() => {
        this.setState({
          loggedIn: false
        })
        message.success(`Successfull signed out`);
      }).catch((err) => {
        message.error(err.message);
      })
  }




  render() {
    return (
      <Header>
        <Row justify="space-between">
          <Col>

          </Col>
          <Col>
            {
              this.state.loggedIn ? 
              <Button shape="round" onClick={this.signoutOnClick}>
                Logout</Button> :
              (
                <>
                  <Login />
                  <Register />
                </>
              )
            }
          </Col>
        </Row>
      </Header>
    ) 
  }
}