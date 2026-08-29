import org.junit.jupiter.api.Test;
import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.valueobject.Address;
import org.mjvera.infrastructure.repository.InMemoryRecitalRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InMemoryRecitalRepositoryTest {

    @Test
    void shouldSaveValidRecitalWithoutThrowing() {
        InMemoryRecitalRepository repository = new InMemoryRecitalRepository();
        Recital recital = new Recital(
                "The Metal Fest 2027",
                new Venue("2", "Movistar Arena", new Address("Av. Beauchef 1204", "Santiago", "RM")),
                List.of("Ratzinger", "Manhattan Sur", "Chances")
        );

        assertDoesNotThrow(() -> repository.save(recital));
    }

    @Test
    void shouldAllowSavingTheSameRecitalMoreThanOnce() {
        InMemoryRecitalRepository repository = new InMemoryRecitalRepository();
        Recital recital = new Recital(
                "The Metal Fest 2027",
                new Venue("2", "Movistar Arena", new Address("Av. Beauchef 1204", "Santiago", "RM")),
                List.of("Ratzinger", "Manhattan Sur", "Chances")
        );

        assertDoesNotThrow(() -> {
            repository.save(recital);
            repository.save(recital);
        });
    }
}
