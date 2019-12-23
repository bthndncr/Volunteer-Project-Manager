<template>
      <div id="carousel-container">
        <b-carousel
          id="carousel-1"
          v-model="slide"
          :interval="4000"
          fade
          indicators
          background="#ababab"
          img-width="800 "
          img-height="320"
          style="text-shadow: 1px 1px 2px #333;"
          @sliding-start="onSlideStart"
          @sliding-end="onSlideEnd"
        >
          <!-- Text slides with image -->
          <b-carousel-slide
            id="slide"
            v-for="(image, index) in images"
            :key="index"
            :caption="image.caption"
            :text="image.text"
            :img-src="image.url"
            >
          </b-carousel-slide>

        </b-carousel>
      </div>
      
</template>

<script>
  export default {
    data() {
      return {
        slide: 0,
        sliding: null,
        images: [
           {url: require('../assets/image-4.jpg'), caption: "VOLUNTEERS", text: "Find opportunities to serve"},
           {url: require('../assets/image-3.jpg'), caption: "TEAMS", text: "Grow your membership & maximize your impact"},
           {url: require('../assets/image-2.jpg'), caption: "CAUSES", text: "Fuel your passion for service" },
           {url: require('../assets/image-1.jpg'), caption: "ORGANIZATIONS", text: "Share your events and gather support"}
        ]
      }
    },
    methods: {
      onSlideStart() {
        this.sliding = true
      },
      onSlideEnd() {
        this.sliding = false
      }
    }
  }
</script>

<style scoped>
#slide {
  max-height: 83vh;
}
</style>
