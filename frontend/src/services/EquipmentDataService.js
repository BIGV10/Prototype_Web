import http from "../http-common";

class EquipmentDataService {
  getAll() {
    return http.get(`/equipments`);
  }

  get(id) {
    return http.get(`/equipments/${id}`);
  }

  create(data) {
    return http.post(`/equipments`, data);
  }

  update(id, data) {
    return http.put(`/equipments/${id}`, data);
  }

  delete(id) {
    return http.delete(`/equipments/${id}`);
  }

  deleteAll() {
    return http.delete(`/equipments`);
  }

  findByBarcode(barcode) {
    return http.get(`/equipments?barcode=${barcode}`);
  }
}

export default new EquipmentDataService();
