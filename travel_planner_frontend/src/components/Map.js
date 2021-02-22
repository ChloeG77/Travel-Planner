import React, { Component } from 'react';

import GoogleMapReact from 'google-map-react';

import styled from 'styled-components';

import AutoComplete from './Autocomplete';
import Marker from './Marker';

const Wrapper = styled.main`
  width: 100%;
  height: 100%;
`;

class Map extends Component {


    state = {
        mapApiLoaded: false,
        mapInstance: null,
        mapApi: null,
        geoCoder: null,
        places: [],
        center: [],
        zoom: 9,
        lat: [],
        lng: []
    };

    componentWillMount() {
        this.setCurrentLocation();
    }




    _onChange = ({ center, zoom }) => {
        this.setState({
            center: center,
            zoom: zoom,
        });

    }



    apiHasLoaded = (map, maps) => {
        this.setState({
            mapApiLoaded: true,
            mapInstance: map,
            mapApi: maps,
        });

    };

    addPlace = (place) => {
        // this.setState({
        //     places: [place],
        //     lat: place.geometry.location.lat(),
        //     lng: place.geometry.location.lng()
        // });
        
        this.setState({
            places: [...this.state.places, place],
            lat: [...this.state.lat, place.geometry.location.lat()],
            lng: [...this.state.lng, place.geometry.location.lng()]
        });

    };



    // Get Current Location Coordinates
    setCurrentLocation() {
        if ('geolocation' in navigator) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.setState({
                    center: [position.coords.latitude, position.coords.longitude],
                    lat: [position.coords.latitude],
                    lng: [position.coords.longitude]
                });
            });
        }
    }

    render() {
        const {
            places, mapApiLoaded, mapInstance, mapApi,
        } = this.state;


        return (
            <Wrapper>
                {mapApiLoaded && (
                    <div>
                        <AutoComplete map={mapInstance} mapApi={mapApi} addplace={this.addPlace} />
                    </div>
                )}
                <GoogleMapReact
                    center={this.state.center}
                    zoom={this.state.zoom}
                    onChange={this._onChange}
                    onChildClick={() => console.log('child click')}
                    bootstrapURLKeys={{
                        key: 'AIzaSyBVPTDmI9UqcOBK1mYOFkMv-3sBWypsfsA',
                        libraries: ['places', 'geometry'],
                    }}
                    yesIWantToUseGoogleMapApiInternals
                    onGoogleApiLoaded={({ map, maps }) => this.apiHasLoaded(map, maps)}
                >

                    {
                        this.state.lat.map((lat, idx) => {
                            return (
                        <Marker
                            text={'hello'}
                            lat={this.state.lat[idx]}
                            lng={this.state.lng[idx]}
                        />
                            )
                        
                        })
                    }
                    
                </GoogleMapReact>

                <div className="info-wrapper">
                    <div className="map-details">Latitude: <span>{this.state.lat}</span>, Longitude: <span>{this.state.lng}</span></div>

                </div>


            </Wrapper >
        );
    }
}

export default Map;