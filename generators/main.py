from generators.currency_generator import CurrencyGenerator

if __name__ == "__main__":
    currencyNames = ['Ruble', 'Dollar', 'Momey', 'Euro', 'Money']
    currencyGenerator = CurrencyGenerator(currencyNames)
    currencyGenerator.generate(1000)

    with open('dml.sql', 'w') as file:
        file.write(currencyGenerator.get_query())