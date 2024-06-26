import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.task.Task;
import org.tasklist.TaskList;



import static org.assertj.core.api.Assertions.assertThat;



public class JUnitTest {
    @Test()
    void tasksAmountTest(){
        TaskList taskList = new TaskList();
        Task task = new Task("Test task", "Test task");
        Assumptions.assumeTrue(task.getTitle() != null && task.getDescription() != null,
                "Task title and description should not be null");

        taskList.addTask(task);

        assertThat(taskList.getAllTasks()).hasSize(1);

    }

    @ParameterizedTest
    @CsvSource({
            "Test task 1, This is the first test task",
            "Test task 2, This is the second test task",
            "Test task 3, This is the third test task"
    })


    void parameterAddTasksTest(String title, String description) {
        TaskList taskList = new TaskList();
        Task task = new Task(title, description);

        Assumptions.assumeTrue(title != null && description != null,
                "Task title and description should not be null");

        taskList.addTask(task);

        Task addedTask = taskList.getAllTasks().get(0);
        assertThat(title).isEqualTo(addedTask.getTitle());
        assertThat(description).isEqualTo(addedTask.getDescription());
    }


}
