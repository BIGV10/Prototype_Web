<template>
    <div>
        <equipments-list :equipments="equipments" />
    </div>
</template>

<script>
    import EquipmentList from 'components/equipments/EquipmentList.vue'

    export default {
        components: {
            EquipmentList
        },
        data() {
            return {
                equipments: frontendData.equipments
            }
        }
    }
</script>

<style scoped>

</style>