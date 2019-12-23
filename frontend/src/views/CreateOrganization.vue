<template>
    <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-10">	
            <div class="form">
                  <form @submit="createOrganization" @reset="onReset" v-if="show">
                    <h1>Create Your Own Organization</h1>

                    <div class="input-group col-md-12 col-md-offset-2 drop">
                      <dropzone v-on:uploaded="setFileName($event);" ref="dz" ></dropzone>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="form.orgName" required autocomplete="off">
                      <label><i class="fas fa-signature"></i> Organization Name</label>
                    </div>
                  
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="email" class="input" :class="styleClass()" v-model="form.email" required autocomplete="off">
                      <label><i class="fas fa-envelope"></i> Organization Email</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="form.city" required >
                      <label><i class="fas fa-building"></i> City</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <b-form-select v-model="form.state" :options="states" required ></b-form-select>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <textarea class="input" cols="30" rows="10" :class="styleClass()" v-model="form.description" required />
                      <label><i class="fas fa-align-left"></i> My organization is great because...</label>
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
              orgName: '',
              email: '',
              city: '',
              state: null,
              description: '',
              photoUrl: ''
          },
          states: [{ text: 'State', value: null }, 'AL', 'AK', 'AS', 'AZ', 'AR', 'CA', 'CO', 'CT', 'DE', 'DC', 'FM', 'FL', 'GA', 'GU',
          'HI', 'ID', 'IL', 'IN', 'IA', 'KS', 'KY', 'LA', 'ME', 'MH', 'MD', 'MA', 'MI', 'MN', 'MS', 'MO', 'MT', 'NE', 'NV', 'NH', 'NJ', 'NM',
          'NY', 'NC', 'ND', 'MP', 'OH', 'OK', 'OR', 'PW', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX', 'UT', 'VT', 'VI', 'VA', 'WA', 'WV', 'WI', 'WY' ],
          show: true
      }
    },
    methods: {
    setFileName(input) {
        this.form.photoUrl = input;
    },

    onReset(event) {
        event.preventDefault();
        for (let entry in this.form) {
            this.form[entry] = ''
        }
        this.show = false;
        this.$nextTick(() => this.show = true);
    },
    createOrganization: function () {
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/organizations`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                Authorization: 'Bearer ' + auth.getToken()
            },
            body: JSON.stringify(this.form)
        })
          .then((response) => {
            if (response.ok) {
                return response.text();
            }
        })
          .catch((err) => console.error(err));
        this.$router.push('/');
        
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
}
</script>

<style scoped src="@/assets/customFormStyle.css" >
</style>