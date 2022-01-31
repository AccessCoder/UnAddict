import {useEffect, useState} from "react";
import axios from "axios";

export default function ContainerNonSmokedCigarettes(){

    const [nonSmoked, setNonSmoked] = useState(0)

    useEffect(() => {
        axios.get('/api/nonsmoked')
            .then(response => response.data)
            .then(nonSmoked => setNonSmoked(nonSmoked))
            .catch(error => console.error(error))
    }, [])

    return(
        <div className={"outerContainer"}>
            <div className={"textContainer"}>
                <h3>{nonSmoked} Cigarettes</h3>
                <h6>Not smoked!</h6>
            </div>
        </div>
    )
}