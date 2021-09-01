package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping("/getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @GetMapping("/getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @PostMapping(value="/createTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
           Task task = taskMapper.mapToTask(taskDto);
           service.saveTask(task);
    }

    @PutMapping(value="updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
    }

//    @PutMapping(value="updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public TaskDto updateTask(@RequestBody TaskDto taskDto){
//        Task task = taskMapper.mapToTask(taskDto);
//        Task savedTask = service.saveTask(task);
//        return taskMapper.mapToTaskDto(savedTask);
//    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestParam Long taskId) throws TaskNotFoundException{
        service.deleteTask(taskId);
    }

}
