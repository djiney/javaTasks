package telran.lessons._36.controllers;

import telran.lessons._36.components.Generator;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public abstract class AbstractCreator
{
	protected abstract Generator<Object> getGenerator();

	public void create() throws Exception
	{
		Generator<Object> generator = getGenerator();

		ObjectOutputStream output = new ObjectOutputStream(
			new FileOutputStream(generator.getFileName())
		);

		output.writeObject(generator.generate());
	}
}
