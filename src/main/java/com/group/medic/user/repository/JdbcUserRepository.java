package com.group.medic.user.repository;

import com.group.medic.document.model.Document;
import com.group.medic.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcUserRepository implements UserRepository{

    private static final BeanPropertyRowMapper<User> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertTask;

    public JdbcUserRepository(
            JdbcTemplate jdbcTemplate,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.insertTask = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * from users order by name", ROW_MAPPER);
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?",
                ROW_MAPPER, (Integer) id);
        return DataAccessUtils.singleResult(users);
    }


    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public User add(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("role_name", user.getRoleName());

        Number newKey = insertTask.executeAndReturnKey(map);
        user.setId(newKey.intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail())
                .addValue("phone_number", user.getPhoneNumber())
                .addValue("role_name", user.getRoleName().toString());

        return 1 == namedParameterJdbcTemplate.update("update users set name = :name, login = :login, " +
                "password = :password, email = :email, phone_number = :phone_number, role_name = :role_name " +
                "where id = :id", map);
    }

    @Override
    public User findByLogin(String login) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE login=?",
                ROW_MAPPER, (String) login);
        return DataAccessUtils.singleResult(users);
    }
}
