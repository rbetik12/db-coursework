import random

from generators.abstractgenerator import AbstractGenerator


class FactoryInputItemGenerator(AbstractGenerator):

    def __init__(self, itemIds: list):
        super()
        super().__init__()
        self.factoryInputItemIds = []
        self.itemIds = itemIds

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.factoryInputItemIds.append(self.id)
            self.queries += f"insert into factory_input_item(id, item_id, next_item)" \
                            f" values ({self.id}, {self.get_random_item_id()}, " \
                            f"{self.get_random_next_item_id()});\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.factoryInputItemIds

    def get_random_item_id(self):
        return random.choice(self.itemIds)

    def get_random_next_item_id(self):
        randNullReturn = random.randint(1, 30)
        if randNullReturn == self.id:
            return "null"
        if randNullReturn == 15:
            return "null"
        elif len(self.factoryInputItemIds) > 0:
            randId = random.choice(self.factoryInputItemIds)
            if randId == self.id:
                return "null"
            return randId
        else:
            return "null"
