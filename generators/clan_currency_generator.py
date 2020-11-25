import random

from generators.abstractgenerator import AbstractGenerator


class ClanCurrencyGenerator(AbstractGenerator):

    def __init__(self, clanIds: list, currencyIds: list):
        super()
        super().__init__()
        self.clanIds = clanIds
        self.currencyIds = currencyIds
        self.pickedClanCurrency = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            clanId = self.get_random_clan_id()
            currencyId = self.get_random_currency_id()
            if (clanId, currencyId) in self.pickedClanCurrency:
                continue
            self.pickedClanCurrency.append((clanId, currencyId))
            self.queries += f"insert into clancurrency(clan_id, currency_id, amount) " \
                            f"values ({clanId}, {currencyId}, {random.randint(20, 10000)});\n"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return []

    def get_random_clan_id(self):
        return random.choice(self.clanIds)

    def get_random_currency_id(self):
        return random.choice(self.currencyIds)

