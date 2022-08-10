import './App.css';
import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Root from "./views/Root";
import SQLInjectionScanner from "./views/SQLInjectionScanner";
import store from "./store";
import GlobalStyle from "./theme/GlobalStyle";
import { Provider } from "react-redux";
import { ThemeProvider } from "styled-components";
import QuickScan from "./views/QuickScan";

const theme = {
  primary: 'yellow'
}

function App() {
  return (
    <Provider store={store}>
      <GlobalStyle />
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Root />} />
            <Route path='/home' element={<Root />} />
            <Route path='/quick-scan' element={<QuickScan />} />
            <Route path='/sql-injection-scanner' element={<SQLInjectionScanner />} />
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
  );
}

export default App;
