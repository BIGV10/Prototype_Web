<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="equipment.name"
          name="name"
        />
      </div>

      <div class="form-group">
        <label for="barcode">Barcode</label>
        <input
                class="form-control"
                id="barcode"
                required
                v-model="equipment.barcode"
                name="barcode"
        />
      </div>

      <div class="form-group">
        <label for="comment">Comment</label>
        <input
                class="form-control"
                id="comment"
                required
                v-model="equipment.comment"
                name="comment"
        />
      </div>

      <button @click="saveEquipment" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEquipment">Add</button>
    </div>
  </div>
</template>

<script>
import EquipmentDataService from "../services/EquipmentDataService";

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
      submitted: false
    };
  },
  methods: {
    saveEquipment() {
      var data = {
        name: this.equipment.name,
        barcode: this.equipment.barcode,
        comment: this.equipment.comment
      };

      EquipmentDataService.create(data)
        .then(response => {
          this.equipment.id = response.data.id;
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
