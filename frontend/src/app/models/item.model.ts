import {ItemType} from './itemtype.model';

export interface Item {
    id: number;
    type: ItemType | null;
    name: string;
    description: string;
}
