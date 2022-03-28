import { createApp } from "vue"
import app from "./App.vue"
import router from "./router"
import store from "./store"
import vuetify from "./plugins/vuetify"
import { loadFonts } from "./plugins/webfontloader"
import { createAuth0 } from "@auth0/auth0-vue"

loadFonts()

let auth0 = createAuth0({
    domain: "dev-n5ogkgjf.eu.auth0.com",
    client_id: "tX5Z6cnCZvjDrPdseWxc3E34KnrnWfme",
    redirect_uri: window.location.origin,
})

createApp(app).use(router).use(store).use(vuetify).use(auth0).mount("#app")
