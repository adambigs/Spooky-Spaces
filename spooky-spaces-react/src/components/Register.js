import { useContext, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import AuthContext from './AuthContext';
import Errors from './Errors';

function Register() { //for user without account
  const auth = useContext(AuthContext);

  const [username, setUsername] = useState(''); //set initial state for username and password
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]); //errors is initially empty

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try { //use user api to POST new user
        const response = await fetch('http://localhost:5000/create_account', {
          method: 'POST',
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username,
            password
          })
        });

      if (response.status === 201) { //if the attempt is successful
        try {
          await auth.authenticate(username, password); //authnticate the new user
        history.push('/');
        } catch (err) {
          throw new Error('Unknown Error');
        }     
      } else if (response.status === 400) { //if username is taken
        throw new Error('The account is already in use');
      } else {
        throw new Error('Unknown Error');
      }
    } catch (err) {
      setErrors([err.message]);
    } 
  }

  return ( //for to collect username and password
    <div>
      <h2>Register</h2>
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
          <button class="btn btn-primary" type="submit">Register</button>
          <Link to={'/login'} class="btn btn-secondary">Existing user</Link>
        </div>
      </form>
    </div>
  );
}

export default Register;