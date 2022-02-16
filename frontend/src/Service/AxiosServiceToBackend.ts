import ILoginData from "../Models/ILoginData";
import axios from "axios";

export const TOKEN_STORAGE_KEY = 'MY_TOKEN';
const config = {headers:{'Authorization': 'Bearer '+localStorage.getItem(TOKEN_STORAGE_KEY) || ""}}

export const postLogin = (loginData:ILoginData) =>
    axios.post("/auth/login", loginData)

export const getLifetime = () =>
    axios.get('/api/lifetime', config)
        .then(response => response.data)

export const getNonSmoked = () =>
    axios.get('/api/nonsmoked', config)
        .then(response => response.data)

export const getMoneySaved = () =>
    axios.get('/api/savedmoney', config)
        .then(response => response.data)

export const getTimeSmokeFree = () =>
    axios.get('/api/timesmokefree', config)
        .then(response => response.data)