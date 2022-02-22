import {useEffect, useState} from "react";
import {getLifetime} from "../Service/AxiosServiceToBackend";
import healing from "../Pictures/healing.png"

export default function ContainerLifetimeGained() {

    const [gainedLifetime, setGainedLifetime] = useState("")

    useEffect(() => {
        getLifetime()
            .then(gainedLifetime => setGainedLifetime(gainedLifetime))
            .catch(error => console.error(error))
    }, [])

    return (
        <div className={"outerContainer"} >
            <div className={"textContainer"}>
                <img src={healing} alt={"Heart"} style={{"width": "40px"}}/>
                <h3>{gainedLifetime}</h3>
                <h6>Lifetime gained</h6>
            </div>
        </div>

    )
}