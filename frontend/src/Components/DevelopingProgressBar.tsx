import React from "react";
import "react-step-progress-bar/styles.css";
// @ts-ignore
import { ProgressBar, Step} from "react-step-progress-bar";
import "./DevelopingProgressBar.css"
import love from "../Pictures/love.png"
import o2 from "../Pictures/oxygen-tank.png"
import taste from "../Pictures/tongue.png"
import star from "../Pictures/star.png"
import cleanMind from "../Pictures/relief.png"
import bloodVessel from "../Pictures/blood-vessel.png"
import starStage from "../Pictures/starStage.png"
import swal from "sweetalert";


export default class DevelopingProgressBar extends React.Component {

    render() {

        return (
            <div className={"designBar"}>
            <ProgressBar
                percent={60}
                stepPositions={[0, 8, 17, 27, 38, 55, 70, 100]}
                filledBackground="linear-gradient(to right, #ffa640, #ff8c00)"
            >
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://upload.wikimedia.org/wikipedia/commons/6/6b/No_Smoking.svg"
                            alt={""}
                            onClick={() => swal({
                                title: "The Journey begins",
                                text: "You did the right choice! This is the First Step, into your Smokefree life!",
                                icon: "success",
                                buttons: [false]
                                })}
                        />)}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={o2}
                            alt={"8hr, better o2 in blood"}
                            onClick={() => swal({
                                title: "The first Milestone",
                                text: "You beat the first 8 Hours without smoking. The messurable amount of o2 in your Blood starts rising.",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={love}
                            alt={"24hr, -risk for Heartattack"}
                            onClick={() => swal({
                                title: "You will thank you later",
                                text: "You beat the first 24 Hours without smoking. The Risk for a Heartattack will begin to decrease!",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={taste}
                            alt={"48hr, better taste and sense of smell"}
                            onClick={() => swal({
                                title: "The first Thing to realise",
                                text: "You beat the first 48 Hours without smoking. Your sense of taste and smell should be better than before. Why not try your favourite food again?",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={star}
                            alt={"72hr, you did the heaviest lifting, the first 3 days are the hardest!"}
                            onClick={() => swal({
                                title: "The hardest Part is over",
                                text: "You beat the first 72 Hours without smoking. If you stood strong, the cravings for a cigarette should decrease drastically in the next days!",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={cleanMind}
                            alt={"3 Weeks, all cravings should be gone. Welcome to your new Life <3"}
                            onClick={() => swal({
                                title: "Easy mode from here on!",
                                text: "You beat 3 weeks without smoking. Statistically, you should have lost all cravings for cigarettes. You can already embrace your new Life. As long as you dont start smoking again, the last parts of this journey will run by in no time!",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={bloodVessel}
                            alt={"1y, your risk of 'verengung der Blutgefäße' reduces"}
                            onClick={() => swal({
                                title: "And there goes one Year!",
                                text: "1 year down! The messurable risk for the narrowing of blood vessels is decreased by a lot and will further decrease!",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={starStage}
                            alt={"5y, your risk for Heartattack is extremely lowered, you claimed all Lifetime back, that you could, good Job!"}
                            onClick={() => swal({
                                title: "The final Milestone, be proud of yourself!",
                                text: "You did it! 5 years down the pipe (pun intended). You gained back all the Lifetime you can from not smoking. Stay healthy!",
                                icon: "success",
                                buttons: [false]
                            })}
                        />
                    )}
                </Step>
            </ProgressBar>
            </div>
        );
    }
}