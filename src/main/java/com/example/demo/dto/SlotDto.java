package com.example.demo.dto;

public class SlotDto {
	private String date;
	private int num;

	public SlotDto(String date, int num) {
		super();
		this.date = date;
		this.num = num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
