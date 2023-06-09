package com.codeup.tasker.controllers;

import com.codeup.tasker.models.Task;
import com.codeup.tasker.models.User;
import com.codeup.tasker.repos.TasksRepo;
import com.codeup.tasker.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {

    private final TasksRepo tasksDao;

    private final UserRepo userDao;

    public TaskController(TasksRepo tasksDao, UserRepo userDao) {
        this.tasksDao = tasksDao;
        this.userDao = userDao;
    }

    @GetMapping("/tasks")
    public String viewAllTasks(Model model) {
        List<Task> tasks = tasksDao.findAll();
        model.addAttribute("taskList", tasks);
        return "/tasks/index";
    }

    @GetMapping("/tasks/create")
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "/tasks/create";
    }

    @PostMapping("/tasks/create")
    public String createTask(@ModelAttribute Task task) {
        User user = userDao.findById(1L).get();
        task.setUser(user);
        tasksDao.save(task);
        return "redirect:/tasks";
    }
}
