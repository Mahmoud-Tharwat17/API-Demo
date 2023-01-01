package com.qacart.todo.testcases;
import com.qacart.todo.UserApi.UserApi;
import com.qacart.todo.data.ErrorMEessages;
import com.qacart.todo.models.Error;
import com.qacart.todo.models.User;
import com.qacart.todo.steps.UserSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Feature("User Test cases Class")
public class User_Test {
    
    @Story("User Should be able to register")
    @Test (description = "User Should be able to register")
    public void should_be_able_to_register() {

        User user= UserSteps.generateUser();
        Response response = UserApi.register(user);
        User returnedUser= response.body().as(User.class);
        assertThat(response.statusCode(),equalTo(201));
        assertThat(returnedUser.getFirstName(),equalTo(user.getFirstName()));

    }
    @Story("User should not be able to register with same email")
    @Test(description =  "User should not be able to register with same email")
    public void should_no_be_able_to_register_with_same_email() {

        User user = UserSteps.getRegisteredUser();
        Response response = UserApi.register(user);
        Error returnedError = response.body().as(Error.class);
        assertThat(response.statusCode(),equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(ErrorMEessages.EMAIL_IS_ALREADY_REGISTERED));
    }
    @Story("User should be able to login")
    @Test(description =  "User should be able to login")
    public void UserShouldbeAbleToLogin() {

                User user= UserSteps.getRegisteredUser();
                User loginData=new User(user.getEmail(), user.getPassword());

                Response response = UserApi.Login(loginData);
                User returnedUser= response.body().as(User.class);
                assertThat(response.statusCode(),equalTo(200));
                assertThat(returnedUser.getFirstName(),  equalTo(user.getFirstName()));
                assertThat(returnedUser.getAccessToken(), not(equalTo(null)));
    }
    @Story("User should not be able to login")
    @Test (description = "User should not be able to login")
    public void UserShouldNotbeAbleToLogin() {
                User user= UserSteps.getRegisteredUser();
                User loginData=new User(user.getEmail(), "Wrongpassword");

                Response response = UserApi.Login(loginData);
                assertThat(response.statusCode(),equalTo(401));
                assertThat(response.path("message"), (equalTo(ErrorMEessages.EMAIL_OR_PASSWORD_IS_NOT_CORRECT)));
    }
}