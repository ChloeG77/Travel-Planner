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
    
    let history = useHistory();

    const newTripOnClick=()=> {
        console.log('new trip');
        let path = `newtrip`
        history.push(path);
    }

    // const showTrips=() =>{
    //     <Link to="/planner">PlannerPage</Link>
    //     console.log('show trips');
    // }


    return (
        <div className="homepage">
            <div>
            <TravelHeader onSuccess={props.onSuccess}
                         isLoggedIn={props.isLoggedIn}/>
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
                        <NewTrip token={props.token}/>
                    </div>
                    :
                    <div>
                        <Login onSuccess={props.onSuccess}/>
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