import random

from generators.abstractgenerator import AbstractGenerator


class FactoryOwnerGenerator(AbstractGenerator):

    def __init__(self, actorIds: list, clanIds: list, factoriesIds: list):
        super()
        super().__init__()
        self.factoryOwnerIds = []
        self.actorIds = actorIds
        self.clanIds = clanIds
        self.factoriesIds = factoriesIds

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.factoryOwnerIds.append(self.id)
            if i % 2 == 0:
                self.queries += f"insert into factory_owner(rel_id, clan_id, actor_id, factory_id) " \
                                f"values ({self.id}, {self.get_random_clan_id()}, null, " \
                                f"{self.get_random_factory_id()});\n"
            else:
                self.queries += f"insert into factory_owner(rel_id, clan_id, actor_id, factory_id) " \
                                f"values ({self.id}, null, {self.get_random_actor_id()}, " \
                                f"{self.get_random_factory_id()});\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.factoryOwnerIds

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_clan_id(self):
        return random.choice(self.clanIds)

    def get_random_factory_id(self):
        return random.choice(self.factoriesIds)
