import { Input, Space } from 'antd';
import { API_KEY } from '../constants'
import axios from "axios";

const { Search } = Input;


const SearchBar = (props) => {
    const { destination, addPlaceToTable, clearTable } = props;

    const handleSearch = text => {
        // const searchUrl = `/api/place/search?text=${text}&city=${destination}`

        const searchUrl = `/api/place/searchByName?text=${text}&city=${destination}`

        clearTable();

        const token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4bWEiLCJpYXQiOjE2MTQzODMxMjgsImV4cCI6MTYxNDg4Mzc4Mn0.7X82EB-u1TOVl9auv0ZvIwwHzsKQxBTUp9qFFwQbxYwXoUUCQ00vUT_tVyXxdr0ZQ31YZ0vibTYA5LgR20Wnvw";

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

            })
    }

    return (
        <Space direction="vertical">
            <Search style={{ width: 500 }} placeholder="input search text" onSearch={handleSearch} enterButton />
        </Space>
    )
}

export default SearchBar
