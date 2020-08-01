package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ResultDto;

@Repository
public class Dao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public void insertTest(long count, String name) {
		String sql = "insert into test(c1, c2) values(?,?)";
		jdbcTemplate.update(sql, count, name);
	}
	
	public void insertResult(ResultDto result) {
		String sql = "insert into results(username, correct, total, consume, create_time) values(?,?,?,?,now())";
		jdbcTemplate.update(sql, result.getUsername(), result.getCorrect(), result.getTotal(), result.getConsume());
	}
}
