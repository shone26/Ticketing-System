import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./index.css";



import SignUpPage from "./pages/login page/sign-up.page";
import SignInPage from "./pages/sign page/sign-in.page";
import Home from "./pages/home page/home";
import ConfigurationPage from "./pages/configuration page/configuration.page";
import NewConfig from "./pages/configuration page/new.configration";
import StartSystemButton from "./pages/configuration page/start";
import CustomerDetailPage from "./pages/customer page/customer.detail";
import VendorDetailPage from "./pages/vendor page/vender.detail";
import TestPage from "./pages/customer page/test";



const router = createBrowserRouter([
  {
    path: "/",
    element: <Home/>,
  },
  {
    path: "/newConfiguration",
    element: <NewConfig/>,
  },
  {
    path: "configuration",
    element: <ConfigurationPage/>,
  },
  {
    path: "/sign-up",
    element: <SignUpPage/>,
  },
  {
    path: "/sign-in",
    element: <SignInPage/>,
  },
  {
    path: "/add",
    element: <StartSystemButton/>
  },
  {
    path: "/customer-details",
    element: <CustomerDetailPage/>,
  },
  {
    path: "/vendor-details",
    element: <VendorDetailPage/>,
  },
  {
    path: "/test",
    element: <TestPage/>,
  },


    
  
]); 



ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
