import random
from random import randint

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class RegionGenerator(AbstractGenerator):
    def __init__(self, names: list, currencyList: list):
        super()
        super().__init__()
        self.names = names
        self.regionIds = list()
        self.currencyList = currencyList
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
            self.regionIds.append(self.id)
            self.queries += f"insert into region(id, currency_id, name) " \
                            f"values ({self.id}, {self.get_currency_id()}, '{self.get_random_name()}');\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.regionIds

    def get_currency_id(self) -> int:
        return random.choice(self.currencyList)
