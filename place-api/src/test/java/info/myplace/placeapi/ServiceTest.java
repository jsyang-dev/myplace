package info.myplace.placeapi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = PlaceApiApplication.class)
@ActiveProfiles("test")
@Transactional
public abstract class ServiceTest {
}
