<template>
    <div class="container">
        <header class="jumbotron">
            <h3>
                Профиль пользователя <strong>{{currentUser.username}}</strong>
            </h3>
        </header>
        <p>
            <strong>Token:</strong>
            {{currentUser.accessToken.substring(0, 20)}} ...
            {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
        </p>
        <!--        <p>-->
        <!--            <strong>ID:</strong>-->
        <!--            {{currentUser.id}}-->
        <!--        </p>-->
        <!--        <p>-->
        <!--            <strong>Почта:</strong>-->
        <!--            {{currentUser.email}}-->
        <!--        </p>-->
        <strong>Права:</strong>
        <ul>
            <li :key="index" v-for="(role,index) in currentUser.authorities">{{role}}</li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: 'Profile',
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            }
        },
        mounted() {
            if (!this.currentUser) {
                this.$router.push('/login');
            }
        }
    };
</script>