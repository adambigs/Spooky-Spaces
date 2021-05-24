function AddComment() {
  const Add = () => {
    const [firstName, setFirstName] = useState("");

    const history = useHistory();

    const onSubmit = (e) => {
      e.preventDefault();

      const newComment = {
        username: username,
        rating: rating,
        description: comment,
        encounterId: encounterId,
      };

      const init = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify(newComment),
      };

      fetch("http://localhost:8080/api/agent", init)
        .then((response) => {
          if (response.status !== 201) {
            return Promise.reject("response is not OK");
          }
          return response.json();
        })
        .then(history.push("/encounter"))
        .catch(console.log);
    };
  };

  return (
    <div classNameName="container">
      <form>
        <div class="mb-3">
          <label for="commentText" class="form-label">
            Comment
          </label>
          <textarea
            class="form-control"
            id="commentText"
            rows="4"
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default AddComment;
