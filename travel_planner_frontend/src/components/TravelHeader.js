import React, { Component, useState} from "react";
import { Button, Col, Layout, message, Row } from 'antd';
import Trips from './Trips';
import { useHistory } from 'react-router-dom';

const { Header, Content, Sider } = Layout;


const TravelHeader = (props) => {

  const {onLoggedInStatus, isLoggedIn, token, trips, onCurTrip} = props;

  const history = useHistory();

  const signupOnClick = () => {
    let path = 'signup';
    history.push(path);
  }


  const signoutOnClick = () => {
      
    const data = {
      accessToken: null
    }

    onLoggedInStatus(false, data);  
    history.push("/");
      
  }


  return (
    <Header>
      <Row justify="space-between">
        <Col>
        {isLoggedIn && <Trips onLoggedInStatus={onLoggedInStatus}
                                    isLoggedIn={isLoggedIn}
                                    token={token}
                                    trips={trips}
                                    onCurTrip={onCurTrip}/>}
        </Col>
        {/* <Col style={{posotion: "relative", fontSize: "40px"}}>Travel Planner</Col> */}
        <Col>
          {
            isLoggedIn ? 
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