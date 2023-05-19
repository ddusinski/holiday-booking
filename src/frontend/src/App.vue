<template>
  <UserList />
  <EventList />
</template>

<script>
import UserList from './components/UserList.vue'
import EventList from './components/EventList.vue'
export default {
  name: 'App',
  components: {
    UserList, EventList
  }
}
</script>

<style></style>