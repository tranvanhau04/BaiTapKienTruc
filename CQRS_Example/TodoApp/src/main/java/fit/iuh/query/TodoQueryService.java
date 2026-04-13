package fit.iuh.query;

import fit.iuh.common.Todo;
import fit.iuh.common.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoQueryService {
    @Autowired
    private TodoRepository repository;

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}