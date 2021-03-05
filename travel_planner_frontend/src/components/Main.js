import React, { Component } from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from './Marker';
import { API_KEY } from '../constants';
import axios from "axios";
import SearchBar from './SearchBar';
import { Table, Button, List, Layout, Spin, message, Menu } from 'antd';
import DailyPlan from './DailyPlan';
import { addPlaceToTrip, deletePlaceFromTrip, addPlaceToDay, deletePlaceFromDay } from '../utils/auth.js';
import { ArrowRightOutlined } from '@ant-design/icons';

const { Sider, Content } = Layout;
const { SubMenu } = Menu;

class Main extends Component {
  constructor(props) {
    super(props);
    let tempSelected = [];
    this.props.curTrip.places.map(item => tempSelected.push(item.placeId));
    this.state = {
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
      placeInPlanner: {},
      selectedId: tempSelected,
      columns: [
        { title: 'Name', dataIndex: 'name', key: 'name' },
        { title: 'Rating', dataIndex: 'rating', key: 'rating' },
        {
          title: 'Action',
          dataIndex: '',
          key: 'x',
          render: (record) => <Button type="primary" onClick={() => this.insertToAdd(record.key)} disabled={this.state.selectedId.includes(this.state.placedata[record.key].placeId)}>Add</Button>,
        }
      ],
      curTrip: this.props.curTrip
    };
  }



  componentWillMount() {
    const { isLoggedIn, token, curTrip } = this.props;
    const destination = curTrip.cities[0];
    console.log("will mount" + destination)

    const url = `/api/place/searchByName?text=${destination}&city=${destination}`;

    // const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4bWEiLCJpYXQiOjE2MTQxOTM3MjEsImV4cCI6MTYxNDY5NDM3NX0.42nGjPcsd94jhiQKc3uuW5srnKicH0G8h6-NpkLKCHhZW6AXC9h914SwiHP5m2YM0kly0OeWx-qMIq2skcvkXw";

    // axios.get(url, {
    //     headers: {
    //         'Authorization': 'Bearer ' + token
    //     }
    // })
    //     .then(res => {
    //         const curPlace = res.data[0];
    //         console.log("curPlace", curPlace);
    //         // console.log(res.data);
    //         this.setMapCenter(curPlace);
    //     })
    //     .catch(e => {
    //         console.log(e);
    //     });

    this.setMapCenter(destination);


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
    const { placedata, toAddPlace, curTrip } = this.state;
    console.log(placedata);
    this.setState({
      toAddPlace: [...toAddPlace, placedata[key]],
      selectedId: [...this.state.selectedId, placedata[key].placeId]
    })
    // curTrip.tripId, placedata[key].id
    addPlaceToTrip(curTrip.tripId, placedata[key].placeId, this.props.token)
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
      lng: [...this.state.lng, lng]
    });
  };

  addToPlanner = (e, place) => {
    // this.onAddPlaceToDay(e.key, place)

    this.addMarker(place);

    const dayId = e.key;
    this.onAddPlaceToDay(dayId, place);

    if (dayId in this.state.placeInPlanner) {
      this.setState({
        placeInPlanner: {
          ...this.state.placeInPlanner,
          [dayId]: [...this.state.placeInPlanner[dayId], place]
        }
      })
    } else {
      this.setState({
        placeInPlanner: {
          ...this.state.placeInPlanner,
          [dayId]: [place]
        }
      })
    }
  }

  onAddPlaceToDay = (dayId, place) => {
    const { placedata, toAddPlace, curTrip } = this.state;


    addPlaceToDay(dayId, place.placeId, this.props.token)
      .then((data) => {
        message.success('add place to trip');
        console.log("addplace", data.places);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })

    console.log(curTrip)
  }

  onDeletePlaceFromDay = (dayId, place) => {
    deletePlaceFromDay(dayId, place.placeId, this.props.token)
      .then((data) => {
        message.success('delete place');
        console.log("deleteplace", data.places);
      }).catch((err) => {
        console.log(err);
        message.error(err.message);
      })
  }

  removePlace = (place) => {

  }
  onDeletePlaceFromTrip = (e, place) => {
    e.stopPropagation();

    // this.addMarker(place);
    console.log(place);
    let list = this.state.toAddPlace.filter(item => item.placeId !== place.placeId);
    let tempSelected = this.state.selectedId.filter(item => item !== place.placeId);

    this.setState({
      toAddPlace: list,
      selectedId: tempSelected
    })
    deletePlaceFromTrip(this.state.curTrip.tripId, place.placeId, this.props.token)
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


  placeExistInPlanner = (place) => {
    for (let key in this.state.placeInPlanner) {
      if (this.state.placeInPlanner[key].includes(place)) {
        return true;
      };
    }
  };

  onDeletePlaceFromPlanner = (e, place) => {
    e.stopPropagation();

    this.onDeletePlaceFromDay(e.dayId, place.placeId);

    for (let key in this.state.placeInPlanner) {
      if (this.state.placeInPlanner[key].includes(place)) {
        let result = this.state.placeInPlanner[key].filter((p) => p.placeId !== place.placeId)
        this.setState({
          placeInPlanner: {
            ...this.state.placeInPlanner,
            [key]: result
          }
        })
      }
    }
  }


  render() {
    const {
      mapApiLoaded, mapInstance, mapApi, lat, lng, placedata, columns, isLoading, placeInPlanner
    } = this.state;

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
              destination={this.props.curTrip.cities[0].name} addPlaceToTable={this.addPlaceToTable}
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
                <List.Item>
                  <List.Item.Meta
                    title={<p>{place.name}</p>}
                  />
                  <Menu
                    onClick={(e) => this.addToPlanner(e, place)}
                  >
                    <Button type="primary"
                      onClick={(e) => this.onDeletePlaceFromTrip(e, place)}>
                      Delete
                                        </Button>
                    <SubMenu title="Add to planner" disabled={this.placeExistInPlanner(place)} >
                      { this.state.curTrip.days.map((d, i) => { return <Menu.Item key={d.dayId} icon={<ArrowRightOutlined />}>Day {i + 1}</Menu.Item> })}
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
            <div className="main-trip-name">Trip Name {this.state.curTrip.name}</div>
            <DailyPlan curTrip={this.state.curTrip} placeInPlanner={placeInPlanner} onDelete={this.onDeletePlaceFromPlanner} token={this.props.token}/>
          </Sider>
        }


      </Layout>
    );
  }
}

export default Main;