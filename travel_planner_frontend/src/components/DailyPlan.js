import React, { Component } from "react";
import { Tabs, DatePicker, Drawer, Button } from 'antd';

const { RangePicker } = DatePicker;

const { TabPane } = Tabs;

export default class DailyPlan extends Component {
    constructor(props) {
        super(props);
        this.state = {

        };
    }



    render() {
        const { startDate, numDays } = this.props.tripData;

        return (
            <div>

                <Tabs defaultActiveKey="1" tabPosition="left" style={{ height: 400, width: 300 }}>
                    {[...Array.from({ length: 30 }, (v, i) => i)].map(i => (
                        <TabPane tab={`Day ${i}`} key={i}>
                            Content of day {i}

                        </TabPane>
                    ))}
                </Tabs>


                <Button type="primary">Save Plan</Button>

            </div>
        );
    }
}