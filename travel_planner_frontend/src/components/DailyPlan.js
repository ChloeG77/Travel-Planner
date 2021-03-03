import React, { Component } from "react";
import { Tabs, DatePicker, Drawer, Button, List } from 'antd';

const { TabPane } = Tabs;

export default class DailyPlan extends Component {

    dayFilter = (placeInPlanner, i) => {
        return placeInPlanner.filter(a => parseInt(a.day) === i)
    }

    render() {
        const { tripData, placeInPlanner } = this.props

        console.log(this.dayFilter(placeInPlanner, 1))
        return (
            <div>

                <Tabs defaultActiveKey="1" tabPosition="left" style={{ height: 400, width: 300 }}>

                    {[...Array.from({ length: tripData.numDays }, (v, i) => i + 1)]
                        .map(i => {
                            return <TabPane tab={"Day " + i} key={i}>
                                <List>
                                    {this.dayFilter(placeInPlanner, i)
                                        .map(p => {
                                            return <List.Item key={p.place.key}>{p.place.name}</List.Item>
                                        })
                                    }
                                </List>
                            </TabPane>
                        })}

                </Tabs>


                <Button type="primary">Save Plan</Button>

            </div>
        );
    }
}