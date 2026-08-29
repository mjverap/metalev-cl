import org.junit.jupiter.api.Test;
import org.mjvera.bootstrap.RecitalBootstrap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RecitalBootstrapTest {

    @Test
    void shouldRunBootstrapWithoutThrowing() {
        assertDoesNotThrow(RecitalBootstrap::bootstrap);
    }

    @Test
    void shouldRunMainWithoutThrowing() {
        assertDoesNotThrow(() -> RecitalBootstrap.main(new String[0]));
    }
}
