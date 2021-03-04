import React, { Component } from "react";
import { Tabs, Button, List } from 'antd';

const { TabPane } = Tabs;

export default class DailyPlan extends Component {



    render() {
        const { curTrip, placeInPlanner, onDelete } = this.props

        return (
            <div>
                <Tabs defaultActiveKey="1" tabPosition="left" style={{ height: 400, width: 300 }}>

                    {[...Array.from({ length: curTrip.numDays }, (v, i) => i + 1)]
                        .map(i => {
                            return <TabPane tab={"Day " + i} key={i}>
                                <List>
                                    {
                                        ["day" + i] in placeInPlanner &&
                                        placeInPlanner["day" + i].map(place => {
                                            return <List.Item key={place.key}>
                                                {place.name}
                                                <Button type="primary" onClick={(e) => onDelete(e,place)}>Delete</Button>
                                            </List.Item>

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