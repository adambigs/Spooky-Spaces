import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";
import { useState } from 'react';
import jwt_decode from 'jwt-decode';
import "./bootstrap.min.css";
import Home from "./components/Home";
import About from "./components/About";
import NotFound from "./components/NotFound";
import LocationList from "./components/LocationList";
import Location from "./components/Location";
import Login from "./components/Login";
import Register from "./components/Register";
import AddEncounter from "./components/AddEncounter";
import EditEncounter from "./components/EditEncounter";
import AuthContext from "./components/AuthContext";
import AddComment from "./components/AddComment";
import EditComment from "./components/EditComment";
import Nav from "./components/Nav";
import NewMap from "./components/NewMap";
import ReadOnlyMap from "./components/ReadOnlyMap";
import Wishlist from "./components/Wishlist";

function App() {

const [user, setUser] = useState(null);
  
const login = (token) => {
  const{ id, sub: username, roles: rolesString } = jwt_decode(token);
  const roles = rolesString.split(',');

  const user = {
    id,
    username,
    roles,
    hasRole(role) {
      return this.roles.includes(role);
    },
    isValid() {
      return true;
    }
  }

  setUser(user);
}

const authenticate = async (username, password) => {
  const response = await fetch('http://localhost:5000/authenticate', {
    method: 'POST',
    headers: {
      "content-type": "application/json"
    },
    body: JSON.stringify({
      username,
      password
    })
  });

  if(response.status === 200) {
    const { jwt_token } = await response.json();
    login(jwt_token);
  } else if (response.status === 403) {
    throw new Error('Bad username or password');
  } else {
    throw new Error('There was a problem logging in...');
  }
}

const logout = () => {
  setUser(null);
}

const auth = {
  user,
  authenticate,
  logout
}

  return (
    <div className="App">
    <AuthContext.Provider value={auth}> 
        <Router>
        <Nav user={user} logout={logout}/>
          <Switch>
            <Route exact path="/">
              {user ? (<NewMap />) : (
                <ReadOnlyMap />
              )}
            </Route>
            <Route path="/about">
              <About />
            </Route>
            <Route path="/location/:id" username={user}>
              {user ? <LocationList user={user}/> : <Redirect to="/login" />}
            </Route>
            <Route path="/encounter/add/:id">
            {user ? <AddEncounter /> : <Redirect to="/login" />}
            </Route>
            <Route path="/encounter/edit/:id">
            {user ? <EditEncounter /> : <Redirect to="/login" />}
            </Route>
            <Route path="/comment/add/:id">
            {user ? <AddComment user={user} /> : <Redirect to="/login" />}
            </Route>
            <Route path="/comment/edit/:id">
            {user ? <EditComment /> : <Redirect to="/login" />}
            </Route>
            <Route path="/wishlist/:username">
            {user ? <Wishlist/> : <Redirect to="/login" />}
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
        </AuthContext.Provider>    
    </div>    
  );
}

export default App;
