import { 
  BrowserRouter as Router,
  Link,
  Switch,
  Route,
  Redirect} from 'react-router-dom';
import { useState } from 'react';  
import jwt_decode from 'jwt-decode';

function App() {
  return (
    <div className="App">
      <Router>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">About Us</Link>
            </li>
          </ul>
        
          <Switch>
            <Route exact path="/">
              <Home />        
            </Route>
            <Route path="/about">
              <About />
            </Route>
          </Switch> 
        </Router>   
    </div>
  );
}

export default App;
