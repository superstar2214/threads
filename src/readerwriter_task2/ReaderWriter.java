package readerwriter_task2;

import readerwriter.Data;
import readerwriter.Reader_;
import readerwriter.Writer;

public class ReaderWriter {

	public static void main(String[] args) {
		createThreads();
	}

	public static void createThreads() {
		readerwriter.Data data = new readerwriter.Data();
		createReaderThreads(data);
		createWriterThreads(data);
	}

	private static void createReaderThreads(readerwriter.Data data) {
		Thread readerThread;

		for (int i = 0; i < 5; i++) {
			readerThread = new Thread(new Reader_(i, data));
			readerThread.start();
		}
	}

	private static void createWriterThreads(Data data) {
		Thread writerThread;

		for (int i = 0; i < 2; i++) {
			writerThread = new Thread(new Writer(i, data));
			writerThread.start();
		}

	}
}
