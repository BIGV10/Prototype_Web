<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <a href="#" class="navbar-brand">Prototype</a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <a href="/equipments" class="nav-link">Equipments</a>
        </li>
        <li class="nav-item">
          <a href="/addEquipment" class="nav-link">Add</a>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>