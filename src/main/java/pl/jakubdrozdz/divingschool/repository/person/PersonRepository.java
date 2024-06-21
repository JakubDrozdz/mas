package pl.jakubdrozdz.divingschool.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jakubdrozdz.divingschool.model.person.Person;

/**
 * Interface used as DAO for Person class
 *
 * @author Jakub Drozdz
 */
@SuppressWarnings("unused")//for the purpose of durability management
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
