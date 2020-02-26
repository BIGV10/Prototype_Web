<template>
    <div style="position: relative; width: 100%;">
    <equipment-form :equipments="equipments" :equipmentAttr="equipment" />
    <equipment-row v-for="equipment in equipments"
                   :key="equipment.id"
                   :equipment="equipment"
                   :editMethod="editMethod"
                   :deleteEquipment="deleteEquipment"
                   :equipments="equipments" />
    </div>
</template>

<script>
    import EquipmentRow from 'components/equipments/EquipmentRow.vue'
    import EquipmentForm from 'components/equipments/EquipmentForm.vue'

    export default {
        props: ['equipments'],
        components: {
            EquipmentRow,
            EquipmentForm
        },
        data() {
            return {
                equipment: null
            }
        },
        methods: {
            editMethod(equipment) {
                this.equipment = equipment
            },
            deleteEquipment(equipment) {
                this.$resource('/api/equipments{/id}').remove({id: equipment.id}).then(result => {
                    if (result.ok) {
                        this.equipments.splice(this.equipments.indexOf(equipment), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>
