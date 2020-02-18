function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var eqipmentsApi = Vue.resource('/api/equipments/{id}');

Vue.component('equipment-form', {
    props: ['eqipments', 'eqipmentsAttr'],
    data: function() {
        return {
            id: '',
            name: '',
            barcode: '',
            comment: ''
        }
    },
    watch: {
        eqipmentsAttr: function(newVal, oldVal) {
            this.id = newVal.id;
            this.name = newVal.name;
            this.barcode = newVal.barcode;
            this.comment = newVal.comment;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write something" v-model="text" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var eqipments = { text: this.text };

            if (this.id) {
                eqipmentsApi.update({id: this.id}, eqipments).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.eqipments, data.id);
                        this.eqipments.splice(index, 1, data);
                        this.text = ''
                        this.id = ''
                    })
                )
            } else {
                eqipmentApi.save({}, eqipment).then(result =>
                    result.json().then(data => {
                        this.eqipments.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('eqipments-row', {
    props: ['eqipment', 'editMethod', 'eqipments'],
    template: '<div>' +
        '<i>({{ eqipment.id }})</i> {{ eqipment.name }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.eqipment);
        },
        del: function() {
            eqipmentApi.remove({id: this.eqipment.id}).then(result => {
                if (result.ok) {
                    this.eqipments.splice(this.eqipments.indexOf(this.eqipment), 1)
                }
            })
        }
    }
});

Vue.component('eqipments-list', {
    props: ['eqipments'],
    data: function() {
        return {
            eqipment: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<eqipment-form :eqipments="eqipments" :eqipmentAttr="eqipment" />' +
        '<eqipment-row v-for="eqipment in eqipments" :key="eqipment.id" :eqipment="eqipment" ' +
        ':editMethod="editMethod" :eqipments="eqipments" />' +
        '</div>',
    created: function() {
        eqipmentApi.get().then(result =>
            result.json().then(data =>
                data.forEach(eqipment => this.eqipments.push(eqipment))
            )
        )
    },
    methods: {
        editMethod: function(eqipment) {
            this.eqipment = eqipment;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<equipments-list :equipments="equipments" />',
    data: {
        eqipmentss: []
    }
});