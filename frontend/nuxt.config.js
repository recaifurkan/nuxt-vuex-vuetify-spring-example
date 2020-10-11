import colors from "vuetify/es5/util/colors";
import i18n from "./i18n";

export default {
  // Global page headers (https://go.nuxtjs.dev/config-head)
  router: {
    middleware: []
  },

  // plugins: ["plugins/i18n"],
  head: {
    titleTemplate: "%s - test",
    title: "test",
    meta: [
      { charset: "utf-8" },
      { name: "viewport", content: "width=device-width, initial-scale=1" },
      { hid: "description", name: "description", content: "" }
    ],
    link: [{ rel: "icon", type: "image/x-icon", href: "/favicon.ico" }]
  },

  // Global CSS (https://go.nuxtjs.dev/config-css)
  css: [],

  // Auto import components (https://go.nuxtjs.dev/config-components)
  components: true,

  // Modules for dev and build (recommended) (https://go.nuxtjs.dev/config-modules)
  buildModules: [
    // https://go.nuxtjs.dev/vuetify
    "@nuxtjs/vuetify"
  ],

  // Modules (https://go.nuxtjs.dev/config-modules)
  modules: ["@nuxtjs/axios", "@nuxtjs/auth", ["nuxt-i18n", i18n]],

  auth: {
    redirect: {
      login: "/auth",

      home: "/"
    },
    strategies: {
      local: {
        endpoints: {
          login: {
            url: "http://localhost:8080/users/signin",
            method: "post",
            propertyName: "data"
          },
          logout: false,
          user: {
            url: "http://localhost:8080/users/me",
            method: "get",
            propertyName: false // <--- Default "user"
          }
        },
        tokenRequired: true,
        tokenType: "Bearer",
        globalToken: true,
        autoFetchUser: true
      }
    }
  },

  // Vuetify module configuration (https://go.nuxtjs.dev/config-vuetify)
  vuetify: {
    customVariables: ["~/assets/variables.scss"],
    theme: {
      dark: true,
      themes: {
        dark: {
          primary: colors.blue.darken2,
          accent: colors.grey.darken3,
          secondary: colors.amber.darken3,
          info: colors.teal.lighten1,
          warning: colors.amber.base,
          error: colors.deepOrange.accent4,
          success: colors.green.accent3
        }
      }
    }
  },

  // Build Configuration (https://go.nuxtjs.dev/config-build)
  build: {}
};
