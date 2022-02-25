import {useEffect, useState} from "react";
import {getUserName} from "../Service/AxiosServiceToBackend";

export default function Header() {

    const[nameOfUser, setNameOfUser]= useState("")

        useEffect(() => {
            getUserName()
                .then(nameOfUser => setNameOfUser(nameOfUser))
                .catch(error => console.error(error))
        }, [])

    return (
        <div>
            <h1> Hello {nameOfUser}, today´s a good day to reach your Goals!</h1>
        </div>
    )
}