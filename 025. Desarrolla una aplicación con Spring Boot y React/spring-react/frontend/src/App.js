import logo from './logo.svg';
import './App.css';
import EmployeeList from './components/EmployeeList'
import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom'

function App() {
  return (
    <div>
        <h1>React - Spring Boot</h1>
        <Router>
            {/*<Suspense fallback={<div>...Loading...</div>}*/}
            <Routes>
                <Route path="/" element={<Navigate to="/employees"/>}/>
                <Route exact path="/employees" element={<EmployeeList/>}/>
            </Routes>
        </Router>

    </div>
  );
}

export default App;
