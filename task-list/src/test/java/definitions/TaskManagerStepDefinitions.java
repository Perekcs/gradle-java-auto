package definitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.task.Task;
import org.tasklist.TaskList;

import java.util.List;

public class TaskManagerStepDefinitions {
    private TaskList taskList;
    private Task task;

    @Given("an empty task list")
    public void an_empty_task_list() {
        taskList = new TaskList();
    }

    @When("I add a task with title {string} and description {string}")
    public void i_add_a_task_with_title_and_description(String title, String description) {
        task = new Task(title, description);
        taskList.addTask(task);
    }

    @Then("the task list should contain a task with title {string} and description {string}")
    public void the_task_list_should_contain_a_task_with_title_and_description(String title, String description) {
        List<Task> tasks = taskList.getAllTasks();
        Assertions.assertEquals(1, tasks.size(), "Task list should contain one task");
        Task addedTask = tasks.get(0);
        Assertions.assertEquals(title, addedTask.getTitle(), "Task title should match");
        Assertions.assertEquals(description, addedTask.getDescription(), "Task description should match");
    }
}
