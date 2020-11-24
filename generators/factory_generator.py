import random

from generators.abstractgenerator import AbstractGenerator


class FactoryGenerator(AbstractGenerator):

    def __init__(self, inputItemIds: list, outputItemIds: list):
        super()
        super().__init__()
        self.factoryTypes = ['Tools', 'Weapon', 'Smelting']
        self.factoryIds = []
        self.inputItemIds = inputItemIds
        self.outputItemIds = outputItemIds

    def generate(self, amount: int) -> None:
        for i in range(amount):
            self.factoryIds.append(self.id)
            self.queries += f"insert into factory(id, type, productivity, input_items, output_item)" \
                            f" values ({self.id}, '{self.get_random_type()}', {random.uniform(0, 1)}," \
                            f" {self.get_random_input_item()}," \
                            f" {self.get_random_output_item()});\n"
            self.id += 1

    def get_query(self) -> str:
        return self.queries

    def get_ids(self) -> list:
        return self.factoryIds

    def get_random_type(self):
        return random.choice(self.factoryTypes)

    def get_random_input_item(self):
        return random.choice(self.inputItemIds)

    def get_random_output_item(self):
        return random.choice(self.outputItemIds)
