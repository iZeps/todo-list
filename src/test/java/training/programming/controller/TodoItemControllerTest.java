package training.programming.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import training.programming.config.WebConfig;
import training.programming.model.TodoItem;
import training.programming.service.TodoItemService;
import training.programming.service.TodoItemServiceImpl;
import training.programming.util.AttributeName;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
public class TodoItemControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TodoItemService todoItemServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        todoItemServiceMock = mock(TodoItemServiceImpl.class);
        Mockito.reset(todoItemServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void shouldReturnItemListView() throws Exception {
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(view().name("items_list"))
                .andExpect(forwardedUrl("/WEB-INF/view/items_list.jsp"));
    }

    @Test
    public void shouldReturnItemViewByParameterValue() throws Exception {
        TodoItem item = new TodoItem("title","details", LocalDate.now());
        when(todoItemServiceMock.getItem(1)).thenReturn(item);

        mockMvc.perform(get("/viewItem?id={id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("view_item"))
                .andExpect(forwardedUrl("/WEB-INF/view/view_item.jsp"))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("title", is("title"))))
                .andExpect(model().attribute(AttributeName.TODO_ITEMS, hasProperty("details", is("details"))));

        verify(todoItemServiceMock.getItem(1));
    }

    @Test
    public void addEditItem() {
    }

    @Test
    public void processItem() {
    }

    @Test
    public void deleteItem() {
    }
}