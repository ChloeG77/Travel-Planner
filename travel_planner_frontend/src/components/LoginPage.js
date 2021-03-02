import React, { Component } from "react";
import Login from './Login';
import TravelHeader from './TravelHeader';
import Footer from './Footer'
import { useHistory } from "react-router";



const LoginPage = (props) =>{
    const history = useHistory();
    const {onLoggedInStatus, isLoggedIn, token, trips, onCurTrip} = props;


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
                <TravelHeader onLoggedInStatus={onLoggedInStatus}
                         isLoggedIn={isLoggedIn}
                         token={token}
                         trips={trips}
                         onCurTrip={onCurTrip}/>
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