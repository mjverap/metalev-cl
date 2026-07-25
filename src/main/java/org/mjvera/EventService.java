package org.mjvera;

import org.mjvera.exceptions.InvalidEventInfoException;
import org.mjvera.models.EventModel;

import java.util.List;

public class EventService {
    private final EventRepository repository;
    public EventService(EventRepository repository) {
        this.repository = repository;
    }
    public void createEvent(EventModel event) {
        if (isStringInvalid(event.getName()) ||
                isStringInvalid(event.getVenue()) ||
                isListInvalid(event.getBands())) {
            throw new InvalidEventInfoException("Invalid event info.");
        }
        repository.save(event);
    }

    private boolean isStringInvalid(String string) {
        return string == null || string.isEmpty();
    }
    private boolean isListInvalid(List<String> list) {
        return list == null || list.isEmpty();
    }

}
