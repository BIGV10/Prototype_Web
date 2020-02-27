<template>
    <div v-if="currentEquipment" class="edit-form">
        <h4>Изменение оборудования</h4>
        <form>
            <div class="form-group">
                <label for="id">ID</label>
                <input type="text" class="form-control" id="id"
                       v-model="currentEquipment.id"
                       disabled
                />
            </div>
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" class="form-control" id="name"
                       v-model="currentEquipment.name"
                />
            </div>
            <div class="form-group">
                <label for="barcode">Штрихкод</label>
                <input type="text" class="form-control" id="barcode"
                       v-model="currentEquipment.barcode"
                />
            </div>

            <div class="form-group">
                <label for="comment">Комментарий</label>
                <textarea type="text" class="form-control" id="comment" rows="3"
                          v-model="currentEquipment.comment"
                />
            </div>
        </form>

        <div class="d-flex justify-content-between bd-highlight">
            <button class="btn btn-danger mr-2"
                    @click="deleteEquipment"
            >
                Удалить
            </button>

            <button type="submit" class="btn btn-success"
                    @click="updateEquipment"
            >
                Обновить
            </button>
        </div>

        <p/>
        <p>
            {{ message }}
        </p>
    </div>

    <div v-else>
        <br/>
        <p>Please click on a Equipment...</p>
    </div>
</template>

<script>
    import EquipmentDataService from "../services/EquipmentDataService";

    export default {
        name: "equipment",
        data() {
            return {
                currentEquipment: null,
                message: ''
            };
        },
        methods: {
            getEquipment(id) {
                EquipmentDataService.get(id)
                    .then(response => {
                        this.currentEquipment = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            updateEquipment() {
                EquipmentDataService.update(this.currentEquipment.id, this.currentEquipment)
                    .then(response => {
                        console.log(response.data);
                        this.message = 'Оборудование было обновлено!';
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },

            deleteEquipment() {
                EquipmentDataService.delete(this.currentEquipment.id)
                    .then(response => {
                        console.log(response.data);
                        this.$router.push({name: "equipments"});
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        },
        mounted() {
            this.message = '';
            this.getEquipment(this.$route.params.id);
        }
    };
</script>

<style>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
