package telran.additional._09_Stock;

/**
 * На фондовой бирже, человек покупает акцию для продажи в будущем.
 * Приведены цены на акции за N дней в виде массива A[N] и число T, представляющее максимальное количество операций.
 * Необходимо рассчитать максимальную прибыль, которую человек может получить, выполнив максимум T операций.
 *
 * Действием считается операция покупки + продажи. Невозможно выполнять перекрывающие операции.
 * Новое действие можно совершать только после того, как было завершено предыдущее.
 *
 * Данные:
 * Первая строка: максимальное количество действий T
 * Вторая строка: массив чисел длиной N, представляющий диапазон цен акций.
 *
 * Пример:
 * 2
 * {10, 22, 5, 75, 65, 80}
 *
 * Ответ: 87
 */
public class Main
{
	public static void main(String[] args)
	{
		int[] prices = {10, 22, 5, 75, 65, 80};
		int operations = 2;

		System.out.println(calculateProfit(prices, operations));
	}

	private static int calculateProfit(int[] prices, int operations)
	{
		if (prices.length < 2) {
			return 0;
		}

		return calculateProfit(prices, true, 0, operations * 2);
	}

	private static int calculateProfit(int[] prices, boolean buy, int index, int operations)
	{
		if (operations < 1 || index >= prices.length) {
			return 0;
		}

		int profit = (buy ? -1 : 1) * prices[index];

		int operationDone = profit + calculateProfit(prices, !buy, index + 1, operations - 1);
		int operationSkipped = calculateProfit(prices, buy, index + 1, operations);

		return Math.max(operationDone, operationSkipped);
	}
}
