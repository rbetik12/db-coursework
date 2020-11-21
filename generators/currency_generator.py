from random import randint

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class CurrencyGenerator(AbstractGenerator):
    def __init__(self, names: list):
        super()
        super().__init__()
        self.names = names
        self.currencyIds = list()
        self.pickedNames = list()
        self.queries += 'insert into currency default values;\n'
        self.id += 1

    def get_random_name(self) -> str:
        randIndex = randint(0, len(self.names) - 1)
        name = self.names[randIndex]
        while name in self.pickedNames:
            if len(self.pickedNames) >= len(self.names):
                name = get_random_string(randint(3, 6))
            else:
                randIndex = randint(0, len(self.names) - 1)
                name = self.names[randIndex]
        self.pickedNames.append(name)
        return name

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.currencyIds.append(self.id)
            self.queries += f"insert into currency(id, name, price) " \
                            f"values ({self.id}, '{self.get_random_name()}', {randint(1, 10000)});\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.currencyIds
