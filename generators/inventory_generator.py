import random

from generators.abstractgenerator import AbstractGenerator


class InventoryGenerator(AbstractGenerator):

    def __init__(self, actorIds: list, itemIds: list):
        super()
        super().__init__()
        self.actorIds = actorIds
        self.itemIds = itemIds
        self.pickedItemActor = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            actorId = self.get_random_actor_id()
            itemId = self.get_random_item_id()
            if (actorId, itemId) in self.pickedItemActor:
                continue
            self.pickedItemActor.append((actorId, itemId))
            self.queries += f"insert into actor_inventory(actor_id, item_id, amount)" \
                            f" values ({actorId}, {itemId}, " \
                            f"{random.randint(1, 100)});\n"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return []

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_item_id(self):
        return random.choice(self.itemIds)
