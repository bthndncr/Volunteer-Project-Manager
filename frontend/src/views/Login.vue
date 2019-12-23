<template>
    <!-- login -->
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-10">
            <div class="form">
                  <form  @submit.prevent="login" >
                    <h1>Welcome back!</h1>
                    <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
                      Invalid username and password!
                    </div>
                    <div class="alert alert-success" role="alert" v-if="this.$route.query.registration">
                      Thank you for registering, please sign in.
                    </div>
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="user.username" autocomplete="off">
                      <label><i class="fas fa-user"></i> Username</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="password" v-model="user.password" class="input" :class="styleClass()">
                      <label><i class="fas fa-unlock-alt"></i> Password</label>
                    </div>

                    <div class="action text-center">
                      <div class="col-md-8 col-md-offset-4 col-sm-8">
                          <router-link :to="{ name: 'register' }" class="needAccount ">Need an account?</router-link>
                        <button type="submit" class="btn btn-outline-primary btn-lg mt-2">
                          LOGIN
                        </button>
                      </div>
                    </div>
                  </form>
            </div>
          </div>
        </div>
      </div>
</template>


<script>
import auth from '../auth';
import profileStore from '../profile.js';

export default {
  name: 'login',
  components: {},
  data() {
    return {
      user: {
        username: '',
        password: '',
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/login`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } else {
            this.invalidCredentials = true;
          }
        })
        .then((token) => {
          if (token != undefined) {
            if (token.includes('"')) {
              token = token.replace(/"/g, '');
            }
            auth.saveToken(token);
            this.fetchProfile();
            this.$router.push('/');
          }
        })
        .catch((err) => console.error(err));
    },
    fetchProfile() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/api/profiles/`, {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + auth.getToken()
        }
      })
        .then((response) => {
          if (response.ok) {
            return response.text();
          } 
        })
        .then((profile) => {
          profileStore.saveProfile(profile);
        })
        .catch(() => {
          profileStore.saveProfile(null);
          this.$router.push('/');
        });
    },
    styleClass() {
      var inputs = document.querySelectorAll('.form .input-group input');
        inputs.forEach((input) => {
            input.addEventListener('focusout', (e) => {
                if (e.target.value === "") {
                    return e.target.classList.remove('has-value');
                }

                return e.target.classList.add('has-value');
            });
        });
    }
  },
};
</script>

<style scoped src="@/assets/customFormStyle.css">
</style>
