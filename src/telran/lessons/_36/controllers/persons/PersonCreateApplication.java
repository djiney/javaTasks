package telran.lessons._36.controllers.persons;

import telran.lessons._36.components.Generator;
import telran.lessons._36.components.PersonsGenerator;
import telran.lessons._36.controllers.AbstractCreator;

public class PersonCreateApplication extends AbstractCreator
{
	public static void main(String[] args) throws Exception
	{
		new PersonCreateApplication().create();
	}

	@Override
	protected Generator<Object> getGenerator()
	{
		return new PersonsGenerator();
	}
}