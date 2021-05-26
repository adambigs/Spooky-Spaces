import { useState, useEffect} from 'react';

import Comment from './Comment';

function CommentList({ encounterId }) {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    //get the list of comments for encounter
    fetch(`http://localhost:8080/api/comment/encounter/${encounterId}`)
      .then((response) => {
        if (response.status !== 200) {
          console.log(response);
          return Promise.reject("GET didn't work");
        }
        return response.json();
      })
      .then((json) => setComments(json))
      .catch(console.log);
  },[]);

  return (
    <div className="container">
      {comments.map((comment) => (
        <Comment
          key={comment.commentId}
          commentId={comment.commentId}
          username={comment.username}
          rating={comment.rating}
          description={comment.description}
          encounterId={comment.encounterId}
        />
      ))}
    </div>
  );
}

export default CommentList;
