import React from "react";
import "react-step-progress-bar/styles.css";
// @ts-ignore
import { ProgressBar, Step} from "react-step-progress-bar";
import "./DevelopingProgressBar.css"


export default class DevelopingProgressBar extends React.Component {
    render() {

        return (
            <div className={"designBar"}>
            <ProgressBar
                percent={20}
                stepPositions={[0, 8, 17, 27, 38, 55, 70, 100]}
                filledBackground="linear-gradient(to right, #fefb72, #f0bb31)"
            >
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://upload.wikimedia.org/wikipedia/commons/6/6b/No_Smoking.svg"
                            alt={"Stop smoking, the Journey begins"}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons-png.flaticon.com/512/1593/1593587.png"
                            alt={"8hr, better o2 in blood"}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons.flaticon.com/png/512/2839/premium/2839214.png?token=exp=1645545404~hmac=1a0c4397a36c429be6e97cb569db9bf9"
                            alt={"24hr, -risk for Heartattack"}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons.flaticon.com/png/512/2416/premium/2416825.png?token=exp=1645545477~hmac=38aefce5aba7fb321f122e137423a935"
                            alt={"48hr, better taste and sense of smell"}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons.flaticon.com/png/512/3899/premium/3899600.png?token=exp=1645545563~hmac=14317ffebc996d283b45e9b56f8fc269"
                            alt={"72hr, you did the heaviest lifting, the first 3 days are the hardest!"}
                        />
                    )}
                </Step>
                <Step transition="scale" position={60}>
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons-png.flaticon.com/512/2534/2534929.png"
                            alt={"3 Weeks, all cravings should be gone. Welcome to your new Life <3"}
                        />
                    )}
                </Step>
                <Step transition="scale" position={60}>
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons-png.flaticon.com/512/4807/4807703.png"
                            alt={"1y, your risk of 'verengung der Blutgefäße' reduces"}
                        />
                    )}
                </Step>
                <Step transition="scale">
                    {({ accomplished }:any) => (
                        <img
                            style={{ filter: `grayscale(${accomplished ? 0 : 80}%)` }}
                            width="30"
                            src="https://cdn-icons.flaticon.com/png/512/1184/premium/1184726.png?token=exp=1645545779~hmac=4c7d67e606ab7371a3df792c385d56fa"
                            alt={"5y, your risk for Heartattack is extremely lowered, you claimed all Lifetime back, that you could, good Job!"}
                        />
                    )}
                </Step>
            </ProgressBar>
            </div>
        );
    }
}