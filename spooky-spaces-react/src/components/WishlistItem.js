import { useState, useEffect } from 'react';

function Wishlist({wishlistId, username, locationId, deleteItem}){
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
          deleteItem(locationId);
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
    <div className="container mt-3">
    <div className="card pt-3 pb-3 text-center">
      <div className="row">
      <div className="col-6">
      <h5>{location.locationName}: {location.address}</h5>
      </div>
      <div className="col-6">
      <button className="btn btn-info" onClick={deleteById}>Delete</button>
      </div>
    </div>
    </div>  
    </div>
  ); 
}

export default Wishlist;