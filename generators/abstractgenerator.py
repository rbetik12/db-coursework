from abc import ABC, abstractmethod


class AbstractGenerator(ABC):

    def __init__(self):
        self.id = 1
        self.queries = ''

    @abstractmethod
    def generate(self, amount: int) -> None:
        pass

    @abstractmethod
    def get_query(self) -> str:
        pass

    @abstractmethod
    def get_ids(self) -> list:
        pass
