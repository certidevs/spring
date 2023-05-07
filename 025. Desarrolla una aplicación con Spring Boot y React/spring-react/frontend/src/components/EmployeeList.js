import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Link, useNative} from 'react-router-dom'

const EmployeeList = () => {

    const baseUrl = "http://localhost:8080/api/employees";
    const [employees, setEmployees] = useState([])

    const fetchEmployees = () => {
        axios.get(baseUrl).then(response => {
            console.log(response.data);
            setEmployees(response.data);
        }).catch(error => {
            console.log('Error fetching employee data ' + error);
        })
    }

    useEffect(() => {
        fetchEmployees()
    }, [])

    function removeEmployee(id) {
        console.log("Removing employee " + id)
    }

    return (
        <div className="container text-center">
            <div className="row">
                <div className="col">

                </div>
                <div className="col">

                    <table className="table">
                        <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Role</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        { employees && employees.map(employee => (
                            <tr>
                                <th scope="row">{employee.id}</th>
                                <td>{employee.name}</td>
                                <td>{employee.role}</td>
                                <td>
                                    <Link className="btn btn-success" to={"/edit/" + employee.id}>Editar</Link>
                                    <button className="btn btn-danger" onClick={() => removeEmployee(employee.id)}>Borrar</button>
                                </td>
                            </tr>
                        ))
                        }


                        </tbody>
                    </table>


                </div>
                <div className="col">

                </div>
            </div>
        </div>
    );

}
export default EmployeeList