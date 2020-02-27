<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <form>
                <div class="form-group">
                    <label for="name">Название</label>
                    <input
                            class="form-control"
                            id="name"
                            name="name"
                            required
                            type="text"
                            v-model="equipment.name"
                    >
                </div>

                <div class="form-group">
                    <label for="barcode">Штрихкод</label>
                    <input
                            class="form-control"
                            id="barcode"
                            name="barcode"
                            required
                            v-model="equipment.barcode"
                    >
                </div>

                <div class="form-group">
                    <label for="comment">Коммент</label>
                    <input
                            class="form-control"
                            id="comment"
                            name="comment"
                            required
                            v-model="equipment.comment"
                    >
                </div>

                <button @click="saveEquipment" class="btn btn-success" type="submit">Отправить</button>
            </form>
        </div>

        <div v-else>
            <h4>Оборудование добавлено!</h4>
            <span>ID нового оборудования: {{this.newId}}</span>
            <button @click="newEquipment" class="btn btn-success mt-2">Добавить ещё</button>
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
