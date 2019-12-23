<template>
  <div id="new-post" class="dz-container">
    <form id="post-form" v-on:submit.prevent="sharePhoto">
      <vue-dropzone
        id="dropzone"
        v-bind:options="dropzoneOptions"
        v-model="post.imageUrl"
        v-on:vdropzone-sending="addFormData"
        v-on:vdropzone-success="getSuccess"
        ref="dz"
      ></vue-dropzone>
    </form>
  </div>
</template>

<script>
import vue2Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";

export default {
  name: "new-post",
  components: {
    vueDropzone: vue2Dropzone
  },
  data() {
    return {
      dropzoneOptions: {
        url: "https://api.cloudinary.com/v1_1/voluntr/image/upload",  //this is my url for cloudinary
        thumbnailWidth: 250,
        maxFilesize: 2.0,
        acceptedFiles: ".jpg, .jpeg, .png, .gif",
        uploadMultipe: false,
      },
      post: {
        imageUrl: "",
        caption: ""
      }
    };
  },
  computed: {
    canPost() {
      return this.post.caption && this.post.imageUrl;
    }
  },
  methods: {
    addFormData(file, xhr, formData) {
      formData.append("api_key", "916599636454519");  // my api key
      formData.append("upload_preset", "uaairzz9"); // my upload preset
      formData.append("timestamp", (Date.now() / 1000) | 0);
      formData.append("tags", "vue-app");
    },
    getSuccess(file, response) {
      this.post.imageUrl = response.secure_url;
      this.$emit('uploaded', this.post.imageUrl);
    },
    uploadImage() {
      this.$refs.dz.processQueue();
    }
  }
};
</script>

<style scoped>
#post-form {
  width: 100%;
  border: 1.5px solid #007bff;
    box-shadow: 0px 13px 10px -7px rgba(0, 0, 0,0.1);
  
}
#dropzone {
  border-radius: 50%;
  height: 200px;
  width: 200px;
  overflow: hidden;
}

</style>