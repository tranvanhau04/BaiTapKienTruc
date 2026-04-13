package fit.iuh.command;

import fit.iuh.common.Todo;
import fit.iuh.common.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoCommandService {
    @Autowired
    private TodoRepository repository;

    public void create(String task) {
        Todo todo = new Todo();
        todo.setTask(task);
        todo.setCompleted(false);
        repository.save(todo);
    }

    public void updateStatus(Long id, boolean status) {
        Todo todo = repository.findById(id).orElseThrow();
        todo.setCompleted(status);
        repository.save(todo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}