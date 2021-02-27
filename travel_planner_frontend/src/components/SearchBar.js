import { Input, Space } from 'antd';
import { API_KEY } from '../constants'
import axios from "axios";

const { Search } = Input;


const SearchBar = (props) => {
        const { destination, addPlaceToTable, clearTable } = props;

        const handleSearch = text => {
			// const searchUrl = `/api/place/search?text=${text}&city=${destination}`
            const searchUrl = `/api/place/textsearch/json?query=${text}&key=${API_KEY}`

            clearTable();

			axios.get(searchUrl)
                .then(res => {
                    const placeList = res.data.results;
                    placeList.forEach((ele, idx) => {
                        const placedata = {
                            key:idx,
                            name: ele.name,
                            rating: ele.rating,
                            address:ele.formatted_address,
                            lat: ele.geometry.location.lat,
                            lng: ele.geometry.location.lng,
                            viewport: ele.geometry.viewport,
                            id: ele.place_id
                        }
                        addPlaceToTable(placedata);
                    });
                    
                })
        }
              
        return (
        <Space direction="vertical">
            <Search placeholder="input search text" onSearch={handleSearch} enterButton />
        </Space>
        )
}

export default SearchBar
