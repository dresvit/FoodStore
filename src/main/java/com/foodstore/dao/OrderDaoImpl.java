package com.foodstore.dao;

import com.foodstore.entity.Order;
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

@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    private static final class OrderMapper implements RowMapper<Order> {
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setCustomer(rs.getString("customer"));
            order.setAmount(rs.getString("amount"));
            return order;
        }
    }
	
    public long createOrder(final Order order) {
        final String sql = "insert into sys_order(customer, amount) values(?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, order.getCustomer());
                psst.setString(2, order.getAmount());
                return psst;
            }
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteOrder(Long orderId) {
        String sql = "delete from sys_order where id=?";
        jdbcTemplate.update(sql, orderId);
    }

    public Order findOne(Long orderId) {
        String sql = "select * from sys_order where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{orderId}, new OrderMapper());
    }
}
