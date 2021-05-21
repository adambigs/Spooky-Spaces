import { useEffect, useState } from 'react';
import Location from './Location';

function LocationList(){
    const [locations, setLocations] = useState([]); //List of all locations

    useEffect(() => { //get the list of all location
        fetch("http://localhost:8080/api/location")
        .then(response => {
          if (response.status !== 200) {
          console.log(response);
          return Promise.reject("GET didn't work");
          }
          return response.json();
        })
        .then(json => setLocations(json))
        .catch(console.log);
    }, []);

    return ( //map all values to a location
        <div className="card">
          <h2 className="card-title ml-3">Location List</h2>
          <ul className="list-group list-group-flush">
            {locations.map(l => <Location key={l.locationId} locationId={l.locationId} locationName={l.locationName} address={l.address} latitude={l.latitude} longitude={l.longitude} />)}
          </ul>
        </div>   
    );
}

export default LocationList;