export class Detail {
    id?: number;
    date?: string;
    checkIn?: string;
    checkOut?: string;

    constructor(id?: number, date?: string, checkIn?: string, checkOut?: string){
        this.id = id;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    setId(id: number){
        this.id = id;
    }

    setDate(date: string){
        this.date = date;
    }

    setCheckIn(checkIn: string){
        this.checkIn = checkIn;
    }

    setCheckOut(checkOut: string){
        this.checkOut = checkOut;
    }
}
