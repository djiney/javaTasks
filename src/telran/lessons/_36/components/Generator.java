package telran.lessons._36.components;

import java.util.List;

public interface Generator<T>
{
	String getFileName();
	List<T> generate();
}
