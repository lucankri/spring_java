package edu.school21.service.application;

import edu.school21.service.models.User;
import edu.school21.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository1 = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        UsersRepository usersRepository2 = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        usersRepository1.save(new User(1L, "234@mail.ru"));
        usersRepository1.save(new User(2L, "414123@mail.ru"));
        usersRepository2.save(new User(3L, "87265@mail.ru"));
        usersRepository2.save(new User(4L, "15451@mail.ru"));
        System.out.println(usersRepository1.findAll());
        System.out.println(usersRepository2.findAll());
    }
}
