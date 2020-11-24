import random

from generators.abstractgenerator import AbstractGenerator
from generators.utils import get_random_string


class ListingGenerator(AbstractGenerator):

    def __init__(self, names: list, actorIds: list, clanIds: list):
        super()
        super().__init__()
        self.listingIds = []
        self.actorIds = actorIds
        self.clanIds = clanIds
        self.names = names
        self.pickedNames = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.listingIds.append(self.id)
            if i % 2 == 0:
                self.queries += f"insert into listings(listing_id, seller, author_id, clan_id, description) " \
                                f"values ({self.id}, 'Clan',null, {self.get_random_clan_id()}, " \
                                f"'{self.get_random_name()}');\n"
            else:
                self.queries += f"insert into listings(listing_id, seller, author_id, clan_id, description) " \
                                f"values ({self.id}, 'Actor', {self.get_random_actor_id()}, null, " \
                                f"'{self.get_random_name()}');\n"
            self.id += 1

    def get_random_name(self) -> str:
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

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.listingIds

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_clan_id(self):
        return random.choice(self.clanIds)

