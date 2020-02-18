package telran.additional._04_StackMax.tests;

import org.junit.jupiter.api.Test;
import telran.additional._04_StackMax.StackMax;

import static org.junit.jupiter.api.Assertions.*;

class StackMaxTest {

    @Test
    void testPushPopIsEmpty()
    {
        StackMax stack = new StackMax(4);
        int[] numbers = {10, -8, 70, 75, 30};

        assertTrue(stack.isEmpty());

        for (int number : numbers) {
            stack.push(number);
            assertFalse(stack.isEmpty());
        }

        for (int i = numbers.length - 1; i >= 0; i--) {
            assertEquals(numbers[i], stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    void max()
    {

    }
}