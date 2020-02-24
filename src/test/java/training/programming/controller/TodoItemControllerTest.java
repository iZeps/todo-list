package training.programming.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import training.programming.config.WebConfig;
import training.programming.model.TodoItem;
import training.programming.service.TodoItemService;
import training.programming.util.AttributeName;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class TodoItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoItemService todoItemServiceMock;

    @InjectMocks
    private TodoItemController todoItemController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc =  MockMvcBuilders.standaloneSetup(todoItemController).build();
    }

    @Test
    public void shouldGoToItemListView() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("items_list"))
                .andExpect(forwardedUrl("items_list"));
    }

    @Test
    public void shouldGoToSpecifiedItemView() throws Exception {
        TodoItem item = new TodoItem("title","details", LocalDate.now());
        when(todoItemServiceMock.getItem(1)).thenReturn(item);
        mockMvc.perform(get("/viewItem?id={id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("view_item"))
                .andExpect(forwardedUrl("view_item"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is("title"))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is("details"))));

        verify(todoItemServiceMock, times(1)).getItem(1);
    }

    @Test
    public void shouldEditItemOfListAndGoToAddView() throws Exception {
        TodoItem item = new TodoItem("second changed","second details changed", LocalDate.now());
        when(todoItemServiceMock.getItem(2)).thenReturn(item);
        mockMvc.perform(get("/addItem?id={id}",2))
                .andExpect(status().isOk())
                .andExpect(view().name("add_item"))
                .andExpect(forwardedUrl("add_item"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is("second changed"))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is("second details changed"))));

        verify(todoItemServiceMock, times(1)).getItem(2);
    }

    @Test
    public void shouldAddItemToListAndGoToAddView() throws Exception {
        when(todoItemServiceMock.getItem(-1)).thenReturn(null);
        mockMvc.perform(get("/addItem?id={id}",-1))
                .andExpect(status().isOk())
                .andExpect(view().name("add_item"))
                .andExpect(forwardedUrl("add_item"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is(""))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is(""))));

        verify(todoItemServiceMock, times(1)).getItem(-1);
    }

    @Test
    public void shouldDeleteItemAndRedirectToItemsView() throws Exception {
        doNothing().when(todoItemServiceMock).removeItem(1);
        mockMvc.perform(get("/deleteItem?id={id}",1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/items"))
                .andExpect(redirectedUrl("/items"));

        verify(todoItemServiceMock, times(1)).removeItem(1);
    }

    @Test
    public void shouldAddNewItemAndGoToItemsView() throws Exception {
        TodoItem item = new TodoItem("new","new", LocalDate.now());

        mockMvc.perform(post("/addItem")
                .flashAttr("todoItem", item)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/items"))
                .andExpect(redirectedUrl("/items"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is("new"))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is("new"))));
    }

    @Test
    public void shouldUpdateItemAndGoToItemsView() throws Exception {
        TodoItem item = new TodoItem("first changed","first changed", LocalDate.now());
        item.setId(1);
        mockMvc.perform(post("/addItem")
                .flashAttr("todoItem", item)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/items"))
                .andExpect(redirectedUrl("/items"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is("first changed"))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is("first changed"))));
    }
}