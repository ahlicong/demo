package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BookingDto;
import com.example.demo.dto.Greeting;
import com.example.demo.dto.ResultDto;
import com.example.demo.dto.SlotDto;
import com.example.demo.dto.TimeSlotDto;

@RestController
public class Controller {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Autowired
	private Dao dao;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "username", defaultValue = "World") String name) {
		long count = counter.incrementAndGet();
		dao.insertTest(count, name);
		return new Greeting(count, String.format(template, name));
	}

	@PostMapping("/saveResult")
	public void saveResult(@RequestBody ResultDto result) {
		dao.insertResult(result);
	}

	@GetMapping("/getResults")
	public List<ResultDto> getResults(@RequestParam(value = "username") String username) {
		return dao.getResults(username);
	}

	@RequestMapping("/refreshRanks")
	public void refreshRanks() {
		dao.refreshRanks();
	}

	@GetMapping("/getRanks")
	public List<ResultDto> getRanks() {
		return dao.getRanks();
	}

	@PostMapping("/booking/saveBooking")
	public String saveBooking(@RequestBody BookingDto booking) {
		int count = dao.getBookingCount(booking.getDate(), booking.getTime());
		if (count > 1) {
			return "预约已满";
		}
		try {
			dao.getBooking(booking);
			return "同一天只能预约一次";
		} catch (Exception e) {
			dao.insertBooking(booking);
			return "预约成功";
		}
	}

	@GetMapping("/getTimeSlots")
	public List<TimeSlotDto> getTimeSlots() {
		String today = Utils.today();
		String tmr = Utils.tmr();
		final String[] times = { "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00",
				"14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30" };
		List<TimeSlotDto> timeSlots = new ArrayList<TimeSlotDto>();
		for (String time : times) {
			TimeSlotDto timeSlot = new TimeSlotDto();
			timeSlot.setTime(time);
			timeSlot.getSlots().add(new SlotDto(today, dao.getBookingCount(today, time)));
			timeSlot.getSlots().add(new SlotDto(tmr, dao.getBookingCount(tmr, time)));
			timeSlots.add(timeSlot);
		}
		return timeSlots;
	}

	@GetMapping("/booking/getBookings")
	public List<BookingDto> getBookings(@RequestParam(value = "token") String token) {
		if ("牛魔王".equals(token)) {
			return dao.getBookings();
		}
		return null;
	}
}
