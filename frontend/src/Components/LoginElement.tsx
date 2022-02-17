import {Button, Link, TextField} from "@mui/material";
import {useState} from "react";

export default function LoginElement(){
    const [userEMail, setUserEMail] = useState("");
    const [userPassword, setUserPassword] = useState("");

    return (
        <div className={"loginElements"}>
            <TextField variant="outlined" label="E-Mail" type="eMail" value={userEMail}/>
            <TextField variant="filled" label="Password" type="password" value={userPassword} />
            <Button variant="outlined">Login</Button>
            <Link href="registration" underline="hover" >
                {'New here? - Register now, for free!'}
            </Link>
        </div>
    )
}