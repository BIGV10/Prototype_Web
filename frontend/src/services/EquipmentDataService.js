import http from "../http-common";
import authHeader from './AuthHeader';
// import axios from "axios";

// const API_URL = 'http://localhost:9000/api/';

class EquipmentDataService {
  getAll() {
    return http.get(`/equipments`, {headers: authHeader()});
  }

  get(id) {
    return http.get(`/equipments/${id}`, {headers: authHeader()});
  }

  create(data) {
    return http.post(`/equipments`, data, {headers: authHeader()});
  }

  update(id, data) {
    return http.put(`/equipments/${id}`, data, {headers: authHeader()});
  }

  delete(id) {
    return http.delete(`/equipments/${id}`, {headers: authHeader()});
  }

  deleteAll() {
    return http.delete(`/equipments`, {headers: authHeader()});
  }

  findByBarcode(barcode) {
    return http.get(`/equipments?barcode=${barcode}`, {headers: authHeader()});
  }

  // getUserBoard() {
  //   return axios.get(API_URL + 'equipments', {headers: authHeader()});
  // }
}

export default new EquipmentDataService();
