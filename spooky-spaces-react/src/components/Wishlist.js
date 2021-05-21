function Wishlist({wishlistId, username, locationId, removeWishlist}){
    const deleteById = () => { //delete a wishlist from the list
        fetch(`http://localhost:8080/api/wishlist/${locationId}`, { method: "DELETE" })
          .then(response => {
            if (response.status === 204 || response.status === 404) {
              removeWishlist(wishlistId);
            } else {
              return Promise.reject(`delete found with status ${response.status}`);
            }
        });
    }
    
    return ( //display all wishlist atrobutes and update/delete buttons
        <li className="list-group-item">
          Wishlist {username}
          <button className="btn btn-secondary ml-2" onClick={deleteById}>Delete</button>
        </li>
    );
}

export default Wishlist;