function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var equipmentApi = Vue.resource('/api/equipments{/id}');

Vue.component('equipment-form', {
    props: ['equipments', 'equipmentAttr'],
    data: function() {
        return {
            id: '',
            name: '',
            barcode: '',
            comment: ''
        }
    },
    watch: {
        equipmentAttr: function(newVal, oldVal) {
            this.id = newVal.id;
            this.name = newVal.name;
            this.barcode = newVal.barcode;
            this.comment = newVal.comment;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Штрихкод" v-model="barcode" />' +
        '<input type="text" placeholder="Название" v-model="name" />' +
        '<input type="text" placeholder="Коммент" v-model="comment" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var equipment = {
                name: this.name,
                barcode: this.barcode,
                comment: this.comment
            };

            if (this.id) {
                equipmentApi.update({id: this.id}, equipment).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.equipments, data.id);
                        this.equipments.splice(index, 1, data);
                        this.name = ''
                        this.barcode = ''
                        this.comment = ''
                        this.id = ''
                    })
                )
            } else {
                equipmentApi.save({}, equipment).then(result =>
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
});

Vue.component('equipment-row', {
    props: ['equipment', 'editMethod', 'equipments'],
    template: '<div>' +
        '<i>({{ equipment.id }})</i> {{ equipment.barcode }} {{ equipment.name }} {{ equipment.comment }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.equipment);
        },
        del: function() {
            equipmentApi.remove({id: this.equipment.id}).then(result => {
                if (result.ok) {
                    this.equipments.splice(this.equipments.indexOf(this.equipment), 1)
                }
            })
        }
    }
});

Vue.component('equipments-list', {
    props: ['equipments'],
    data: function() {
        return {
            equipment: null
        }
    },
    template:
        '<div style="position: relative; width: 100%;">' +
        '<equipment-form :equipments="equipments" :equipmentAttr="equipment" />' +
        '<equipment-row v-for="equipment in equipments" :key="equipment.id" :equipment="equipment" ' +
        ':editMethod="editMethod" :equipments="equipments" />' +
        '</div>',
    created: function() {
        equipmentApi.get().then(result =>
            result.json().then(data =>
                data.forEach(equipment => this.equipments.push(equipment))
            )
        )
    },
    methods: {
        editMethod: function(equipment) {
            this.equipment = equipment;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<equipments-list :equipments="equipments" />',
    data: {
        equipments: []
    }
});