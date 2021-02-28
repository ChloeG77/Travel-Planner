// import { Footer } from "antd/lib/layout/layout";
import React, { Component } from "react";
import SignUp from './SignUp';
import Footer from './Footer';
import TravelHeader from './TravelHeader';

const SignUpPage = () => {


    return (
        <div className="signup-page">
            <div>
                <TravelHeader />
            </div>
            <div className="signup-main">
                <SignUp />
            </div>
            <div>
                <Footer />
            </div>
        </div>
    )
    
}

export default SignUpPage;