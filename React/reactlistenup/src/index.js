import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {AuthProvider} from "./Context/AuthProvider";
import Main from "./AdminAccess/Component/Main";


ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
          <AuthProvider>
              <Routes>
                  <Route path="/*" element={<Main />} />
              </Routes>
          </AuthProvider>
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

