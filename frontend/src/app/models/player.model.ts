import {Actor} from './actor.model';

export interface Player {
    id: number | null;
    email: string;
    password: string;
    username: string;
    actor: Actor | null;
}
