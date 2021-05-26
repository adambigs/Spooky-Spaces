import { useState, useEffect } from 'react';

function Wishlist({wishlistId, username, locationId, deleteWishlistItem}){
  const [location, setLocation] = useState("");

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

  const deleteById = ()  => {
    fetch(`http://localhost:8080/api/wishlist/${username}/${locationId}`, { method: "DELETE" })
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
      Name: {location.locationName} Address: {location.address}
      <button className="btn btn-secondary ml-2" onClick={deleteById}>Delete</button>
    </li>
    
  ); 
}

export default Wishlist;