<template>
  <div id="app">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
      <a class="navbar-brand" href="#">Прототип</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarMenu">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">
              Заявки
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="/equipments">Список заявок</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="/addEquipment">Добавить заявку</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown">
              Оборудование
            </a>
            <div class="dropdown-menu">
              <a class="dropdown-item" href="/equipments">Список оборудования</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="/addEquipment">Добавить оборудование</a>
            </div>
          </li>
        </ul>
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