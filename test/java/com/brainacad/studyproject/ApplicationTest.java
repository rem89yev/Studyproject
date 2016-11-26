package com.brainacad.studyproject;

import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class ApplicationTest {

    private File file;

    @Before
    public void setUp() {
        file = new File("hello.txt");
    }

    @After
    public void tearDown() {
        file.delete();
    }

    @Ignore
    @Test
    public void testHello() {
        try (FileOutputStream fos = new FileOutputStream(file);
             PrintStream out = new PrintStream(fos);
             FileReader fis = new FileReader(file);
             BufferedReader br = new BufferedReader(fis)) {
            // arrange
            System.setOut(out);
            // act
            Application.main(null);
            // assert
            assertEquals("Unexpected output", "Hello!", br.readLine());
        } catch (IOException e) {
            fail("IO problems");
        }
    }

}
