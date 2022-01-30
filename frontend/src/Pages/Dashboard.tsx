import Header from "../Components/Header";
import ContainerLifetimeGained from "../Components/ContainerLifetimeGained";
import ContainerSavedMoney from "../Components/ContainerSavedMoney";
import ContainerTimeSmokeFree from "../Components/ContainerTimeSmokeFree";
import ContainerNonSmokedCigarettes from "../Components/ContainerNonSmokedCigarettes";
import NavigationElement from "../Components/NavigationElement";

export default function Dashboard(){
    return(
        <div>
            <Header/>
            <div className={"navBox"}>
                <NavigationElement/>
            </div>
           <div className={"ContainerArea"}>
               <ContainerLifetimeGained/>
               <ContainerSavedMoney/>
               <ContainerTimeSmokeFree/>
               <ContainerNonSmokedCigarettes/>
           </div>
        </div>
    );
}