package training.programming.service;

import org.junit.Before;
import org.junit.Test;
import training.programming.model.TodoData;
import training.programming.model.TodoItem;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoItemServiceImplTest {

    private String title = "title";

    private TodoItemServiceImpl todoItemService = new TodoItemServiceImpl();

    private TodoData todoData = new TodoData();
    private TodoItem todoItem = new TodoItem(title, "details", LocalDate.now());

    @Before
    public void setUp() {
        todoItemService.addItem(todoItem);
    }

    @Test
    public void shouldAddItemToServiceImplementation() {
        assertEquals(5, todoItemService.getData().getItems().size());
    }

    @Test
    public void shouldGetItemFromDataObject() {
        assertEquals(title, todoItemService.getItem(todoItem.getId()).getTitle());
    }

    @Test
    public void shouldUpdateItemsDetails() {
        todoItem.setTitle("changed");
        todoItemService.updateItem(todoItem);
        assertEquals("changed", todoItemService.getItem(todoItem.getId()).getTitle());
    }

    @Test
    public void shouldRemoveItemFromDataObject() {
        todoItemService.removeItem(todoItem.getId());
        assertNull(todoItemService.getItem(todoItem.getId()));
    }

    @Test
    public void shouldGetDataObjectFromService() {
        assertNotNull(todoItemService.getData());
    }
}