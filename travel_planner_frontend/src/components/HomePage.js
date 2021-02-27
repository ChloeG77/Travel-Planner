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
    
    // let history = useHistory();

    // const newTripOnClick=()=> {
    //     console.log('new trip');
    //     let path = `newtrip`
    //     history.push(path);
    // }

    // const showTrips=() =>{
    //     <Link to="/planner">PlannerPage</Link>
    //     console.log('show trips');
    // }


    return (
        <div className="homepage">
            <div>
            <TravelHeader onLoggedInStatus={props.onLoggedInStatus}
                         isLoggedIn={props.isLoggedIn}
                         token={props.token}
                         trips={props.trips}
            />
            </div>
            <div>
            <h1>TravelPlanner</h1>
            <div className="homepage-main">  
                {/* <Button shape="round" onClick={newTripOnClick} size={'large'} style={{ marginRight: '30px' }}>
                New Trip
                </Button>
                {/* <Button shape="round" onClick={showTrips}>
                My Trips
                </Button> */} 
                {
                    props.isLoggedIn ? 
                    <div>
                        <NewTrip onSuccess={props.onLoggedInStatus}
                                 token={props.token} />
                    </div>
                    :
                    <div>
                        <Login onSuccess={props.onLoggedInStatus}/>
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