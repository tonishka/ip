package yoda;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test() {
        ToDo toDo = new ToDo("Laundry", 1);
        assertEquals("[T][X] Laundry", toDo.toString());
    }
}
