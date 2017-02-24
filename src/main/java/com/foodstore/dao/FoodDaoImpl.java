package com.foodstore.dao;

import com.foodstore.entity.Food;
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
import java.util.List;

@Repository
public class FoodDaoImpl implements FoodDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

    private static final class FoodMapper implements RowMapper<Food> {
        public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
            Food food = new Food();
            food.setId(rs.getLong("id"));
            food.setFoodname(rs.getString("foodname"));
            food.setPrice(rs.getString("price"));
            food.setQuantity(rs.getInt("quantity"));
            food.setIsvip(rs.getBoolean("isvip"));
            food.setDescription(rs.getString("description"));
            return food;
        }
    }
	
    public Food createFood(final Food food) {
        final String sql = "insert into sys_food(foodname, price, quantity, isvip, description) values(?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, food.getFoodname());
                psst.setString(2, food.getPrice());
                psst.setInt(3, food.getQuantity());
                psst.setBoolean(4, food.getIsvip());
                psst.setString(5, food.getDescription());
                return psst;
            }
        }, keyHolder);

        food.setId(keyHolder.getKey().longValue());
        return food;
    }

    public Food updateFood(Food food) {
        String sql = "update sys_food set foodname=?, price=?, quantity=?, isvip=?, description=? where id=?";
        jdbcTemplate.update(sql, food.getFoodname(), food.getPrice(), food.getQuantity(), food.getIsvip(), food.getDescription(), food.getId());
        return food;
    }

    public void deleteFood(Long foodId) {
        String sql = "delete from sys_food where id=?";
        jdbcTemplate.update(sql, foodId);
    }

    public Food findOne(Long foodId) {
        String sql = "select * from sys_food where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{foodId}, new FoodMapper());
    }

    public List<Food> findAll() {
        String sql = "select * from sys_food";
        return jdbcTemplate.query(sql, new FoodMapper());
    }
}
