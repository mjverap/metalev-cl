import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mjvera.EventRepository;
import org.mjvera.EventService;
import org.mjvera.exceptions.InvalidEventInfoException;
import org.mjvera.models.EventModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;

    //Arrange for shouldThrowInvalidEventExceptionWhenEventDataIsInvalid
    static Stream<Arguments> invalidEventData() {
        String eventName = "LATAM Tour 2027";
        String venue = "Estadio Nacional";
        List<String> bands = List.of("Metallica");
        return Stream.of(
                Arguments.of("", venue, bands),
                Arguments.of(eventName, "", bands),
                Arguments.of(eventName, venue, List.of()),
                Arguments.of(null, venue, bands),
                Arguments.of(eventName, null, bands),
                Arguments.of(eventName, venue, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidEventData")
    void shouldThrowInvalidEventExceptionWhenEventDataIsInvalid(
            String name, String venue, List<String> bands
    ) {
        //Act & Assert
        EventModel event = new EventModel(name, venue, bands);
        assertThrows(InvalidEventInfoException.class, () -> eventService.createEvent(event));
    }

    @Test
    void shouldSaveEventOnceIfEventIsValid() {
        //Arrange
        EventModel event = new EventModel("The Metal Fest 2027", "Movistar Arena"
                , List.of("Ratzinger", "Manhattan Sur", "Chances"));

        //Act
        eventService.createEvent(event);
        //Assert
        verify(eventRepository, times(1)).save(event);
    }
}
