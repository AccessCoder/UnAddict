import {useEffect, useState} from "react";
import {getNonSmoked} from "../Service/AxiosServiceToBackend";
import smokeFree from "../Pictures/quit-smoking.png"

export default function ContainerNonSmokedCigarettes(){

    const [nonSmoked, setNonSmoked] = useState(0)

    useEffect(() => {
        getNonSmoked()
            .then(nonSmoked => setNonSmoked(nonSmoked))
            .catch(error => console.error(error))
    }, [])

    return(
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <img src={smokeFree} alt={"QuitSmoke"} style={{"width": "40px"}}/>
                <h3>{nonSmoked} Cigarettes</h3>
                <h6>Not smoked!</h6>
            </div>
        </div>

    )
}