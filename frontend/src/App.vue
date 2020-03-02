<template>
  <div id="app">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
      <a class="navbar-brand" href="/">Прототип</a>
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
              <a class="dropdown-item" href="/requests">Список заявок</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="/addRequest">Добавить заявку</a>
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
        <ul class="navbar-nav ml-auto" v-if="!currentUser">
          <li class="nav-item">
            <router-link class="nav-link" to="/register">
              <font-awesome-icon icon="user-plus"/>
              Регистрация
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/login">
              <font-awesome-icon icon="sign-in-alt"/>
              Войти
            </router-link>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto" v-else>
          <li class="nav-item">
            <router-link class="nav-link" to="/profile">
              <font-awesome-icon icon="user"/>
              {{ currentUser.username }}
            </router-link>
          </li>
          <li class="nav-item">
            <a @click.prevent="logOut" class="nav-link" href>
              <font-awesome-icon icon="sign-out-alt"/>
              Выход
            </a>
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
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      },
      showAdminBoard() {
        if (this.currentUser && this.currentUser.roles) {
          return this.currentUser.roles.includes('ADMIN');
        }

        return false;
      },
      showModeratorBoard() {
        if (this.currentUser && this.currentUser.roles) {
          return this.currentUser.roles.includes('MODERATOR');
        }

        return false;
      }
    },
    methods: {
      logOut() {
        this.$store.dispatch('auth/logout');
        this.$router.push('/login');
      }
    }
  };
</script>

<style>

</style>