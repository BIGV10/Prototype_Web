<template>
    <div class="list row" v-if="currentRequest">
        <div class="edit-form col-4">
            <h4>Изменение заявки</h4>
            <form>
                <div class="form-group">
                    <label for="id">ID</label>
                    <input class="form-control" disabled id="id"
                           type="text"
                           v-model="currentRequest.id"
                    />
                </div>
                <div class="form-group">
                    <label for="name">Статус</label>
                    <input class="form-control" id="name" type="text"
                           v-model="currentRequest.status"
                    />
                </div>
                <div class="form-group">
                    <label for="comment">Комментарий</label>
                    <textarea class="form-control" id="comment" rows="3" type="text"
                              v-model="currentRequest.comment"
                    />
                </div>
            </form>

            <div class="d-flex justify-content-between bd-highlight">
                <!--                <button @click="deleteRequest"-->
                <!--                        class="btn btn-danger"-->
                <!--                >Удалить-->
                <!--                </button>-->

                <button @click="updateRequest" class="btn btn-success"
                        type="submit"
                >Обновить
                </button>
            </div>

            <p/>
            <p>
                {{ message }}
            </p>
        </div>
        <div class="col-8">
            <RequestEquipmentList v-bind:request="currentRequest"/>
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
        </div>
    </div>

    <div v-else>
        <Loader/>
    </div>
</template>

<script>
    import RequestDataService from "../../services/RequestDataService";
    import Loader from "../Loader";
    import RequestEquipmentList from "./RequestEquipmentList";
    import EquipmentDataService from "../../services/EquipmentDataService";

    export default {
        name: "request",
        data() {
            return {
                currentRequest: null,
                currentEquipment: null,
                message: '',
                barcode: "",
                failedSearch: false
            };
        },
        mounted() {
            this.message = '';
            this.getRequest(this.$route.params.id);
        },
        methods: {
            getRequest(id) {
                RequestDataService.get(id)
                    .then(response => {
                        this.currentRequest = response.data;
                        this.currentEquipment = response.data.equipment;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            updateRequest() {
                this.currentEquipment = this.currentRequest.currentEquipment;
                RequestDataService.update(this.currentRequest.id, this.currentRequest)
                    .then(response => {
                        console.log(response.data);
                        this.message = 'Заявка была обновлена!';
                        for (let i = 0; i < this.currentRequest.equipment.length; i++) {
                            RequestDataService.addEquipmentToRequest(this.currentRequest.id, this.currentRequest.equipment[i].id);
                            console.log(this.currentRequest.equipment[i].id);
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            searchBarcode() {
                EquipmentDataService.findByBarcode(this.barcode)
                    .then(response => {
                        let index = this.currentEquipment.findIndex(x => x.barcode === response.data.barcode);
                        if (index === -1)
                            this.currentEquipment.push(response.data);
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
            //
            // deleteRequest() {
            //     RequestDataService.delete(this.currentRequest.id)
            //         .then(response => {
            //             console.log(response.data);
            //             this.$router.push({name: "requests"});
            //         })
            //         .catch(e => {
            //             console.log(e);
            //         });
            // }
        },

        components: {
            RequestEquipmentList,
            Loader
        }
    };
</script>

<style>
</style>
