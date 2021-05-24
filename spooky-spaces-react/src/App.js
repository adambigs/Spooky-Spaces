import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Home from "./components/Home";
import About from "./components/About";
import NotFound from "./components/NotFound";
import LocationList from "./components/LocationList";
import Location from "./components/Location";
import Login from "./components/Login";
import Register from "./components/Register";
import AuthContext from "./components/AuthContext";


function App() {
  return (
    <div className="App">
  
        <Router>
          <Switch>
            <Route exact path="/">
              <Home />
            </Route>
            <Route path="/about">
              <About />
            </Route>
            <Route path="/location/:id">
              <LocationList />
            </Route>
            <Route path="/login">
            <Login />
            </Route>
            <Route path="/register">
            <Register />
            </Route>
            <Route path="*">
            <NotFound />
            </Route>
          </Switch>
        </Router>
        
    </div>
  );
}

export default App;
