package edu.school21.service.application;

import edu.school21.service.config.ApplicationConfig;
import edu.school21.service.services.UsersService;
import edu.school21.service.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UsersService usersService = context.getBean("usersService", UsersServiceImpl.class);
        for (int i = 1; i <= 10; i++) {
            System.out.println(usersService.signUp(i + "root@mail.ru"));
        }
        context.close();
    }
}
