import React, { Component, useState} from "react";
import { Button, Col, Layout, message, Row } from 'antd';
import { logout } from '../utils/auth';
import Trips from './Trips';
import { useHistory } from 'react-router-dom';

const { Header, Content, Sider } = Layout;


const TravelHeader = (props) => {
  // const [loggedIn, setLoggedIn] = useState(false);

  const history = useHistory();

  // const signinOnClick = () => {
  //   let path = 'login';
  //   history.push(path);
  // }

  const signupOnClick = () => {
    let path = 'signup';
    history.push(path);
  }

  // const signoutOnClick = () => {
  //   logout()
  //     .then(() => {
  //       // this.setState({
  //       //   loggedIn: false
  //       // })
  //       props.onSuccess(false, null);
  //       message.success(`Successfull signed out`);
  //       history.push("/");
  //     }).catch((err) => {
  //       message.error(err.message);
  //     })
  // }

  const signoutOnClick = () => {
   
      props.onSuccess(false, null);  
      history.push("/");
      
  }

  return (
    <Header>
      <Row justify="space-between">
        <Col>
        {props.isLoggedIn && <Trips/>}
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
            <Button shape="round" type="primary" onClick={signupOnClick}>
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