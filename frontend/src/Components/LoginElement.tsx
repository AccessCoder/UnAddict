import {Button, Link, TextField} from "@mui/material";
import {useState} from "react";
import ILoginData from "../Models/ILoginData";
import {postLogin, TOKEN_STORAGE_KEY} from "../Service/AxiosServiceToBackend";
import "./LoginElement.css"
import logo from "../Pictures/cigarette.png"

export default function LoginElement() {
    const [userEMail, setUserEMail] = useState("");
    const [userPassword, setUserPassword] = useState("");

    const login = () => {
        const loginData: ILoginData = {email: userEMail, password: userPassword}
        postLogin(loginData)
            .then(response => localStorage.setItem(TOKEN_STORAGE_KEY, response.data))
            .then(() => window.location.href = "/dashboard")
            .catch(error => console.error(error))
    }

    return (
        <div className={"loginComponentsMaster"}>
            <h1>Un<img src={logo} alt={"cigarette"} style={{"width": "40px"}}/>ddict</h1>
            <div className={"loginElements"}>
                <TextField variant="outlined" label="E-Mail" type="eMail" value={userEMail}
                           onChange={(e) => setUserEMail(e.target.value)}/>
                <TextField variant="filled" label="Password" type="password" value={userPassword}
                           onChange={(e) => setUserPassword(e.target.value)}/>
            </div>
            <div className={"loginButton"}>
                <Button onClick={() => login()} variant="outlined">Login</Button>
                <br/>
                <Link href="registration" underline="hover">
                    {'New here? - Register now, for free!'}
                </Link>
            </div>
        </div>
    )
}