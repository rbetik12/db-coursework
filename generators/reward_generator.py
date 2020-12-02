import random

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class RewardGenerator(AbstractGenerator):

    def __init__(self, names: list, actorIds: list):
        super()
        super().__init__()
        self.rewardIds = []
        self.names = names
        self.actorIds = actorIds
        self.rewardTypes = ['Purchases', 'Rating', 'Manufacturing']
        self.pickedNames = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.rewardIds.append(self.id)
            self.queries += f"insert into reward(id, type, owner_id, conditions, name) " \
                            f"values ({self.id}, '{self.get_random_type()}', {self.get_actor_id()}, " \
                            f"'{self.get_random_name()}', '{self.get_random_name()}');\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return []

    def get_random_type(self):
        return random.choice(self.rewardTypes)

    def get_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_name(self):
        randIndex = random.randint(0, len(self.names) - 1)
        name = self.names[randIndex]
        while name in self.pickedNames:
            if len(self.pickedNames) >= len(self.names):
                name = get_random_string(random.randint(3, 20))
            else:
                randIndex = random.randint(0, len(self.names) - 1)
                name = self.names[randIndex]
        self.pickedNames.append(name)
        return name

