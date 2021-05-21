import React from 'react';
import { useState, useCallback } from 'react';
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
    const [selected, setSelected] = React.useState(null);

    const onMapClick = React.useCallback((e) => {
    setMarkers((current) => [
      ...current,
      {
        lat: e.latLng.lat(),
        lng: e.latLng.lng(),
        time: new Date(),
        },
        ]);
     }, []);

    const mapRef = React.useRef();
    const onMapLoad = React.useCallback((map) => {
        mapRef.current = map;
    }, []); 

    if (loadError) return "Error loading map";
    if (!isLoaded) return "Loading Maps";

    return <div>
    <GoogleMap mapContainerStyle={mapContainerStyle} 
    zoom={15} 
    center={center} 
    options={options} 
    onClick={onMapClick}
    onLoad={onMapLoad}
    >

    {markers.map((marker) => 
    <Marker 
    key={`${marker.lat}-${marker.lng}`} 
    position={{lat: marker.lat, lng: marker.lng}}
    icon={{
        url: '/ghost2.png',
        origin: new window.google.maps.Point(0, 0),
        anchor: new window.google.maps.Point(15, 15),
        scaledSize: new window.google.maps.Size(30, 30),
    }}
    onClick={( =>)}     
    /> 
    )}
    
    </GoogleMap>
    
    </div>
}
