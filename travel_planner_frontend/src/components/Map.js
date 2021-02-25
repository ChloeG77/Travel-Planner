import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import styled from 'styled-components';
import AutoComplete from './Autocomplete';
import Marker from './Marker';
import { API_KEY } from '../constants';
import axios from "axios";




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
        const destination  = this.props.destination;
        const url = `/api/place/findplacefromtext/json?input=${destination}&inputtype=textquery&fields=rating,opening_hours,geometry,place_id&key=${API_KEY}`;
        axios.get(url)
            .then(res=> {
                const place = res.data.candidates[0];
                this.setMapCenter(place);
            })
            .catch(e => {
                console.log(e);
            });
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
        this.setState({
            places: [...this.state.places, place],
            lat: [...this.state.lat, place.geometry.location.lat()],
            lng: [...this.state.lng, place.geometry.location.lng()]
        });

    };

    setMapCenter = (place) => {
        this.setState({
            center: [place.geometry.location.lat, place.geometry.location.lng]
        })
    };

    // Get Current Location Coordinates
    // setCurrentLocation() {
    //     if ('geolocation' in navigator) {
    //         navigator.geolocation.getCurrentPosition((position) => {
    //             this.setState({
    //                 center: [position.coords.latitude, position.coords.longitude],
    //                 // lat: [position.coords.latitude],
    //                 // lng: [position.coords.longitude]
    //             });
    //         });
    //     }
    // }

    render() {
        const {
             mapApiLoaded, mapInstance, mapApi, lat, lng
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
                    bootstrapRLKeys={{
                        key: `${API_KEY}`,
                        libraries: ['places', 'geometry'],
                    }}
                    yesIWantToUseGoogleMapApiInternals
                    onGoogleApiLoaded={({ map, maps }) => this.apiHasLoaded(map, maps)}
                >

                    {
                        lat.map((ele, idx) => {
                            return (
                        <Marker
                            key={idx}
                            text={'hello'}
                            lat={lat[idx]}
                            lng={lng[idx]}
                        />
                            )
                        
                        })
                    }
                    
                </GoogleMapReact>

                {/* <div className="info-wrapper">
                    <div className="map-details">Latitude: <span>{lat}</span>, Longitude: <span>{lng}</span></div>
                </div> */}


            </Wrapper >
        );
    }
}

export default Map;