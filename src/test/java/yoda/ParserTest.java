package yoda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void cleanTest() {
        String input = "this  is   a sentence  with    too  many   spaces    .";
        Parser parser = new Parser();
        String[] arr = new String[9];
        arr[0] = "this";
        arr[1] = "is";
        arr[2] = "a";
        arr[3] = "sentence";
        arr[4] = "with";
        arr[5] = "too";
        arr[6] = "many";
        arr[7] = "spaces";
        arr[8] = ".";
        assertArrayEquals(arr, parser.clean(input));
    }

    @Test
    public void parseMarkTest() {
        Parser parser = new Parser();
        String[] arr1 = new String[2];
        arr1[0] = "argument is int";
        arr1[1] = "5";
        assertEquals(4, parser.parseMark(arr1));
    }
}
