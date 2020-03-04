<template>
    <div class="list row">
        <div class="col-md-12">
            <!--            TODO Починить поиск-->
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Поиск по штрихкоду"
                       v-model="barcode"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button"
                            @click="searchBarcode"
                    >
                        Поиск
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <h4>Список оборудования</h4>
            <table class="table table-sm table-hover table-responsive-sm">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Штрихкод</th>
                    <th scope="col">Название</th>
                    <th scope="col">Комментарий</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr class=""
                    :class="{ active: index == currentIndex }"
                    v-for="(equipment, index) in equipments"
                    :key="index"
                >
                    <th class="align-middle">{{ equipment.id }}</th>
                    <td class="align-middle">{{ equipment.barcode }}</td>
                    <td class="align-middle">{{ equipment.name }}</td>
                    <td class="align-middle">{{ equipment.comment }}</td>
                    <td class="d-flex justify-content-end">
                        <a class="btn btn-warning btn-sm"
                           :href="'/equipments/' + equipment.id"
                        >
                            <font-awesome-icon icon="edit"/>
                        </a>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="d-flex justify-content-end">
            <span class="d-inline-block" tabindex="0" data-toggle="tooltip" title="Отключено">
      <button class="mt-2 btn btn-danger" @click="removeAllEquipments" disabled>
        Удалить всё
      </button>
      </span>
            </div>
        </div>
    </div>
</template>

<script>
    import EquipmentDataService from "../../services/EquipmentDataService";

    export default {
        name: "equipment-list",
        data() {
            return {
                equipments: [],
                currentEquipment: null,
                currentIndex: -1,
                barcode: "",
                header: {'Authorization': 'Bearer ' + this.$store.getters.getToken}
            };
        },
        methods: {
            retrieveEquipments() {

                EquipmentDataService.getAll()
                    .then(response => {
                        this.equipments = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            refreshList() {
                this.retrieveEquipments();
                this.currentEquipment = null;
                this.currentIndex = -1;
            },

            setActiveEquipment(equipment, index) {
                this.currentEquipment = equipment;
                this.currentIndex = index;
            },

            removeAllEquipments() {
                EquipmentDataService.deleteAll()
                    .then(response => {
                        console.log(response.data);
                        this.refreshList();
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            searchBarcode() {
                EquipmentDataService.findByBarcode(this.barcode)
                    .then(response => {
                        this.equipments = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        },
        mounted() {
            this.retrieveEquipments();
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
