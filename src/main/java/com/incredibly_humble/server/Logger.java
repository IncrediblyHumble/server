package com.incredibly_humble.server;

import com.incredibly_humble.models.Log;

import java.util.ArrayList;
import java.util.Date;

public class Logger {
    private static ArrayList<Log> LOGS;

    public static void INIT(){
        LOGS = new ArrayList<>();
    }

     public static void LOG(String request, String body, String response){
         LOGS.add(new Log(request,new Date(),body,response));
     }

     public static ArrayList<Log> GET_LOGS(){
         return LOGS;
     }
}
