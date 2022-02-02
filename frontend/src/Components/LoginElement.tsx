import {Button, Link, TextField} from "@mui/material";
import {useState} from "react";
import ILoginData from "../Models/ILoginData";
import {postLogin, TOKEN_STORAGE_KEY} from "../Service/AxiosServiceToBackend";

export default function LoginElement(){
    const [userEMail, setUserEMail] = useState("");
    const [userPassword, setUserPassword] = useState("");

    const login = () => {
        const login: ILoginData = {email: userEMail, password: userPassword}
        postLogin(login)
            .then(response => localStorage.setItem(TOKEN_STORAGE_KEY, response.data))
            .catch(error => console.error(error))
    }

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