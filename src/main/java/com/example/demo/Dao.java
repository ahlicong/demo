package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BookingDto;
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
				+ " select username, operand, sum(correct), sum(total), sum(consume), max(create_time)"
				+ " from results group by username, operand;";
		jdbcTemplate.update(sql);
	}

	public List<ResultDto> getRanks() {
		String sql = "select * from ranks";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ResultDto>(ResultDto.class));
	}

	public void insertBooking(BookingDto booking) {
		String sql = "insert into bookings(username, date, time, create_time) values(?,?,?,now())";
		jdbcTemplate.update(sql, booking.getUsername(), booking.getDate(), booking.getTime());
	}

	public BookingDto getBooking(BookingDto booking) {
		String sql = "select * from bookings where username=? and date=?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<BookingDto>(BookingDto.class),
				booking.getUsername(), booking.getDate());
	}
	
	public int getBookingCount(String date, String time) {
		String sql = "select count(1) from bookings where date=? and time=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, date, time);
	}
	
	public List<BookingDto> getBookings() {
		String sql = "select * from bookings where date >= ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<BookingDto>(BookingDto.class), Utils.today());
	}
}
