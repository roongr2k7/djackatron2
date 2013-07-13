package com.test.djackatron2.util;

public class FlatFeePolicy implements FeePolicy {
	private double fee;
	public static final String POLICY_TYPE_TRANSFER = "transfer";

	public FlatFeePolicy(double fee) {
		this.fee = fee;
	}

	@Override
	public double getFee(double amount) {
		return fee;
	}

}
