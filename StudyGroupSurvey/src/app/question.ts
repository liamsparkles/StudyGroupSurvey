export interface Question {
    questionId: number;
    questionText: string;
    responses: {rId: number, rText: string}[];
    answer: number;
}