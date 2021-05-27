import { useState, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import CommentList from "./CommentList";
import Button from "./Button";

function Encounter({
	encounterId,
	description,
	encounterType,
	deleteEncounter,
}) {
	const [isActive, setIsActive] = useState("true");

	const history = useHistory();

	const defaultEncounter = {
		encounterId: 0,
		description: "",
		encounterType: 0,
		locationId: 0,
	};

	const [encounter, setEncounter] = useState(defaultEncounter);

	const handleActive = () => {
		setIsActive(!isActive);
	};

	const deleteById = () => {
		fetch(`http://localhost:8080/api/encounter/${encounterId}`, {
			method: "DELETE",
		})
			.then((response) => {
				if (response.status === 204) {
					deleteEncounter(encounterId);
				} else if (response.status === 404) {
					return Promise.reject("Comment");
				} else {
					return Promise.reject(
						`Delete failed with status: ${response.status}`
					);
				}
			})
			.then(history.push(`/location/${encounter.locationId}`))
			.catch(console.log);
	};

	useEffect(() => {
		//Get encounter by id
		fetch(`http://localhost:8080/api/encounter/${encounterId}`)
			.then((response) => response.json())
			.then((data) => setEncounter(data))
			.catch((error) => console.log(error));
	}, []); //THERE BRACKETS LOSE THEIR MINDS

	return (
		<div className="card mt-2 pt-2 bg-secondary text-white">
			<div className="row">
				<div className="col-6">
					<p>Type: {encounter.encounterType}</p>
				</div>
				<div className="col-6">
					<Link to={`/encounter/edit/${encounterId}`}>
						<Button text="Edit" />
					</Link>
					<button className="btn btn-info ml-2" onClick={() => { if (window.confirm('Are you sure you wish to delete this item?')) deleteById() } }>
						Delete
					</button>
				</div>
			</div>
			<div className="card-body">
				<p>{description}</p>
				<Link to={`/comment/add/${encounterId}`}>
					<Button text="Add Comment" />
				</Link>
				<button type="submit" className="btn btn-info" onClick={handleActive}>
					View Comments
				</button>
				<span className={isActive ? "d-none" : null}>
					<CommentList encounterId={encounterId} />
				</span>
			</div>
		</div>
	);
}

export default Encounter;
