package yoda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() {
        Deadline deadline = new Deadline("LSM1301 Lab Report", "2022-02-13");
        assertEquals("[D][ ] LSM1301 Lab Report (Feb 13 2022)", deadline.toString());
    }
}
