function CommentList() {
  const [comments, setComments] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    //get the list of comments for encounter
    fetch("http://localhost:8080/api/comments/${id}")
      .then((response) => {
        if (response.status !== 200) {
          console.log(response);
          return Promise.reject("GET didn't work");
        }
        return response.json();
      })
      .then((json) => setComments(json))
      .catch(console.log);
  }, []);

  return (
    <div class="container">
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
