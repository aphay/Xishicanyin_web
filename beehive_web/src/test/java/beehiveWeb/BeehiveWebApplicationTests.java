package beehiveWeb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import beehive_web.BeehiveWebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BeehiveWebApplication.class)
@WebAppConfiguration
public class BeehiveWebApplicationTests {

	@Test
	public void contextLoads() {
	}

}
