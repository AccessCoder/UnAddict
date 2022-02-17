import {useEffect, useState} from "react";
import {getLifetime} from "../Service/AxiosServiceToBackend";

export default function ContainerLifetimeGained() {

    const [gainedLifetime, setGainedLifetime] = useState("")

    useEffect(() => {
        getLifetime()
            .then(gainedLifetime => setGainedLifetime(gainedLifetime))
            .catch(error => console.error(error))
    }, [])

    return (
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <h3>{gainedLifetime}</h3>
                <h6>Lifetime gained</h6>
            </div>
        </div>
    )
}