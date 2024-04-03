import axios from "axios";

axios.defaults.baseURL = "http://localhost:8080";
axios.defaults.headers.post["Content-type"] = "application/json";

export const getAccessToken = () => {
    return window.localStorage.getItem("access_token");
};

export const setAccessToken = (access_token) => {
    window.localStorage.setItem("access_token", access_token);
};

export const clearAccessToken = () => { 
    localStorage.removeItem("access_token");
};

export const getUserId = () => {
    return window.localStorage.getItem("user_id");
};

export const setUserId = (userId) => {
    return window.localStorage.setItem("user_id", user_id);
};

export const request = (method, url, data) => {
    let headers = {};
    if (getAccessToken()) {
        headers = { Authorization: `Bearer ${getAccessToken()}` };
    }
    return axios({
        method,
        headers: headers,
        url,
        data,
    });
};
