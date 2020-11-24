from generators.actor_generator import ActorGenerator
from generators.clan_generator import ClanGenerator
from generators.contract_generator import ContractGenerator
from generators.currency_generator import CurrencyGenerator
from generators.factory_generator import FactoryGenerator
from generators.factory_input_item_generator import FactoryInputItemGenerator
from generators.factory_owner_generator import FactoryOwnerGenerator
from generators.inventory_generator import InventoryGenerator
from generators.item_generator import ItemGenerator
from generators.item_listing_generator import ItemListingGenerator
from generators.item_type_generator import ItemTypeGenerator
from generators.listing_generator import ListingGenerator
from generators.property_generator import PropertyGenerator
from generators.region_generator import RegionGenerator

# TO DO:
#   - Fix inventory generator. Sometimes it violates unique constraint
from generators.reward_generator import RewardGenerator

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

    propertyGenerator = PropertyGenerator(actorGenerator.get_ids(), clanGenerator.get_ids())
    propertyGenerator.generate(amount)

    listingDescs = ['Cool listing']
    listingGenerator = ListingGenerator(listingDescs, actorGenerator.get_ids(), clanGenerator.get_ids())
    listingGenerator.generate(amount * 2)

    itemListingGenerator = ItemListingGenerator(listingGenerator.get_ids(), itemGenerator.get_ids(), currencyGenerator.get_ids())
    itemListingGenerator.generate(amount)

    contractGenerator = ContractGenerator(listingGenerator.get_ids(), itemListingGenerator.get_ids(), currencyGenerator.get_ids())
    contractGenerator.generate(amount)

    rewardNames = ['Reward']
    rewardGenerator = RewardGenerator(rewardNames, actorGenerator.get_ids())
    rewardGenerator.generate(amount)

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
        file.write(propertyGenerator.get_query())
        file.write(listingGenerator.get_query())
        file.write(itemListingGenerator.get_query())
        file.write(contractGenerator.get_query())
        file.write(rewardGenerator.get_query())
