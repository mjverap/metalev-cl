import org.junit.jupiter.api.Test;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.valueobject.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VenueEntityTest {

    @Test
    void shouldCreateVenueWithProvidedData() {
        Address address = new Address("Av. Grecia 2001", "Ñuñoa", "RM");

        Venue venue = new Venue("1", "Estadio Nacional", address);

        assertEquals("1", venue.getId());
        assertEquals("Estadio Nacional", venue.getName());
        assertEquals(address, venue.getAddress());
    }

    @Test
    void shouldThrowIllegalArgumentWhenVenueNameIsBlank() {
        Address address = new Address("Av. Grecia 2001", "Ñuñoa", "RM");

        assertThrows(IllegalArgumentException.class, () -> new Venue("1", " ", address));
    }

    @Test
    void shouldRenameVenueWhenNewNameIsValid() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));

        venue.renameTo("Movistar Arena");

        assertEquals("Movistar Arena", venue.getName());
    }

    @Test
    void shouldThrowIllegalArgumentWhenRenamingWithBlankName() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));

        assertThrows(IllegalArgumentException.class, () -> venue.renameTo(""));
    }

    @Test
    void shouldUpdateAddressWhenNewAddressIsProvided() {
        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));
        Address newAddress = new Address("Av. Beauchef 1204", "Santiago", "RM");

        venue.updateAddress(newAddress);

        assertEquals(newAddress, venue.getAddress());
    }
}
