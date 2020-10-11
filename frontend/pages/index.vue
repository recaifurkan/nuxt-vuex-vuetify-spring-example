<template>
  <div>
    {{ $t("welcome") }}

    <div v-if="$auth.loggedIn">
      <span>{{ $auth.user.username }} - {{ $auth.user.email }}</span>

      <v-btn @click="$auth.logout()">
        {{ $t("logout") }}
      </v-btn>
    </div>

    <v-btn v-else @click="$router.push('/auth')">
      {{ $t("login") }}
    </v-btn>
  </div>
</template>
