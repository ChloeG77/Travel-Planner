import React, { useState, useEffect } from 'react'
import { Button, Drawer, List, Checkbox, Avatar, message, Modal} from 'antd';
import { StarFilled, MinusOutlined } from '@ant-design/icons';
import satellite from "../assets/images/satellite.svg";
import { deleteTrip } from '../utils/auth';
import { useHistory } from 'react-router';

const Trips = (props) => {
    // const [trips, setTrips] = useState(props.trips);
    const history = useHistory();

    const [displayDrawer, setDisplayDrawer] = useState(false);
    const [displayModal, setDisplayModal] = useState(false);
    const { trips, token, onLoggedInStatus, onCurTrip } = props;

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
       deleteTrip(e, token)
        .then((data) => {
            // console.log(data);
            message.success(`success delete trip ${e.name}`);
            const newData = {
                accessToken: token,
                trips: data.trips,
            }
            onLoggedInStatus(true, newData);
          }).catch((err) => {
            console.log(err);
            message.error(err.message);
          })
          setDisplayModal(false);

    }


    const onPlan = (e) => {
        e.startCity = "Ottawa";
        history.push("planner");
        onCurTrip(e);
    }

    return (
        <div>
        <Button type='primary' shape="round" onClick={onFavoriteClick} icon={<StarFilled />}>
        My Trips
        </Button>

        <Drawer className="trips-drawer"
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
            dataSource={trips}
            renderItem={item => { 
                console.log(item);
                return (
                <List.Item
                    actions={[
                    <Button type="default"  size="small" danger onClick={()=> onDelete(item)} icon={<MinusOutlined />} ></Button>,
                    <Button type="primary" onClick={() => onPlan(item)}>Plan</Button>,            
                    // <Modal title="Delete Trip" visible={displayModal} onOk={()=> onDelete(item)} onCancel={onModalClose}>
                    // <p>Do you confirm to delete trip {item.name}</p></Modal>
                    
                ]}
                >
                <List.Item.Meta
                avatar={<Avatar size={40} src={satellite} />}
                title={<p>{item.name}</p>}
                description={`ID: ${item.tripId}, Start Date: ${item.startDate}, Days: ${item.numDays}, City: ${item.cities[0].name}, Trip Type: ${item.type}`}
                />
            </List.Item>
            )}}
        />
        </Drawer>      
        </div>
    )
}

export default Trips;
