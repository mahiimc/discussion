import AuthLayout from "../components/auth/AuthLayout";

const { createBrowserRouter } = require("react-router-dom");

const Routes = createBrowserRouter([
    {
        path: "/",
        element: <AuthLayout/>
    }
]);

export default Routes;