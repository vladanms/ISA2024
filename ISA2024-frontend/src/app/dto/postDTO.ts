import { commentDTO } from "./commentDTO";


export class postDTO{
	id !: number;
	owner !: string;
	likes!: string[];
	comments!: commentDTO[];
	imagePath!: string;
	time!: string;
	content!: string;
	location_x!: number;
	location_y!: number	
}