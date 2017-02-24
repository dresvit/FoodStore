package com.foodstore.dao;

import com.foodstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setSalt(rs.getString("salt"));
            return user;
        }
    }
	
    public User createUser(final User user) {
        final String sql = "insert into sys_users(username, password, salt) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, user.getUsername());
                psst.setString(2, user.getPassword());
                psst.setString(3, user.getSalt());
                return psst;
            }
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public User updateUser(User user) {
        String sql = "update sys_users set username=?, password=?, salt=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getId());
        return user;
    }

    public void deleteUser(String username) {
        String sql = "delete from sys_users where username=?";
        jdbcTemplate.update(sql, username);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
        for(Long roleId : roleIds) {
            if(!exists(userId, roleId)) {
            	jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(roleIds == null || roleIds.length == 0) {
            return;
        }
        String sql = "delete from sys_users_roles where user_id=? and role_id=?";
        for(Long roleId : roleIds) {
            if(exists(userId, roleId)) {
            	jdbcTemplate.update(sql, userId, roleId);
            }
        }
    }

    private boolean exists(Long userId, Long roleId) {
        String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, userId, roleId) != 0;
    }

    public User findOne(Long userId) {
        String sql = "select * from sys_users where id=?";
        List<User> userList = jdbcTemplate.query(sql, new UserMapper(), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public User findByUsername(String username) {
        String sql = "select * from sys_users where username=?";
        List<User> userList = jdbcTemplate.query(sql, new UserMapper(), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    public Set<String> findRoles(String username) {
        String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet<String>(jdbcTemplate.queryForList(sql, String.class, username));
    }
}
