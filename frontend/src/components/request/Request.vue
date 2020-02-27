<template>
    <div class="list row" v-if="currentRequest">
        <div class="edit-form col-4" >
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

            <h4>Оборудование в заявке</h4>
            <RequestEquipmentList v-bind:request="currentRequest"/>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Добавить оборудование по штрихкоду" v-model="barcode"/>
                <!--                   v-model="barcode"-->
                <!--            />-->
                <div class="input-group-append">
<!--                    TODO Сделать кнопку-->
                    <button class="btn btn-success" type="button">
                        <!--                        @click="searchBarcode"-->
                        <!--                >-->
                        Добавить
                    </button>
                </div>
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

    export default {
        name: "request",
        data() {
            return {
                currentRequest: null,
                currentEquipment: null,
                message: '',
                barcode: null
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
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            updateRequest() {
                this.currentEquipment = this.currentRequest.currentEquipment
                RequestDataService.update(this.currentRequest.id, this.currentRequest)
                    .then(response => {
                        console.log(response.data);
                        this.message = 'Заявка была обновлена!';
                    })
                    .catch(e => {
                        console.log(e);
                    });
                // TODO Не знаю почему не работает добавление оборудования в заявку (может проблема в AXIOS POST)
                for (let i = 0; i < this.currentRequest.equipment.length; i++)
                {
                    setTimeout("alert('Boom!');", 1000);
                    RequestDataService.addEquipmentToRequest(this.currentRequest.id, this.currentRequest.equipment[i].id)
                    console.log(this.currentRequest.equipment[i].id);

                }
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
