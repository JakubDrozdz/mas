package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
