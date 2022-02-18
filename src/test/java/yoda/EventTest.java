package yoda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test() {
        Event event = new Event("Piano Recital", "Wednesday 2-4PM", 0);
        assertEquals("[E][ ] Piano Recital(Wednesday 2-4PM)", event.toString());
    }
}
