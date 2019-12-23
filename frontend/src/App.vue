<template>
  <div id="app">
    <div id="nav">
      <b-navbar  toggleable="lg" type="dark" id="navbar-vlntr" >
        <b-navbar-brand to="/welcome" id="logo"><img id="logo-img" src="./assets/logo.png"/></b-navbar-brand>
         <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
         <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav class="ml-auto">
              <b-nav-item to="/browseEvents" tag="b-nav-item">BROWSE EVENTS</b-nav-item>
              <b-nav-item to="/login" tag="b-nav-item" v-if="!isUserLoggedIn()">SIGN IN</b-nav-item>
              <b-nav-item to="/register" tag="b-nav-item" v-if="!isUserLoggedIn()">REGISTER</b-nav-item>
              <b-nav-item-dropdown v-if="isUserLoggedIn()" right text="YOUR PROFILE">
                <b-dropdown-item  to="/" tag="b-nav-item" >View Profile</b-dropdown-item>
                <b-dropdown-item  to="/postEvent" tag="b-nav-item">Post Event</b-dropdown-item>
                <b-dropdown-item  to="/createOrganization" tag="b-nav-item" v-if="!hasOrganization">Create Organization</b-dropdown-item>
                <b-dropdown-item  to="/organizationProfile" tag="b-nav-item" v-if="hasOrganization">Your Organization</b-dropdown-item>
                <b-dropdown-item  v-on:click="logOut" tag="b-nav-item" v-if="isUserLoggedIn()">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
         </b-collapse>
      </b-navbar>
    </div>
    <router-view/>
    <Footer></Footer>
  </div>
</template>

<script>
import auth from './auth.js'
import profileStore from './profile.js'
import Footer from './views/Footer.vue'

export default {
  components:{
    Footer
  },
  data(){
    return {
      get profile() {
        return profileStore.getProfile();
      },
      hasOrganization: null
    }
  },
  methods: {
    isUserLoggedIn: function() {
      const user = auth.getUser()
      return user != null ? true : false
    },
    logOut: function() {
      auth.logout();
      profileStore.destroyProfile();
      window.location.reload();
      this.$router.push('/welcome');
    }
  },
  mounted() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/organizations`, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                Authorization: 'Bearer ' + auth.getToken()
            }
        })
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            if(data.orgId != 0){
              this.hasOrganization = true;
            } else {
              this.hasOrganization = false;
              
            }
        })
  }
}
</script>


<style scoped >

#navbar-vlntr {
  background-color: #ed3833 ;
}

#nav-collapse {
  margin-right: 20px;
}

#nav-target {
  background-color: #ed3833 ;
  height: 100px;
}

#logo-img {
  height: 80px;
  width: 226px;
  cursor: pointer;
  margin-left: 20px;
}

#app {
  min-height: 100vh;
  display: grid;
  grid-template-rows: auto 1fr auto;
  margin: 0% 10% ;
}

@media screen and (max-width: 1800px ) {
  #app {
    margin: auto;
  }
}


</style>
