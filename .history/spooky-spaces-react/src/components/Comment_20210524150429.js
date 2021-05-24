import Button from './Button';
import { Link } from 'react-router-dom';

function Comment({ commentId, username, rating, description, encounterId }) {
    
    const deleteComment = ()  => {
        fetch(`http://localhost:8080/api/comment/${commentId}`, { method: "DELETE" })
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
        <div className="container text-center border">
            <div className="row">
                <div className="col-6">
<<<<<<< HEAD
                <p>{username}</p>
=======
                <p>USERNAME HERE{username}</p>
>>>>>>> main
                </div>
                <div className="col-6">{rating}</div>
                <div className="col-6"></div>
                <div className="col-6"></div>
                <div className="col-6"></div>
                <div className="col-6">
                <Link to={`api/comment/${commentId}`}><Button text="Edit" /></Link>
                <Button text="Delete" />
                </div>
            </div>    
            <div className="row mt-4">
                <p>{description}</p>
            </div>    
        </div>
    )
}

export default Comment;
