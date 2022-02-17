import {useState} from "react";
import {Button, Link, TextField} from "@mui/material";

export default function RegistrationElement() {
    const [userEMail, setUserEMail] = useState("");
    const [userPassword, setUserPassword] = useState("");

    return (
        <div className={"registrationElements"}>
            <TextField variant="outlined" label="E-Mail" type="eMail" value={userEMail}/>
            <TextField variant="filled" label="Password" type="password" value={userPassword}/>
            <Button variant="outlined">Register</Button>
            <Link href="login" underline="hover">
                {'Already signed up? - Here´s the Login'}
            </Link>
        </div>
    );
}