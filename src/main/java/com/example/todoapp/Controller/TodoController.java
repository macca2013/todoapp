package com.example.todoapp.Controller;

import com.example.todoapp.Repository.ToDoRepository;
import com.example.todoapp.domain.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final ToDoRepository toDorepository;
    @GetMapping("/")
    public String index(Model model){
        List<ToDo> todos = toDorepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam("todo") String todo){
        ToDo toDo = new ToDo();
        toDo.setTodo(todo);
        toDorepository.save(toDo);

        return "redirect:/";
    }

    @GetMapping("/deleteTodoAll")
    public String deleteTodoAll(){
        toDorepository.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/deleteTodo/{id}")
    public String deleteTodo(@PathVariable Long id){
        toDorepository.deleteById(id);
        return "redirect:/";
    }
}
