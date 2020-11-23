import random
from random import randint

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class ItemGenerator(AbstractGenerator):

    def __init__(self, names: list, itemTypeIds: list):
        super()
        super().__init__()
        self.names = names
        self.itemIds = list()
        self.itemTypeIds = itemTypeIds
        self.pickedNames = list()

    def get_random_name(self) -> str:
        randIndex = randint(0, len(self.names) - 1)
        name = self.names[randIndex]
        while name in self.pickedNames:
            if len(self.pickedNames) >= len(self.names):
                name = get_random_string(randint(3, 20))
            else:
                randIndex = randint(0, len(self.names) - 1)
                name = self.names[randIndex]
        self.pickedNames.append(name)
        return name

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.itemIds.append(self.id)
            self.queries += f"insert into item(id, type, name, description)" \
                            f" values ({self.id}, {self.get_random_item_type_id()}, " \
                            f"'{self.get_random_name()}', '{self.get_random_name()}');\n"
            self.id += 1

    def get_random_item_type_id(self):
        if len(self.itemTypeIds) > 0:
            return random.choice(self.itemTypeIds)
        else:
            return "null"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.itemIds
