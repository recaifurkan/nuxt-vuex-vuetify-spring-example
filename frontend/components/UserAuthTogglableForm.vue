<template>
  <div>
    <div v-if="authScreen == 'login'">
      <h1>{{ loginPhrase }}</h1>

      <SpinnerIfLoading :loading="loading">
        <p>
          New User?
          <a @click="changeScreen('register')">Click here to register.</a>
        </p>
        <UserAuthForm buttonText="Login" :submitForm="loginUser" />
      </SpinnerIfLoading>
    </div>
    <div v-else>
      <h1>{{ registerPhrase }}</h1>
      <SpinnerIfLoading :loading="loading">
        <p>
          Existing User?
          <a @click="changeScreen('login')">Click here to login.</a>
        </p>
        <UserAuthForm
          buttonText="Register"
          :submitForm="registerUser"
          :hasName="true"
          :registrationCheckboxes="true"
        />
      </SpinnerIfLoading>
    </div>
  </div>
</template>

<script>
import UserAuthForm from "@/components/UserAuthForm.vue";
import SpinnerIfLoading from "@/components/SpinnerIfLoading.vue";
import { mapActions } from "vuex";

export default {
  data() {
    let authScreen = this.$route.query.authScreen || this.startingScreen;
    return {
      authScreen,
      loading: false
    };
  },
  components: {
    UserAuthForm,
    SpinnerIfLoading
  },
  methods: {
    ...mapActions("localAuth", ["setUserData"]),
    async loginUser(loginInfo) {
      const request = {
        ...loginInfo
      };
      console.log(request);
      this.loading = true;

      try {
        await this.$auth.loginWith("local", {
          data: request
        });

        this.setUserData({
          user: this.$auth.user,
          token: this.$auth.getToken("local")
        });

        this.postLoginAction();
      } catch (error) {
        console.error(error);
      }

      this.loading = false;
    },
    async registerUser(registrationInfo) {
      this.loading = true;

      let request = {
        ...registrationInfo
      };

      try {
        await this.$axios.post("/users/signup", request);

        await this.$auth.loginWith("local", {
          data: request
        });
        this.$store.dispatch("snackbar/setSnackbar", {
          text: `Thanks for signing up, ${this.$auth.user.name}`
        });

        this.postRegisterAction();
      } catch {
        this.$store.dispatch("snackbar/setSnackbar", {
          color: "red",
          text: "There was an issue signing up.  Please try again."
        });
      }

      this.loading = false;
    },
    changeScreen(newScreen) {
      this.authScreen = newScreen;

      this.$router.replace({
        query: {
          ...this.$route.query,
          authScreen: newScreen
        }
      });
    }
  },
  props: {
    registerPhrase: {
      type: String,
      default: "Register"
    },
    loginPhrase: {
      type: String,
      default: "Login"
    },
    postRegisterAction: {
      type: Function,
      default: () => {}
    },
    postLoginAction: {
      type: Function,
      default: () => {}
    },
    startingScreen: {
      type: String,
      default: "login"
    }
  }
};
</script>

<style lang="scss" scoped></style>
