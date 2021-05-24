<<<<<<< HEAD
import { useState, useEffect } from "react";
import { useHistory, useParams } from "react-router-dom";
import Button from './Button';

function EditComment({ username, commentId }) {
    const defaultComment = {
      commentId: 0,
      username: "",
      rating: 0,
      description: "",
    };
  
    const [comment, setComment] = useState(defaultComment);
  
    const [rating, setRating] = useState(0);
    const [description, setDescription] = useState("");
  
    const history = useHistory();
  
    useEffect(() => {
      fetch(`http://localhost:8080/api/comment/${commentId}`)
        .then((response) => response.json())
        .then((data) => setComment(data))
        .catch((error) => console.log(error));
    }, []);
  
    const onSubmit = (e) => {
      e.preventDefault();
  
      const newComment = {
        commentId: id,
        rating: comment.rating,
        description: description.length > 0 ? description : comment.description,
      };
  
      const init = {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(newComment),
      };
  
      console.log(init);
  
      fetch(`http://localhost:8080/api/comment/${id}`, init)
        .then((response) => {
          if (response.status !== 204) {
            return Promise.reject("couldn't update");
          }
        })
        .then(history.goBack())
        .catch(console.log);
    };
  
    return (
      <div className="container">
        <p>{username}</p>
        <p>Rating</p>
        <form className="form-inline" onSubmit={onSubmit}>
          <div className="form-check form-check-inline mb-3">
            <input
              className="form-check-input"
              type="radio"
              name="rating"
              id="1"
              value="1"
              defaultValue={comment.rating}
              onChange={(e) => setRating(e.target.value)}
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
              defaultValue={comment.rating}
              onChange={(e) => setRating(e.target.value)}
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
              defaultValue={comment.rating}
              onChange={(e) => setRating(e.target.value)}
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
              defaultValue={comment.rating}
              onChange={(e) => setRating(e.target.value)}
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
              defaultValue={comment.rating}
              onChange={(e) => setRating(e.target.value)}
            />
            <label className="form-check-label" htmlFor="5">
              5
            </label>
          </div>
          <div className="mb-3">
            <label for="commentText" class="form-label">
              Comment
            </label>
            <textarea className="form-control" id="commentText" rows="4"  defaultValue={comment.description} onChange={(e) => setDescription(e.target.value)} />
          </div>
          <button type="submit" className="btn btn-outline-light">
            Submit
          </button>
          <Button text="Cancel" onClick={() => history.goBack()} />
        </form>
      </div>
    );
  }
  
  export default EditComment;
  
=======
function EditComment() {
  const defaultComment = {
    commentId: 0,
    rating: 0,
    description: "",
  };

  const [comment, setComment] = useState(defaultComment);
  const { id } = useParams();

  const [rating, setRating] = useState(0);
  const [description, setDescription] = useState("");

  const history = useHistory();

  useEffect(() => {
    fetch(`http://localhost:8080/api/comment/${id}`)
      .then((response) => response.json())
      .then((data) => setAgent(data))
      .catch((error) => console.log(error));
  }, [id]);

  const onSubmit = (e) => {
    e.preventDefault();

    const newComment = {
      commentId: id,
      rating: comment.rating,
      description: description.length > 0 ? description : comment.description,
    };

    const init = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(newComment),
    };

    console.log(init);

    fetch(`http://localhost:8080/api/comment/${id}`, init)
      .then((response) => {
        if (response.status !== 204) {
          return Promise.reject("couldn't update");
        }
      })
      .then(history.goBack())
      .catch(console.log);
  };

  return (
    <div className="container">
      <p>{username}</p>
      <p>Rating</p>
      <form className="form-inline" onSubmit={onSubmit}>
        <div className="form-check form-check-inline mb-3">
          <input
            className="form-check-input"
            type="radio"
            name="rating"
            id="1"
            value="1"
            defaultValue={comment.rating}
            onChange={(e) => setRating(e.target.value)}
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
            defaultValue={comment.rating}
            onChange={(e) => setRating(e.target.value)}
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
            defaultValue={comment.rating}
            onChange={(e) => setRating(e.target.value)}
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
            defaultValue={comment.rating}
            onChange={(e) => setRating(e.target.value)}
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
            defaultValue={comment.rating}
            onChange={(e) => setRating(e.target.value)}
          />
          <label className="form-check-label" htmlFor="5">
            5
          </label>
        </div>
        <div className="mb-3">
          <label for="commentText" class="form-label">
            Comment
          </label>
          <textarea className="form-control" id="commentText" rows="4"  defaultValue={comment.description} onChange={(e) => setDescription(e.target.value)} />
        </div>
        <button type="submit" className="btn btn-outline-light">
          Submit
        </button>
        <Button text="Cancel" onClick={() => history.goBack()} />
      </form>
    </div>
  );
}

export default EditComment;
>>>>>>> main
