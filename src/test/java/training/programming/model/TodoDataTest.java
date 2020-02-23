package training.programming.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoDataTest {

    private TodoData todoData;

    @BeforeEach
    void setUp() {
        todoData = new TodoData();
    }

    @Test
    void shouldGetAllItemsInDataObject() {
        int actual = todoData.getItems().size();
        assertEquals(4, actual);
    }

    @Test
    void shouldAddItemToDataObject() {
        todoData.addItem(new TodoItem("title", "details", LocalDate.now()));
        int actual = todoData.getItems().size();
        assertEquals(5, actual);
    }

    @Test
    void shouldRemoveItemFromDataObject() {
        todoData.removeItem(2);
        assertNull(todoData.getItem(2));
    }

    @Test
    void shouldGetItemFromDataObject() {
        TodoItem todoItem = new TodoItem("title", "details", LocalDate.now());
        todoData.addItem(todoItem);
        String actual = todoData.getItem(todoItem.getId()).getTitle();
        assertEquals("title", actual);
    }

    @Test
    void shouldUpdateExistingItemInDataObject() {
        TodoItem todoItem = new TodoItem("title", "details", LocalDate.now());
        todoData.addItem(todoItem);
        todoItem.setTitle("changed");
        todoData.updateItem(todoItem);
        assertEquals("changed", todoData.getItem(todoItem.getId()).getTitle());
    }
}