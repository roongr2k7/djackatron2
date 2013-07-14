package com.test.djackatron2.service;

import org.joda.time.LocalTime;

public class TimeService {
	private LocalTime startTime;
	private LocalTime endTime;
	
	boolean isServiceAvailable(LocalTime localTime) {		
		return !(localTime.isAfter(endTime) || localTime.isBefore(startTime));
	}
	
	public void setServiceTime(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;	
	}
}
