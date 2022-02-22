import Header from "../Components/Header";
import ContainerLifetimeGained from "../Components/ContainerLifetimeGained";
import ContainerSavedMoney from "../Components/ContainerSavedMoney";
import ContainerTimeSmokeFree from "../Components/ContainerTimeSmokeFree";
import ContainerNonSmokedCigarettes from "../Components/ContainerNonSmokedCigarettes";
import NavigationElement from "../Components/NavigationElement";
import "./Dashboard.css"
import {
    FacebookShareButton,
    FacebookIcon,
    RedditShareButton,
    RedditIcon,
    TwitterShareButton,
    TwitterIcon,
} from "react-share";
import DevelopingProgressBar from "../Components/DevelopingProgressBar";

export default function Dashboard() {

    const targetUrl = process.env.REACT_APP_URL_PAGE_TO_SHARE || ""

    return (
        <div>
            <div className="head">
                <Header/>
            </div>
            <div className={"navBox"}>
                <NavigationElement/>
            </div>

            <div className={"ShareContent"}>
                <div className={"ContainerArea"}>
                    <div className={"box"}><ContainerLifetimeGained/></div>
                    <div className={"box"}><ContainerSavedMoney/></div>
                    <div className={"box"}><ContainerTimeSmokeFree/></div>
                    <div className={"box"}><ContainerNonSmokedCigarettes/></div>
                </div>
            </div>
            <div className={"shareArea"}>
                <FacebookShareButton url={targetUrl}><FacebookIcon size={32} round={true}/></FacebookShareButton>
                <RedditShareButton url={targetUrl}> <RedditIcon size={32} round={true}/> </RedditShareButton>
                <TwitterShareButton url={targetUrl}><TwitterIcon size={32} round={true}/></TwitterShareButton>
            </div>
            <div className="ProgressBar">
                <DevelopingProgressBar/>
            </div>
        </div>
    );
}