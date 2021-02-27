import React from 'react';
import TravelHeader from './Header'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom';
import './styles/App.css';
import PlannerPage from './PlannerPage';
import HomePage from './HomePage'
import Layout from 'antd/lib/layout/layout';


const App = () => (
  <div className="App">


    <Router>
      <div>
        <Layout>
          <TravelHeader />
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
        </Layout>
        
        

        <Switch>
          <Route path="/planner/:destination">
            <PlannerPage />
          </Route>
          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </div>
    </Router>
  </div>
);

export default App;