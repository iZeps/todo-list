package training.programming.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.programming.model.TodoData;
import training.programming.model.TodoItem;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemServiceImplTest {

    private String title = "title";

    private TodoData todoData = new TodoData();
    private TodoItem todoItem = new TodoItem(title, "details", LocalDate.now());

    @BeforeEach
    void setUp() {
        todoData.addItem(todoItem);
    }

    @Test
    void shouldAddItemToDataObject() {
        assertEquals(5, todoData.getItems().size());
    }

    @Test
    void shouldGetItemFromDataObject() {
        assertEquals(title,todoData.getItem(todoItem.getId()).getTitle());
    }

//    @Test
    void shouldUpdateItemsDetails() {
        todoData.updateItem(todoItem);
//        assertEquals();
    }

    @Test
    void shouldRemoveItemFromDataObject() {
        todoData.removeItem(todoItem.getId());
        assertNull(todoData.getItem(todoItem.getId()));
    }

//    @Test
    void getData() {
    }
}