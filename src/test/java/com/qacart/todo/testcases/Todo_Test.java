package com.qacart.todo.testcases;




import com.qacart.todo.UserApi.TodoApi;
import com.qacart.todo.data.ErrorMEessages;
import com.qacart.todo.models.Todo1;
import com.qacart.todo.steps.TodoSteps;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import com.qacart.todo.models.Todo;

@Feature("Todo Class")
public class Todo_Test {
    @Story("USer should be able to add task")
    @Test (description =  "USer should be able to add task")
    public void UserShouldBeAbleToAddTask(){

        Todo todo = new Todo(false,"Learn Appium");

      String Token = UserSteps.getAccessToken();
        Response response = TodoApi.AddTask(todo , Token);
        Todo1 returnedTodo= response.body().as(Todo1.class);
        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
         // assertThat(response.path("isCompleted"),equalTo(todo.getIsCompleted()));

    }
    @Story("User should not be able to add task")
   @Test(description = "User should not be able to add task")
    public void UserShouldNotBeAbleToAddTask() {
                Todo todo = new Todo("Learn Appium");
                String Token = UserSteps.getAccessToken();
                Response response=  TodoApi.AddTask(todo ,Token);
                assertThat(response.statusCode(),equalTo(400));
                assertThat(response.path("message"), equalTo(ErrorMEessages.IS_COMPLETED));
    }

    @Story("User should be able to get task ID ")
    @Test (description = "User should be able to get task ID ")
    public void UserShouldBeAbleToGetTaskByID(){
                String Token = UserSteps.getAccessToken();
                Todo todo = TodoSteps.generateTodo();
                String TodoID= TodoSteps.getTodoID(todo,Token);
                Response response = TodoApi.getTodo(TodoID,Token);

                Todo returnedTodo= response.body().as(Todo.class);
                assertThat(response.statusCode(),equalTo(200));
                assertThat(returnedTodo.getItem(),equalTo(todo.getItem()));
                assertThat(response.path("isCompleted"),equalTo(false));

    }
    @Story("User should be able to delete task by ID")
    @Test(description = "User should be able to delete task by ID ")
    public void UserShouldBeAbleToDeleteTaskById(){
        String Token = UserSteps.getAccessToken();
        Todo todo = TodoSteps.generateTodo();
        String TodoID= TodoSteps.getTodoID(todo,Token);

       Response response =  TodoApi.deleteTodo(TodoID,Token);

                assertThat(response.statusCode(),equalTo(200));
                assertThat(response.path("item"),equalTo(todo.getItem()));
                assertThat(response.path("isCompleted"),equalTo(false));

    }
}
