import { useContext, useState } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import AuthContext from './AuthContext';
import Errors from './Errors';

function Login() { //If the user already has an account
  const auth = useContext(AuthContext);

  const [username, setUsername] = useState(''); //set initial state for username and password
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]);
  
  const history = useHistory();
  const location = useLocation();

  const { state: { from } = { from : '/' } } = location;

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      await auth.authenticate(username, password); //Authenticate with the uer api
      history.push(from);
    } catch (err) {
      setErrors([err.message]); //if it doesn't work, return the error message
    }
  }

  return ( //Form to get username and password, add link if the user doesn't have an account
    <div>
      <h2>Login</h2>
      <Errors errors={errors} />
      <form onSubmit={handleSubmit}>
      <div>
          <label>Username:</label>
          <input type="text" onChange={(event) => setUsername(event.target.value)} />
        </div>
        <div>
          <label>Password:</label>
          <input type="password" onChange={(event) => setPassword(event.target.value)} />
        </div>
        <div>
          <button className="btn btn-primary ml-2" type="submit">Login</button>
          <Link className="btn btn-secondary ml-2" to={from}>Cancel</Link>
          <Link className="btn btn-warning ml-2" to="/register">Create Account</Link>
        </div>
      </form>
    </div>
  );
};

export default Login;