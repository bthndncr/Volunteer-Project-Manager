<template>
    <div class="wrapper">
        <div class="searchBar">
            <input type="search" placeholder="Search" v-model="searchText" @keyup.prevent="" />
        </div>
        <div class="cards">
            <div class="card card--1" v-for="(event,index) in handleSort" :key="index" v-bind:value="index">
                <router-link :to="{ name: 'event-view', params: { event: event } }">
                    <div class="card__img" v-bind:style="{ 'background-image': 'url(' + organizations.get(event.orgId) + ')'}"></div>
                    <div class="card__img--hover" v-bind:style="{ 'background-image': 'url(' + organizations.get(event.orgId) + ')'}"></div>
                </router-link>
                <div class="card__info">
                    <span class="card__category">Event: {{event.eventName}}</span>
                    <h5 class="card__title">{{event.description.split('.')[0]}}...</h5>
                    <span class="card__by">by <span class="card__author" title="author">{{event.orgName}}</span></span>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
// import auth from '../auth.js';

export default {
    data() {
        return {
            events: [],
            organizations: new Map(),
            searchText: ""
        }
    },
    computed: {
        handleSort() {
            return this.events.filter(theData => {
                return theData.eventName.toLowerCase().includes(this.searchText.toLowerCase());
            });
        }
    },
    mounted() {
        fetch(`${process.env.VUE_APP_REMOTE_API}/api/organizations/all`, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                // Authorization: 'Bearer ' + auth.getToken()
            }
        })
        .then((response) => {
            return response.json();
        })
        .then((arr) => {
            arr.forEach(org => {
                this.organizations.set(org.orgId, org.photoUrl);
            });
        })
        .then(err => console.log(err));
            fetch(`${process.env.VUE_APP_REMOTE_API}/browseEvents`, {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
                }
            })
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                this.events = data;
            })
            .then(err => console.log(err))
        }
}
</script>

<style scoped>
/* card style */
@import url('https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700');
@import url('https://fonts.googleapis.com/css?family=Raleway:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i');

/* search bar style */
input {
	outline: none;
}
input[type=search] {
	box-sizing: content-box;
	font-family: inherit;
	font-size: 100%;
}
input::-webkit-search-decoration,
input::-webkit-search-cancel-button {
	display: none;
}

input[type=search] {
	background: #fff url(https://static.tumblr.com/ftv85bp/MIXmud4tx/search-icon.png) no-repeat 9px center;
	border: solid 1px #007bff;
	padding: 9px 10px 9px 32px;
	width: 55px;
	border-radius: 10em;
	transition: all .5s;

}
input[type=search]:focus {
	width: 200px;
	background-color: #fff;
	border-color: #ed3833;
	box-shadow: 0px 13px 10px -7px rgba(0, 0, 0,0.1);
}
.searchBar {
     width: 100%;
     margin-top: 2%;
     display: flex;
     justify-content: center;
     align-items: center;
 }

/* Card styles */
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

.cards {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    margin: 2% 4% 8% 4%;
}

/* .card--1 .card__img, .card--1 .card__img--hover {
    background-image: url('https://picsum.photos/900/250/?image=3');
} */

.card__img {
    visibility: hidden;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 100%;
    height: 235px;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;

}

.card__info-hover {
    position: absolute;
    padding: 16px;
    width: 100%;
    opacity: 0;
    top: 0;
}

.card__img--hover {
  transition: 0.2s all ease-out;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    width: 100%;
    position: absolute;
    height: 235px;
    border-top-left-radius: 12px;
    border-top-right-radius: 12px;
    top: 0;

}
.card {
    margin: 1% 1% 2% 1% ;
    transition: all .4s cubic-bezier(0.175, 0.885, 0, 1);
    background-color: #fff;
    width: 300px;
    height: 450px;
    position: relative;
    border-radius: 12px;
    border-color: #007bff;
    overflow: hidden;
    box-shadow: 0px 13px 10px -7px rgba(0, 0, 0,0.1);
}
.card:hover {
    box-shadow: 0px 30px 18px -8px rgba(0, 0, 0,0.1);
    transform: scale(1.10, 1.10);
}

.card__info {
    z-index: 2;
    background-color: #fff;
    border-bottom-left-radius: 12px;
    border-bottom-right-radius: 12px;
     padding: 16px 24px 24px 24px;
}

.card__category {
    font-family: 'Raleway', sans-serif;
    text-transform: uppercase;
    font-size: 18px;
    letter-spacing: 2px;
    font-weight: 600;
    color: #ed3833;
}


.card__title {
    margin-top: 5px;
    margin-bottom: 10px;
    font-family: 'Roboto Slab', serif;
}

.card__by {
    font-size: 16px;
    font-family: 'Raleway', sans-serif;
    font-weight: 600;
}

.card__author {
    font-weight: 400;
    text-decoration: none;
    color: #007bff;
}

.card:hover .card__img--hover {
    height: 100%;
    opacity: 0.3;
}

.card:hover .card__info {
    background-color: transparent;
    position: relative;
}

.card:hover .card__info-hover {
    opacity: 1;
}
</style>
