import random
from random import randint

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class ClanGenerator(AbstractGenerator):
    def __init__(self, names: list, regionsList: list):
        super()
        super().__init__()
        self.names = names
        self.clanIds = list()
        self.regionList = regionsList
        self.pickedNames = list()
        self.clanTypes = ['Trade', 'Production', 'Mining']

    def get_random_name(self) -> str:
        randIndex = randint(0, len(self.names) - 1)
        name = self.names[randIndex]
        while name in self.pickedNames:
            if len(self.pickedNames) >= len(self.names):
                name = get_random_string(randint(3, 10))
            else:
                randIndex = randint(0, len(self.names) - 1)
                name = self.names[randIndex]
        self.pickedNames.append(name)
        return name

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.clanIds.append(self.id)
            self.queries += f"insert into clan(id, name, region_id, type, rating) " \
                            f"values ({self.id}, '{self.get_random_name()}', {self.get_random_region_id()}, " \
                            f"'{self.get_random_clan_type()}', {randint(-2000, 4000)});\n"
            self.id += 1

    def get_random_region_id(self) -> int:
        return random.choice(self.regionList)

    def get_random_clan_type(self) -> str:
        return random.choice(self.clanTypes)

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.clanIds