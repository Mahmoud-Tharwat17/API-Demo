package com.qacart.todo.UserApi;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.Todo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TodoApi {

    public static Response AddTask(Todo todo, String Token) {
        return given().spec(Specs.getRequestSpecs()).body(todo)
                .auth().oauth2(Token)
                .when().post(Route.TODOS_ROUTE)
                .then().log().all().extract().response();

    }

    public static Response getTodo(String taskID, String token) {

        return given().spec(Specs.getRequestSpecs())
                .auth().oauth2(token)
                .when().get(Route.TODOS_ROUTE+"/" + taskID)
                .then().log().all().extract().response();
    }

    public static Response deleteTodo(String taskID, String token) {

        return given().baseUri("https://qacart-todo.herokuapp.com")
                .auth().oauth2(token)
                .when().delete(Route.TODOS_ROUTE+"/" + taskID)
                .then().log().all().extract().response();
    }
}

