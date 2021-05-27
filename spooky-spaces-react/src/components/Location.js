function Location({ locationId, locationName, address, latitude, longitude }) {
	return (
		//display all location attributes
		<div className="list-group-item">
			<p>this is a location</p>
			Location {locationId}: {locationName} {address} {latitude} {longitude}
		</div>
	);
}

export default Location;
