import React, { Component } from 'react';
import TravelHeader from './TravelHeader';
import { Button } from 'antd';
import Footer from './Footer';
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom';
import { useHistory } from "react-router-dom";
import { useState } from 'react';
import NewTrip from './NewTrip';
import Login from './Login';

const HomePage = (props) => {
    const { onLoggedInStatus, isLoggedIn, token, trips, onCurTrip } = props;

    return (
        <div className="homepage">
            <div id="homepage-header">
            <TravelHeader onLoggedInStatus={onLoggedInStatus}
                         isLoggedIn={isLoggedIn}
                         token={token}
                         trips={trips}
                         onCurTrip={onCurTrip}
            />
            </div>
            <div>
            <h1>TravelPlanner</h1>
            <div className="homepage-main">  
                {
                    isLoggedIn ? 
                    <div>
                        <NewTrip onSuccess={onLoggedInStatus}
                                 token={token}
                                 onCurTrip={onCurTrip} />
                    </div>
                    :
                    <div>
                        <Login onSuccess={onLoggedInStatus}/>
                    </div>
                }
                <p>A new way of planning your trip</p>
            </div>
            </div>
            <div>
                <Footer />
            </div>
        </div>
    )
    
}


export default HomePage;