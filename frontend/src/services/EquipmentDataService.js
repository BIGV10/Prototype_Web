import http from "../http-common";
import authHeader from '../services/auth-header';

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
}

export default new EquipmentDataService();
