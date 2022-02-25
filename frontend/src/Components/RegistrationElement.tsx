import {FormEvent, useState} from "react";
import {Button, FormControl, InputLabel, Link, MenuItem, Select, SelectChangeEvent, TextField} from "@mui/material";
import axios from "axios";
import "./RegistrationElement.css"
import logo from "../Pictures/cigarette.png";


export default function RegistrationElement() {
    const [userEmail, setUserEmail] = useState("");
    const [userPassword1, setUserPassword1] = useState("");
    const [userPassword2, setUserPassword2] = useState("");
    const [userName, setUserName] = useState("");
    const [userAge, setUserAge] = useState(0);
    const [userSmoked, setUserSmoked] = useState(0);
    const [userYearsSmoked, setUserYearsSmoked] = useState(0);
    const [userSmokeCategory, setUserSmokeCategory] = useState("1");

    const [, setServerError] = useState("")
    const [, setPasswordError] = useState("")
    const [, setEmailError] = useState("")
    const [, setLoading] = useState(true || false)

    const dropDownHandleChange = (e: SelectChangeEvent) => {
        setUserSmokeCategory(e.target.value)
    }

    const submitHandler = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        if (validatePasswords(userPassword1, userPassword2) && validateEmail(userEmail)) {
            setLoading(true)
            const credentials = {
                "email": userEmail,
                "name": userName,
                "password": userPassword1,
                "matchingPassword": userPassword2,
                "age": userAge,
                "cigarettesSmokedEachDayLastYear": userSmoked,
                "yearsSmoked": userYearsSmoked,
                "cigarettesBranchCategory": userSmokeCategory
            }
            axios.post("/user/register", credentials)
                .then((response) => response.data)
                .then((data) => {
                    setServerError("")
                    return data
                })
                .then((data) => {
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

    const validateEmail = (email: string) => {
        if (!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) {
            setEmailError("Email not valid")
            return false
        }
        setEmailError("")
        return true
    }

    const validatePasswords = (password1: string, password2: string) => {
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
        if (!/[A-Z]/.test(password1)) {
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
            <h1>Un<img src={logo} alt={"cigarette"} style={{"width": "40px"}}/>ddict</h1>
            <form onSubmit={submitHandler}>
                <TextField required variant="outlined" label="E-Mail" type="email" value={userEmail}
                           onChange={(e) => setUserEmail(e.target.value)}/>
                <TextField required variant="filled" label="Password" type="password" value={userPassword1}
                           onChange={(e) => setUserPassword1(e.target.value)}/>
                <TextField required variant="filled" label="Repeat Password" type="password" value={userPassword2}
                           onChange={(e) => setUserPassword2(e.target.value)}/>
                <TextField required variant="outlined" label="Name" type="name" value={userName}
                           onChange={(e) => setUserName(e.target.value)}/>
                <TextField required variant="outlined" label="Age" type="age" value={userAge}
                           onChange={(e) => setUserAge(parseInt(e.target.value))}/>
                <TextField required variant="outlined"
                           label="How many cigarettes each day?"
                           type="cigarettesSmokedEachDayLastYear" value={userSmoked}
                           onChange={(e) => setUserSmoked(parseInt(e.target.value))}/>
                <TextField required variant="outlined" label="Years smoking" type="yearsSmoked" value={userYearsSmoked}
                           onChange={(e) => setUserYearsSmoked(parseInt(e.target.value))}/>
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Cigarette Branch</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={userSmokeCategory}
                        label="Age"
                        onChange={dropDownHandleChange}
                    >
                        <MenuItem value={1}>Premium (Marlboro, Lucky Strike, etc.)</MenuItem>
                        <MenuItem value={2}>Discounter (Boston, Edison, Giants, etc.)</MenuItem>
                        <MenuItem value={3}>Selfmade</MenuItem>
                    </Select>
                </FormControl>
                <Button type="submit" variant="outlined">Register</Button>
            </form>
            <Link href="/" underline="hover">
                {'Already signed up? - Here´s the Login'}
            </Link>
        </div>
    );
}