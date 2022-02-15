package com.example.examserver.custom.exceptions;

public class UserAlreadyExistException extends Exception{
   public UserAlreadyExistException() throws Exception{
        throw new Exception("Username Already Exist");
    }
}
