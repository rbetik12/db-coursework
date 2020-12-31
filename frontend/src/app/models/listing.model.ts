import {Actor} from './actor.model';
import {Clan} from './clan.model';

export interface Listing {
    id: number;
    seller: string;
    actor: Actor | null;
    clan: Clan | null;
    description: string;
}
