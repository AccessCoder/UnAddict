import {FormEvent, useState} from "react";
import {Button, FormControl, InputLabel, Link, MenuItem, TextField} from "@mui/material";
import { Select } from "@mui/material";
import axios from "axios";
// @ts-ignore
import {useHistory} from "react-router-dom";

export default function RegistrationElement() {
    const [userEmail, setUserEmail] = useState("");
    const [userPassword1, setUserPassword1] = useState("");
    const [userPassword2, setUserPassword2] = useState("");
    const [userName, setUserName] = useState("");
    const [userAge, setUserAge] = useState(0);
    const [userSmoked, setUserSmoked] = useState(0);
    const [userYearsSmoked, setUserYearsSmoked] = useState(0);
    const [userSmokeCategory, setUserSmokeCategory] = useState(0);
    const [serverError, setServerError] = useState("")
    const [passwordError, setPasswordError] = useState("")
    const [emailError, setEmailError] = useState("")
    const [loading, setLoading] = useState(true||false)
    const history = useHistory()

    const submitHandler = (event:FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        if (validatePasswords(userPassword1, userPassword2) && validateEmail(userEmail)) {
            setLoading(true)
            const credentials = {
                "email": userEmail,
                "name": userName,
                "password": userPassword1,
                "age":userAge,
                "cigarettesSmokedEachDayLastYear":userSmoked,
                "yearsSmoked":userYearsSmoked,
                "cigarettesBranchCategory":userSmokeCategory
            }
            axios.post("/user/register", credentials)
                .then((response) => response.data)
                .then((data) => {
                    setServerError("")
                    return data
                })
                .then((data) => {
                    history.push("/registration/done")
                    return data
                })
                .catch((error) => {
                    if (error.response?.data?.message !== undefined) {
                        setServerError(error.response.data.message)
                    }
                    console.error(error)
                })
                .finally(() => {
                    setLoading(false)
                })
        }
    }

    const validateEmail = (email:string) => {
        if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) {
            setEmailError("Email not valid")
            return false
        }
        setEmailError("")
        return true
    }

    const validatePasswords = (password1:string, password2:string) => {
        if (password1.length < 8) {
            setPasswordError("Password too short")
            return false
        }
        if (password1.length > 128) {
            setPasswordError("Password too long")
            return false
        }
        if (!/[a-z]/.test(password1)) {
            setPasswordError("lowercase letter required")
            return false
        }
        if (!/[A-Z]/.test(password1)){
            setPasswordError("uppercase letter required")
            return false
        }
        if (!/\d/.test(password1)) {
            setPasswordError("number required")
            return false
        }
        if (!/[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/.test(password1)) {
            setPasswordError("special character required")
            return false
        }
        if (password1 !== password2) {
            setPasswordError("Passwords do not match")
            return false
        }

        setPasswordError("")
        return true
    }

    return (
        <div className={"registrationElements"}>
            <form onSubmit={submitHandler}>
            <TextField variant="outlined" label="E-Mail" type="email" value={userEmail}/>
            <TextField variant="filled" label="Password" type="password" value={userPassword1}/>
            <TextField variant="filled" label="Repeat Password" type="repeat_password" value={userPassword2}/>
                <TextField variant="outlined" label="Name" type="name" value={userName}/>
                <TextField variant="outlined" label="Age" type="age" value={userAge}/>
                <TextField variant="outlined" label="How many cigarettes do you smoked each day approximately in the last year?" type="cigarettesSmokedEachDayLastYear" value={userSmoked}/>
                <TextField variant="outlined" label="Years smoking" type="yearsSmoked" value={userYearsSmoked}/>
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Age</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={userSmokeCategory}
                        label="Age"
                    >
                        <MenuItem value={1}>Premium (Marlboro, Lucky Strike, etc.)</MenuItem>
                        <MenuItem value={2}>Discounter (Boston, Edison, Giants, etc.)</MenuItem>
                        <MenuItem value={3}>Selfmade</MenuItem>
                    </Select>
                </FormControl>
            <Button variant="outlined">Register</Button>
            </form>
            <Link href="login" underline="hover">
                {'Already signed up? - Here´s the Login'}
            </Link>
        </div>
    );
}