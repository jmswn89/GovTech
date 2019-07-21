package org.james.GovTech.developer.interview.todoScanner;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests some behaviors of TodoScanner class.
 * 
 * @author James Jayaputera
 *
 */
public class TodoScannerTest {
	File resourcesDirectory;

	@Before
	public void setup() {
		resourcesDirectory = new File("src/test/resources");
	}
	
	/**
	 * Test listAllFiles(). The expected result is to return a list of files which are in 
	 * the resources directory.
	 */
	@Test
	public void testListAllFiles() {
		TodoScanner scanner = new TodoScanner(resourcesDirectory.getAbsolutePath());
		List<String> fileList;
		try {
			fileList = scanner.listAllFiles();
			assertTrue(fileList.size() == 6);
			// The expected file list.
			File result1 = new File(resourcesDirectory, "data/file1.txt");
			File result2 = new File(resourcesDirectory, "data/message.js");
			File result3 = new File(resourcesDirectory, "data/subdir1/hello.js");
			File result4 = new File(resourcesDirectory, "data/subdir1/subsubDir1/lorem.txt");
			File result5 = new File(resourcesDirectory, "data/subdir2/fileList.js");
			File result6 = new File(resourcesDirectory, "data/subdir2/getCount.js");
			assertTrue(fileList.contains(result1.getAbsolutePath()));
			assertTrue(fileList.contains(result2.getAbsolutePath()));
			assertTrue(fileList.contains(result3.getAbsolutePath()));
			assertTrue(fileList.contains(result4.getAbsolutePath()));
			assertTrue(fileList.contains(result5.getAbsolutePath()));
			assertTrue(fileList.contains(result6.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception shouldn't be thrown");
		}
	}

	/**
	 * Test run(). The expected result is to match a list of files which contains TODO keyword and 
	 * a file which does not contain TODO keyword. 
	 */
	@Test
	public void testRun() {
		TodoScanner scanner = new TodoScanner(resourcesDirectory.getAbsolutePath());
		List<String> fileList;
		try {
			fileList = scanner.listAllFiles();
			assertTrue(fileList.size() == 6);
			List<String> result = scanner.run();
			assertTrue(result.size() == 5);
			// This files does not contain TODO keyword.
			File file1 = new File(resourcesDirectory, "data/file1.txt");
			assertFalse(result.contains(file1.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
			fail("Exception shouldn't be thrown");
		}
	}

}
