<template>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-10">	
            <div class="form">
                  <form  @submit.prevent="register" >
                    <h1>Create Account</h1>
                    <div class="alert alert-danger" role="alert" v-if="registrationErrors">
                      There were problems registering this user.
                    </div>
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input " :class="styleClass()" v-model="user.username" required autocomplete="off">
                      <label><i class="fas fa-user"></i> Username</label>
                    </div>
                  
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="password" v-model="user.password" class="input" :class="styleClass()">
                      <label><i class="fas fa-unlock-alt"></i> Password</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="password" v-model="user.confirmPassword" class="input" :class="styleClass()">
                      <label><i class="fas fa-lock"></i> Confirm Password</label>
                    </div>
                
                    <div class="action text-center">
                      <div class="col-md-8 col-md-offset-4 col-sm-8">
                          <router-link :to="{ name: 'login' }" class="needAccount ">Have an account?</router-link>
                        <button type="submit" class="btn btn-outline-primary btn-lg mt-2">
                          REGISTER
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
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push({ path: '/login', query: { registration: 'success' } });
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
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
  }
};
</script>

<style scoped src="@/assets/customFormStyle.css">
</style>
