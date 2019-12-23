<template>
  <div>
      <b-container class="bv-example-row">
      <b-row id="profile-pic-row">
        <b-col id="profile-picture"><img :src='org.photoUrl'/></b-col>
      </b-row>
      <b-row>
        <b-col id="content-row"><i class="fas fa-map-marker-alt"></i><h4 id="profile-cityState">{{ "  " + org.city + ", " + org.state }}</h4></b-col>
      </b-row>
      <b-row>
        <b-col><h1 id="profile-name">{{ org.orgName}}</h1></b-col>
      </b-row>
      <b-row>
        <b-col id="content-row"><p>{{ org.email }}</p></b-col>
      </b-row>
      <b-row>
        <b-col id="content-row"><p>{{ org.description }}</p></b-col>
      </b-row>

      <b-row>
        <b-col id="content-row"><h3>ACTIVITY FEED</h3></b-col>
      </b-row>
      <b-table sticky-header table-variant="light" :items="items" :fields="fields">
        <template v-slot:cell(eventName)="data">
          <b class="text-info" @click="goToEvent(data.item)">{{ data.item.eventName }}</b>
        </template>
      </b-table>
    </b-container>
  </div>
</template>

<script>
import auth from "../auth";

export default {
  
    data(){
        return {
            org:{},
            fields: ['eventName', { key: 'orgName', label: 'Organization'}, 'eventDate', 'startTime', 'location', 'description'],
            items: [],
        }
    },
    methods: {
        goToEvent(data) {
          this.$router.push({ name: 'event-view', params: { event: data} })

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
            this.org = data;
        })
        .then(error => console.log(error))

        fetch(`${process.env.VUE_APP_REMOTE_API}/api/orgEvents`, {
        method: 'GET',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
          Authorization: 'Bearer ' + auth.getToken()
        }
      })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        this.items = data;
      })
      .then(err => console.log(err))
    }

}
</script>

<style scoped>
.bv-example-row {
  text-align: center;
  margin-top: 4%;
  margin-bottom: 8%;
  background: #ffffff;
  border-radius: 10px;
  filter: drop-shadow(0px 5px 5px rgb(48, 48, 48));
}

#profile-name{
  text-transform: uppercase;
}

.fa-map-marker-alt { 
  display: inline;
  color: black;

}

#profile-cityState {
  display: inline;
  text-transform: uppercase;
  color:#ed3833;

}

#profile-pic-row {
  border-radius: 10px 10px 0 0;
  background: linear-gradient(to right, #ed3833, #007bff);
  margin-bottom: 100px;
}


#profile-picture img{
  transform: translate(0, 50%);
  height: 200px;
  width: 200px;
  border-radius: 50%;
  /* filter: drop-shadow(0 -1mm 2mm rgb(105, 105, 105)); */
  border: .5rem solid white;
}

#content-row{
  margin-top: 10px;
  margin-bottom: 10px;
}

.text-info{
  cursor: pointer;  
}
</style>