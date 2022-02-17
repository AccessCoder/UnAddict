import Header from "../Components/Header";
import ContainerLifetimeGained from "../Components/ContainerLifetimeGained";
import ContainerSavedMoney from "../Components/ContainerSavedMoney";
import ContainerTimeSmokeFree from "../Components/ContainerTimeSmokeFree";
import ContainerNonSmokedCigarettes from "../Components/ContainerNonSmokedCigarettes";
import NavigationElement from "../Components/NavigationElement";
import "./Dashboard.css"

export default function Dashboard() {
    return (
        <div>
            <div className="head">
                <Header/>
            </div>
            <div className={"navBox"}>
                <NavigationElement/>
            </div>

            <div className={"ContainerArea"}>
                <div className={"box"}><ContainerLifetimeGained/></div>
                <div className={"box"}><ContainerSavedMoney/></div>
                <div className={"box"}><ContainerTimeSmokeFree/></div>
                <div className={"box"}><ContainerNonSmokedCigarettes/></div>
            </div>
        </div>
    );
}