import React from "react";
import { useState, useCallback, useEffect } from "react";
import {
	GoogleMap,
	useLoadScript,
	Marker,
	InfoWindow,
} from "@react-google-maps/api";
import mapStyles from "./mapStyles";
import usePlacesAutocomplete, {
	getGeocode,
	getLatLng,
} from "use-places-autocomplete";

import { useParams, useHistory } from "react-router-dom";
import {
	Combobox,
	ComboboxInput,
	ComboboxPopover,
	ComboboxList,
	ComboboxOption,
} from "@reach/combobox";
import "@reach/combobox/styles.css";

const libraries = ["places"];
const mapContainerStyle = {
	width: "100%",
	height: "50vw",
};

const center = {
	lat: 43.032715,
	lng: -87.91388,
};

const options = {
	styles: mapStyles,
	disableDefaultUI: true,
	zoomControl: true,
	streetViewControl: true,
};

export default function NewMap() {
	const { isLoaded, loadError } = useLoadScript({
		googleMapsApiKey: "AIzaSyDRbKGz40335THbxaRmojuQUTwdwK5SCZA",
		libraries,
	});

	const [markers, setMarkers] = useState([]);

	const [selected, setSelected] = React.useState(null);
	const [selected2, setSelected2] = React.useState(null);
	const [locations, setLocations] = useState([]); //List of all locations
	const { id } = useParams();

	const [lat1, setLat1] = useState("");
	const [lng1, setLng1] = useState("");
	const [address, setAddress] = useState("");
	const [name, setName] = useState("");

	useEffect(() => {
		//get the list of all location
		fetch("http://localhost:8080/api/location")
			.then((response) => {
				if (response.status !== 200) {
					console.log(response);
					return Promise.reject("GET didn't work");
				}
				return response.json();
			})
			.then((json) => setLocations(json))
			.catch(console.log);
	});

	const onMapClick = React.useCallback((e) => {
		//when the map is clicked a new ghost maker is added
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

			<GoogleMap //generate the map
				id="map"
				mapContainerStyle={mapContainerStyle}
				zoom={15}
				center={center}
				options={options}
				onClick={onMapClick}
				onLoad={onMapLoad}
			>
				{locations.map((l) => (
					<Marker
						key={l.locationId}
						locationId={l.locationId}
						locationName={l.locationName}
						address={l.address}
						latitude={l.latitude}
						longitude={l.longitude} //get all the locations and adds markers
						position={{
							lat: parseFloat(l.latitude),
							lng: parseFloat(l.longitude),
						}}
						title={l.locationName}
						onClick={() => {
							//set all necessary values after clicking on a marker
							setSelected2(l);
							setLat1(l.latitude);
							setLng1(l.longitude);
							setAddress(l.address);
							setName(l.locationName);
						}}
						icon={{
							//sets the maker to a ghost
							url: "/ghost2.png",
							origin: new window.google.maps.Point(0, 0),
							anchor: new window.google.maps.Point(15, 15),
							scaledSize: new window.google.maps.Size(30, 30),
						}}
					/>
				))}

				{selected2 ? ( //for an existing ghost
					<InfoWindow
						position={{ lat: parseFloat(lat1), lng: parseFloat(lng1) }}
						onCloseClick={() => {
							setSelected2(null);
							setSelected(null);
						}}
					>
						<div className="text-center">
							<h2>
								<span role="img" aria-label="ghost">
									👻
								</span>
								Ghost Alert!
							</h2>
							<div className="text-center">
								<h4 className="text-primary">
									<u>
										<a href={`/location/${selected2.locationId}`}>{name}</a>
									</u>
								</h4>
								<p> {address} </p>
							</div>
						</div>
					</InfoWindow>
				) : null}
			</GoogleMap>
		</div>
	);
}

function Locate({ panTo }) {
	//search bar at the top of the map
	return (
		<button
			className="locate"
			onClick={() => {
				navigator.geolocation.getCurrentPosition(
					(position) => {
						panTo({
							lat: position.coords.latitude,
							lng: position.coords.longitude,
						});
					},
					() => null
				);
			}}
		>
			<img src="/compass.svg" alt="compass" />
		</button>
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
			location: { lat: () => 43.032715, lng: () => -87.91388 },
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
