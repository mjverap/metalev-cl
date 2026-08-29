package org.mjvera.infrastructure.repository;

import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.repository.RecitalRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRecitalRepository implements RecitalRepository {
    private final List<Recital> recitals = new ArrayList<>();

    @Override
    public void save(Recital recital) {
        recitals.add(recital);
    }
}
