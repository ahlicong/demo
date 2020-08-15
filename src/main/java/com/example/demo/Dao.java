package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
		String sql = "insert into results(username, operand, correct, total, consume, create_time) values(?,?,?,?,?,now())";
		jdbcTemplate.update(sql, result.getUsername(), result.getOperand(), result.getCorrect(), result.getTotal(),
				result.getConsume());
	}

	public List<ResultDto> getResults(String username) {
		String sql = "select * from results where username=?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ResultDto>(ResultDto.class), username);
	}

	public void refreshRanks() {
		String sql = "truncate table ranks;"
				+ " insert into ranks(username, operand, correct, total, consume, create_time)"
				+ " select username, operand, sum(correct), sum(total), sum(consume), now()"
				+ " from results group by username, operand;";
		jdbcTemplate.update(sql);
	}

	public List<ResultDto> getRanks() {
		String sql = "select * from ranks";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ResultDto>(ResultDto.class));
	}

}
