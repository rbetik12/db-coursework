import random

from generators.abstractgenerator import AbstractGenerator


class ActorCurrencyGenerator(AbstractGenerator):

    def __init__(self, actorIds: list, currencyIds: list):
        super()
        super().__init__()
        self.actorIds = actorIds
        self.currencyIds = currencyIds
        self.pickedActorCurrency = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            actorId = self.get_random_actor_id()
            currencyId = self.get_random_currency_id()
            if (actorId, currencyId) in self.pickedActorCurrency:
                continue
            self.pickedActorCurrency.append((actorId, currencyId))
            self.queries += f"insert into actorcurrency(actor_id, currency_id, amount) " \
                            f"values ({actorId}, {currencyId}, {random.randint(20, 10000)});\n"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return []

    def get_random_actor_id(self):
        return random.choice(self.actorIds)

    def get_random_currency_id(self):
        return random.choice(self.currencyIds)


