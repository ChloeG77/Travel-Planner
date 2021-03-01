import React, { useState, useEffect } from 'react'
import { Button, Drawer, List, Checkbox, Avatar, message, Modal} from 'antd';
import { StarFilled, MinusOutlined } from '@ant-design/icons';
import satellite from "../assets/images/satellite.svg";
import { deleteTrip } from '../utils/auth';

const Trips = (props) => {
    // const [trips, setTrips] = useState(props.trips);

    const [displayDrawer, setDisplayDrawer] = useState(false);
    const [displayModal, setDisplayModal] = useState(false);

    const onFavoriteClick = () => {
        setDisplayDrawer(true);
    }
    const onDrawerClose = () => {
        setDisplayDrawer(false);
    }

    const onModalClick = () => {
        setDisplayModal(true);
    }

    const onModalClose = () => {
        setDisplayModal(false)
    }

    const onDelete = (e) => {
        console.log('delete', e);
       deleteTrip(e, props.token)
        .then((data) => {
            console.log(data);
            message.success(`success delete trip ${e.name}`);
            const newData = {
                accessToken: props.token,
                trips: data.trips,
            }
            props.onLoggedInStatus(true, newData);
          }).catch((err) => {
            console.log(err);
            message.error(err.message);
          })
    }


    const onPlan = (e) => {
        console.log('plan', e);
    }

    return (
        <>
        <Button type='primary' shape="round" onClick={onFavoriteClick} icon={<StarFilled />}>
        My Trips
        </Button>

        <Drawer
        title="My Trips"
        placement="right"
        width={520}
        height={100}
        visible={displayDrawer}
        onClose={onDrawerClose}
        >
        <List
            className="trip-list"
            itemLayout="horizontal"
            size="small"
            dataSource={props.trips}
            renderItem={item => (
            <List.Item
                actions={[
                <Button type="default"  size="small" danger onClick={onModalClick} icon={<MinusOutlined />} ></Button>
              , <Button type="primary" onClick={() => onPlan(item)}>Plan</Button>]}
            >
            <Modal title="Delete Trip" visible={displayModal} onOk={()=> onDelete(item)} onCancel={onModalClose}>
            <p>Do you confirm to delete trip {item.name}</p>
            </Modal>
                <List.Item.Meta
                avatar={<Avatar size={40} src={satellite} />}
                title={<p>{item.name}</p>}
                description={`ID: ${item.tripId}, Start Date: ${item.startDate}, Days: ${item.numDays}, City: ${item.cities}, Trip Type: ${item.type}`}
                />
            </List.Item>
            )}
        />
        </Drawer>      
        </>
    )
}

export default Trips;
