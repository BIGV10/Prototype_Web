import http from "../http-common";

class RequestDataService {
  getAll() {
    return http.get(`/requests`);
  }

  get(id) {
    return http.get(`/requests/${id}`);
  }

  create(data) {
    return http.post(`/requests`, data);
  }

  update(id, data) {
    return http.put(`/requests/${id}`, data);
  }

  addEquipmentToRequest(requestId, equipmentId) {
    return http.post(`/requests/${requestId}/equipment/${equipmentId}`);
  }

  delete(id) {
    return http.delete(`/requests/${id}`);
  }

  deleteAll() {
    return http.delete(`/requests`);
  }
}

export default new RequestDataService();
