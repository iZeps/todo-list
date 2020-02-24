package training.programming.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TodoDataTest {

    private TodoData todoData;

    @Before
    public void setUp() {
        todoData = new TodoData();
    }

    @Test
    public void shouldGetAllItemsInDataObject() {
        int actual = todoData.getItems().size();
        assertEquals(4, actual);
    }

    @Test
    public void shouldAddItemToDataObject() {
        todoData.addItem(new TodoItem("title", "details", LocalDate.now()));
        int actual = todoData.getItems().size();
        assertEquals(5, actual);
    }

    @Test
    public void shouldRemoveItemFromDataObject() {
        todoData.removeItem(2);
        assertNull(todoData.getItem(2));
    }

    @Test
    public void shouldGetItemFromDataObject() {
        TodoItem todoItem = new TodoItem("title", "details", LocalDate.now());
        todoData.addItem(todoItem);
        String actual = todoData.getItem(todoItem.getId()).getTitle();
        assertEquals("title", actual);
    }

    @Test
    public void shouldUpdateExistingItemInDataObject() {
        TodoItem todoItem = new TodoItem("title", "details", LocalDate.now());
        todoData.addItem(todoItem);
        todoItem.setTitle("changed");
        todoItem.setDeadline(LocalDate.of(1990,4,7));
        todoItem.setDetails("also changed");
        todoData.updateItem(todoItem);

        assertEquals("changed", todoData.getItem(todoItem.getId()).getTitle());
        assertEquals("also changed", todoData.getItem(todoItem.getId()).getDetails());
        assertEquals(LocalDate.of(1990,4,7), todoData.getItem(todoItem.getId()).getDeadline());
    }
}