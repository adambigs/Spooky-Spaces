import React from 'react';
import { GoogleMap,
useLoadScript,
Marker,
InfoWindow } from "@react-google-maps/api";
import mapStyles from "./M"
import usePlacesAutocomplete, {
    getGeocode,
    getLatLng,
  } from "use-places-autocomplete";

const libraries =["places"]
const mapContainerStyle = {
    width: "100%",
    height: "100vw",
}

const center = { 
    lat: 43.0327150,
    lng: -87.913880
}

const options {
 styles: mapStyles
}

export default function NewMap(){
    const {isLoaded, loadError} = useLoadScript({
        googleMapsApiKey: "AIzaSyDRbKGz40335THbxaRmojuQUTwdwK5SCZA",
        libraries,
    });

    if (loadError) return "Error loading map";
    if (!isLoaded) return "Loading Maps";

    return <div>
    <GoogleMap mapContainerStyle={mapContainerStyle} 
    zoom={15} 
    center={center} ></GoogleMap>
    options={}
    </div>
}
