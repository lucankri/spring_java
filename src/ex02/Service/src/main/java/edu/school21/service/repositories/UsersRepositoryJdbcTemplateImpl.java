package edu.school21.service.repositories;

import edu.school21.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("usersRepositoryJdbcTemplate")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("driverManagerDataSourceBean") DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sqlFindByIdQuery = "SELECT id, email FROM \"user\" WHERE id = :id";
        return jdbcTemplate.query(sqlFindByIdQuery,
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        String sqlFindAllQuery = "SELECT id, email FROM \"user\"";
        return jdbcTemplate.query(sqlFindAllQuery, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) throws EmailExistsException {
        String sqlSaveQuery = "INSERT INTO \"user\" (email, password) VALUES (:email, :password)";
        try {
            jdbcTemplate.update(sqlSaveQuery,
                    new MapSqlParameterSource()
                            .addValue("email", entity.getEmail())
                            .addValue("password", entity.getPassword()));
        } catch (DataAccessException e) {
            throw new EmailExistsException();
        }
    }

    @Override
    public void update(User entity) {
        String sqlSaveQuery = "UPDATE \"user\" SET email = :email, password = :password WHERE id = :id";
        jdbcTemplate.update(sqlSaveQuery,
                new MapSqlParameterSource().addValue("email", entity.getEmail())
                        .addValue("id", entity.getId())
                        .addValue("password", entity.getPassword()));
    }

    @Override
    public void delete(Long id) {
        String sqlDeleteQuery = "DELETE FROM \"user\" WHERE id = :id";
        jdbcTemplate.update(sqlDeleteQuery,
                new MapSqlParameterSource().addValue("id", id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sqlFindByEmail = "SELECT id, email FROM \"user\" WHERE email = :email";
        return jdbcTemplate.query(sqlFindByEmail,
                new MapSqlParameterSource().addValue("email", email),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
}
