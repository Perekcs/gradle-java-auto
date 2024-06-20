package org.app;

import org.task.Task;
import org.tasklist.TaskList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class Main {

    private TaskList taskList = new TaskList();
    private DefaultListModel<Task> taskListModel = new DefaultListModel<Task>();
    private JList<Task> taskListUI = new JList<Task>(taskListModel);
    private JTextField titleField = new JTextField(20);
    private JTextField descriptionField = new JTextField(20);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.initUI();

            try {
                Method methodAdd = app.getClass().getDeclaredMethod("addTask", ActionEvent.class);
                methodAdd.setAccessible(true);
                MethodLoggingLogic.logExecution(methodAdd);

                Method methodDelete = app.getClass().getDeclaredMethod("removeTask", ActionEvent.class);
                methodDelete.setAccessible(true);
                MethodLoggingLogic.logExecution(methodDelete);

                Method methodMark = app.getClass().getDeclaredMethod("markTaskAsCompleted", ActionEvent.class);
                methodMark.setAccessible(true);
                MethodLoggingLogic.logExecution(methodMark);
            } catch (NoSuchMethodException e) {
                System.out.println("No such method");
            }
        });
    }



    private void initUI() {

        JFrame frame = new JFrame("Task list");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        taskListUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainPanel.add(new JScrollPane(taskListUI), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("New task: "));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Task description: "));
        inputPanel.add(descriptionField);
        inputPanel.add(createButton("Add", this::addTask));
        inputPanel.add(createButton("Delete", this::removeTask));
        inputPanel.add(createButton("Mark as completed", this::markTaskAsCompleted));
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
        updateTaskListUI();

    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    @MethodLogging
    private void addTask(ActionEvent e) {

        String title = titleField.getText();
        String description = descriptionField.getText();
        Task task = new Task(title, description);
        taskList.addTask(task);
        updateTaskListUI();
    }

    @MethodLogging
    private void removeTask(ActionEvent e) {

        int selectedIndex = taskListUI.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = taskListModel.getElementAt(selectedIndex);
            taskList.removeTask(selectedTask);
            updateTaskListUI();
        }
    }

    private void markTaskAsCompleted(ActionEvent e) {

        int selectedIndex = taskListUI.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = taskListModel.getElementAt(selectedIndex);
            selectedTask.setCompleted(true);
            updateTaskListUI();
        }


    }

    private void updateTaskListUI() {
        taskListModel.clear();
        for (Task task : taskList.getAllTasks()) {
            taskListModel.addElement(task);
        }
    }
}