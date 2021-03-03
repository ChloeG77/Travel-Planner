import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';
import { API_KEY } from '../constants';
import axios from "axios";
import SearchBar from './SearchBar';
import { Table, Button, List, Layout, Spin, Menu } from 'antd';
import { DownOutlined, ArrowRightOutlined } from '@ant-design/icons';

import DailyPlan from './DailyPlan'


const { Sider, Content } = Layout;
const { SubMenu } = Menu;


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
        toAddPlace: [],
        placeInPlanner: [],
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
        ]
    };


    componentWillMount() {
        const { destination, isLoggedIn, token, tripInfo } = this.props;

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


        this.setState({
            dayPlan: Array.from(Array(3)).forEach((x, i) => i)
        })
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
        const { placedata, toAddPlace } = this.state;
        this.setState({
            toAddPlace: [...toAddPlace, placedata[key]],
            selectedId: [...this.state.selectedId, placedata[key].id]
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
            lng: [...this.state.lng, lng]
        });
    };

    addToPlanner = (e, place) => {
        console.log('click', e.key);
        console.log('click', place);
        this.addMarker(place);
        this.setState({
            placeInPlanner: [...this.state.placeInPlanner, { "day": e.key, "place": place }]
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
            mapApiLoaded, mapInstance, mapApi, lat, lng, placedata, columns, isLoading, placeInPlanner
        } = this.state;

        // Generate Dropdown menu on AddToPlanner button according to trip days
        const numDays = this.props.tripInfo.numDays;
        // const menu = (
        //     <Menu onClick={this.handleMenuClick}>
        //         {[...Array.from({ length: numDays }, (v, i) => i + 1)]
        //             .map(i => { return <Menu.Item key={i} icon={<ArrowRightOutlined />}>Day {i}</Menu.Item> })}
        //     </Menu>
        // );

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
                            toggleLoading={this.toggleLoading}
                            token={this.props.token} />
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
                                    pagination={{ pageSize: 3 }}
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
                                    <Menu onClick={(e) => this.addToPlanner(e, place)}>
                                        <SubMenu title="Add to planner">
                                            {[...Array.from({ length: numDays }, (v, i) => i + 1)]
                                                .map(i => { return <Menu.Item key={i} icon={<ArrowRightOutlined />}>Day {i}</Menu.Item> })}
                                        </SubMenu>
                                    </Menu>
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
                    <Sider width={400}
                        theme={"light"}>
                        <DailyPlan tripData={this.props.tripInfo} placeInPlanner={placeInPlanner} />
                    </Sider>
                }


            </Layout>
        );
    }
}

export default Main;