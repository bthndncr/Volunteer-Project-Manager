<template>
<div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 col-sm-10">	
            <div class="form">
                  <form  @submit.prevent="postEvent">
                    <h1>Post an Event</h1>
                    <div class="alert alert-danger" role="alert" v-if="postErrors">
                    There were problems posting this event.
                    </div>
                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="event.eventName" required autocomplete="off">
                      <label><i class="fas fa-signature"></i> Event Name</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="event.creatorId"  required autocomplete="off">
                      <label><i class="fas fa-user-tie"></i> Creator Name</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                    <i class="far fa-clock mr-2 pt-2"></i>
                    <vue-timepicker class="time" v-model="event.startTime" />
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="date" class="input" :class="styleClass()"  v-model="event.eventDate" required >
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="event.location" required >
                      <label><i class="fas fa-address-book"></i> # street, city, state</label>
                    </div>

                    <div class="input-group col-md-12 col-md-offset-2">
                      <input type="text" class="input" :class="styleClass()" v-model="event.description" required >
                      <label><i class="fas fa-align-left"></i> Description</label>
                    </div>
                
                    <div class="action text-center">
                      <div class="col-md-8 col-md-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-outline-primary btn-lg mt-2">
                          Post Event
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
import VueTimepicker from 'vue2-timepicker/src/vue-timepicker.vue'
import auth from "../auth"

export default {
    name: "postEvent",
    components: {VueTimepicker},
    data() {
        return {
            event: {
                orgId: 0,      
                eventName: "",    
                startTime: "",
                location: "",
                eventDate: "",
                creatorId: "",
                description: ""
            },
            postErrors: false,
        }
    }, 
    methods: {
        postEvent(){
            fetch(`${process.env.VUE_APP_REMOTE_API}/createEvent`, {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
            },
                body: JSON.stringify(this.event),
            })
            .then(response => {
                if(response.ok){
                    this.$router.push("/browseEvents")
                } else {
                    this.postErrors = true;
                }
            })
            .then(err => console.log(err));   
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
    created() {
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
        .then(data => {
            this.event.orgId = data.orgId;
        })
        .then(error => console.log(error))
    }
}
</script>

<style scoped src="@/assets/customFormStyle.css">
</style>