import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainATMIntegrationTest {

    @Test
    void testRunWithoutCrash() {
        assertDoesNotThrow(() -> MainATM.main(new String[]{}));
    }
}