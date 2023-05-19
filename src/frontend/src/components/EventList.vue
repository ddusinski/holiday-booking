<template>
  <h1>{{ helloMsg }}</h1>
  <h2>Event's list:</h2>
  <h2>{{ status }}</h2>

  <form @submit.prevent="postData" method="post">
    <div class="mb-3 row">
      <label for="userInput1" class="col-sm-2 col-form-label">User</label>
      <div class="col-sm-10">
        <input type="text" id="userInput1" class="form-control" v-model="newEvent.user">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userInput2" class="col-sm-2 col-form-label">Event Type</label>
      <div class="col-sm-10">
        <input type="text" id="userInput2" class="form-control" v-model="newEvent.eventType">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userInput3" class="col-sm-2 col-form-label">Description</label>
      <div class="col-sm-10">
        <input type="text" id="userInput3" class="form-control" v-model="newEvent.description">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userInput3" class="col-sm-2 col-form-label">Start Date</label>
      <div class="col-sm-10">
        <input type="text" id="userInput3" class="form-control" v-model="newEvent.startDate">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userInput3" class="col-sm-2 col-form-label">End Date</label>
      <div class="col-sm-10">
        <input type="text" id="userInput3" class="form-control" v-model="newEvent.endDate">
      </div>
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-3">Add Event</button>
    </div>
  </form>


  <div>
    <table class="table .table-striped">
      <thead>
        <th>id</th>
        <th>user</th>
        <th>Event Type</th>
        <th>description</th>
        <th>startDate</th>
        <th>endDate</th>
      </thead>
      <tbody>
        <tr v-for="event in events">
          <td>{{ event.id }}</td>
          <td>{{ event.user.name }}</td>
          <td>{{ event.eventType }}</td>
          <td>{{ event.description }}</td>
          <td>{{ event.startDate }}</td>
          <td>{{ event.endDate }}</td>
          <td><button @click.prevent="removeEvents(event.id)" type="button" class="btn btn-danger mb-3">Remove</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'EventList',
  data() {
    return {
      helloMsg: '',
      status: '',
      events: [],
      newEvent: {
        user: null,
        eventType: null,
        description: null,
        startDate: null,
        endDate: null
      }
    }
  },
  methods: {
    postData() {
      fetch("/api/events/add", {
        method: "POST",
        headers: {
          "Content-type": "application/json"
        },
        body: JSON.stringify(this.newEvent)
      })
        .then((response) => {
          if (response.status != 201) {
            this.status = "Event succesfully added";
          } else {
            this.status = "Error: " + response.status;
          }
        })
        .then(() => this.getevents());
    },
    getEvents() {
      fetch("/api/events/get")
        .then((response) => response.text())
        .then((data) => {
          this.events = JSON.parse(data);
          this.helloMsg = data
        });
    },
    getHello() {
      fetch("/api/hello")
        .then((response) => response.text())
        .then((data) => { this.msg = data; });
    },
    removeEvents(id) {
      fetch("/api/events/delete/" + id, { method: "DELETE" })
        .then((response) => {
          if (response.status != 200) {
            this.status = response.status;
          } else {
            this.status = "Event succesfully deleted";
          }
        }
        )
        .then(() => this.getevents());
    }
  },
  mounted() {
    this.getHello();
    this.getEvents();
  }
}
</script>