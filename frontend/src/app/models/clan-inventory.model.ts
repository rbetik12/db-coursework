import {Clan} from './clan.model';
import {Item} from './item.model';

export interface ClanInventory {
    clan: Clan;
    item: Item;
    amount: number;
}
