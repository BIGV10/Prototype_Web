<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <a href="#" class="navbar-brand">Прототип</a>
      <div class="navbar-nav mr-auto">
        <li>
          <div class="dropdown show ml-2">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownRequest" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Заявки
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="/equipments">Список заявок</a>
              <a class="dropdown-item" href="/addEquipment">Добавить заявку</a>
            </div>
          </div>
        </li>
        <li>
          <div class="dropdown show ml-2">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownEquipment" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Оборудование
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
              <a class="dropdown-item" href="/equipments">Список оборудования</a>
              <a class="dropdown-item" href="/addEquipment">Добавить оборудование</a>
            </div>
          </div>
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