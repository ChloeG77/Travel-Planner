import React, { Component } from "react";
import Login from './Login';
import TravelHeader from './TravelHeader';
import Footer from './Footer'
import { useHistory } from "react-router";



const LoginPage = (props) =>{
    const history = useHistory();

    const signinOnSuccess = (isLoggedIn, data) => {
        // getFavoriteItem().then((data) => {
        //   this.setState({
        //     loggedIn: true
        //   })
        // }).catch((err) => {
        //   message.error(err.message);
        // })
        // console.log("login success")
        props.onLoggedInStatus(isLoggedIn, data);
        history.push('/');
    }
  
    return (
        <div className="login-page">
            <div>
                <TravelHeader onSuccess={props.onLoggedInStatus}
                         isLoggedIn={props.isLoggedIn}
                         token={props.token}
                         trips={props.trips}/>
            </div>
            <div className="login-main">
                <Login onSuccess={signinOnSuccess}/>
            </div>
            <div>
                <Footer />
            </div>
        </div>
    )
    
}

export default LoginPage;