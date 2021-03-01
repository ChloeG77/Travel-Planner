import { Input, Space, Spin } from 'antd';
import axios from "axios";

const { Search } = Input;


const SearchBar = (props) => {
    const { destination, addPlaceToTable, clearTable, token, toggleLoading } = props;


    const handleSearch = text => {

        const searchUrl = `/api/place/searchByName?text=${text}&city=${destination}`

        // const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4bWEiLCJpYXQiOjE2MTQxOTM3MjEsImV4cCI6MTYxNDY5NDM3NX0.42nGjPcsd94jhiQKc3uuW5srnKicH0G8h6-NpkLKCHhZW6AXC9h914SwiHP5m2YM0kly0OeWx-qMIq2skcvkXw";

        clearTable();

        toggleLoading();


        axios.get(searchUrl, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(res => {
                const placeList = res.data;
                placeList.forEach((ele, idx) => {
                    const placedata = {
                        key: idx,
                        name: ele.name,
                        rating: ele.rating,
                        address: ele.address,
                        lat: ele.latitude,
                        lng: ele.longitude,
                        // viewport: ele.geometry.viewport,
                        id: ele.placeId
                    }
                    addPlaceToTable(placedata);
                });
                toggleLoading();
            })
    }

    return (
        <Space direction="vertical">
            <Search style={{ width: 500 }} placeholder="input search text" onSearch={handleSearch} enterButton />
        </Space>
    )
}

export default SearchBar
