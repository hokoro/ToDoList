import React from 'react';
import './App.css';
import {BrowserRouter as Router , Routes,Route} from "react-router-dom";
import Layout from './component/TSX/Layout';
import Home from './pages/Home';
import Login from './pages/Login';
import Signup from './pages/Signup';
import ToDoList from "./pages/ToDoList";
function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<Layout/>}>
                  <Route index element={<Home/>}/>
                  <Route path="/login" element={<Login/>}/>
                  <Route path="/Signup" element={<Signup/>}/>
                  <Route path="/ToDoList" element={<ToDoList/>}/>
              </Route>
          </Routes>
      </Router>

  );
}

export default App;
