package com.nwhite;

import com.nwhite.service.ConsoleRunnerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.nwhite");
        context.refresh();

        ConsoleRunnerService consoleRunnerService = (ConsoleRunnerService)context.getBean("consoleRunnerService");

        consoleRunnerService.run();
    }
}
