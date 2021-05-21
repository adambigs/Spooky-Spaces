import { 
  BrowserRouter as Router,
  Link,
  Switch,
  Route,
  Redirect} from 'react-router-dom';
import { useState } from 'react';  
import Home from './components/Home'; 
import About from './components/static/About';

function App() {
  return (
    <div className="App">
    <div className="container">  
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
    </div>
  );
}

export default App;
