<template>
    <div>
    <input type="text" placeholder="Штрихкод" v-model="barcode" />
    <input type="text" placeholder="Название" v-model="name" />
    <input type="text" placeholder="Коммент" v-model="comment" />
    <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['equipments', 'equipmentAttr'],
        data: function () {
            return {
                id: '',
                name: '',
                barcode: '',
                comment: ''
            }
        },
        watch: {
            equipmentAttr: function (newVal, oldVal) {
                this.id = newVal.id;
                this.name = newVal.name;
                this.barcode = newVal.barcode;
                this.comment = newVal.comment;
            }
        },
        methods: {
            save() {
                var equipment = {
                    name: this.name,
                    barcode: this.barcode,
                    comment: this.comment
                };

                if (this.id) {
                    this.$resource('/api/equipments{/id}').update({id: this.id}, equipment).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.messages, data.id)
                            this.equipments.splice(index, 1, data);
                            this.name = ''
                            this.barcode = ''
                            this.comment = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/api/equipments{/id}').save({}, equipment).then(result =>
                        result.json().then(data => {
                            this.equipments.push(data);
                            this.name = ''
                            this.barcode = ''
                            this.comment = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>

</style>
