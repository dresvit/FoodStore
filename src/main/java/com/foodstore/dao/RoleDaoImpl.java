package com.foodstore.dao;

import com.foodstore.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    public Role createRole(final Role Role) {
        final String sql = "insert into sys_roles(role, description) values(?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, Role.getRole());
                psst.setString(2, Role.getDescription());
                return psst;
            }
        }, keyHolder);
        Role.setId(keyHolder.getKey().longValue());

        return Role;
    }

    public void deleteRole(Long roleId) {
        String sql = "delete from sys_users_roles where role_id=?";
        jdbcTemplate.update(sql, roleId);

        sql = "delete from sys_roles where id=?";
        jdbcTemplate.update(sql, roleId);
    }
}
