import React, { Component, useState} from "react";
import { Button, Col, Layout, message, Row } from 'antd';
import { logout } from '../utils/auth';
import Trips from './Trips';
import { useHistory } from 'react-router-dom';

const { Header, Content, Sider } = Layout;


const TravelHeader = (props) => {

  const history = useHistory();

  const signupOnClick = () => {
    let path = 'signup';
    history.push(path);
  }


  const signoutOnClick = () => {
      
    const data = {
      accessToken: null
    }

    props.onLoggedInStatus(false, data);  
    history.push("/");
      
  }

  return (
    <Header>
      <Row justify="space-between">
        <Col>
        {props.isLoggedIn && <Trips onLoggedInStatus={props.onLoggedInStatus}
                                    isLoggedIn={props.isLoggedIn}
                                    token={props.token}
                                    trips={props.trips}/>}
        </Col>
        <Col>
          {
            props.isLoggedIn ? 
            <Button shape="round" onClick={signoutOnClick}>
              Logout</Button> :
            (
              <>
            {/* <Button shape="round" onClick={signinOnClick} style={{ marginRight: '20px' }}>
            Login</Button> */}
            <Button className="register-btn"
              shape="round" type="primary" onClick={signupOnClick}>
              Register</Button>
            </>
            )
          }
        </Col>
      </Row>
    </Header>
  ) 

}

export default TravelHeader;