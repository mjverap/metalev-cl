package org.mjvera.bootstrap;

import org.mjvera.application.usecase.CreateRecitalUseCase;
import org.mjvera.domain.entities.Recital;
import org.mjvera.domain.entities.Venue;
import org.mjvera.domain.valueobject.Address;
import org.mjvera.infrastructure.repository.InMemoryRecitalRepository;

import java.util.List;

public class RecitalBootstrap {
    public static void main(String[] args) {
        bootstrap();
    }

    public static void bootstrap() {
        InMemoryRecitalRepository repository = new InMemoryRecitalRepository();
        CreateRecitalUseCase createRecitalUseCase = new CreateRecitalUseCase(repository);

        Venue venue = new Venue("1", "Estadio Nacional", new Address("Av. Grecia 2001", "Ñuñoa", "RM"));
        Recital recital = new Recital("Festival del Metal", venue, List.of("Ratzinger", "Chances"));

        createRecitalUseCase.execute(recital);
    }
}
