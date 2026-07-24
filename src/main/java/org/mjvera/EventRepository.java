package org.mjvera;

import org.mjvera.models.EventModel;

public interface EventRepository {
    void save(EventModel event);
}
