<template>
    <div class="submit-form">
        <div class="list row" v-if="!submitted">
            <div class="col-md-3">
                <form>
                    <div class="form-group">
                        <label for="comment">Комментарий</label>
                        <input
                                class="form-control"
                                id="comment"
                                name="comment"
                                required
                                type="text"
                                v-model="request.comment"
                        >
                    </div>
                    <button @click="saveRequest" class="btn btn-success" type="submit">Отправить</button>
                </form>
            </div>
            <div class="col-md-9">
                <h4>Добавить оборудование</h4>
                <div class="input-group mb-3">
                    <input class="form-control" placeholder="Добавить оборудование по штрихкоду" type="text"
                           v-model="barcode"
                    />
                    <div class="input-group-append">
                        <button @click="searchBarcode" class="btn btn-success"
                                type="button"
                        >
                            Добавить
                        </button>
                    </div>
                </div>
                <div v-if="failedSearch">
                    Оборудования с номером {{ failedBarcode }} нет
                </div>
                <div>
                    <h4>Список оборудования</h4>
                    <strong>Количество: </strong>{{ equipmentList.length }}
                    <table class="table table-sm table-hover table-responsive-sm mt-2">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Штрихкод</th>
                            <th scope="col">Комментарий</th>
                            <th scope="col">Статус</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr :key="equipment.barcode"
                            class=""
                            v-for="equipment in equipmentList"
                        >
                            <th class="align-middle">{{ equipment.id }}</th>
                            <td class="align-middle">{{ equipment.barcode }}</td>
                            <td class="align-middle">{{ equipment.name }}</td>
                            <td class="align-middle">{{ equipment.comment }}</td>
                            <td class="d-flex justify-content-end">
                                <a :href="'/equipments/' + equipment.id"
                                   class="btn btn-warning btn-sm"
                                >
                                    <font-awesome-icon icon="edit"/>
                                </a>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-end">
            <span class="d-inline-block" data-toggle="tooltip" tabindex="0" title="Отключено">
      <button @click="removeAllEquipments" class="mt-2 btn btn-danger" disabled>
        Удалить всё
      </button>
      </span>
                    </div>
                </div>
            </div>
        </div>

        <div v-else>
            <h4>Заявка добавлена!</h4>
            <span>ID новой заявки: {{this.newId}}</span>
            <p/>
            <button @click="newRequest" class="btn btn-success mt-2">Добавить ещё</button>
        </div>
    </div>
</template>

<script>
    import RequestDataService from "../../services/RequestDataService";
    import EquipmentDataService from "../../services/EquipmentDataService";

    export default {
        name: "add-request",
        data() {
            return {
                request: {
                    id: null,
                    comment: "",
                    status: 0
                },
                barcode: "",
                equipmentList: [],
                submitted: false,
                newId: null,
                failedSearch: false,
                failedBarcode: "",
                q: null
            };
        },
        methods: {
            saveRequest() {
                var data = {
                    comment: this.request.comment,
                    status: 0,
                };
                RequestDataService.create(data)
                    .then(response => {
                        this.request.id = response.data.id;
                        this.newId = response.data.id;
                        console.log(response.data);
                        this.addEquipment()
                    })
                    .catch(e => {
                        console.log(e);
                    });

                this.submitted = true;
            },

            addEquipment() {
                for (let i = 0; i < this.equipmentList.length; i++) {
                    RequestDataService.addEquipmentToRequest(parseInt(this.newId), parseInt(this.equipmentList[i].id));
                }
            },

            newRequest() {
                this.submitted = false;
                this.request = {};
            },

            searchBarcode() {
                EquipmentDataService.findByBarcode(this.barcode)
                    .then(response => {
                        let index = this.equipmentList.findIndex(x => x.barcode == response.data.barcode);
                        if (index === -1)
                            this.equipmentList.push(response.data);
                        this.failedSearch = false
                    })
                    .catch(e => {

                        if (e.response.status === 404) {
                            this.failedSearch = true;
                            this.failedBarcode = this.barcode
                        } else
                            console.log(e);
                    });
            },

            removeAllEquipments() {
                this.currentEquipment = []
            }
        }
    };
</script>

<style>
</style>
