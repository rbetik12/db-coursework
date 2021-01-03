import {Item} from './item.model';

export interface FactoryInputItem {
    id: number;
    item: Item;
    next: FactoryInputItem | null;
}
