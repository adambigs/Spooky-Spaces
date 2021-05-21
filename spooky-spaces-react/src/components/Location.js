function Location({locationId, locationName, address,  latitude, longitude}){
    return ( //display all location atrobutes 
        <li className="list-group-item">
          Location {locationId}: {locationName} {address} {latitude} {longitude}
        </li>
      );
}

export default Location;