<template>
  <div v-if="currentEquipment" class="edit-form">
    <h4>Equipment</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
          v-model="currentEquipment.name"
        />
      </div>
      <div class="form-group">
        <label for="barcode">Barcode</label>
        <input type="text" class="form-control" id="barcode"
          v-model="currentEquipment.barcode"
        />
      </div>

      <div class="form-group">
        <label for="comment">Comment</label>
        <input type="text" class="form-control" id="comment"
               v-model="currentEquipment.comment"
        />
      </div>
    </form>

<!--    <button class="badge badge-primary mr-2"-->
<!--      v-if="currentEquipment.published"-->
<!--      @click="updatePublished(false)"-->
<!--    >-->
<!--      UnPublish-->
<!--    </button>-->
<!--    <button v-else class="badge badge-primary mr-2"-->
<!--      @click="updatePublished(true)"-->
<!--    >-->
<!--      Publish-->
<!--    </button>-->

    <button class="badge badge-danger mr-2"
      @click="deleteEquipment"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateEquipment"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Equipment...</p>
  </div>
</template>

<script>
import EquipmentDataService from "../services/EquipmentDataService";

export default {
  name: "equipment",
  data() {
    return {
      currentEquipment: null,
      message: ''
    };
  },
  methods: {
    getEquipment(id) {
      EquipmentDataService.get(id)
        .then(response => {
          this.currentEquipment = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // updatePublished(status) {
    //   var data = {
    //     id: this.currentEquipment.id,
    //     title: this.currentEquipment.title,
    //     description: this.currentEquipment.description,
    //     published: status
    //   };
    //
    //   EquipmentDataService.update(this.currentEquipment.id, data)
    //     .then(response => {
    //       this.currentEquipment.published = status;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    updateEquipment() {
      EquipmentDataService.update(this.currentEquipment.id, this.currentEquipment)
        .then(response => {
          console.log(response.data);
          this.message = 'The equipment was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteEquipment() {
      EquipmentDataService.delete(this.currentEquipment.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "equipments" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getEquipment(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>
