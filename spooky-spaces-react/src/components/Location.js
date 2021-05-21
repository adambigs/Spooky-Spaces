function Location({locationId, locationName, latitude, longitude}){
    return ( //display all location atrobutes 
        <li className="list-group-item">
          Location {locationId}: {locationName} {latitude} {longitude}
        </li>
      );
}

export default Location;