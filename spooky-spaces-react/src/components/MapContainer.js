import React from 'react';
import {Map, InfoWindow, Marker, GoogleApiWrapper} from 'google-maps-react';

export class MapContainer extends React.Component {

state = {
  showingInfoWindow: false,
  activeMarker: {},
  selectedPlace: {},
};

onMarkerClick = (props, marker, e) =>
  this.setState({
    selectedPlace: props,
    activeMarker: marker,
    showingInfoWindow: true, 
  });

onMapClicked = (props) => {
  if (this.state.showingInfoWindow) {
    this.setState({
      showingInfoWindow: false,
      activeMarker: null
    })
  }
};

render() {
  return (
    <Map
          google={this.props.google}
          initialCenter={{
            lat: 43.0327150,
            lng: -87.913880
          }}
          zoom={15}
          onClick={this.onMapClicked}
        >

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Technology and nnovation Center</a>}
        position={{lat: 43.04368198, lng: -88.04444294}}
        // icon={{
        //   url: "./favicon.png"}} 
        ></Marker>
        
        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Pfitser Hotel</a>}
        position={{lat: 43.0395621930838, lng: -87.9055136747693}}
        // icon={{
        //   url: "./favicon.png"}} 
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Pabst Mansion</a>}
        position={{lat: 43.0392265001733, lng: -87.9379181208035}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Shakers</a>}
        position={{lat: 43.02709626, lng: -87.91223392}}
        ></Marker>
        
        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Sabbatic</a>}
        position={{lat: 43.024537, lng: -87.91234892}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Marion Center for Nonprofits</a>}
        position={{lat: 42.98614047, lng: -87.87074732}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Brumder Mansion</a>}
        position={{lat: 43.03943369, lng: -87.95283152}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">The Riverside Theater</a>}
        position={{lat: 43.03900341, lng: -87.9111033}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Historic Miller Caves</a>}
        position={{lat:43.04231145, lng: -87.96216669}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Marquette University</a>}
        position={{lat: 43.039324, lng: -87.92795602}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">North Point Lighthouse</a>}
        position={{lat: 43.06580298, lng: -87.87136542}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Milwaukee Public Museum</a>}
        position={{lat: 43.04115971, lng: -87.92084314}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">The Rave</a>}
        position={{lat: 43.03832494, lng: -87.94314067}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Modjeska Theater</a>}
        position={{lat: 43.0128759639969, lng: -87.9265019594383}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Hilton Garden Inn</a>}
        position={{lat: 43.0380106548861, lng:-87.9079159945014 }}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Ambassador Hotel</a>}
        position={{lat: 43.0395564921141, lng: -87.9419190821826}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Skylight Music Theater</a>}
        position={{lat: 43.0323840945197, lng: 87.9067534821863}}
        ></Marker>

        <Marker
        onClick={this.onMarkerClick}
        name={<a href="/about">Sunset Playhouse</a>}
        position={{lat: 43.0405018863577, lng: -88.0780774821821}}
        ></Marker>
        
       
      <InfoWindow
        marker={this.state.activeMarker}
        visible={this.state.showingInfoWindow}>
          <div>
            <h1>{this.state.selectedPlace.name}</h1>
          </div>
      </InfoWindow>
    </Map>
  );
}
}
export default GoogleApiWrapper({
  apiKey: 'AIzaSyDRbKGz40335THbxaRmojuQUTwdwK5SCZA'
})(MapContainer);