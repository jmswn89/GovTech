package org.james.GovTech.developer.interview.todoScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class will scan TODO keyword inside a list of files.
 * 
 * @author James Jayaputera
 *
 */
public class TodoScanner {
	private String path;
	
	/**
	 * Constructor
	 * 
	 * @param path A path of a file.
	 */
	public TodoScanner(String path) {
		this.path = path;
	}
	
	/**
	 * List all files under the path.
	 * 
	 * @return A list of absolute file paths.
	 * @throws IOException
	 */
	public List<String> listAllFiles() throws IOException {
		List<String> filesList = null;
		try (Stream<Path> walk = Files.walk(Paths.get(this.path))) {

			filesList = walk.filter(Files::isRegularFile)
					        .map(file -> file.toString())
					        .collect(Collectors.toList());
		} catch (IOException e) {
			throw e;
		}
		return filesList;
	}
	
	/**
	 * Check the file content if it contains TODO keyword.
	 * 
	 * @param filename
	 * @return true if the file contains has TODO keyword. Otherwise, it returns false.
	 * @throws FileNotFoundException Thrown if the given filename is not found.
	 */
	private boolean hasTodoKeyword(String filename) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(filename))) {
			while (scanner.hasNext()) {
				if (scanner.next().contains("TODO")) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			  System.out.println(MessageFormat.format("File '{0}' not found.", filename));
			  throw e;
		}
		return false;
	}

	/**
	 * Traverse a directory, check a list of files in the directory if they contain TODO keyword and store them into a list.
	 * @return A list of files which contains TODO list.
	 */
	public List<String> run() {
		List<String> result = new ArrayList<>();
		try {
			List<String> filesList = listAllFiles();
			for (String file : filesList) {
				if (hasTodoKeyword(file)) {
					result.add(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Main method.
	 * @param args Program arguments.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: TodoScanner <directory/file>");
			System.exit(0);
		}
		String path = args[0];
		TodoScanner scanner = new TodoScanner(path);
		scanner.run().stream().forEach(System.out::println);
	}
}
