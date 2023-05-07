import axios from "axios";

class EmployeeService {

    http = axios.create({
        baseURL: "http://localhost:8081/api/employees",
        headers: {
            "Content-type": "application/json"
        }
    });

    findAll(){
        return this.http.get("");
    }
    findById(id){
        return this.http.get(`/${id}`);
    }
    create(data){
        return this.http.post("", data);
    }

}

export default new EmployeeService();