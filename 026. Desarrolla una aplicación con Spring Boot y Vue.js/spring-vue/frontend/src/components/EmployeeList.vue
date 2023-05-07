<template>

  <div class="container text-center">
    <div class="row">
      <div class="col-2">

      </div>
      <div class="col-8">
        <h2>Employee List</h2>
        <ul class="list-group">
          <li class="list-group-item"
              v-for="(employee, index) in employees" :key="index">
            {{employee.id}} - {{employee.name}} - {{employee.role}}
          </li>
        </ul>

        <table class="table">
          <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(employee, index) in employees" :key="index">
            <th scope="row">{{employee.id}}</th>
            <td>{{employee.name}}</td>
            <td>{{employee.role}}</td>
          </tr>
          </tbody>
        </table>


      </div>


      <div class="col-2">

      </div>
    </div>
  </div>

  <router-view></router-view>
</template>

<script>
import EmployeeService from '@/services/EmployeeService';
export default {
  name: "EmployeeList",
  data() {
    return {
      employees: []
    }
  },
  methods: {
    fetchEmployees() {
      EmployeeService.findAll().then(response => {
        this.employees = response.data;
        console.log(response.data);
      }).catch(error => {console.log(error)});
    }

  },
  mounted(){
    this.fetchEmployees();
  }
}
</script>

<style scoped>

</style>