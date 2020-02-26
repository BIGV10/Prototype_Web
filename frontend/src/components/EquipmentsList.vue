<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by barcode"
          v-model="barcode"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchBarcode"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Equipment List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(equipment, index) in equipments"
          :key="index"
          @click="setActiveEquipment(equipment, index)"
        >
          {{ equipment.name }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllEquipments">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentEquipment">
        <h4>Equipment</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentEquipment.name }}
        </div>
        <div>
          <label><strong>Barcode:</strong></label> {{ currentEquipment.barcode }}
        </div>
        <div>
          <label><strong>Comment:</strong></label> {{ currentEquipment.comment }}
        </div>

        <a class="badge badge-warning"
          :href="'/equipments/' + currentEquipment.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Equipment...</p>
      </div>
    </div>
  </div>
</template>

<script>
import EquipmentDataService from "../services/EquipmentDataService";

export default {
  name: "equipment-list",
  data() {
    return {
      equipments: [],
      currentEquipment: null,
      currentIndex: -1,
      barcode: ""
    };
  },
  methods: {
    retrieveEquipments() {
      EquipmentDataService.getAll()
        .then(response => {
          this.equipments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveEquipments();
      this.currentEquipment = null;
      this.currentIndex = -1;
    },

    setActiveEquipment(equipment, index) {
      this.currentEquipment = equipment;
      this.currentIndex = index;
    },

    removeAllEquipments() {
      EquipmentDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchBarcode() {
      EquipmentDataService.findByBarcode(this.barcode)
        .then(response => {
          this.equipments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveEquipments();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 100%;
  margin: auto;
}
</style>
