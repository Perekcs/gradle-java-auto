package org.tasklist;

import org.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void markAsCompleted(Task task) {
        task.setCompleted(true);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<Task>(tasks);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Task List:\n");
        for (Task task : tasks) {
            result.append(task.toString()).append("\n");
        }
        return result.toString();
    }
}
