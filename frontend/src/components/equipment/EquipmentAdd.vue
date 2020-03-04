<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <form>
                <div class="form-group">
                    <label for="name">Название</label>
                    <input
                            type="text"
                            class="form-control"
                            id="name"
                            v-model="equipment.name"
                            name="name"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="barcode">Штрихкод</label>
                    <input
                            class="form-control"
                            id="barcode"
                            v-model="equipment.barcode"
                            name="barcode"
                            required
                    >
                </div>

                <div class="form-group">
                    <label for="comment">Коммент</label>
                    <input
                            class="form-control"
                            id="comment"
                            v-model="equipment.comment"
                            name="comment"
                            required
                    >
                </div>

                <button @click="saveEquipment" type="submit" class="btn btn-success">Отправить</button>
            </form>
        </div>

        <div v-else>
            <h4>Оборудование добавлено!</h4>
            <span>ID нового оборудования: {{this.newId}}</span>
            <p/>
            <button class="btn btn-success mt-2" @click="newEquipment">Добавить ещё</button>
        </div>
    </div>
</template>

<script>
    import EquipmentDataService from "../../services/EquipmentDataService";

    export default {
        name: "add-equipment",
        data() {
            return {
                equipment: {
                    id: null,
                    name: "",
                    barcode: "",
                    comment: ""
                },
                submitted: false,
                newId: null
            };
        },
        methods: {
            //TODO Добавить проверку полей на ввод
            saveEquipment() {
                var data = {
                    name: this.equipment.name,
                    barcode: this.equipment.barcode,
                    comment: this.equipment.comment
                };
                EquipmentDataService.create(data)
                    .then(response => {
                        this.equipment.id = response.data.id;
                        this.newId = response.data.id;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });

                this.submitted = true;
            },

            newEquipment() {
                this.submitted = false;
                this.equipment = {};
            }
        }
    };
</script>

<style>
    .submit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
