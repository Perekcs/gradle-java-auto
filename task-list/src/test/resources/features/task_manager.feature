Feature: Task Manager
  As a user, I want to manage my tasks so that I can keep track of what needs to be done.

  Scenario Outline: Add tasks to the task list
    Given an empty task list
    When I add a task with title "<title>" and description "<description>"
    Then the task list should contain a task with title "<title>" and description "<description>"

    Examples:
      | title         | description                |
      | Test task 1   | This is the first test task|
      | Test task 2   | This is the second test task|
      | Test task 3   | This is the third test task|