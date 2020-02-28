import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:9000/api",
    // baseURL: "http://bigv.ddns.net:9000/api",
    headers: {
        "Content-type": "application/json"
    }
});