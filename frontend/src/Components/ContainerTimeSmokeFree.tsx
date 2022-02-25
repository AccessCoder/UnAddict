import {useEffect, useState} from "react";
import {getTimeSmokeFree} from "../Service/AxiosServiceToBackend";
import time from "../Pictures/stopwatch.png";

export default function ContainerTimeSmokeFree() {
    const [timeSmokeFree, setTimeSmokeFree] = useState("0")

    useEffect(() => {
        getTimeSmokeFree()
            .then(timeSmokeFree => setTimeSmokeFree(timeSmokeFree))
            .catch(error => console.error(error))
    }, [])

    return (
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <img src={time} alt={"QuitSmoke"} style={{"width": "40px"}}/>
                <h3>{timeSmokeFree}</h3>
                <h6>Without smoking</h6>
            </div>
        </div>

    )
}