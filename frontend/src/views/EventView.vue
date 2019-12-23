<template>
    <div class="theEvent">
        <div class="card">
            <div class="card__info">
                <button @click="goBack" class="btn btn-outline-primary btn-lg mb-2"><i class="fas fa-arrow-circle-left"></i></button>
                <h1 class="card__category">Event: {{ event.eventName }} </h1>
                <h3 class="card__by">Sponsored by <span class="card__author" title="author">{{ event.orgName }}</span></h3>
                <h5 class="card__title"><i class="far fa-calendar-alt"></i> Date: {{event.eventDate}}</h5>
                <h5 class="card__title"><i class="far fa-clock"></i> Start Time: {{event.startTime}}</h5>
                <h5 class="card__title"><i class="fas fa-map-marker-alt"></i> Location: {{event.location}}</h5>
                <h5 class="card__title"><i class="fas fa-signature"></i> Description: {{event.description}}</h5>
                <b-button variant="primary" @click="signUpForEvent"  v-if="isUserLoggedIn()">Sign Up!</b-button>
                <b-button variant="warning" @click="$router.push('/register')" v-if="!isUserLoggedIn()">Register to Sign Up!</b-button>
            </div>
            <div class="map">
                <Map :location="event.location" />
            </div>
        </div>
  </div>
</template>


<script>
import auth from "../auth.js"
import Map from "./Map"

export default {
    name: "event-view",
    components: {Map},
    props: {
        event: Object
    },
    methods: {
        isUserLoggedIn: function() {
        const user = auth.getUser()
        return user != null ? true : false
        },
        signUpForEvent: function() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/signUp`, {
                method: 'POST',
                headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                Authorization: 'Bearer ' + auth.getToken()
                },
                body: JSON.stringify(this.event),

            })
            .then((response) => {
                if (response.ok) {
                    return response.text();
                }
            })
            .catch((err) => console.error(err));
            this.$router.push('/')
        },
        goBack() {
            this.$router.go(-1);
        }
    },
    mounted() {
        window.scroll(0,0);
    }

}
</script>

<style scoped>
*{
  box-sizing: border-box;
}

body, html {
    font-family: 'Roboto Slab', serif;
    margin: 0;
    width: 100%;
    height: 100%;
    padding: 0;
}
.buttons {
    margin-left: 25px;
}

.theEvent {
    display: flex;
    justify-content: center;
    margin-top: 2%;
}
.map {
    margin-top: 1%;
    padding: 1%;
}

.card {
    margin: 1% 1% 2% 1% ;
    transition: all .4s cubic-bezier(0.175, 0.885, 0, 1);
    background-color: #fff;
    width: 95%;
    position: relative;
    border-radius: 12px;
    border-color: #007bff;
    overflow: hidden;
    box-shadow: 0px 13px 10px -7px rgba(0, 0, 0,0.1);
}

.card__info {
  background-color: #fff;
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
  padding: 16px 24px 0px 24px;
}

.card__category {
    font-family: 'Raleway', sans-serif;
    text-transform: uppercase;
    letter-spacing: 2px;
    font-weight: 700;
    color: #ed3833;
}


.card__title {
    margin-top: 5px;
    margin-bottom: 10px;
    font-family: 'Roboto Slab', serif;
}

.card__by {
    font-family: 'Raleway', sans-serif;
    font-weight: 500;
}

.card__author {
    font-weight: 600;
    text-decoration: none;
    color: #007bff;
}

</style>
