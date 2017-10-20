package com.mohammadmirzakhani.courses;

import com.google.gson.Gson;
import com.mohammadmirzakhani.courses.enams.StatusResponse;
import com.mohammadmirzakhani.courses.interfaces.UserService;
import com.mohammadmirzakhani.courses.model.StandardResponse;
import com.mohammadmirzakhani.courses.model.User;
import org.omg.CORBA.UserException;

import java.util.ArrayList;
import java.util.Collection;

import static spark.Spark.*;

/**
 * Created by Mohammad Mirzakhani on 10/6/17.
 */
public class Main {


    private static UserService userService;


    public static void main(String[] args) {

//row - json
        post("/users", (request, response) -> {
            response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);

            userService.addUser(user);

            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //x-www-form
        post("/addUsers", (request, response) -> {
           userService.addUser(new User(request.queryParams("id"),
                   request.queryParams("firstName"),
                   request.queryParams("lastName"),request.queryParams("email")));
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });


        get("/users", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.getUsers())));
        });


        get("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson()
                            .toJsonTree(userService.getUser(request.params(":id")))));
        });

        delete("/users/:id", (request, response) -> {
            response.type("application/json");
            userService.deleteUser(request.params(":id"));
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, "user deleted"));
        });

        options("/users/:id", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,
                            (userService.userExist(
                                    request.params(":id"))) ? "User exists" : "User does not exists" ));
        });




        userService = new UserService() {
            ArrayList<User> users = new ArrayList<>();

            @Override
            public void addUser(User user) {
                users.add(user);
            }

            @Override
            public Collection<User> getUsers() {
                return users;
            }

            @Override
            public User getUser(String id) {
                for(User user : users){
                    if(user.getId().equalsIgnoreCase(id))
                        return user;
                }
                return null;
            }

            @Override
            public User editUser(User user) throws UserException {
                return null;
            }

            @Override
            public void deleteUser(String id) {
                for(User user: users){
                    if(user.getId().equalsIgnoreCase(id))
                        users.remove(user);
                }

            }

            @Override
            public boolean userExist(String id) {
                for (User user : users){
                    if(user.getId().equalsIgnoreCase(id))
                        return true;
                }

                return false;
            }
        };


    }

}
