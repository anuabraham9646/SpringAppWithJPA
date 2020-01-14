import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
class AppTest {

	@Test
	void testMain() {
		App.main(new String[] {});
	}

}
