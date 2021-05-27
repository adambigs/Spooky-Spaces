import { useContext, useState } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import AuthContext from "./AuthContext";
import Button from "./Button";
import Errors from './Errors';

function Login() {
	//If the user already has an account
	const auth = useContext(AuthContext);

	const [username, setUsername] = useState(""); //set initial state for username and password
	const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);

	const history = useHistory();
	const location = useLocation();

	const { state: { from } = { from: "/" } } = location;

	const handleSubmit = async (event) => {
		event.preventDefault();

		try {
			await auth.authenticate(username, password); //Authenticate with the uer api
			history.push(from);
		} catch (err) {
			setErrors([err.message]); //if it doesn't work, return the error message
		}
	};

	return (
		//Form to get username and password, add link if the user doesn't have an account
		<div className="container text-center mt-5">
			<div className="row">
				<h2 className="mt-3">Login</h2>
				<form onSubmit={handleSubmit}>
					<div className="mt-2">
						<label className="me-2">Username </label>
						<input
							type="text"
							onChange={(event) => setUsername(event.target.value)}
						/>
					</div>
					<div className="mt-1">
						<label className="me-2">Password </label>
						<input
							className="ms-1"
							type="password"
							onChange={(event) => setPassword(event.target.value)}
						/>
					</div>
					<div className="mt-2 text-center">
						<button className="btn btn-info mx-1" type="submit">
							Login
						</button>
						<Link to={from}>
							<Button text="Cancel" />
						</Link>
						<Link to="/register">
							<Button text="Register" />
						</Link>
					</div>
				</form>
			</div>
		</div>
	);
}

export default Login;
