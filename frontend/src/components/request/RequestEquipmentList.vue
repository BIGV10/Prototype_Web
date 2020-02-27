<template>
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
                v-for="(equipment, index) in request.equipment"
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
                        <i class="fas fa-edit"></i>
                    </a>
                </td>
            </tr>
            </tbody>
        </table>
</template>

<script>
    export default {
        name: "RequestEquipmentList",
        props: ['request'],
        data() {
            return {
                currentIndex: -1,
            };
        },
    }
</script>

<style scoped>

</style>