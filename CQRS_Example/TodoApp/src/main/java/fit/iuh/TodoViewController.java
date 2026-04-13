package fit.iuh;

import fit.iuh.command.TodoCommandService;
import fit.iuh.query.TodoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoViewController {
    @Autowired
    private TodoQueryService queryService;
    @Autowired
    private TodoCommandService commandService;

    @GetMapping
    public String index(Model model) {
        // Gửi Query để lấy dữ liệu
        model.addAttribute("todos", queryService.findAll());
        return "todo-list";
    }

    @PostMapping("/add")
    public String add(@RequestParam String task) {
        // Gửi Command để thay đổi dữ liệu
        commandService.create(task);
        return "redirect:/todos";
    }

    @PostMapping("/toggle/{id}")
    public String toggle(@PathVariable Long id, @RequestParam boolean status) {
        commandService.updateStatus(id, status);
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commandService.delete(id);
        return "redirect:/todos";
    }
}