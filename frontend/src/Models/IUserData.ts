export default interface IUserData{
    //unique ID for each User
    eMail:string;

    //User Data
    name:string;
    password:string;
    age:number;

    //api relevant Data
    cigarettesSmokedEachDayLastYear:number;
    yearsSmoked:number;
    cigarettesBranchCategory:number;
}