package com.test.djackatron2.util

import org.joda.time.LocalTime

import spock.lang.Specification
import spock.lang.Unroll

import com.test.djackatron2.service.TimeService

class TimeServiceSpec extends Specification {
	
	@Unroll("service aval 06:00 - 22:00 test with time #testTimeHour:#testTimeMinute")
	def "check available time"() {
		setup:
			def timeService = new TimeService()
			LocalTime startTime = new LocalTime(6, 00)
			LocalTime endTime = new LocalTime(22, 00)
			timeService.setServiceTime(startTime, endTime)
			LocalTime testTime = new LocalTime(testTimeHour, testTimeMinute)
		
		when: 
			def actual = timeService.isServiceAvailable(testTime)		
		
		then:
			expect == actual
			
		where:
		testTimeHour | testTimeMinute 	| expect
		 5			 | 59				| false
		 6			 | 00				| true
		 8			 | 00				| true
		 22			 | 00				| true
		 22			 | 01				| false
	}
}
