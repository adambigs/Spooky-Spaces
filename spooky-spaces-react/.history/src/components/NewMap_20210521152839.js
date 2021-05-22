import React from 'react';
import { useState } from 'react';
import { GoogleMap,
useLoadScript,
Marker,
InfoWindow } from "@react-google-maps/api";
import mapStyles from "./mapStyles";
import {
    usePlacesAutocomplete, 
    getGeocode,
    getLatLng,
  } from "use-places-autocomplete";
  import { formatRelative } from "date-fns";

const libraries =["places"]
const mapContainerStyle = {
    width: "100%",
    height: "50vw",
}

const center = { 
    lat: 43.0327150,
    lng: -87.913880
}

const options = {
    styles: mapStyles,
    disableDefaultUI: true,
    zoomControl: true,
    streetViewControl: true
}



export default function NewMap(){
    const {isLoaded, loadError} = useLoadScript({
        googleMapsApiKey: "AIzaSyDRbKGz40335THbxaRmojuQUTwdwK5SCZA",
        libraries,
    });
    
    const [markers, setMarkers] = useState([]);

    if (loadError) return "Error loading map";
    if (!isLoaded) return "Loading Maps";

    return <div>
    <GoogleMap mapContainerStyle={mapContainerStyle} 
    zoom={15} 
    center={center} 
    options={options} 
    onClick={(event) => {
        setMarkers(current => [...current, 
        {
            lat: event.latLng.lat(),
            lng: event.latLng.lng(),
            time: new Date(),
        },
        ]);
    }}>

    {markers.map(marker => <Marker key={Marker.time.ISOString()} 
    position={{lat: marker.lat, lng: marker.lng}}/> )}
    
    </GoogleMap>
    
    </div>
}
