<template>
    <div class="map">
        <l-map :zoom="zoom" :center="[lat, long]" style="height: 500px;">
            <l-tile-layer :url="url" :attribution="attribution"></l-tile-layer>
            <l-marker :lat-lng="[lat, long]"></l-marker>
        </l-map>
    </div>
</template>

<script>
import {LMap, LTileLayer, LMarker } from 'vue2-leaflet';
import { OpenStreetMapProvider } from 'leaflet-geosearch';

var L = window.L;
const provider = new OpenStreetMapProvider();

export default {
    name: 'my-map',
    props: ["location"],
    components: {
        LMap,
        LTileLayer,
        LMarker
    },
    data() {
        return {
        lat: "",
        long: "",
        zoom:13,
        center: L.latLng(47.413220, -1.219482),       // default coords
        url:'http://{s}.tile.osm.org/{z}/{x}/{y}.png',
        attribution:'&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        marker: L.latLng(47.413220, -1.219482),       // default coords
        }
    },

  created() {
    provider
        .search({ query: this.location })
        .then(result => { 
            this.lat = result[0].y,
            this.long = result[0].x
        });
    }
}

</script>

<style scoped>
</style>