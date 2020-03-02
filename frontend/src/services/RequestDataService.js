import http from "../http-common";
import authHeader from '../services/auth-header';

class RequestDataService {
  getAll() {
    return http.get(`/requests`, {headers: authHeader()});
  }

  get(id) {
    return http.get(`/requests/${id}`, {headers: authHeader()});
  }

  create(data) {
    return http.post(`/requests`, data, {headers: authHeader()});
  }

  update(id, data) {
    return http.put(`/requests/${id}`, data, {headers: authHeader()});
  }

  addEquipmentToRequest(requestId, equipmentId) {
    return http.post(`/requests/${requestId}/equipment/${equipmentId}`, {headers: authHeader()});
  }

  delete(id) {
    return http.delete(`/requests/${id}`, {headers: authHeader()});
  }

  deleteAll() {
    return http.delete(`/requests`, {headers: authHeader()});
  }
}

export default new RequestDataService();
