package com.javarush.test.level38.lesson06.home01;

/**
 * Created by lollipop on 01.08.2016.
 */
public class ExceptionFactory
{
    public static Throwable exceptions(Enum e) throws Exception
    {
        if (e instanceof ExceptionApplicationMessage) {
            switch ((ExceptionApplicationMessage) e) {
                case UNHANDLED_EXCEPTION: return new Exception("Unhandled exception");
                case SOCKET_IS_CLOSED: return new Exception("Socket is closed");
            }
        }
        else if (e instanceof ExceptionDBMessage) {
            switch ((ExceptionDBMessage) e) {
                case NOT_ENOUGH_CONNECTIONS: return new RuntimeException("Not enough connections");
                case RESULT_HAS_NOT_GOTTEN_BECAUSE_OF_TIMEOUT: return new RuntimeException("Result has not gotten because of timeout");
            }
        }
        else if (e instanceof ExceptionUserMessage) {
            switch ((ExceptionUserMessage) e) {
                case USER_DOES_NOT_EXIST: return new Error("User does not exist");
                case USER_DOES_NOT_HAVE_PERMISSIONS: return new Error("User does not have permissions");
            }
        }
        else return new IllegalArgumentException();
        return null;
    }
}
