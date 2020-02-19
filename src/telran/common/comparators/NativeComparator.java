package telran.common.comparators;

import java.util.Comparator;

public class NativeComparator<K> implements Comparator<K>
{
    @SuppressWarnings("unchecked")
    @Override
    public int compare(K firstObject, K secondObject)
    {
        Comparable<K> t1 = (Comparable<K>) firstObject;
        Comparable<K> t2 = (Comparable<K>) secondObject;

        return t1.compareTo((K) t2);
    }
}
