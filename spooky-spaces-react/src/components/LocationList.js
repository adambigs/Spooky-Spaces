import { useEffect, useState } from 'react';
import {useParams } from "react-router-dom";
import Encounter from "./Encounter";
import CommentList from './CommentList';

function LocationList(){
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
    const [encounterId, setEncounterId] = useState(0);

    useEffect (() => { //Get location by id
      fetch(`http://localhost:8080/api/location/${id}`)
      .then(response => response.json())
      .then(data => setLocation(data))
      .catch(error => console.log(error));
  }, [id]);

    return ( //map all values to a location
        <div className="container text-center">
        {location.locationName} {location.address}
        {location.encounters.map(en => <Encounter key={en.encounterId} encounterId={en.encounterId} description={en.description}  />)}
        {console.log(location.encounters[0])}
        <div className="row">
        <CommentList encounterId={location.encounters.encounterId}/>
        </div>
        </div>   
    );
}

export default LocationList;