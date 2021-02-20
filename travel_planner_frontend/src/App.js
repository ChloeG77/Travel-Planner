import React from 'react';
import TravelHeader from './components/Header'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom';
import './App.css';
import PlannerPage from './components/PlannerPage';
import HomePage from './components/HomePage'
import Layout from 'antd/lib/layout/layout';

const App = () => (
  <div className="App">


    <Router>
      <div>
        <Layout>
          <TravelHeader />
          <nav>
            <ul>
              <li>
                <Link to="/">HomePage</Link>
              </li>
              <li>
                <Link to="/planner">PlannerPage</Link>
              </li>
            </ul>
          </nav>
        </Layout>
        
        

        <Switch>
          <Route path="/planner">
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