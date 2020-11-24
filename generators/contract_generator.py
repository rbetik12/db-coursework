import random

from generators.abstractgenerator import AbstractGenerator


class ContractGenerator(AbstractGenerator):

    def __init__(self, listingIds: list, pickedListingIds: list, currencyIds: list):
        super()
        super().__init__()
        self.contractIds = []
        self.listingIds = listingIds
        self.currencyIds = currencyIds
        self.pickedListingIds = pickedListingIds
        self.contractTypes = ['Robbery', 'Buy', 'Kill', 'Blank']

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.contractIds.append(self.id)
            self.queries += f"insert into contract(contract_id, type, reward, currency, rating_amount, listing_id) " \
                            f"values ({self.id}, '{self.get_random_type()}', {random.randint(1000, 150000)}, " \
                            f"{self.get_currency_id()}, {random.randint(-1000, 1000)}, {self.get_listing_id()});\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.contractIds

    def get_random_type(self):
        return random.choice(self.contractTypes)

    def get_currency_id(self):
        return random.choice(self.currencyIds)

    def get_listing_id(self):
        if len(self.pickedListingIds) >= len(self.listingIds):
            return "null"
        else:
            randomId = random.choice(self.listingIds)
            while randomId in self.pickedListingIds:
                randomId = random.choice(self.listingIds)
            self.pickedListingIds.append(randomId)
            return randomId
