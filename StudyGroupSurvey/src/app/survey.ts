export class Survey {
    public employee: {firstName: string, lastName: string};
    public responses: {qId: string, rId: number}[];

    constructor(firstName: string, lastName: string, responses: {qId: string, rId: number}[]) {
        this.employee = {firstName: firstName, lastName: lastName}
        this.responses = responses;
    }

    getName() {
        return this.employee;
    }

    getResults() {
        return this.responses;
    }

    addResult(qId: string, rId: number) {
        this.responses.push({qId:qId, rId:rId});
    }

    setName(firstName: string, lastName: string) {
        this.employee.firstName = firstName;
        this.employee.lastName = lastName;
    } 
}