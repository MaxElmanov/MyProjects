package ru.vebinar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.vebinar.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    public JdbcTemplate jt;

    @Override
    public List<User> findAll() {
        return jt.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User u = new User();
                u.setId(resultSet.getInt(1));
                u.setName(resultSet.getString(2));
                u.setEmail(resultSet.getString(3));
                u.setAge(resultSet.getInt(4));

                return u;
            }
        });
    }
}
