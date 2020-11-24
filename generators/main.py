from generators.actor_generator import ActorGenerator
from generators.clan_generator import ClanGenerator
from generators.currency_generator import CurrencyGenerator
from generators.factory_generator import FactoryGenerator
from generators.factory_input_item_generator import FactoryInputItemGenerator
from generators.factory_owner_generator import FactoryOwnerGenerator
from generators.inventory_generator import InventoryGenerator
from generators.item_generator import ItemGenerator
from generators.item_type_generator import ItemTypeGenerator
from generators.region_generator import RegionGenerator

if __name__ == "__main__":
    amount = 1000
    currencyNames = ['Dollar', 'Momey', 'Euro', 'Money']
    currencyGenerator = CurrencyGenerator(currencyNames)
    currencyGenerator.generate(amount)

    actorNames = ['rbetik12', 'rbetik', 'vitalyaiscool', 'vitalyaisverycool']
    actorGenerator = ActorGenerator(actorNames)
    actorGenerator.generate(amount)

    regionNames = ['Lol', 'Kek', 'Landia', 'RUSSIA']
    regionGenerator = RegionGenerator(regionNames, currencyGenerator.get_ids())
    regionGenerator.generate(amount + 500)

    clanNames = ['Cool clan', '1', '200', 'Romanian clan']
    clanGenerator = ClanGenerator(clanNames, regionGenerator.get_ids())
    clanGenerator.generate(amount)

    itemTypeNames = ['Tool', 'Weapon', 'Material']
    itemTypeGenerator = ItemTypeGenerator(itemTypeNames)
    itemTypeGenerator.generate(amount)

    itemNames = ['Lol', 'Kek']
    itemGenerator = ItemGenerator(itemNames, itemTypeGenerator.get_ids())
    itemGenerator.generate(amount)

    factoryInputItemGenerator = FactoryInputItemGenerator(itemGenerator.get_ids())
    factoryInputItemGenerator.generate(amount)

    factoryGenerator = FactoryGenerator(factoryInputItemGenerator.get_ids(), itemGenerator.get_ids())
    factoryGenerator.generate(amount)

    inventoryGenerator = InventoryGenerator(actorGenerator.get_ids(), itemGenerator.get_ids())
    inventoryGenerator.generate(amount)

    factoryOwnerGenerator = FactoryOwnerGenerator(actorGenerator.get_ids(),
                                                  clanGenerator.get_ids(), factoryGenerator.get_ids())
    factoryOwnerGenerator.generate(amount)

    with open('dml.sql', 'w') as file:
        file.write(currencyGenerator.get_query())
        file.write(regionGenerator.get_query())
        file.write(clanGenerator.get_query())
        file.write(actorGenerator.get_query())
        file.write(itemTypeGenerator.get_query())
        file.write(itemGenerator.get_query())
        file.write(factoryInputItemGenerator.get_query())
        file.write(factoryGenerator.get_query())
        file.write(inventoryGenerator.get_query())
        file.write(factoryOwnerGenerator.get_query())
