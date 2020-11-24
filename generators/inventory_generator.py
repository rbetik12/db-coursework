import random

from generators.abstractgenerator import AbstractGenerator


class InventoryGenerator(AbstractGenerator):

    def __init__(self, actorIds: list, itemIds: list):
        super()
        super().__init__()
        self.actorIds = actorIds
        self.itemIds = itemIds

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.queries += f"insert into inventory(actor_id, item_id, amount)" \
                            f" values ({self.get_random_actor_id()}, {self.get_random_item_id()}, " \
                            f"{random.randint(1, 100)});\n"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return []

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_item_id(self):
        return random.choice(self.itemIds)
