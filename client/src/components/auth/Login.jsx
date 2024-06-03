import { Button, Form } from "react-bootstrap";
import './auth.css';
import { useState } from "react";

const Login = () => {

    const [validated, setValidated] = useState(false);

    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        setValidated(true);
        console.log(form);
    }

    return (
        <Form noValidate validated={validated} onSubmit={handleSubmit} className="login-form">
            <Form.Group className="mb-3" controlId="lg-email">
                <Form.Label>Email</Form.Label>
                <Form.Control 
                    type="email" 
                    placeholder="Enter email"
                    required
                    name="email"
                    />
                    <Form.Control.Feedback type="invalid"/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="lg-pass">
                <Form.Label>Password</Form.Label>
                <Form.Control 
                    type="password" 
                    placeholder="Enter password" 
                    required
                    name="password"
                    />
                <Form.Control.Feedback type="invalid"/>
            </Form.Group>

            <Button variant="primary" type="submit">
                Login
            </Button>
        </Form>
    );
}

export default Login;