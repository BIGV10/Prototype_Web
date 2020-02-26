<template>
    <div>
        <i>({{ equipment.id }})</i> {{ equipment.barcode }} {{ equipment.name }} {{ equipment.comment }}
        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>
</template>

<script>
    export default {
        props: ['equipment', 'editEquipment', 'deleteEquipment', 'equipments'],
        methods: {
            edit() {
                this.editEquipment(this.equipment)
            },
            del() {
                this.deleteEquipment(this.equipment)
            }
        }
    }
</script>

<style>

</style>
