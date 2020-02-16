package training.programming.controller;

import training.programming.model.TodoData;
import training.programming.model.TodoItem;
import training.programming.service.TodoItemService;
import training.programming.util.AttributeName;
import training.programming.util.Mappings;
import training.programming.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class TodoItemController {

    // fields
    private final TodoItemService todoItemService;

    // constructors
    @Autowired
    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    // model attribute section
    @ModelAttribute
    public TodoData todoData() {
        return todoItemService.getData();
    }

    // handler methods
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }

    @GetMapping(Mappings.ADD_ITEM)
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id, Model model) {
        TodoItem todoItem = todoItemService.getItem(id);
        if ( todoItem == null ) {
            todoItem = new TodoItem("", "", LocalDate.now());
        }
        model.addAttribute(AttributeName.TODO_ITEMS, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {
        model.addAttribute(AttributeName.TODO_ITEMS, todoItemService.getItem(id));
        return ViewNames.VIEW_ITEM;
    }

    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        todoItemService.removeItem(id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeName.TODO_ITEMS) TodoItem todoItem) {
        if ( todoItem.getId() == 0) {
            todoItemService.addItem(todoItem);
        } else {
            todoItemService.updateItem(todoItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }
}
