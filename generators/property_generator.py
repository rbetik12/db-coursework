import random

from generators.abstractgenerator import AbstractGenerator


class PropertyGenerator(AbstractGenerator):

    def __init__(self, actorIds: list, clanIds: list):
        super()
        super().__init__()
        self.propertyIds = []
        self.actorIds = actorIds
        self.clanIds = clanIds

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.propertyIds.append(self.id)
            if i % 2 == 0:
                self.queries += f"insert into property_listing(id, owner_id, clan_owner_id) " \
                                f"values ({self.id}, null, {self.get_random_clan_id()});\n"
            else:
                self.queries += f"insert into property_listing(id, owner_id, clan_owner_id) " \
                                f"values ({self.id}, {self.get_random_actor_id()}, null);\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.propertyIds

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_clan_id(self):
        return random.choice(self.clanIds)