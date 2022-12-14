import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {AuthProvider} from "./Context/AuthProvider";
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

