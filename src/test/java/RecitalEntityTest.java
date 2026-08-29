import org.junit.jupiter.api.Test;
import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.exception.InvalidDateRangeException;
import org.mjvera.domain.exception.InvalidPriceRangeException;
import org.mjvera.domain.exception.InvalidRecitalInfoException;
import org.mjvera.domain.valueobject.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecitalEntityTest {

    @Test
    void shouldCreateEmptyRecitalWithDefaultState() {
        Recital recital = new Recital();

        assertEquals("", recital.getName());
        assertNull(recital.getVenue());
        assertEquals(List.of(), recital.getBands());
        assertEquals(0, recital.getMinTicketPrice());
        assertEquals(0, recital.getMaxTicketPrice());
        assertNull(recital.getStartDate());
        assertNull(recital.getEndDate());
    }

    @Test
    void shouldCreateValidRecitalWithProvidedData() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));
        List<String> bands = List.of("Band A", "Band B");

        Recital recital = new Recital("Festival", venue, bands);

        assertNotNull(recital.getId());
        assertEquals("Festival", recital.getName());
        assertEquals(venue, recital.getVenue());
        assertEquals(bands, recital.getBands());
    }

    @Test
    void shouldReturnDefensiveCopyOfBands() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), new ArrayList<>(List.of("Band A")));

        List<String> bands = recital.getBands();
        bands.add("Band B");

        assertEquals(List.of("Band A"), recital.getBands());
    }

    @Test
    void shouldRenameRecitalWhenNameIsValid() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));

        recital.renameTo("New Festival");

        assertEquals("New Festival", recital.getName());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenRenamingWithBlankName() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));

        assertThrows(InvalidRecitalInfoException.class, () -> recital.renameTo(""));
    }

    @Test
    void shouldMoveRecitalToNewVenueWhenVenueIsValid() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));
        Venue newVenue = new Venue("2", "Movistar Arena", new Address("Av. Beauchef 1204", "Santiago", "RM"));

        recital.moveTo(newVenue);

        assertEquals(newVenue, recital.getVenue());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenMovingToNullVenue() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));

        assertThrows(InvalidRecitalInfoException.class, () -> recital.moveTo(null));
    }

    @Test
    void shouldAddBandWhenBandNameIsValid() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band A"));

        recital.addBand("Band B");

        assertEquals(List.of("Band A", "Band B"), recital.getBands());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenAddingBlankBand() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band A"));

        assertThrows(InvalidRecitalInfoException.class, () -> recital.addBand(" "));
    }

    @Test
    void shouldRemoveBandWhenBandExists() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band A", "Band B"));

        recital.removeBand("Band A");

        assertEquals(List.of("Band B"), recital.getBands());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenRemovingBlankBand() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band A"));

        assertThrows(InvalidRecitalInfoException.class, () -> recital.removeBand(""));
    }

    @Test
    void shouldThrowInvalidPriceRangeIfMinPriceIsGreaterThanMaxPrice() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));
        int minPrice = 80000;
        int maxPrice = 50000;

        assertThrows(InvalidPriceRangeException.class, () -> recital.updateTicketPriceRange(minPrice, maxPrice));
    }

    @Test
    void shouldSetPriceRangeSuccessfullyIfPriceRangeIsValid() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));
        int minPrice = 30000;
        int maxPrice = 50000;

        assertDoesNotThrow(() -> recital.updateTicketPriceRange(minPrice, maxPrice));
        assertNotNull(recital.getTicketPriceRange());
        assertEquals(minPrice, recital.getMinTicketPrice());
        assertEquals(maxPrice, recital.getMaxTicketPrice());
    }

    @Test
    void shouldThrowInvalidDateRangeIfStartDateIsAfterEndDate() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.plusDays(1);

        assertThrows(InvalidDateRangeException.class, () -> recital.reprogramTo(startDate, endDate));
    }

    @Test
    void shouldSetDateRangeSuccessfullyIfDateRangeIsValid() {
        Recital recital = new Recital("Festival", new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM")), List.of("Band"));
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        assertDoesNotThrow(() -> recital.reprogramTo(startDate, endDate));
        assertNotNull(recital.getDateRange());
        assertEquals(startDate, recital.getStartDate());
        assertEquals(endDate, recital.getEndDate());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenVenueIsNull() {
        assertThrows(InvalidRecitalInfoException.class,
                () -> new Recital("Festival", null, List.of("Band")));
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenNameIsBlank() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));
        assertThrows(InvalidRecitalInfoException.class,
                () -> new Recital("", venue, List.of("Band")));
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenBandsAreEmpty() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));
        assertThrows(InvalidRecitalInfoException.class,
                () -> new Recital("Festival", venue, List.of()));
    }
}
