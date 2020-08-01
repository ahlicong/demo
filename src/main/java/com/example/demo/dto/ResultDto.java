package com.example.demo.dto;

import java.util.Date;

public class ResultDto {
	private String username;
	private int correct;
	private int total;
	private int consume;
	private Date createTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getConsume() {
		return consume;
	}

	public void setConsume(int consume) {
		this.consume = consume;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
