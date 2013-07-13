package com.test.djackatron2.service

import static spock.lang.MockingApi.*

import org.junit.rules.ExpectedException;

import spock.lang.*

import com.test.djackatron2.model.Account
import com.test.djackatron2.util.FlatFeePolicy
import com.test.djackatron2.util.IllegalAmountException
import com.test.djackatron2.util.InsufficientFundException

class TransferServiceSpec extends Specification {
	Account account1
	Account account2
	FlatFeePolicy policy
	TransferService transferService
	
	def setup() {
		account1 = new Account()
		account2 = new Account()	
		policy = Mock()
		transferService = new TransferService()
	}
	
	@Unroll("transfer #transferAmount, fee #fee; a1 #balance1 to a2 #balance2 then a1(#newBalance1) and a2(#newBalance2)")
	def "test transfer"() {
		given:
			account1.setBalance(balance1)
			account2.setBalance(balance2)						
			transferService.setFeePolicy(policy)
			1 * policy.getFee(_) >> {return fee}
			
		when:
			transferService.transfer(account1, account2, transferAmount)
								
		then:			
			newBalance1 == account1.balance
			newBalance2 == account2.balance
			
		where:
		balance1	| balance2	| fee	| transferAmount 	| newBalance1 	| newBalance2
		100			| 0			| 5		| 30				| 65			|	30
		100			| 0			| 5		| 10				| 85			|	10
		100			| 0			| 5		| 95				| 0				|	95
		100			| 0			| 0		| 30				| 70			|	30
	}
	
	
	def "test insufficient fund case"(){
		setup:
			def balance1 = 10
			def balance2 = 0
			def fee = 5
			account1.setBalance(balance1)
			account2.setBalance(balance2)
			transferService.setFeePolicy(policy)
			1 * policy.getFee(_) >> {return fee}

		when:
			transferService.transfer(account1, account2, transferAmount)
			
		then:			
			thrown(expectedException)
			balance1 == account1.balance
			balance2 == account2.balance
					
		where:
		transferAmount 	| expectedException
		 30				| InsufficientFundException
		 30				| InsufficientFundException
	} 
	
	
	def "test illegal amount case"(){
		setup:
			def balance1 = 100
			def balance2 = 0
			def fee = 5
			account1.setBalance(balance1)
			account2.setBalance(balance2)
			transferService.setFeePolicy(policy)
			transferService.setMinimumAmount(minimumAmount)
			1 * policy.getFee(_) >> {return fee}

		when:
			transferService.transfer(account1, account2, transferAmount)
			
		then:			
			thrown(expectedException)
			balance1 == account1.balance
			balance2 == account2.balance
		
		where:
		minimumAmount	| transferAmount	| expectedException
		 0				| 0					| IllegalAmountException
		 0				| -10				| IllegalAmountException
		 30				| 29				| IllegalAmountException
	}
		
}
