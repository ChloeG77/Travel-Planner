import React from "react";
import Main from './Main'
import { useParams, useLocation } from "react-router-dom";

const PlannerPage = ({isLoggedIn , token }) => {
  let { destination } = useParams();
  const tripInfo = useLocation();
  return (
    <div className="main-wrapper">
      <Main destination={destination} isLoggedIn={isLoggedIn} token={token} tripInfo={tripInfo.state}/>
    </div>
  )
}



export default PlannerPage