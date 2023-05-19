<template>
  <h1>{{ helloMsg }}</h1>
  <h2>User's list:</h2>
  <h2>{{ status }}</h2>

  <form @submit.prevent="postData" method="post">
    <div class="mb-3 row">
      <label for="userInput1" class="col-sm-2 col-form-label">Name</label>
      <div class="col-sm-10">
        <input type="text" id="userInput1" class="form-control" name="user" v-model="newUser.name">
      </div>
    </div>
    <div class="mb-3 row">
      <label for="userInput2" class="col-sm-2 col-form-label">Password</label>
      <div class="col-sm-10">
        <input type="text" id="userInput2" class="form-control" name="password" v-model="newUser.password">
      </div>
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-3">Add</button>
    </div>
  </form>


  <div>
    <table class="table .table-striped">
      <thead>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>remove</th>
      </thead>
      <tbody>
        <tr v-for="user in users">
          <td>{{ user.id }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.password }}</td>
          <td><button @click.prevent="removeOwners(user.id)" type="button" class="btn btn-danger mb-3">Remove</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'UserList',
  data() {
    return {
      helloMsg: '',
      status: '',
      users: [],
      newUser: {
        name: null,
        password: null
      }
    }
  },
  methods: {
    postData() {
      fetch("/api/owners/add", {
        method: "POST",
        headers: {
          "Content-type": "application/json"
        },
        body: JSON.stringify(this.newUser)
      })
        .then((response) => {
          if (response.status != 201) {
            this.status = "User succesfully added";
          } else {
            this.status = "Error: " + response.status;
          }
        })
        .then(() => this.getOwners());
    },
    getOwners() {
      fetch("/api/owners/get")
        .then((response) => response.text())
        .then((data) => { this.users = JSON.parse(data); });
    },
    getHello() {
      fetch("/api/hello")
        .then((response) => response.text())
        .then((data) => { this.msg = data; });
    },
    removeOwners(id) {
      fetch("/api/owners/delete/" + id, { method: "DELETE" })
        .then((response) => {
          if (response.status != 200) {
            this.status = response.status;
          } else {
            this.status = "User succesfully deleted";
          }
        }
        )
        .then(() => this.getOwners());
    }
  },
  mounted() {
    this.getHello();
    this.getOwners();
  }
}
</script>