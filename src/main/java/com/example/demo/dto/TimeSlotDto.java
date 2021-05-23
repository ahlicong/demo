package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotDto {
	private String time;
	private List<SlotDto> slots = new ArrayList<SlotDto>();

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<SlotDto> getSlots() {
		return slots;
	}

	public void setSlots(List<SlotDto> slots) {
		this.slots = slots;
	}

}
