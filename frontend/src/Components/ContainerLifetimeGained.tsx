import {useEffect, useState} from "react";
import axios from "axios";

export default function ContainerLifetimeGained(){

    const [gainedLifetime, setGainedLifetime] = useState(0)

    useEffect(() => {
        axios.get('/api/lifetime')
            .then(response => response.data)
            .then(gainedLifetime => setGainedLifetime(gainedLifetime))
            .catch(error => console.error(error))
    }, [])

    return(
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <h3>{gainedLifetime} Days</h3>
                <h6>Lifetime gained</h6>
            </div>
        </div>
    )
}