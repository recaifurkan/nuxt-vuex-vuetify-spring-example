<template>
  <div>
    Hello

    <div v-if="$auth.loggedIn">
      <span>{{ $auth.user.username }} - {{ $auth.user.email }}</span>

      <v-btn @click="$auth.logout()">
        Çıkış yap
      </v-btn>
    </div>

    <v-btn v-else @click="$router.push('/auth')">
      Giriş Yap
    </v-btn>
  </div>
</template>
