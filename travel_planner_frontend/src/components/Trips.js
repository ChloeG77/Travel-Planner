import React, { Component } from 'react'
import { Button, Drawer } from 'antd';
import { StarFilled } from '@ant-design/icons';

class Trips extends Component {
    constructor() {
        super();
        this.state = {
            displayDrawer: false
        }
    }

    onFavoriteClick = () => {
        this.setState({
            displayDrawer: true
        })
    }
    onDrawerClose = () => {
        this.setState({
            displayDrawer: false
        })
    }

    render() {
        return (
            <>
            <Button type='primary' shape="round" onClick={this.onFavoriteClick} icon={<StarFilled />}>
            My Trips
            </Button>
            <Drawer
            title="My Trips"
            placement="right"
            width={520}
            height={100}
            visible={this.state.displayDrawer}
            onClose={this.onDrawerClose}
            >
            </Drawer>      
            </>
        )
    }
}

export default Trips;
