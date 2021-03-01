import React, { Component } from "react";
import { Tabs, DatePicker, Drawer, Button } from 'antd';

const { RangePicker } = DatePicker;

const { TabPane } = Tabs;

export default class DailyPlan extends Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            startDate: new Date(2020, 2, 1),
            numOfDays: 3,
        };
    }



    render() {
        const { visible, startDate, numOfDays } = this.state;

        const showDrawer = () => {
            this.setState({ visible: true });
        };

        const onClose = () => {
            this.setState({ visible: false });
        };

        return (
            <div>
                <Button type="primary" onClick={showDrawer}> Daily Plan </Button>

                <Drawer
                    title="Daily Plan"
                    width={500}
                    placement="right"
                    closable={true}
                    onClose={onClose}
                    visible={visible}
                >
                    <Tabs defaultActiveKey="1" tabPosition="left" style={{ height: 500, width: 300 }}>
                        {[...Array.from({ length: 10 }, (v, i) => i)].map(i => (
                            <TabPane tab={`Day ${i}`} key={i}>
                                Content of day {i}

                            </TabPane>
                        ))}
                    </Tabs>


                    <Button type="primary">Save Plan</Button>
                </Drawer>
            </div>
        );
    }
}