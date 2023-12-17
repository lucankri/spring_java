package edu.school21.service.config;

import edu.school21.service.repositories.UsersRepositoryJdbcTemplateImpl;
import edu.school21.service.services.UsersService;
import edu.school21.service.repositories.UsersRepository;
import edu.school21.service.services.UsersServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
//@Import(ApplicationConfig.class)
public class TestApplicationConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @PostConstruct
    public void init() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"user\" (" +
                "id SERIAL PRIMARY KEY, " +
                "email VARCHAR(50) UNIQUE, " +
                "password VARCHAR(20)" +
                ")";
        jdbcTemplate.execute(createTableSQL);
    }

    @Bean
    public UsersRepository usersRepositoryJdbcTemplate() {
        return new UsersRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepositoryJdbcTemplate());
    }
}