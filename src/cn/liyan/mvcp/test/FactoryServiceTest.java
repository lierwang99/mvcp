package cn.liyan.mvcp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cn.liyan.mvcp.service.FactoryService;

class FactoryServiceTest {

	@Test
	void testIsBlankSpace() {
		String str = "                                ";
		boolean blankSpace = FactoryService.isBlankSpace(str);
		System.out.println(blankSpace);
	}

}
