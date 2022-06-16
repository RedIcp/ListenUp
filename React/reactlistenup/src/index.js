import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {AuthProvider} from "./Context/AuthProvider";
import AdminMain from "./AdminAccess/Component/AdminMain";
import CustomerMain from "./CustomerAccess/Component/CustomerMain";
import App from "./App";


ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
          <AuthProvider>
              <Routes>
                  <Route path="/*" element={<App />} />
              </Routes>
          </AuthProvider>
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

