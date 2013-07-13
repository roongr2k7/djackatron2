package com.test.djackatron2.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FeePolicyTests {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTransferFee() {
		double flatFee = 5.0d;
		FlatFeePolicy policy = new FlatFeePolicy(flatFee);	
		double actual = policy.getFee(1);
		
		assertEquals(flatFee, actual, 0);
	}
	
	

}
