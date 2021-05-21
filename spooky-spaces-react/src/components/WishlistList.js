import { useEffect, useState } from 'react';
import Wishlist from './Wishlist';

function WishlistList() {
    const [wishlists, setWishlists] = useState([]); //List of all wishlist
    const [messages, setMessages] = useState(""); //Any error messages

    useEffect(() => { //get the list of all wishlist
        fetch("http://localhost:8080/api/wishlist")
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

    const removeWishlist = (WishlistId) => {
        let newWishlists= [];
    
        for (let i = 0; i < wishlists.length; i++) { //go through all Wishlists, add them to an array
          if (wishlists[i].WishlistId !== WishlistId) { //dont add Wishlist being deleted to the array
            wishlists.push(wishlists[i]);
          }
        }
    
        if (newWishlists.length !== wishlists.length) {
          setWishlists(newWishlists);
          setMessages("");
        } else {
          setMessages("Could not find that wishlist to remove");
        } 
    }

    return ( //map all values to an wishlist
        <div className="card">
          <h2 className="card-title ml-3">Wishlist List</h2>
          <ul className="list-group list-group-flush">
            {wishlists.map(w => <Wishlist key={w.wishlistId} username={w.username} removeWishlist={removeWishlist}/>)}
          </ul>
        </div>   
      );

}

export default WishlistList;