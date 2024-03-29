import { useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import Button from "./Button";

function AddComment({ user }) {
	const [rating, setRating] = useState(0);
	const [description, setDescription] = useState("");

	const { id } = useParams();

	const history = useHistory();

	const onSubmit = (e) => {
		e.preventDefault();

		const newComment = {
			username: user.username,
			rating: rating,
			description: description,
			encounterId: id,
		};

		const init = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Accept: "application/json",
			},
			body: JSON.stringify(newComment),
		};

		fetch("http://localhost:8080/api/comment", init)
			.then((response) => {
				if (response.status !== 201) {
					return Promise.reject("response is not OK");
				}
				return response.json();
			})
			.then(history.goBack())
			.catch(console.log);
	};

	const handleDescriptionChange = (event) => {
		setDescription(event.target.value);
	};

	const handleRatingChange = (event) => {
		setRating(event.target.value);
	};

	return (
		<div className="container mt-5">
    <div className="row pt-3">
			<p>Rating</p>
			<form className="form-inline" onSubmit={onSubmit}>
				<div className="form-check form-check-inline mb-3">
					<input
						className="form-check-input"
						type="radio"
						name="rating"
						id="1"
						value="1"
						onChange={handleRatingChange}
					/>
					<label className="form-check-label" htmlFor="1">
						1
					</label>
				</div>
				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="rating"
						id="2"
						value="2"
						onChange={handleRatingChange}
					/>
					<label className="form-check-label" htmlFor="2">
						2
					</label>
				</div>
				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="rating"
						id="3"
						value="3"
						onChange={handleRatingChange}
					/>
					<label className="form-check-label" htmlFor="3">
						3
					</label>
				</div>
				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="rating"
						id="4"
						value="4"
						onChange={handleRatingChange}
					/>
					<label className="form-check-label" htmlFor="4">
						4
					</label>
				</div>
				<div className="form-check form-check-inline">
					<input
						className="form-check-input"
						type="radio"
						name="rating"
						id="5"
						value="5"
						onChange={handleRatingChange}
					/>
					<label className="form-check-label" htmlFor="5">
						5
					</label>
				</div>
				<div className="mb-3">
					<label htmlFor="commentText" className="form-label">
						Comment
					</label>
					<input
						type="text"
						className="form-control"
						id="commentText"
						rows="4"
						onChange={handleDescriptionChange}
					/>
          <div className="mt-2">
            <button type="submit" className="btn btn-info mx-1">
              Add
            </button>
            <Button text="Cancel" onClick={() => history.goBack()} />
          </div>    
		</div>
    </form>
    </div>
    </div>
	);
}

export default AddComment;
