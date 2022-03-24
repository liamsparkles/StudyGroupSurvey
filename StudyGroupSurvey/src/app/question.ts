export interface Question {
    questionId: string;
    questionText: string;
    responses: {rId: number, rText: string}[];
    answer: number;
}