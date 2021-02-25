import React, { Component } from "react";
import Map from './Map'
import { useParams } from "react-router-dom";

const PlannerPage = () => {
  let { destination } = useParams();
  
  return (
    <div className="main-wrapper">
      <Map destination={destination}/>
    </div>
  )
}



export default PlannerPage