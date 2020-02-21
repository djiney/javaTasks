package telran.common.predicates;

import java.util.function.Predicate;

public class EvenNumbersPredicate implements Predicate<Integer>
{
    @Override
    public boolean test(Integer number)
    {
        return number % 2 == 0;
    }
}
