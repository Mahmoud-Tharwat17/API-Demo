package com.qacart.todo.UserApi;

import com.qacart.todo.base.Specs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    public  static Response register (User user) {

       return  given().spec(Specs.getRequestSpecs()).body(user).when().post(Route.REGISTER_ROUTE)
               .then().log().all().extract().response();
    }
public static  Response Login (User user) {
    return   given().spec(Specs.getRequestSpecs()).body(user).when().post(Route.LOGIN_ROUTE)
            .then().log().all().extract().response();
}
}
