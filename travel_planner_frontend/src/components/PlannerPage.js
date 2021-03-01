import React, { Component } from "react";
import Main from './Main'
import { useParams } from "react-router-dom";

const PlannerPage = () => {
  let { destination } = useParams();
  
  return (
    <div className="main-wrapper">
      <Main destination={destination}/>
    </div>
  )
}



export default PlannerPage