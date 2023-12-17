package edu.school21.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Objects;

@Configuration
@ComponentScan("edu.school21.service")
@PropertySource("classpath:db.properties")
public class ApplicationConfig {
    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource driverManagerDataSourceBean() {
        DriverManagerDataSource driverManagerDataSource = new
                DriverManagerDataSource(Objects.requireNonNull(env.getProperty("db.url")),
                Objects.requireNonNull(env.getProperty("db.user")),
                Objects.requireNonNull(env.getProperty("db.password")));
        driverManagerDataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver.name")));
        return driverManagerDataSource;
    }

    @Bean
    public HikariDataSource hikariDataSourceBean() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(env.getProperty("db.url"));
        hikariDataSource.setUsername(env.getProperty("db.user"));
        hikariDataSource.setPassword(env.getProperty("db.password"));
        hikariDataSource.setDriverClassName(env.getProperty("db.driver.name"));
        return hikariDataSource;
    }
}
