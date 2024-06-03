import { useState } from "react";
import { Tab, Tabs } from "react-bootstrap";
import   './auth.css';
import Login from "./Login";
import Register from "./Register";

const AuthLayout = () => {

    const [active, setActive] = useState('login');

    return(
    <Tabs
            activeKey={active}
            className="mb-3 auth-layout"
            id="controlled-tab-example"
            onSelect={(k) => setActive(k)} 
        >
        <Tab 
            eventKey="login" 
            title="Login"
            >
            <Login/>
        </Tab>

        <Tab eventKey="register" 
                title="Register"
                >
           <Register/>
        </Tab>

    </Tabs>
    );
}

export default AuthLayout;