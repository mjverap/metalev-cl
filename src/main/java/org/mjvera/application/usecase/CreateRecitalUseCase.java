package org.mjvera.application.usecase;

import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.exception.InvalidRecitalInfoException;
import org.mjvera.domain.repository.RecitalRepository;

public class CreateRecitalUseCase {
    private final RecitalRepository recitalRepository;

    public CreateRecitalUseCase(RecitalRepository recitalRepository) {
        this.recitalRepository = recitalRepository;
    }

    public void execute(Recital recital) {
        if (recital == null) {
            throw new InvalidRecitalInfoException("Invalid recital info.");
        }
        recitalRepository.save(recital);
    }
}
