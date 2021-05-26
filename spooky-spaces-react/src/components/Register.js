import { useContext, useState } from 'react';
import { Link, useHistory, useLocation } from 'react-router-dom';
import AuthContext from './AuthContext';
import Button from './Button'

function Register() { //for user without account
  const auth = useContext(AuthContext);

  const [username, setUsername] = useState(''); //set initial state for username and password
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]); //errors is initially empty
  const [wishlists, setWishlists] = useState([]);
  const [messages, setMessages] = useState("");

  const history = useHistory();
  const location = useLocation();

  const { state: { from } = { from : '/' } } = location;

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

    
    const wishlist = {
      username: username,
      locationId: 0
    }
        
    const init = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Accept": "application/json"
        },
        body: JSON.stringify(wishlist)
    };
    
    fetch("http://localhost:8080/api/wishlist", init)
      .then(response => {
        if (response.status !== 201) {
          return Promise.reject("POST doesn't work");
        }
        return response.json();
      })
      .then(json => {
        setWishlists([...wishlists, json]);
        setMessages("");
      })
      .catch(console.log);
  }

  return ( //for to collect username and password
    <div className="container text-center mt-5">
    <div className="row">
    <h2 className="mt-3">Register</h2>
    <form onSubmit={handleSubmit}>
    <div className="mt-2">
        <label className="me-2">Username </label>
        <input type="text" onChange={(event) => setUsername(event.target.value)} />
      </div>
      <div className="mt-1">
        <label className="me-2">Password </label>
        <input className="ms-1" type="password" onChange={(event) => setPassword(event.target.value)} />
      </div>
      <div className="mt-2 text-center">
        <button className="btn btn-info mx-1" type="submit">Register</button>
        <Link to={from}><Button text="Cancel" /></Link>
        <Link to="/login"><Button text="Login" /></Link>
      </div>
    </form>
    </div>
  </div>
  );
}

export default Register;