import {Region} from './region.model';

export interface Clan {
    id: number | null;
    name: string;
    type: string;
    rating: number;
    region: Region;
}
