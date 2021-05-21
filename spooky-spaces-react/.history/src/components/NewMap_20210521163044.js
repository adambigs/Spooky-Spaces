import React from 'react';
import { useState, useCallback, useEffect } from 'react';
import { GoogleMap,
useLoadScript,
Marker,
InfoWindow } from "@react-google-maps/api";
import mapStyles from "./mapStyles";
import usePlacesAutocomplete, {
  getGeocode,
  getLatLng,
} from "use-places-autocomplete";

import { formatRelative } from "date-fns";
import {Link} from "react-router-dom";
import LocationList from "./LocationList";
import Location from './Location';
import {
  Combobox,
  ComboboxInput,
  ComboboxPopover,
  ComboboxList,
  ComboboxOption,
} from "@reach/combobox";

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

// put in list of locations 

export default function NewMap(){
    const {isLoaded, loadError} = useLoadScript({
        googleMapsApiKey: "AIzaSyDRbKGz40335THbxaRmojuQUTwdwK5SCZA",
        libraries,
    });
    
    const [markers, setMarkers] = useState([]);
    const [selected, setSelected] = React.useState(null);
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

    const panTo = React.useCallback(({ lat, lng }) => {
        mapRef.current.panTo({ lat, lng });
        mapRef.current.setZoom(14);
      }, []);

    if (loadError) return "Error loading map";
    if (!isLoaded) return "Loading Maps";

    return ( 
        <div>

        <Search panTo={panTo} />
        
            <GoogleMap 
            id="map"
            mapContainerStyle={mapContainerStyle} 
            zoom={15} 
            center={center} 
            options={options} 
            onClick={onMapClick}
            onLoad={onMapLoad}
            >
    
  {locations.map(l => <Location key={l.locationId} locationId={l.locationId} locationName={l.locationName} latitude={l.latitude} longitude={l.longitude} />)}
            
    {markers.map((marker) => 
    <Marker 
        key={`${marker.lat}-${marker.lng}`} 
        position={{lat: marker.lat, lng: marker.lng}}
        onClick={() => {
            setSelected(marker);
        }}
        icon={{
            url: '/ghost2.png',
            origin: new window.google.maps.Point(0, 0),
            anchor: new window.google.maps.Point(15, 15),
            scaledSize: new window.google.maps.Size(30, 30),
        }}     
    /> 
    )}
    
    {selected ? (
        <InfoWindow
          position={{ lat: selected.lat, lng: selected.lng }}
          onCloseClick={() => {
            setSelected(null);
          }}
        >
          <div>
            <h2>
              <span role="img" aria-label="bear">
                👻
              </span>{" "}
              Ghost Alert
            </h2>
            <p></p>
            <h3> Address: </h3>
            <p><Link to="/about"> Details </Link></p>
          </div>
        </InfoWindow>
      ) : null}
    </GoogleMap>
    
    </div>
    );
}

function Search({ panTo }) {
  const {
    ready,
    value,
    suggestions: { status, data },
    setValue,
    clearSuggestions,
  } = usePlacesAutocomplete({
    requestOptions: {
      location: { lat: () => 43.0327150, lng: () => -87.913880 },
      radius: 100000,
    },
  });
  

  const handleInput = (e) => {
    setValue(e.target.value);
  };

  const handleSelect = async (address) => {
    setValue(address, false);
    clearSuggestions();

    try {
      const results = await getGeocode({ address });
      const { lat, lng } = await getLatLng(results[0]);
      panTo({ lat, lng });
    } catch (error) {
      console.log("Error: ", error);
    }
  };

  return (
    <div className="search">
      <Combobox onSelect={handleSelect}>
        <ComboboxInput
          value={value}
          onChange={handleInput}
          disabled={!ready}
          placeholder="Search your location"
        />
        <ComboboxPopover>
          <ComboboxList>
            {status === "OK" &&
              data.map(({ id, description }) => (
                <ComboboxOption key={id} value={description} />
              ))}
          </ComboboxList>
        </ComboboxPopover>
      </Combobox>
    </div>
  );
}

