<template>
  <!-- <OwnerList /> -->
  <EventList />
  <FullCalendar />
</template>

<script>
import OwnerList from './components/OwnerList.vue'
import EventList from './components/EventList.vue'
import FullCalendar from './components/Calendar.vue'
export default {
  name: 'App',
  components: {
    OwnerList, EventList, FullCalendar
  }
}
</script>

<style></style>