import org.junit.jupiter.api.Test;
import org.mjvera.domain.exception.InvalidRecitalInfoException;
import org.mjvera.domain.valueobject.Address;
import org.mjvera.domain.valueobject.BandList;
import org.mjvera.domain.valueobject.DateRange;
import org.mjvera.domain.valueobject.PriceRange;
import org.mjvera.domain.valueobject.RecitalName;
import org.mjvera.domain.valueobject.VenueName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValueObjectTest {

    @Test
    void shouldCompareVenueNameByValue() {
        VenueName venueName = new VenueName("Estadio Nacional");
        VenueName sameVenueName = new VenueName("Estadio Nacional");

        assertEquals("Estadio Nacional", venueName.value());
        assertEquals(venueName, sameVenueName);
        assertEquals(venueName.hashCode(), sameVenueName.hashCode());
    }

    @Test
    void shouldThrowIllegalArgumentWhenVenueNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new VenueName(" "));
    }

    @Test
    void shouldCompareRecitalNameByValue() {
        RecitalName recitalName = new RecitalName("Festival");
        RecitalName sameRecitalName = new RecitalName("Festival");

        assertEquals("Festival", recitalName.value());
        assertEquals(recitalName, sameRecitalName);
        assertEquals(recitalName.hashCode(), sameRecitalName.hashCode());
    }

    @Test
    void shouldThrowInvalidRecitalInfoWhenRecitalNameIsBlank() {
        assertThrows(InvalidRecitalInfoException.class, () -> new RecitalName(""));
    }

    @Test
    void shouldCompareAddressByValue() {
        Address address = new Address("Av. Grecia 2001", "Ñuñoa", "RM");
        Address sameAddress = new Address("Av. Grecia 2001", "Ñuñoa", "RM");

        assertEquals("Av. Grecia 2001", address.street());
        assertEquals("Ñuñoa", address.city());
        assertEquals("RM", address.state());
        assertEquals("Av. Grecia 2001, Ñuñoa, RM", address.toString());
        assertEquals(address, sameAddress);
        assertEquals(address.hashCode(), sameAddress.hashCode());
    }

    @Test
    void shouldThrowIllegalArgumentWhenStreetIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Address(" ", "Ñuñoa", "RM"));
    }

    @Test
    void shouldThrowIllegalArgumentWhenCityIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Av. Grecia 2001", null, "RM"));
        assertThrows(IllegalArgumentException.class, () -> new Address("Av. Grecia 2001", " ", "RM"));
    }

    @Test
    void shouldThrowIllegalArgumentWhenStateIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Address("Av. Grecia 2001", "Ñuñoa", null));
        assertThrows(IllegalArgumentException.class, () -> new Address("Av. Grecia 2001", "Ñuñoa", " "));
    }

    @Test
    void shouldComparePriceRangeByValue() {
        PriceRange priceRange = new PriceRange(10000, 30000);
        PriceRange samePriceRange = new PriceRange(10000, 30000);

        assertEquals(10000, priceRange.minPrice());
        assertEquals(30000, priceRange.maxPrice());
        assertEquals(priceRange, samePriceRange);
        assertEquals(priceRange.hashCode(), samePriceRange.hashCode());
    }

    @Test
    void shouldThrowIllegalArgumentWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new PriceRange(-1, 30000));
    }

    @Test
    void shouldThrowIllegalArgumentWhenMinPriceIsGreaterThanMaxPrice() {
        assertThrows(IllegalArgumentException.class, () -> new PriceRange(40000, 30000));
    }

    @Test
    void shouldCompareDateRangeByValue() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        DateRange dateRange = new DateRange(startDate, endDate);
        DateRange sameDateRange = new DateRange(startDate, endDate);

        assertEquals(startDate, dateRange.startDate());
        assertEquals(endDate, dateRange.endDate());
        assertEquals(dateRange, sameDateRange);
        assertEquals(dateRange.hashCode(), sameDateRange.hashCode());
    }

    @Test
    void shouldThrowIllegalArgumentWhenDateRangeHasNullValues() {
        assertThrows(IllegalArgumentException.class, () -> new DateRange(null, LocalDate.now()));
    }

    @Test
    void shouldThrowIllegalArgumentWhenStartDateIsAfterEndDate() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> new DateRange(startDate, endDate));
    }

    @Test
    void shouldCompareBandListByValueAndProtectItsContents() {
        List<String> bands = new ArrayList<>(List.of("Band A", "Band B"));

        BandList bandList = new BandList(bands);
        BandList sameBandList = new BandList(List.of("Band A", "Band B"));

        assertEquals(List.of("Band A", "Band B"), bandList.value());
        assertEquals(bandList, sameBandList);
        assertEquals(bandList.hashCode(), sameBandList.hashCode());
    }

    @Test
    void shouldCreateDefensiveCopyOfBandList() {
        List<String> bands = new ArrayList<>(List.of("Band A"));

        BandList bandList = new BandList(bands);
        bands.add("Band B");

        assertEquals(List.of("Band A"), bandList.value());
        assertThrows(UnsupportedOperationException.class, () -> bandList.value().add("Band C"));
    }

    @Test
    void shouldThrowIllegalArgumentWhenBandListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new BandList(List.of()));
    }

    @Test
    void shouldThrowIllegalArgumentWhenBandListContainsBlankNames() {
        assertThrows(IllegalArgumentException.class, () -> new BandList(List.of("Band A", " ")));
    }

    @Test
    void shouldDistinguishDifferentRecordValues() {
        assertNotEquals(new VenueName("Estadio Nacional"), new VenueName("Movistar Arena"));
        assertNotEquals(new RecitalName("Festival A"), new RecitalName("Festival B"));
        assertNotEquals(new Address("A", "B", "C"), new Address("X", "Y", "Z"));
        assertNotEquals(new PriceRange(1000, 2000), new PriceRange(1000, 3000));
        assertNotEquals(new DateRange(LocalDate.now(), LocalDate.now().plusDays(1)), new DateRange(LocalDate.now().plusDays(2), LocalDate.now().plusDays(3)));
        assertNotEquals(new BandList(List.of("Band A")), new BandList(List.of("Band B")));
    }
}
