import Vue from 'vue'
import Router from 'vue-router'
import auth from './auth'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import Register from './views/Register.vue'
import Welcome from "./views/Welcome.vue"
import CreateProfile from "./views/CreateProfile.vue"
import BrowseEvents from "./views/BrowseEvents.vue"
import CreateOrganization from "./views/CreateOrganization.vue"
import EventView from "./views/EventView.vue"
import PostEvent from "./views/PostEvent.vue"
import OrganizationProfile from "./views/OrganizationProfile.vue"

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/welcome",
      name: "welcome",
      component: Welcome,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:"/browseEvents",
      name: "events",
      component: BrowseEvents,
      meta: {
        requiresAuth: false
      }
    },
    {
      path:"/browseEvents/single",
      name: "event-view",
      component: EventView,
      props: true,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/postEvent",
      name: "postEvent",
      component: PostEvent,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/createProfile",
      name: "create-profile",
      component: CreateProfile,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: "/createOrganization",
      name: "create-organization",
      component: CreateOrganization,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/organizationProfile",
      name: "organization-profile",
      component: OrganizationProfile,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const user = auth.getUser();

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && !user) {
    next("/welcome");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
