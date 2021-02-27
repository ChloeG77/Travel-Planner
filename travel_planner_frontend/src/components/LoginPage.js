import React, { Component } from "react";
import Login from './Login';
import TravelHeader from './TravelHeader';
import Footer from './Footer'
import { useHistory } from "react-router";



const LoginPage = (props) =>{
    const history = useHistory();

    const signinOnSuccess = (isLoggedIn, token) => {
        // getFavoriteItem().then((data) => {
        //   this.setState({
        //     loggedIn: true
        //   })
        // }).catch((err) => {
        //   message.error(err.message);
        // })
        // console.log("login success")
        props.onSuccess(isLoggedIn, token);
        history.push('/');
    }
  
    return (
        <div className="login-page">
            <div>
                <TravelHeader />
            </div>
            <div className="login">
                <Login onSuccess={signinOnSuccess}/>
            </div>
            <div>
                <Footer />
            </div>
        </div>
    )
    
}

export default LoginPage;