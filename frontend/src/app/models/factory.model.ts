import {Item} from './item.model';
import {FactoryInputItem} from './factory-input-item.model';

export interface Factory {
    id: number;
    type: string;
    productivity: number;
    inputItems: FactoryInputItem;
    outputItem: Item;
}
