import {useEffect, useState} from "react";
import axios from "axios";

export default function ContainerTimeSmokeFree(){
    const [timeSmokeFree, setTimeSmokeFree] = useState(0)

    useEffect(() => {
        axios.get('/api/timesmokefree')
            .then(response => response.data)
            .then(timeSmokeFree => setTimeSmokeFree(timeSmokeFree))
            .catch(error => console.error(error))
    }, [])

    return(
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <h3>{timeSmokeFree} Days</h3>
                <h6>Without smoking</h6>
            </div>
        </div>
    )
}