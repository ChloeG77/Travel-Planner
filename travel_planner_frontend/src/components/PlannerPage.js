import React from "react";
import Main from './Main'
import { useParams } from "react-router-dom";

const PlannerPage = ({isLoggedIn , token }) => {
  let { destination } = useParams();
  
  return (
    <div className="main-wrapper">
      <Main destination={destination} isLoggedIn={isLoggedIn} token={token}/>
    </div>
  )
}



export default PlannerPage