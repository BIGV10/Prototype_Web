<template>
    <div>
        <h4>Информация по заявке</h4>
        <strong>ID: </strong>
        <span>{{ request.id }}</span>
        <br>

        <strong>Комментарий: </strong>
        <span>{{ request.comment }}</span>
        <br>

        <strong>Статус: </strong>
        <span>{{ request.status }}</span>
        <br>

        <strong>Время создания: </strong>
        <span>{{ request.dateIssued | moment("YYYY.MM.DD HH:mm:ss") }}</span>
        <br>

        <strong>Кем создано: </strong>
        <span>{{ request.issuedBy }}</span>
        <br>

    </div>
</template>

<script>
    export default {
        name: "RequestInfo",
        props: ['request']
    }
</script>

<style scoped>

</style>