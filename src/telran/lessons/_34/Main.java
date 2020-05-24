package telran.lessons._34;

import telran.lessons._34.tests.performance.FileOperationPerformance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Main
{
	private static final String SOURCE_FILE = "src\\telran\\lessons\\_34\\resources\\file";
	private static final String COPY_FILE = "src\\telran\\lessons\\_34\\resources\\file-copy";

	private static long bufferSize;
	private static final long[] bufferSizes = {
		1024,
		100 * 1024,
		1024 * 1024,
		100 * 1024 * 1024,
		Runtime.getRuntime().freeMemory()
	};

	public static void main(String[] args) throws IOException
	{
		int runsAmount = 1;
		performanceTest(runsAmount);
	}

	private static void performanceTest(int runsAmount) throws IOException
	{
		new FileOperationPerformance(
			"Standard", runsAmount, Main::standardMethod
		).run();

		for (long value : bufferSizes) {
			bufferSize = value;
			new FileOperationPerformance(
				"Custom, buffer: " + formatSize(), runsAmount, Main::customMethod
			).run();
		}

		Files.deleteIfExists(Paths.get(COPY_FILE));
	}

	private static String formatSize()
	{
		long absB = bufferSize == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bufferSize);
		if (absB < 1024) {
			return bufferSize + " B";
		}
		long value = absB;
		CharacterIterator ci = new StringCharacterIterator("KMGTPE");
		for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
			value >>= 10;
			ci.next();
		}
		value *= Long.signum(bufferSize);
		return String.format("%.1f %ciB", value / 1024.0, ci.current());
	}

	private static void standardMethod() throws IOException
	{
		Path src = Paths.get(SOURCE_FILE);
		Path dst = Paths.get(COPY_FILE);

		Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
	}

	private static void customMethod() throws IOException
	{
		InputStream input = Files.newInputStream(Paths.get(SOURCE_FILE));
		OutputStream output = new FileOutputStream(COPY_FILE);

		byte[] buffer = new byte[(int) bufferSize];

		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1) {
			output.write(buffer, 0, bytesRead);
		}

		input.close();
		output.close();
	}
}
