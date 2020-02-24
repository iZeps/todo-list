package training.programming.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import training.programming.model.TodoData;
import training.programming.model.TodoItem;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoItemServiceImplTest {

    private String title = "title";

    private TodoItemServiceImpl todoItemService = new TodoItemServiceImpl();

    private TodoData todoData = new TodoData();
    private TodoItem todoItem = new TodoItem(title, "details", LocalDate.now());

    @BeforeEach
    void setUp() {
        todoItemService.addItem(todoItem);
    }

    @Test
    void shouldAddItemToServiceImplementation() {
        assertEquals(5, todoItemService.getData().getItems().size());
    }

    @Test
    void shouldGetItemFromDataObject() {
        assertEquals(title, todoItemService.getItem(todoItem.getId()).getTitle());
    }

    @Test
    void shouldUpdateItemsDetails() {
        todoItem.setTitle("changed");
        todoItemService.updateItem(todoItem);
        assertEquals("changed", todoItemService.getItem(todoItem.getId()).getTitle());
    }

    @Test
    void shouldRemoveItemFromDataObject() {
        todoItemService.removeItem(todoItem.getId());
        assertNull(todoItemService.getItem(todoItem.getId()));
    }

    @Test
    void shouldGetDataObjectFromService() {
        assertNotNull(todoItemService.getData());
    }
}