import React from 'react';
import Header from './components/Header'
import { BrowserRouter as Router, Link, Route, Switch } from 'react-router-dom';
import './App.css';
import PlannerPage from './components/PlannerPage';
import HomePage from './components/HomePage'

const App = () => (
  <div className="App">


    <Router>
      <Header />
      <div>
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