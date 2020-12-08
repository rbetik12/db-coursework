import random

from generators.abstractgenerator import AbstractGenerator


class CurrencyListingGenerator(AbstractGenerator):

    def __init__(self, currencyIds: list, listingIds: list):
        super()
        super().__init__()
        self.listingIds = listingIds
        self.currencyIds = currencyIds
        self.pickedListingIds = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.queries += f"insert into currency_listing(listing_id, currency_for_sell_id, " \
                            f"currency_for_buy_id, sell_amount, buy_amount) values " \
                            f"({self.get_random_listing_id()}, {self.get_random_currency_id()}, " \
                            f"{self.get_random_currency_id()}," \
                            f"{random.randint(10, 3000)}, {random.randint(10, 4000)});\n"

    def get_random_listing_id(self):
        if len(self.pickedListingIds) >= len(self.listingIds):
            return "null"
        else:
            pickedId = random.choice(self.listingIds)
            while pickedId in self.pickedListingIds:
                pickedId = random.choice(self.listingIds)
            self.pickedListingIds.append(pickedId)
            return pickedId

    def get_random_currency_id(self):
        return random.choice(self.currencyIds)

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return