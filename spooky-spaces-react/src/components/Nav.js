import { Link } from "react-router-dom";
import Button from "./Button";

function Nav({ user, logout }) {
	return (
		<nav className="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div className="container-fluid">
				<span className="navbar-brand mb-0 h1">Spooky Spaces</span>
				<div className="collapse navbar-collapse" id="navbarSupportedContent">
					<ul className="navbar-nav me-auto mb-2 mb-lg-0">
						<li className="nav-item">
							<Link className="nav-link" to="/">
								Home
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link" to="/about">
								About
							</Link>
						</li>
					</ul>
				</div>
			</div>
			<>
				{user ? (
					<>
						<p className="m-0 pe-2" style={{ color: "white" }}>
							{user.username}
						</p>
						<Link className="btn btn-dark" to={`/wishlist/${user.username}`}>
							❤️
						</Link>
						<Link to="/login" onClick={logout}>
							<button className="btn btn-outline-info mx-1">Logout</button>
						</Link>
					</>
				) : (
					<>
						<Link to="/login">
							<button className="btn btn-outline-info mx-1">Login</button>
						</Link>
						<Link to="/register">
							<button className="btn btn-outline-info">Register</button>
						</Link>
					</>
				)}
			</>
		</nav>
	);
}

export default Nav;
