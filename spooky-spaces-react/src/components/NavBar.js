import { Link } from 'react-router-dom';
import Button from './Button';
import { useState } from 'react';
import jwt_decode from "jwt-decode";

function NavBar() {
  const [user, setUser] = useState(null);

  const login = (token) => {
    const { id, sub: username, roles: rolesString } = jwt_decode(token);
    const roles = rolesString.split(",");

    const user = {
      id,
      username,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      },
      isValid() {
        return true;
      },
    };

    setUser(user);
  };

  const authenticate = async (username, password) => {
    const response = await fetch("http://localhost:5000/authenticate", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    });

    if (response.status === 200) {
      const { jwt_token } = await response.json();
      login(jwt_token);
    } else if (response.status === 403) {
      throw new Error("Bad username or password");
    } else {
      throw new Error("There was a problem logging in...");
    }
  };

  const logout = () => {
    setUser(null);
  };

  const auth = {
    user,
    authenticate,
    logout,
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
     <div className="container-fluid">
     <span className="navbar-brand mb-0 h1">Spooky Spaces</span>
    <div className="collapse navbar-collapse" id="navbarSupportedContent">
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
        <Link className="nav-link" to="/">Home</Link>
        </li>
        <li className="nav-item">
        <Link className="nav-link" to="/about">About</Link> 
        </li>
        <li className="nav-item">
         <Link className="nav-link" to="/encounters">Encounters</Link> 
        </li>
      </ul>
    </div>
    </div>
    <>
    {user ? (
      <>
      <li>{user.username}</li>
      <li className="m-2">
      <Link className="btn btn-outline-info" onClick={logout} to='/agents/login'> Log Out</Link>
      </li>
      </>
    ) : (
    <li className="btn-group">
    <Link className="btn btn-outline-info" to='/agents/login'>Login</Link>
    <Link className="btn btn-outline-info" to='/agents/signup'>Sign Up</Link>
    </li>
    ) }
  </>
</nav>
  )
};

export default NavBar;
