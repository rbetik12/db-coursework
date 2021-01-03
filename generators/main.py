from generators.actor_currency_generator import ActorCurrencyGenerator
from generators.actor_generator import ActorGenerator
from generators.clan_currency_generator import ClanCurrencyGenerator
from generators.clan_generator import ClanGenerator
from generators.contract_generator import ContractGenerator
from generators.currency_generator import CurrencyGenerator
from generators.currency_listing_generator import CurrencyListingGenerator
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
#   - Fix inventory generator. Sometimes it violates unique constraint (DONE)

sequencesUpdate = [
    "select setval('listing_listing_id_seq', (select max(listing_id) from listing));",
    "select setval('reward_id_seq', (select max(id) from reward));",
    "select setval('actor_id_seq', (select max(id) from actor));",
    "select setval('clan_id_seq', (select max(id) from clan));",
    "select setval('currency_id_seq', (select max(id) from currency));",
    "select setval('item_type_id_seq', (select max(id) from item_type));",
    "select setval('item_id_seq', (select max(id) from item));",
    "select setval('factory_input_item_id_seq', (select max(id) from factory_input_item));",
    "select setval('contract_listing_contract_id_seq', (select max(contract_id) from contract_listing));",
]

from generators.reward_generator import RewardGenerator

if __name__ == "__main__":
    amount = 10
    currencyNames = ['Dollar', 'Momey', 'Euro', 'Money']
    currencyGenerator = CurrencyGenerator(currencyNames)
    currencyGenerator.generate(amount)

    actorNames = ['rbetik12', 'rbetik', 'vitalyaiscool', 'vitalyaisverycool']
    actorGenerator = ActorGenerator(actorNames)
    actorGenerator.generate(amount)

    regionNames = ['Lol', 'Kek', 'Landia', 'RUSSIA']
    regionGenerator = RegionGenerator(regionNames, currencyGenerator.get_ids())
    regionGenerator.generate(amount * 2)

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
    listingGenerator.generate(amount * 3)

    itemListingGenerator = ItemListingGenerator(listingGenerator.get_ids(), itemGenerator.get_ids(),
                                                currencyGenerator.get_ids())
    itemListingGenerator.generate(amount)

    contractGenerator = ContractGenerator(listingGenerator.get_ids(), itemListingGenerator.get_ids(),
                                          currencyGenerator.get_ids())
    contractGenerator.generate(amount)

    currencyListingGenerator = CurrencyListingGenerator(currencyGenerator.get_ids(), listingGenerator.get_ids())
    currencyListingGenerator.generate(amount)

    rewardNames = ['Reward']
    rewardGenerator = RewardGenerator(rewardNames, actorGenerator.get_ids())
    rewardGenerator.generate(amount)

    actorCurrencyGenerator = ActorCurrencyGenerator(actorGenerator.get_ids(), currencyGenerator.get_ids())
    actorCurrencyGenerator.generate(amount)

    clanCurrencyGenerator = ClanCurrencyGenerator(clanGenerator.get_ids(), currencyGenerator.get_ids())
    clanCurrencyGenerator.generate(amount)

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
        file.write(actorCurrencyGenerator.get_query())
        file.write(clanCurrencyGenerator.get_query())
        file.write(currencyListingGenerator.get_query())
        file.write("\n".join(sequencesUpdate))
