import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.exception.InvalidRecitalInfoException;
import org.mjvera.domain.repository.RecitalRepository;
import org.mjvera.domain.valueobject.Address;
import org.mjvera.application.usecase.CreateRecitalUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateRecitalUseCaseTest {

    @Mock
    private RecitalRepository recitalRepository;

    @InjectMocks
    private CreateRecitalUseCase createRecitalUseCase;

    @Test
    void shouldThrowInvalidRecitalInfoWhenRecitalIsNull() {
        assertThrows(InvalidRecitalInfoException.class, () -> createRecitalUseCase.execute(null));
    }

    @Test
    void shouldSaveRecitalOnceIfRecitalIsValid() {
        Venue venue = new Venue("2", "Movistar Arena", new Address("Av. Beauchef 1204", "Santiago", "RM"));
        Recital recital = new Recital("The Metal Fest 2027", venue, List.of("Ratzinger", "Manhattan Sur", "Chances"));

        createRecitalUseCase.execute(recital);

        verify(recitalRepository, times(1)).save(recital);
    }
}
