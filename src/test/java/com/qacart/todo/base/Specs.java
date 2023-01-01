package com.qacart.todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
public class Specs {

    public static String getENV(){
       String env =  System.getProperty("env","PRODUCTION");
        String baseURL ;
        switch (env){
           case "PRODUCTION":
               baseURL = "https://qacart-todo.herokuapp.com";
               break;
            case "LOCAL":
                baseURL="http://localhost:8080";
                break;
            default:
               // throw  new RuntimeException("Environement is not supported");
                baseURL = "https://qacart-todo.herokuapp.com";

       }
       return baseURL ;
    }
    public static RequestSpecification getRequestSpecs(){
        System.getProperty("env");
        return  given().baseUri(getENV()).
                contentType(ContentType.JSON).log().all();
            }
}
