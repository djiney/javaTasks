package telran.lessons._36.controllers;

import telran.lessons._36.components.Generator;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public abstract class AbstractLoader<T>
{
	protected abstract Generator<Object> getGenerator();
	protected abstract void processList(List<T> stream);

	@SuppressWarnings("unchecked")
	public void load()
	{
		Generator<Object> generator = getGenerator();

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(generator.getFileName()));
			processList(
				(List<T>) input.readObject()
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
