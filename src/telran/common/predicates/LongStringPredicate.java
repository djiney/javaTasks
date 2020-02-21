package telran.common.predicates;

import java.util.function.Predicate;

public class LongStringPredicate implements Predicate<String>
{
    @Override
    public boolean test(String string)
    {
        return string.length() > 4;
    }
}
