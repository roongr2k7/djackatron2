package com.test.djackatron2.util

import spock.lang.*

class FeePolicySpec extends Specification {
	
	
	@Unroll("transfer #amount with flatFee #flatFee expect fee to equal #expect")
	def "test flat fee policy"() {
		given:
			FeePolicy policy = new FlatFeePolicy(flatFee)
		
		when:
			def actual = policy.getFee(amount)
		
		then:
			expect == actual
		
		where:
		amount 	| flatFee 	| expect
		1		|	5		| 5
		100		|	5		| 5
		1_000	|	5		| 5
		1_000	|	3		| 3
	}
	
	def "test variable fee policy"() {
		given:
			FeePolicy policy = new VariableFeePolicy()
		
		when:
			def actual = policy.getFee(amount)
		
		then:
			expect == actual
		
		where:
		amount 		| expect
		1_000		| 0
		1_001		| 10.01
		1_000_000	| 10_000
		1_000_001	| 20_000
	}
}
