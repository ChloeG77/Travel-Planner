// import React, { Component } from 'react';
// import styled from 'styled-components';



<<<<<<< HEAD

const Wrapper = styled.div`
  position: relative;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;
  text-align:center;
`;
=======
// const Wrapper = styled.div`
//   position: relative;
//   align-items: center;
//   justify-content: center;
//   width: 100%;
//   padding: 20px;
//   text-align:center;
// `;
>>>>>>> 45dde9cb81677ecff814c2dbe8fb90703b83b26f

// class AutoComplete extends Component {
//     constructor(props) {
//         super(props);
//     }

//     componentDidMount({ map, mapApi } = this.props) {
//         const options = {
//             // restrict your search to a specific type of result
//             // types: ['address'],
//             // restrict your search to a specific country, or an array of countries
//             // componentRestrictions: { country: ['gb', 'us'] },
//         };
//         this.autoComplete = new mapApi.places.Autocomplete(
//             this.searchInput,
//             options,
//         );
//         this.autoComplete.addListener('place_changed', this.onPlaceChanged);
//         this.autoComplete.bindTo('bounds', map);
        
//     }

//     componentWillUnmount({ mapApi } = this.props) {
//         mapApi.event.clearInstanceListeners(this.searchInput);
//     }

//     onPlaceChanged = ({ map, addplace } = this.props) => {
//         const place = this.autoComplete.getPlace();

//         if (!place.geometry) return;
//         if (place.geometry.viewport) {
//             map.fitBounds(place.geometry.viewport);
//         } else {
//             map.setCenter(place.geometry.location);
//             map.setZoom(17);
//         }

//         addplace(place);
//         this.searchInput.blur();
//     };

    

    clearSearchBox = () => {
        this.searchInput.value = '';
    }

//     render() {
//         return (
//             <Wrapper>
//                 <input
//                     className="search-input"
//                     ref={(ref) => {
//                         this.searchInput = ref;
//                     }}
//                     type="text"
//                     onFocus={this.clearSearchBox}
//                     placeholder="Enter a location"
//                 />
//             </Wrapper>
//         );
//     }
// }

// export default AutoComplete;