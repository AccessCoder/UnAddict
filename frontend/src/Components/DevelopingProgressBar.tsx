import React, {useState} from "react";
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


export default class DevelopingProgressBar extends React.Component {

    render() {

        return (
            <div className={"designBar"}>
            <ProgressBar
                percent={60}
                stepPositions={[0, 8, 17, 27, 38, 55, 70, 100]}
                filledBackground="linear-gradient(to right, #fefb72, #f0bb31)"
            >
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://upload.wikimedia.org/wikipedia/commons/6/6b/No_Smoking.svg"
                            alt={""}
                            onClick={() => alert("You did the right choice! This is the First Step, into your Smokefree life")}
                            />)}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src={o2}
                            alt={"8hr, better o2 in blood"}
                            onClick={() => alert("You did the right choice! This is the First Step, into your Smokefree life")}
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
                            onClick={() => alert("After long 5 Years, you finally gained back all Lifetime you could. But additionally, look how much Money you saved and be proud of yourself, because you finished this quest for a better life")}
                        />
                    )}
                </Step>
            </ProgressBar>
            </div>
        );
    }
}