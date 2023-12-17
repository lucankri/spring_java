package edu.school21.service.repositories;

import edu.school21.service.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sqlFindByIdQuery = "SELECT * FROM \"user\" WHERE id = :id";
        return jdbcTemplate.query(sqlFindByIdQuery,
                new MapSqlParameterSource().addValue("id", id),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public List<User> findAll() {
        String sqlFindAllQuery = "SELECT * FROM \"user\"";
        return jdbcTemplate.query(sqlFindAllQuery, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        String sqlSaveQuery = "INSERT INTO \"user\" (id, email) VALUES (:id, :email)";
        jdbcTemplate.update(sqlSaveQuery,
                new MapSqlParameterSource()
                        .addValue("id", entity.getId())
                        .addValue("email", entity.getEmail()));
    }

    @Override
    public void update(User entity) {
        String sqlSaveQuery = "UPDATE \"user\" SET email = :email WHERE id = :id";
        jdbcTemplate.update(sqlSaveQuery,
                new MapSqlParameterSource().addValue("email", entity.getEmail())
                        .addValue("id", entity.getId()));
    }

    @Override
    public void delete(Long id) {
        String sqlDeleteQuery = "DELETE FROM \"user\" WHERE id = :id";
        jdbcTemplate.update(sqlDeleteQuery,
                new MapSqlParameterSource().addValue("id", id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sqlFindByEmail = "SELECT * FROM \"user\" WHERE email = :email";
        return jdbcTemplate.query(sqlFindByEmail,
                new MapSqlParameterSource().addValue("email", email),
                new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }
}
