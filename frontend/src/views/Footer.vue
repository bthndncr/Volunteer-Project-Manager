<template>
    <!-- <b-nav vertical id="footer" type="dark">
      <span><i class="fas fa-copyright"/> Team <i class="fas fa-equals"/><i class="fas fa-equals"/><i class="fas fa-equals"/></span>
      <b-nav-text id="address-text">7100 Euclid Avenue #140</b-nav-text>
      <b-nav-text id="address-text">Cleveland, OH 44103</b-nav-text>
      <b-nav-text id="address-text">(877) 606-3203</b-nav-text>
    </b-nav> -->
    <div id="footer">
        <div id="team-logo"><p><i class="fas fa-copyright"/> Team <i class="fas fa-equals"/><i class="fas fa-equals"/><i class="fas fa-equals"/></p></div>
        <div id="address">
            <p>Contact Us:</p>
            <p>7100 Euclid Avenue #140</p>
            <p>Cleveland, OH 44103 - P: (877) 606-3203 </p>
        </div>
        <div id="team-info">
            <p>Created by:</p>
            <a href="https://www.linkedin.com/in/m-batuhan-dincer/" target="_blank" class="mr-4">Muhammed B. Dincer <i class="fab fa-linkedin"></i></a>
            <a href="https://www.linkedin.com/in/wllf/" target="_blank">Will Federkiel <i class="fab fa-linkedin"></i> </a><br>
            <a href="https://www.linkedin.com/in/jacob-hawk/" target="_blank" class="mr-4">Jacob Hawk <i class="fab fa-linkedin"></i> </a>
            <a href="https://www.linkedin.com/in/christianmcdonald90/" target="_blank">Chris McDonald <i class="fab fa-linkedin"></i> </a>
        </div>
    </div>
  
</template>

<script>
export default {

}
</script>

<style>
#footer {
  background-color: #ed3833;
  padding: 20px 50px 20px 50px;
  color: white;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas:
    "address team-logo team-info";
  
}
#team-logo {
    grid-area: team-logo;
    display: flex;
    justify-content: center;
    align-items: center;
}


#address {
   grid-area: address;
   
}

#address p{
    margin-bottom: 0px;
}

#team-info {
    grid-area: team-info;
    text-align: right;
   
}

#team-info p {
    margin-bottom: 0px;
}

#team-info a {
    color: white;
}

@media screen and (max-width: 1063px) {
    #footer {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-template-areas: 
            "team-logo team-logo"
            "address team-info"
    }
}

@media screen and (max-width: 795px) {
    #footer {
        display: grid;
        grid-template-columns: 1fr;
        grid-template-areas: 
            "team-logo"
            "team-info"
            "address";
    }
    #address {
        text-align: center;
    }
    #team-info{
        text-align: center;
        padding-bottom: 10px;
    }
}

</style>    
    
