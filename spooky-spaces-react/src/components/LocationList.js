import { useEffect, useState } from 'react';
import {useParams } from "react-router-dom";
import Encounter from "./Encounter";
import CommentList from './CommentList';
import Button from './Button';
import { Link } from 'react-router-dom';
import { useHistory} from 'react-router-dom';

function LocationList( { username }){
    const { id } = useParams();

    const defaultLocation = {
      locationId: 0,
      longitude: "",
      latitude: "",
      locationName: "",
      address: "",
      locationImage: "",
      encounters: []
    } 

    const [location, setLocation] = useState(defaultLocation);

    useEffect (() => { //Get location by id
      fetch(`http://localhost:8080/api/location/${id}`)
      .then(response => response.json())
      .then(data => setLocation(data))
      .catch(error => console.log(error));
  });

  const history = useHistory();

  const handleAdd = (event) => {
    let wishlist = {};
    wishlist["username"] = username;
    wishlist["locationId"] = id;
    addWishlist(wishlist);
  }

  const [wishlists, setWishlists] = useState([]); 
  const [messages, setMessages] = useState(""); //Any error messages

    // useEffect(() => { //get the list of all wishlists
    //     fetch(`http://localhost:8080/api/wishlist/${username}`)
    //     .then(response => {
    //       if (response.status !== 200) {
    //       console.log(response);
    //       return Promise.reject("GET didn't work");
    //       }
    //       return response.json();
    //     })
    //     .then(json => setWishlists(json))
    //     .catch(console.log);
    // });
  
    const addFetch = (wishlist) => { //add a wishlist
      const init = {
          method: "POST",
          headers: {
              "Content-Type": "application/json",
              "Accept": "application/json"
          },
          body: JSON.stringify(wishlist)
      };

      fetch(`http://localhost:8080/api/wishlist/`, init)
        .then(response => {
          if (response.status !== 201) {
            return Promise.reject("POST doesn't work");
          }
          return response.json();
        })
        .then(json => {
          setWishlists([...wishlists, json]);
          setMessages("");
        })
        .then(history.goBack()) 
        .catch(console.log);
    }

    const addWishlist = (wishlist) => {
      let canSet = true;

      for (let i = 0; i < wishlists.length; i++) { 
        if (wishlist.wishlistId === wishlists[i].wishlistId) {
          canSet = false;
        }
      }
      if (canSet) {
      addFetch(wishlist); //add wishlist item if no errors
      } else {
      setMessages("Location Id is alread on wishlist");
      } 
    }

  return(
    <div className="container text-center mt-5">
    <div className="row">
    <h3 className="mt-4">{location.locationName} <Button text="❤️" onClick={handleAdd}/> <Link to={`/encounter/add/${id}`}><Button text="Add Encounter"/></Link></h3>
    <h5>{location.address}</h5>
    </div>
    <div>
    {location.encounters.map(en => <Encounter key={en.encounterId} encounterId={en.encounterId} description={en.description} encounterType={en.encounterType}  />)}
    </div>
    </div>   
  );
}

export default LocationList;