import { Link } from 'react-router-dom';
import Button from './Button';

function Nav({user, logout}) {
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
      </ul>
    </div>
    </div>
    <>
    {user ? (
      <>
      <li className="text-light list-unstyled m-2">{user.username}</li>
      <li className="m-2">
      <Link className="btn btn-outline-info" to={`/wishlist/${user.username}`}>❤️</Link>
      </li>
      <li className="m-2">
      <Link className="btn btn-outline-info" onClick={logout} to='/login'> Log Out</Link>
      </li>
      </>
    ) : (
    <li className="btn-group">
    <Link className="btn btn-outline-info" to='/login'>Login</Link>
    <Link className="btn btn-outline-info" to='/register'>Sign Up</Link>
    </li>
    ) }
  </>
</nav>
  )
};

export default Nav;
