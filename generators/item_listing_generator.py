import random

from generators.abstractgenerator import AbstractGenerator


class ItemListingGenerator(AbstractGenerator):

    def __init__(self, listingIds: list, itemIds: list, currencyIds: list):
        super()
        super().__init__()
        self.listingIds = listingIds
        self.itemIds = itemIds
        self.currencyIds = currencyIds
        self.pickedListingIds = []

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.pickedListingIds.append(self.id)
            self.queries += f"insert into itemlisting(listing_id, item_id, price, amount, currency) " \
                            f"values ({self.get_listing_id()}, {self.get_item_id()}, {random.randint(2, 100000)}, " \
                            f"{random.randint(1, 20000)}, {self.get_currency_id()});\n"

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.pickedListingIds

    def get_listing_id(self):
        if len(self.pickedListingIds) >= len(self.listingIds):
            return "null"
        else:
            randomId = random.choice(self.listingIds)
            while randomId in self.pickedListingIds:
                randomId = random.choice(self.listingIds)
            self.pickedListingIds.append(randomId)
            return randomId

    def get_item_id(self):
        return random.choice(self.itemIds)

    def get_currency_id(self):
        return random.choice(self.currencyIds)

