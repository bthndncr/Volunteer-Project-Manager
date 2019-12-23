<template>
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-10">	
            <div class="form">
                  <form  @submit="createProfile" @reset="onReset" v-if="show" >
                    <h1>Create Your Profile</h1>

                    <div class="input-group col-md-12 col-md-offset-2 drop">
                      <dropzone v-on:uploaded="setFileName($event);" ref="dz" ></dropzone>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input " :class="styleClass()" v-model="form.firstName" autocomplete="off">
                      <label>First Name</label>
                    </div>
                  
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input " v-model="form.lastName" :class="styleClass()">
                      <label>Last Name</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="email" class="input " v-model="form.email" :class="styleClass()">
                      <label>Email</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input " v-model="form.city" :class="styleClass()">
                      <label>City</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <b-form-select
                        v-model="form.state"
                        :options="states"
                        required
                      ></b-form-select>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <textarea  v-model="form.bio" cols="30" rows="10" class="input " :class="styleClass()" />                    
                      <label>Bio</label>
                    </div>
                
                    <div class="action text-center">
                      <div class="col-md-8 col-md-offset-4 col-sm-8">
                          <button type="submit" class="btn btn-outline-primary btn-lg mb-2" >Submit</button>
                          <button type="reset" class="btn btn-outline-danger btn-lg">Reset</button>		
                      </div>
                    </div>
                  </form>
            </div>
          </div>
        </div>
      </div>
</template>

<script>
import Dropzone from "@/views/Dropzone.vue";
import auth from "../auth";

  export default {
      components: {
          Dropzone
      },
    data() {
      return {
        form: {
          firstName: '',
          lastName: '',
          email: '',
          city: '',
          state: null,
          photoUrl: null,
          bio: '',
        },
        states: [{ text: 'State', value: null }, 'AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FM', 'FL', 'GA', 'GU',
        'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MH', 'MD', 'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM',
        'NY', 'NC', 'ND', 'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VI', 'VA', 'WA', 'WV', 'WI', 'WY' ],
        show: true
      }
    },
    methods: {

      setFileName(input){
        this.form.photoUrl = input;
      },

      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.firstName = ""
        this.form.lastName = ""
        this.form.email = ''
        this.form.city = ''
        this.form.state = null
        this.form.photoUrl = null
        this.form.bio = ""
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
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
    },

      createProfile: function () {
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/profiles`, {
          method: 'POST',
          headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + auth.getToken()
          },
          body: JSON.stringify(this.form),
        })
          .then((response) => {
            if (response.ok) {
              return response.text();
            }}
          )
          .catch((err) => console.error(err));
        this.$router.push('/');
      },
    }
  }
</script>

<style scoped src="@/assets/customFormStyle.css">

</style>