import React from 'react';
import './App.css';
import RegistrationPage from "./Pages/RegistrationPage";
import LoginPage from "./Pages/LoginPage";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Dashboard from "./Pages/Dashboard";

export default function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path={"/login"} element={<LoginPage />} />
                <Route path={"/registration"} element={<RegistrationPage />} />
                <Route path={"/dashboard"} element={<Dashboard />} />
            </Routes>
        </BrowserRouter>
    </div>
  );
}


