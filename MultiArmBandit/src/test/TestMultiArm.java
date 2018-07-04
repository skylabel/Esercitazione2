package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.intecs.test.Bandit2;
import com.intecs.test.Reward2;

class TestMultiArm {

	@Test
	void testBandit1Loose() {
     MultiArm2 multi=new MultiArm2();   
     
     Reward2 actualreward=multi.choseArm1();
     assertEquals(new Reward2(false),actualreward);
	}
	
	
	@Test
	void testBandit1Win() {
     MultiArm2 multi=new MultiArm2();   
     
     assertEquals(new Reward2(false),multi.choseArm1());
     assertEquals(new Reward2(false),multi.choseArm1());
     assertEquals(new Reward2(true),multi.choseArm1());
	}
	
	
	
	

}
