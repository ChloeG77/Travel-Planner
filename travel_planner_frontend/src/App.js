import React from 'react';
import TravelHeader from './components/TravelHeader'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom';
import './styles/App.css';
import PlannerPage from './components/PlannerPage';
import NewTrip from './components/NewTrip';
import Layout from 'antd/lib/layout/layout';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import { useState } from 'react';

const App = () => {

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState(null);
  const [trips, setTrip] = useState(null);
  
  const onLoggedInStatus =(loggin, data) => {
      setIsLoggedIn(loggin);
      setToken(data.accessToken);
      setTrip(data.trips);
  }

  return (
  <div className="App">


    <Router>
      <div>
        {/* <Layout>
          {/* <TravelHeader /> */}
          {/* <nav>
            <ul>
              <li>
                <Link to="/">HomePage</Link>
              </li>
              <li>
                <Link to="/planner">PlannerPage</Link>
              </li>
            </ul>
          </nav> */}
        {/* </Layout> */}
         {/* */}
        

        <Switch>
          <Route path="/planner/:destination">
            <PlannerPage isLoggedIn={isLoggedIn}
                         token={token}  
            />
          </Route>
          <Route path="/login">
            <LoginPage onLoggedInStatus={onLoggedInStatus}/>
          </Route>
          <Route path="/signup">
            <SignUpPage />
          </Route>
          <Route path="/">
            <HomePage onLoggedInStatus={onLoggedInStatus}
                      isLoggedIn={isLoggedIn}
                      token={token}
                      trips={trips}
                                
            />
          </Route>
        </Switch>
      </div>
    </Router>
  </div>
  )
};

export default App;