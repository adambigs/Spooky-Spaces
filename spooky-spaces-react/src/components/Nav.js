import { Link } from 'react-router-dom';
import Button from './Button';

function Nav() {
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
    <Link to="/login"><Button text="Login" /></Link>
    <Link to="/register"><Button text="Register" /></Link>
</nav>
  )
};

export default Nav;
