import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import App from './App';
import { MainContextProvider } from './contexts/MainContext';
import MainLayout from './layout/MainLayout'
import AddTopic from './pages/AddTopic';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </React.StrictMode>
)
