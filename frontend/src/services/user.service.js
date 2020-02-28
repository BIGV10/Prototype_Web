import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:9000/api/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'equipments');
  }

  getUserBoard() {
    return axios.get(API_URL + 'equipments', {headers: authHeader()});
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'requests', {headers: authHeader()});
  }

  getAdminBoard() {
    return axios.get(API_URL + 'requests', {headers: authHeader()});
  }
}

export default new UserService();
