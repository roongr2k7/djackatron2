package com.test.djackatron2.service;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.util.FeePolicy;
import com.test.djackatron2.util.IllegalAmountException;
import com.test.djackatron2.util.InsufficientFundException;

public class TransferService {
	FeePolicy policy;
	private double minimumAmount;

	public void setFeePolicy(FeePolicy policy) {
		this.policy = policy;
	}

	public void transfer(Account source, Account target, double amount) {
		double sourceBalance = source.getBalance();
		double deductAmount = amount + policy.getFee(amount);
		double newSourceBalance = sourceBalance - deductAmount;
		if (amount <= this.getMinimumAmount() || amount <= 0) {
			throw new IllegalAmountException();
		} else	if (sourceBalance >= deductAmount) {
			source.setBalance(newSourceBalance);
			target.setBalance(amount);
		} else {
			throw new InsufficientFundException();
		}
	}

	public double getMinimumAmount() {
		return minimumAmount;
	}

	public void setMinimumAmount(double minimumAmount) {
		this.minimumAmount = minimumAmount;
	}
}
