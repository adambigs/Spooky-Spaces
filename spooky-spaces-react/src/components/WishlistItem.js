import { useState, useEffect } from 'react';

function Wishlist({wishlistId, username, locationId}){
  const [location, setLocation] = useState();


  useEffect(() => { //get locations
    fetch(`http://localhost:8080/api/location/${locationId}`)
    .then(response => {
      if (response.status !== 200) {
      console.log(response);
      return Promise.reject("GET didn't work");
      }
      return response.json();
    })
    .then(json => setLocation(json))
    .catch(console.log);
  }, []);

  const deleteWishlistItem = ()  => {
    fetch(`http://localhost:8080/api/wishlist/${locationId}`, { method: "DELETE" })
      .then((response) => {
        if (response.status === 204) {
          deleteWishlistItem(locationId);
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

  return(
    <li className="list-group-item">
      Wishlist {username}
      <button className="btn btn-secondary ml-2" onClick={deleteWishlistItem}>Delete</button>
      {location.locationName} {location.address}
    </li>
  ); 
}

export default Wishlist;