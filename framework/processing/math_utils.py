import math
from typing import Dict, Union

class MathUtils:
    """
    Provides utility functions for mathematical and statistical computations.

    These methods are designed for handling frequent mathematical operations,
    such as logarithmic calculations, rounding numbers, reversing ratios, and
    generating metrics per a certain number of inhabitants.
    """

    HUNDRED_VALUE = 100
    THOUSAND_VALUE = 1000
    TEN_THOUSAND_VALUE = 10000
    HUNDRED_THOUSAND_VALUE = 100000

    @staticmethod
    def get_log_value(product: float) -> float:
        """
        Determine the result of applying Math.log against a product of indicators.
        Return 1 if the result of the product is 1, otherwise calculate the log of the product.

        Args:
            product: The value of the product of a series of indicators

        Returns:
            float: The logarithm of the product, or 1 if product equals 1
        """
        if product == 1:
            return product
        return math.log(product)

    @staticmethod
    def round(value: float, places: int) -> float:
        """
        Round a value to a specified number of decimal places.

        Args:
            value: The input value
            places: The number of decimals for formatting

        Returns:
            float: The value rounded to 'places' decimal places

        Raises:
            ValueError: If places is negative
        """
        if places < 0:
            raise ValueError("places must be non-negative")

        factor = 10 ** places
        prepared_value = value * factor
        rounded_value = round(prepared_value)

        return rounded_value / factor

    @staticmethod
    def percentage_reverse_ratio(map_data: Dict[str, Union[int, float]], key: str) -> float:
        """
        Get the differences between 100% and the current ratio.
        Examples:
        - Dropout Ratio = 7% => Graduating Ratio = 100% - 7% = 93%
        - Gender pay gap = -3% => 100% + 3% = 103%
        - Gender pay gap = +8% => 100% - 8% = 92%

        Args:
            map_data: The related dictionary
            key: The key to look up

        Returns:
            float: The reversed ratio
        """
        number = map_data.get(key)
        value = float(number) if number is not None else 0
        return 100 - value

    @staticmethod
    def generate_per_hundred_inhabitants(
            population_map: Dict[str, Union[int, float]],
            key: str,
            value: float
    ) -> float:
        """
        Transform the value into a value per hundred inhabitants.

        Args:
            population_map: Total population dictionary
            key: The key used to extract the total population
            value: The initial value

        Returns:
            float: The value per hundred inhabitants
        """
        population = float(population_map.get(key))
        return value / population * MathUtils.HUNDRED_VALUE

    @staticmethod
    def generate_per_thousand_inhabitants(
            population_map: Dict[str, Union[int, float]],
            key: str,
            value: float
    ) -> float:
        """
        Transform the value into a value per thousand inhabitants.

        Args:
            population_map: Total population dictionary
            key: The key used to extract the total population
            value: The initial value

        Returns:
            float: The value per thousand inhabitants
        """
        population = float(population_map.get(key))
        return value / population * MathUtils.THOUSAND_VALUE

    @staticmethod
    def generate_per_ten_thousand_inhabitants(
            population_map: Dict[str, Union[int, float]],
            key: str,
            value: float
    ) -> float:
        """
        Transform the value into a value per ten thousand inhabitants.

        Args:
            population_map: Total population dictionary
            key: The key used to extract the total population
            value: The initial value

        Returns:
            float: The value per ten thousand inhabitants
        """
        population = float(population_map.get(key))
        return value / population * MathUtils.TEN_THOUSAND_VALUE

    @staticmethod
    def generate_per_hundred_thousand_inhabitants(
            population_map: Dict[str, Union[int, float]],
            key: str,
            value: float
    ) -> float:
        """
        Transform the value into a value per hundred thousand inhabitants.

        Args:
            population_map: Total population dictionary
            key: The key used to extract the total population
            value: The initial value

        Returns:
            float: The value per hundred thousand inhabitants
        """
        population = float(population_map.get(key))
        return value / population * MathUtils.HUNDRED_THOUSAND_VALUE
