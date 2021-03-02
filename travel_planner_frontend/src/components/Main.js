import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';
import { API_KEY } from '../constants';
import axios from "axios";
import SearchBar from './SearchBar';
import { Table, Button, List, Layout, Spin, message } from 'antd';
import DailyPlan from './DailyPlan';
import { addPlaceToTrip, deletePlaceToTrip } from '../utils/auth.js';

const { Sider, Content } = Layout;

class Main extends Component {
    state = {
        isLoading: false,
        mapApiLoaded: false,
        mapInstance: null,
        mapApi: null,
        geoCoder: null,
        center: [],
        zoom: 9,
        lat: [],
        lng: [],
        placedata: [],
        toAddPlace: this.props.curTrip.places,
        placeInPlanner : [],
        selectedId: [],
        columns: [
            { title: 'Name', dataIndex: 'name', key: 'name' },
            { title: 'Rating', dataIndex: 'rating', key: 'rating' },
            {
                title: 'Action',
                dataIndex: '',
                key: 'x',
                render: (record) => <Button type="primary" onClick={() => this.insertToAdd(record.key)} disabled={this.state.selectedId.includes(this.state.placedata[record.key].id)}>Add</Button>,
            }
        ],
        curTrip: this.props.curTrip
    };


    componentWillMount() {
        const { isLoggedIn, token, curTrip} = this.props;
        const destination = curTrip.startCity;
        console.log(destination)
        const url = `/api/place/searchByName?text=${destination}&city=${destination}`;

        // const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4bWEiLCJpYXQiOjE2MTQxOTM3MjEsImV4cCI6MTYxNDY5NDM3NX0.42nGjPcsd94jhiQKc3uuW5srnKicH0G8h6-NpkLKCHhZW6AXC9h914SwiHP5m2YM0kly0OeWx-qMIq2skcvkXw";

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

    insertToAdd = (key) => {
        const { placedata, toAddPlace, curTrip } = this.state;
        this.setState({
            toAddPlace : [...toAddPlace, placedata[key]],
            selectedId: [...this.state.selectedId, placedata[key].id]
        })
        // curTrip.tripId, placedata[key].id
        addPlaceToTrip(curTrip.tripId, placedata[key].id, this.props.token)
            .then((data) => {
                message.success('add place to trip');
                console.log("addplace", data.places);
            }).catch((err) => {
                console.log(err);
                message.error(err.message);
              })

    }

    addMarker = (place) => {
        const { mapInstance } = this.state;
        const lat = place.lat;
        const lng = place.lng;
        // mapInstance.fitBounds(placedata[key].viewport);
        this.setState({ center: [lat, lng] })
        mapInstance.setZoom(13);


        this.setState({
            lat: [...this.state.lat, lat],
            lng: [...this.state.lng, lng],
            placeInPlanner: [...this.state.placeInPlanner, place]
        });
    };

    addToPlanner = (place) => {
        this.addMarker(place);
    }

    removePlace = (place) => {

    }
    onDeletePlace = (place) => {
        // this.addMarker(place);
        console.log(place);
        let list = this.state.toAddPlace.filter(item => item.id !== place.id);
        this.setState({
            toAddPlace : list,
        })
        deletePlaceToTrip(this.state.curTrip.tripId, place.id, this.props.token)
            .then((data) => {
                message.success('delete place');
                console.log("deleteplace", data.places);
            }).catch((err) => {
                console.log(err);
                message.error(err.message);
              })
    }


    toggleLoading = () => {
        this.setState((state) => ({ isLoading: !state.isLoading }));
    };


    clearTable = () => {
        this.setState({ placedata: [] });
    };

    setMapCenter = (place) => {
        this.setState({
            center: [place.latitude, place.longitude]
        })
    };

    addPlaceToTable = (data) => {
        this.setState({ placedata: [...this.state.placedata, data] });
    };

    


    render() {
        const {
            mapApiLoaded, mapInstance, mapApi, lat, lng, placedata, columns, isLoading
        } = this.state;
        // console.log(placedata)
        console.log(this.state.curTrip);

        return (
            <Layout
                style={{ height: "100%", width: "100%" }}
            >

                {mapApiLoaded && (
                    <Sider
                        width={400}
                        theme={"light"}
                    >       
                            <SearchBar
                                className='search-bar'
                                destination={this.props.destination} addPlaceToTable={this.addPlaceToTable} 
                                clearTable={this.clearTable} 
                                toggleLoading = {this.toggleLoading} 
                                token={this.props.token}/>
                            {
                                isLoading ? 
                                <div className="spin-box">
                                    <Spin tip="Loading..." size="large" />
                                </div>
                                :
                                <Table className="search-table"
                                    columns={columns}
                                    expandable={{
                                        expandedRowRender: record => <p style={{ margin: 0 }}>{placedata[record.key].address}</p>,
                                        rowExpandable: record => record.name !== 'Not Expandable',
                                    }}
                                    dataSource={placedata}
                                    pagination={{pageSize: 3}}
                                />
                            }

                            <List
                                className="to-add-list"
                                itemLayout="horizontal"
                                size="small"
                                dataSource={this.state.toAddPlace}
                                renderItem={place => (
                                    <List.Item
                                        // actions={[<Checkbox dataInfo={item} onChange={this.onChange}/>]}
                                    >
                                        <List.Item.Meta
                                            // avatar={<Avatar size={50} src={satellite} />}
                                            title={<p>{place.name}</p>}
                                            // description={`Launch Date: ${item.launchDate}`}
                                        />
                                        <Button type="primary" 
                                            onClick={() => this.addToPlanner(place)}
                                            disabled={this.state.placeInPlanner.includes(place)}
                                            style={{marginRight:"10px"}}>Add to planner
                                        </Button>
                                        <Button type="primary" 
                                            onClick={() => this.onDeletePlace(place)}
                                            >Delete
                                        </Button>
                                    </List.Item>
                                )}
                            />
                            
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

                {
                    mapApiLoaded &&
                        <Sider width={200}
                                theme={"light"}>
                            <div className="main-trip-name">Trip Name {this.state.curTrip.name}</div>
                            <hr/>
                            <DailyPlan />
                        </Sider>
                }
                

            </Layout>
        );
    }
}

export default Main;