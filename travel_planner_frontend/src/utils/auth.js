const SERVER_ORIGIN = '';

const loginUrl = `${SERVER_ORIGIN}/api/auth/login`;

export const login = (credential) => {
  return fetch(loginUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify(credential)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to log in');
    }

    return response.json();
  })
}

const registerUrl = `${SERVER_ORIGIN}/api/auth/signup`;

export const register = (data) => {
  return fetch(registerUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to register');
    }
  })
}



const addTripUrl = `${SERVER_ORIGIN}/api/trip/newTrip`;

export const newTrip = (data, token) => {
  return fetch(addTripUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(data)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to add trip');
    }
    return response.json();
  })
}


const logoutUrl = `${SERVER_ORIGIN}/logout`;

export const logout = () => {
  return fetch(logoutUrl, {
    method: 'POST',
    credentials: 'include',
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to log out');
    }
  })
}


const deleteTripUrl = `${SERVER_ORIGIN}/api/trip/deleteTrip?tripId=`;

export const deleteTrip = (data, token) => {
  return fetch(`${deleteTripUrl}${data.tripId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(data)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to delete trip');
    }
    return response.json();
  })
}

// const deleteTripUrl = `${SERVER_ORIGIN}/api/trip/deleteTrip?tripId=`;


const addPlaceToTripUrl = `${SERVER_ORIGIN}/api/trip/place?`;

export const addPlaceToTrip = (tripId, placeId, token) => {
  return fetch(`${addPlaceToTripUrl}tripId=${tripId}&&placeId=${placeId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(null)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to add place to trip');
    }
    return response.json();
  })
}



const deletePlaceFromTripUrl = `${SERVER_ORIGIN}/api/trip/place?`;

export const deletePlaceFromTrip = (tripId, placeId, token) => {
  return fetch(`${deletePlaceFromTripUrl}tripId=${tripId}&&placeId=${placeId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(null)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to delete place from trip');
    }
    return response.json();
  })
}


const addPlaceToDayUrl = `${SERVER_ORIGIN}/api/trip/day/place?`;

export const addPlaceToDay = (dayId, placeId, token) => {
  return fetch(`${addPlaceToDayUrl}dayId=${dayId}&&placeId=${placeId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(null)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to add place to day');
    }
    return response.json();
  })
}


const deletePlaceFromDayUrl = `${SERVER_ORIGIN}/api/day/place?`;

export const deletePlaceFromDay = (dayId, placeId, token) => {
  return fetch(`${deletePlaceFromDayUrl}dayId=${dayId}&&placeId=${placeId}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(null)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to delete place from day');
    }
    return response.json();
  })
}


const getAllCitiesUrl = `${SERVER_ORIGIN}/api/city/getAllCities`;

export const getAllCities = (token) => {
  return fetch(`${getAllCitiesUrl}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    }
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to get all cities');
    }
    return response.json();
  })
}

const getRouteUrl = `${SERVER_ORIGIN}/api/trip/day/route?`;

export const getRoute = (dayId, token) => {
  return fetch(`${getRouteUrl}dayId=${dayId}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to get route');
    }
    console.log("getRoute(): ")
    return response.json();
  })
}

const genRouteUrl = `${SERVER_ORIGIN}/api/trip/day/route/gen?`;

export const genRoute = (dayId, token) => {
  return fetch(`${genRouteUrl}dayId=${dayId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token,
    },
    body: JSON.stringify(null)
  }).then((response) => {
    if (response.status !== 200) {
      throw Error('Fail to generate route');
    }
    console.log("Route generated");
    return response.json();
  })
}