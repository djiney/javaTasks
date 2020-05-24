package telran.lessons._34.tests.performance;

import telran.lessons._19_0.tests.PerformanceTest;
import java.io.IOException;

public class FileOperationPerformance extends PerformanceTest
{
	FileOperation operation;

	public FileOperationPerformance(String testName, Integer runsAmount, FileOperation operation)
	{
		super(testName, runsAmount);
		this.operation = operation;
	}

	@Override
	protected void runTest()
	{
		try {
			operation.run();
		} catch (IOException ignored) {}
	}

	public interface FileOperation
	{
		void run() throws IOException;
	}
}
