package training.programming.service;

import training.programming.model.TodoData;
import training.programming.model.TodoItem;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    // fields
    private final TodoData todoData = new TodoData();

    // methods
    @Override
    public void addItem(TodoItem todoItem) {
        todoData.addItem(todoItem);
    }

    @Override
    public void removeItem(int id) {
        todoData.removeItem(id);
    }

    @Override
    public TodoItem getItem(int id) {
        return todoData.getItem(id);
    }

    @Override
    public void updateItem(TodoItem todoItem) {
        todoData.updateItem(todoItem);
    }

    @Override
    public TodoData getData() {
        return todoData;
    }
}
