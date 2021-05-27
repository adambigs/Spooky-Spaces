import Button from "./Button";
import { Link } from "react-router-dom";

function Comment({ commentId, username, rating, description, encounterId }) {
	const deleteComment = () => {
		fetch(`http://localhost:8080/api/comment/${commentId}`, {
			method: "DELETE",
		})
			.then((response) => {
				if (response.status === 204) {
					deleteComment(commentId);
				} else if (response.status === 404) {
					return Promise.reject("Comment");
				} else {
					return Promise.reject(
						`Delete failed with status: ${response.status}`
					);
				}
			})
			.catch(console.log);
	};

	return (
		<div className="card mt-3 pt-2 bg-light text-dark">
			<div className="row">
				<div className="col-6">
					<h6><strong>{username}</strong></h6>
				</div>
				<div className="col-6">
					<Link to={`/comment/edit/${commentId}`}>
						<Button text="Edit" />
					</Link>
					<Button text="Delete" onClick={() => deleteComment(commentId)} />
				</div>
			</div>
			<div className="row mt-4">
				<p><strong>Rating:</strong> {rating}</p>
				<p>{description}</p>
			</div>
		</div>
	);
}

export default Comment;
