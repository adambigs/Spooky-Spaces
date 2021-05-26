import { useEffect, useState } from 'react';
import WishlistItem from './WishlistItem';

function Wishlist({ username }) {
    const [wishlists, setWishlists] = useState([]); //List of all wishlist
    const [messages, setMessages] = useState(""); //Any error messages

    useEffect(() => { //get the list of all wishlist
        fetch(`http://localhost:8080/api/wishlist/${username}`)
        .then(response => {
          if (response.status !== 200) {
          console.log(response);
          return Promise.reject("GET didn't work");
          }
          return response.json();
        })
        .then(json => setWishlists(json))
        .catch(console.log);
    }, []);

    return ( //map all values to an wishlist
        <div className="card">
          <h2 className="card-title ml-3">Wishlist List</h2>
          <ul className="list-group list-group-flush">
            {wishlists.map(w => <WishlistItem key={w.wishlistId} wishlistId={w.wishlistId} username={w.username} locationId={w.locationId} />)}
          </ul>
        </div>   
      );

}

export default Wishlist;