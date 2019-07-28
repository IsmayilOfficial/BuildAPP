import axios from 'axios';

let username = '';
let headers = {};

export const HTTP = (otherHeader) => {
    return axios.create({
        baseURL: 'http://localhost:8100/api/',
        headers: otherHeader || headers
    })
}

export const SetHeader = (param) => {
    headers = param
}

export const SetUser = (param) => {
    username = param
}

// {
//     Authorization: 'Bearer {token}'
//   }