from generators.actor_generator import ActorGenerator
from generators.clan_generator import ClanGenerator
from generators.currency_generator import CurrencyGenerator
from generators.region_generator import RegionGenerator

if __name__ == "__main__":
    currencyNames = ['Dollar', 'Momey', 'Euro', 'Money']
    currencyGenerator = CurrencyGenerator(currencyNames)
    currencyGenerator.generate(1000)

    actorNames = ['rbetik12', 'rbetik', 'vitalyaiscool', 'vitalyaisverycool']
    actorGenerator = ActorGenerator(actorNames)
    actorGenerator.generate(1000)

    regionNames = ['Lol', 'Kek', 'Landia', 'RUSSIA']
    regionGenerator = RegionGenerator(regionNames, currencyGenerator.get_ids())
    regionGenerator.generate(1500)

    clanNames = ['Cool clan', '1', '200', 'Romanian clan']
    clanGenerator = ClanGenerator(clanNames, regionGenerator.get_ids())
    clanGenerator.generate(1000)

    with open('dml.sql', 'w') as file:
        file.write(currencyGenerator.get_query())
        file.write(regionGenerator.get_query())
        file.write(clanGenerator.get_query())
        file.write(actorGenerator.get_query())
