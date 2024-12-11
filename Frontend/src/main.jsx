import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./index.css";
import Home from "./pages/home page/home";
import NewConfig from "./pages/configuration page/new.configration";
import ConfigurationPage from "./pages/configuration page/configuration.page";
import CustomerDetailPage from "./pages/customer page/customer.detail";
import VendorDetailPage from "./pages/vendor page/vender.detail"; 
import LogViewer from "./pages/log/logviewer.page";









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
    path: "/configuration",
    element: <ConfigurationPage/>,
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
    path: "/log",
    element: <LogViewer/>,
  },
]); 



ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
