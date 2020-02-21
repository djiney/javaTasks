package telran.common.comparators;

import java.util.Comparator;

public class NativeComparator<K> implements Comparator<K>
{
    @SuppressWarnings("unchecked")
    @Override
    public int compare(K firstObject, K secondObject)
    {
        Comparable<K> t1 = (Comparable<K>) firstObject;
        return t1.compareTo(secondObject);
    }
}
