<template>
    <div class="list row">
        <!--            TODO Нужен ли тут поиск?-->
        <!--        <div class="col-md-12">-->
        <!--            <div class="input-group mb-3">-->
        <!--                <input type="text" class="form-control" placeholder="Поиск по штрихкоду"-->
        <!--                       v-model="barcode"/>-->
        <!--                <div class="input-group-append">-->
        <!--                    <button class="btn btn-outline-secondary" type="button"-->
        <!--                            @click="searchBarcode"-->
        <!--                    >-->
        <!--                        Поиск-->
        <!--                    </button>-->
        <!--                </div>-->
        <!--            </div>-->
        <!--        </div>-->
        <div class="col-md-6 mb-3">
            <h4>Список заявок</h4>
            <table class="table table-sm table-hover table-responsive-sm">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Комментарий</th>
                    <th scope="col">Статус</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr :class="{ active: index == currentIndex }"
                    :key="index"
                    @click="setActiveRequest(request, index)"
                    class=""
                    v-for="(request, index) in requests"
                >
                    <th class="align-middle">{{ request.id }}</th>
                    <td class="align-middle">{{ request.comment }}</td>
                    <td class="align-middle">{{ request.status }}</td>
                    <td class="d-flex justify-content-end">
                        <a :href="'/requests/' + request.id"
                           class="btn btn-warning btn-sm"
                        >
                            <i class="fas fa-edit"></i>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="d-flex justify-content-end">
            <span class="d-inline-block" data-toggle="tooltip" tabindex="0" title="Отключено">
      <button @click="removeAllRequests" class="mt-2 btn btn-danger" disabled>
        Удалить всё
      </button>
      </span>
            </div>
        </div>
        <div class="col-md-6">
            <div v-if="currentRequest">
                <h4>Оборудование в заявке</h4>
                <RequestEquipmentList v-bind:request="currentRequest"/>
            </div>
        </div>
    </div>
</template>

<script>
    import RequestDataService from "../../services/RequestDataService";
    import RequestEquipmentList from "./RequestEquipmentList";

    export default {
        name: "request-list",
        components: {RequestEquipmentList},
        data() {
            return {
                requests: [],
                currentRequest: null,
                currentIndex: -1,
                barcode: ""
            };
        },
        methods: {
            retrieveRequests() {
                RequestDataService.getAll()
                    .then(response => {
                        this.requests = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            refreshList() {
                this.retrieveRequests();
                this.currentRequest = null;
                this.currentIndex = -1;
            },

            setActiveRequest(request, index) {
                this.currentRequest = request;
                this.currentIndex = index;
            },

            removeAllRequests() {
                RequestDataService.deleteAll()
                    .then(response => {
                        console.log(response.data);
                        this.refreshList();
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            // searchBarcode() {
            //     RequestDataService.findByBarcode(this.barcode)
            //         .then(response => {
            //             this.requests = response.data;
            //             console.log(response.data);
            //         })
            //         .catch(e => {
            //             console.log(e);
            //         });
            // }
        },
        mounted() {
            this.retrieveRequests();
        }
    };
</script>

<style>
    .list {
        text-align: left;
        max-width: 100%;
        margin: auto;
    }
</style>
