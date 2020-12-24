import {Clan} from './clan.model';

export interface Actor {
    id: number;
    clan: Clan;
    type: string;
    rating: number;
    name: string;
}
