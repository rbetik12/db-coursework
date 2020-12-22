export interface ItemType {
    id: number;
    name: string;
    parent: ItemType;
    children: ItemType[];
}
