import random
from random import randint

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class ItemTypeGenerator(AbstractGenerator):
    def __init__(self, names: list):
        super()
        super().__init__()
        self.names = names
        self.itemTypeIds = list()
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
            self.itemTypeIds.append(self.id)
            self.queries += f"insert into item_type(id, name, parent_type)" \
                            f" values ({self.id}, '{self.get_random_name()}', {self.get_random_item_type_id()});\n"
            self.id += 1

    def get_random_item_type_id(self):
        if len(self.itemTypeIds) > 0:
            randomId = random.choice(self.itemTypeIds)
            if randomId == self.id:
                return "null"
            return randomId
        else:
            return "null"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.itemTypeIds
