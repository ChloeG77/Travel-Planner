import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import styled from 'styled-components';
import AutoComplete from './Autocomplete';
import Marker from './Marker';
import { API_KEY } from '../constants';
import axios from "axios";
import SearchBar from './SearchBar';
import { Table, Button } from 'antd';
import DailyPlan from './DailyPlan'
<<<<<<< HEAD
=======
import { Layout } from 'antd';
>>>>>>> bfb766114dec9cb337e4b44d6da9a2972a730a8d

const { Sider, Content } = Layout;

const Wrapper = styled.div`
  width: 100%;
  height: 100%;
`;






class Main extends Component {
    state = {
        mapApiLoaded: false,
        mapInstance: null,
        mapApi: null,
        geoCoder: null,
        center: [],
        zoom: 9,
        lat: [],
        lng: [],
        placedata: [],
        selectedId: [],
        columns: [
            { title: 'Name', dataIndex: 'name', key: 'name' },
            { title: 'Rating', dataIndex: 'rating', key: 'rating' },
            { title: 'Address', dataIndex: 'address', key: 'address' },
            {
                title: 'Action',
                dataIndex: '',
                key: 'x',
                render: (record) => <Button type="primary" onClick={() => this.addMarker(record.key)} disabled={this.state.selectedId.includes(this.state.placedata[record.key].id)}>Add Marker</Button>,
            }
        ]
    };


    componentWillMount() {
        const { destination, isLoggedIn, token } = this.props;
        // const url = `/api/place/findplacefromtext/json?input=${destination}&inputtype=textquery&fields=geometry&key=${API_KEY}`;

        const url = `/api/place/searchByName?text=${destination}&city=${destination}`;

        axios.get(url, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(res => {
                const place = res.data[0];
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

    // addPlace = (place) => {
    //     this.setState({
    //         lat: [...this.state.lat, place.geometry.location.lat()],
    //         lng: [...this.state.lng, place.geometry.location.lng()]
    //     });

    // };

    addMarker = (key) => {
        const { placedata, mapInstance } = this.state;
        const lat = placedata[key].lat;
        const lng = placedata[key].lng;
        // mapInstance.fitBounds(placedata[key].viewport);
        this.setState({ center: [lat, lng] })
        mapInstance.setZoom(13);


        this.setState({
            lat: [...this.state.lat, lat],
            lng: [...this.state.lng, lng],
            selectedId: [...this.state.selectedId, placedata[key].id]
        });
    };

    clearTable = () => {
        this.setState({ placedata: [] });
    }

    setMapCenter = (place) => {
        this.setState({
            center: [place.latitude, place.longitude]
        })
    };

    addPlaceToTable = (data) => {
        this.setState({ placedata: [...this.state.placedata, data] });
    }



    render() {
        const {
            mapApiLoaded, mapInstance, mapApi, lat, lng, placedata, columns
        } = this.state;

        const { destination, isLoggedIn, token } = this.props;


        return (
            <Layout
                style={{ height: "100%", width: "100%" }}
            >

                {mapApiLoaded && (
                    <Sider
                        width={500}
                        theme={"light"}
                    >
                            {/* <AutoComplete map={mapInstance} mapApi={mapApi} addplace={this.addPlace} /> */}
                            <SearchBar destination={this.props.destination} addPlaceToTable={this.addPlaceToTable} clearTable={this.clearTable} />
                            <Table
                                columns={columns}
                                expandable={{
                                    expandedRowRender: record => <p style={{ margin: 0 }}>{record.description}</p>,
                                    rowExpandable: record => record.name !== 'Not Expandable',
                                }}
                                dataSource={placedata}
                                pagination={{pageSize: 3}}
                            />
                            <DailyPlan />
                    </Sider>
                )}

                {/* <div className="info-wrapper">
                    <div className="map-details">Latitude: <span>{lat}</span>, Longitude: <span>{lng}</span></div>
                </div> */}
                <Content>
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
                </Content>
            </Layout>
        );
    }
}

export default Main;