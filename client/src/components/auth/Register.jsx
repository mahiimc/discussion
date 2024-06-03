import { Button, Form } from "react-bootstrap";
import './auth.css';

const Register = () => {



    return (
        <Form className="login-form">
            <Form.Group className="mb-3" controlId="rg-fname">
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="Enter first name" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="rg-lname">
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Enter last name" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="rg-email">
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" placeholder="Enter email" />
            </Form.Group>
            
            <Form.Group className="mb-3" controlId="rg-pass">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Enter password" />
            </Form.Group>

            <Form.Group className="mb-3" controlId="rg-cpass">
                <Form.Label>Confirm Password</Form.Label>
                <Form.Control type="password" placeholder="Re-enter password" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Register
            </Button>
        </Form>
    );
}

export default Register;