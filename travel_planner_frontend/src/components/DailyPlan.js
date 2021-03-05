import React, { Component } from "react";
import { Tabs, Button, List, message, Menu } from 'antd';
import { genRoute, getRoute } from '../utils/auth.js';
import { AppstoreOutlined, MailOutlined, SettingOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;

const { TabPane } = Tabs;

export default class DailyPlan extends Component {
    constructor(props) {
        super(props);
        this.state = {
            dayPlan: []
        }
    }

    onGenRoute = (curTrip) => {
        curTrip.days.map((d) => {
            genRoute(d.dayId, this.props.token)
                .then((data) => {
                    console.log("generate route", data);
                }).catch((err) => {
                    console.log(err);
                    message.error(err.message);
                })
        })
        message.success('Route successfully generated!');
        console.log(curTrip)
    }



    onGetRoute = (curTrip) => {
        curTrip.days.map((d) => {
            getRoute(d.dayId, this.props.token)
                .then((data) => {
                    message.success('Route retrived!');
                    console.log("getRoute()", data);
                    this.setState({
                        dayPlan: [...this.state.dayPlan, { [d.dayId]: data.route }]
                    })
                }).catch((err) => {
                    console.log(err);
                    message.error(err.message);
                })
        })
    }

    render() {
        const { curTrip, placeInPlanner, onDelete } = this.props

        return (
            <div>
                <Tabs defaultActiveKey="1" tabPosition="left" style={{ height: 300, width: 300 }}>

                    {curTrip.days.map((d, i) => {
                        return <TabPane tab={"Day " + (i + 1)} key={d.dayId}>
                            <List>
                                {
                                    d.dayId in placeInPlanner &&
                                    placeInPlanner[d.dayId].map(place => {
                                        return <List.Item key={place.key}>
                                            {place.name}
                                            <Button type="primary" onClick={(e) => onDelete(e, place)}>Delete</Button>
                                        </List.Item>

                                    })
                                }
                            </List>
                        </TabPane>
                    })}



                </Tabs>


                <Button
                    type="primary"
                    onClick={() => this.onGenRoute(curTrip)}
                >Generate Plan</Button>

                <Button type="primary"
                    onClick={() => this.onGetRoute(curTrip)}
                    style={{ marginLeft: 20 }}>Show Plan</Button>


                {   this.state.dayPlan.map((d, i) => {
                    return <List size="small" header={"Day " + (i + 1)} key={i}>
                        {
                            Object.entries(d)[0][1].map((p,k) => {
                                return <List.Item>Stop {k + 1}: {p.place.name}</List.Item>
                            })
                        }
                    </List>
                })
                }

            </div>
        );
    }
}