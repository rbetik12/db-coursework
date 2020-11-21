from generators.actor_generator import ActorGenerator
from generators.currency_generator import CurrencyGenerator

if __name__ == "__main__":
    currencyNames = ['Dollar', 'Momey', 'Euro', 'Money']
    currencyGenerator = CurrencyGenerator(currencyNames)
    currencyGenerator.generate(1000)

    actorNames = ['rbetik12', 'rbetik', 'vitalyaiscool', 'vitalyaisverycool']
    actorGenerator = ActorGenerator(actorNames)
    actorGenerator.generate(1000)

    with open('dml.sql', 'w') as file:
        file.write(currencyGenerator.get_query())
        file.write(actorGenerator.get_query())