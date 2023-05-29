<template>
  <h1>{{ helloMsg }}</h1>
  <h2>List of events:</h2>
  <h2>{{ status }}</h2>

  <form @submit.prevent="postData" method="post">
    <div class="mb-3 row">
      <label for="ownerInput" class="col-sm-2 col-form-label">User</label>
      <div class="col-sm-10">
        <select v-model="newEvent.user" class="form-select" id="ownerInput">
          <option disabled value="">Select owner</option>
          <option v-for="owner in owners" :value="owner">{{ owner.name }}</option>
        </select>
      </div>
    </div>
    <div class="mb-3 row">
      <label for="eventTypeInput" class="col-sm-2 col-form-label">Event Type</label>
      <div class="col-sm-10">
        <select v-model="newEvent.title" class="form-select" id="eventTypeInput">
          <option disabled value="">Select event type</option>
          <option v-for="eventType in eventTypes" :value="eventType">{{ eventType }}</option>
        </select>
      </div>
    </div>
    <div class="mb-3 row">
      <label for="descriptionInput" class="col-sm-2 col-form-label">Description</label>
      <div class="col-sm-10">
        <input type="text" id="descriptionInput" class="form-control" v-model="newEvent.description">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="startDateInput" class="col-sm-2 col-form-label">Start Date</label>
      <div class="col-sm-10">
        <input type="date" id="startDateInput" class="form-control" v-model="newEvent.start">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="endDateInput" class="col-sm-2 col-form-label">End Date</label>
      <div class="col-sm-10">
        <input type="date" id="endDateInput" class="form-control" v-model="newEvent.end">
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
          <td>{{ event.title }}</td>
          <td>{{ event.description }}</td>
          <td>{{ event.start }}</td>
          <td>{{ event.end }}</td>
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
      owners: [],
      eventTypes: [],
      newEvent: {
        user: null,
        title: null,
        description: null,
        start: null,
        end: null
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
        .then(() => this.getEvents());

    },
    getEvents() {
      fetch("/api/events/get")
        .then((response) => response.text())
        .then((data) => {
          this.events = JSON.parse(data);
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
        .then(() => this.getEvents());
    },
    getOwners() {
      fetch("/api/owners/get")
        .then((response) => response.text())
        .then((data) => {
          this.owners = JSON.parse(data);
        });
    },
    getEventTypes() {
      fetch("/api/event-types/get")
        .then((response) => response.text())
        .then((data) => {
          this.eventTypes = JSON.parse(data);
          // this.helloMsg = this.eventTypes;
        });
    }
  },
  mounted() {
    this.getHello();
    this.getEvents();
    this.getOwners();
    this.getEventTypes();
  }
}
</script>