package training.programming.service;

import training.programming.model.TodoData;
import training.programming.model.TodoItem;

public interface TodoItemService {

    void addItem(TodoItem todoItem);

    void removeItem(int id);

    TodoItem getItem(int id);

    void updateItem(TodoItem todoItem);

    TodoData getData();
}
