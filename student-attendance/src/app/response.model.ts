import { Student } from "./student.model";

export interface Response<T>{
    status? : number;
    message? : string;
    data? : T;
}