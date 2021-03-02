import React from "react";
import Main from './Main'
// import { useParams } from "react-router-dom";

const PlannerPage = (props) => {
  // let { destination } = useParams();
  const { isLoggedIn, token, curTrip } = props;
  console.log("curtrip", curTrip)
  return (
    <div className="main-wrapper">
      <Main isLoggedIn={isLoggedIn} 
            token={token}
            curTrip={curTrip}/>
    </div>
  )
}



export default PlannerPage;