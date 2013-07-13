package com.test.djackatron2.util;

public class VariableFeePolicy implements FeePolicy {

	@Override
	public double getFee(double amount) {
		if (amount <= 1000) {
			return 0;
		} else if (amount <= 1000000) {
			return amount * 0.01;
		} else if (amount > 1000000) {
			return 20000;
		}
		return 0;
	}

}
