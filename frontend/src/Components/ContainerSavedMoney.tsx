import {useEffect, useState} from "react";
import axios from "axios";

export default function ContainerSavedMoney() {

    const [savedMoney, setSavedMoney] = useState(0)

    useEffect(() => {
        axios.get('/api/savedmoney')
            .then(response => response.data)
            .then(savedMoney => setSavedMoney(savedMoney))
            .catch(error => console.error(error))
    }, [])

    return (
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <h3>{savedMoney} EURO</h3>
                <h6>Saved by not smoking</h6>
            </div>
        </div>
    )
}