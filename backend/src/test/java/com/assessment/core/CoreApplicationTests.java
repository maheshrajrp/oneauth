package com.assessment.core;

import com.assessment.core.testutils.BaseTestContainerTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoreApplicationTests extends BaseTestContainerTest {

    @Test
	void applicationStarts() {
	}

}
