import org.junit.jupiter.api.Test;
import org.mjvera.exceptions.InvalidDateRangeException;
import org.mjvera.models.EventModel;
import org.mjvera.exceptions.InvalidPriceRangeException;

import java.time.LocalDate;

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
    void shouldThrowInvalidDateRangeIfStartDateIsAfterEndDate() {
        //Arrange
        EventModel event = new EventModel();
        LocalDate endDate =  LocalDate.now();
        LocalDate startDate = endDate.plusDays(1);

        //Assert
        assertThrows(InvalidDateRangeException.class, () -> {
            //Act
            event.setDateRange(startDate, endDate);
        });
    }
}
