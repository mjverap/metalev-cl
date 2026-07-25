import org.junit.jupiter.api.Test;
import org.mjvera.exceptions.InvalidDateRangeException;
import org.mjvera.models.EventModel;
import org.mjvera.exceptions.InvalidPriceRangeException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventModelTest {

    @Test
    void shouldThrowInvalidPriceRangeIfMinPriceIsGreaterThanMaxPrice() {
        //Arrange
        EventModel event = new EventModel();
        int minPrice = 80000;
        int maxPrice = 50000;

        //Assert
        assertThrows(InvalidPriceRangeException.class, () -> {
            //Act
            event.setPriceRange(minPrice, maxPrice);
        });
    }

    @Test
    void shouldSetPriceRangeSuccessfullyIfPriceRangeIsValid() {
        //Arrange
        EventModel event = new EventModel();
        int minPrice = 30000;
        int maxPrice = 50000;

        assertDoesNotThrow(() -> event.setPriceRange(minPrice, maxPrice));

        assertEquals(minPrice, event.getMinTicketPrice());
        assertEquals(maxPrice, event.getMaxTicketPrice());
    }

    @Test
    void shouldThrowInvalidDateRangeIfStartDateIsAfterEndDate() {
        //Arrange
        EventModel event = new EventModel();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.plusDays(1);

        //Assert
        assertThrows(InvalidDateRangeException.class, () -> {
            //Act
            event.setDateRange(startDate, endDate);
        });
    }

    @Test
    void shouldSetDateRangeSuccessfullyIfDateRangeIsValid() {
        //Arrange
        EventModel event = new EventModel();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        assertDoesNotThrow(() -> event.setDateRange(startDate, endDate));

        assertEquals(startDate, event.getStartDate());
        assertEquals(endDate, event.getEndDate());
    }
}
