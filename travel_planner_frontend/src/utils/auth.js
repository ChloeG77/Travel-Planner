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
