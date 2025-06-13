package br.com.osterloh;

import br.com.osterloh.model.Person;
import br.com.osterloh.service.IPersonService;
import br.com.osterloh.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    Person person;
    List<Person> persons;
    IPersonService service;

    @BeforeEach
    void setup(){
        service = new PersonService();
        person = new Person("John", "Doe", "john.doe@email.com", "Santa Catarina - SC", "M");

        persons = new ArrayList<>();
        persons.add(new Person("John0", "Doe", "john0.doe@email.com", "Santa Catarina - SC", "M"));
        persons.add(new Person("John1", "Doe", "john1.doe@email.com", "Santa Catarina - SC", "M"));
        persons.add(new Person("John2", "Doe", "john2.doe@email.com", "Santa Catarina - SC", "M"));
    }

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, () -> "The createPerson() should not have returned null!!!");
    }

//    @DisplayName("When Create a Person with Success Should Contains FirstName in Person Object")
    @DisplayName("When Create a Person with Success Should Contains Valid Fields in Person Object")
    @Test
    void testCreatePerson_WhenSuccess_ShouldContainsFirstNameInReturnedPersonObject() {
        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(person.getId(), () -> "Person ID is Missing");
        assertEquals(person.getFirstName(), actual.getFirstName(), () -> "The firstName is Different!!!");
        assertEquals(person.getLastName(), actual.getLastName(), () -> "The lastName is Different!!!");
        assertEquals(person.getEmail(), actual.getEmail(), () -> "The E-Mail is Different!!!");
        assertEquals(person.getAddress(), actual.getAddress(), () -> "The Address is Different!!!");
        assertEquals(person.getGender(), actual.getGender(), () -> "The Gender is Different!!!");
    }

    @DisplayName("When Create a Person with null E-Mail Should throw exception")
    @Test
    void testCreatePerson_WhithNullEmail_ShouldThrowIllegalArgumentException() {
        // Given / Arrange
        person.setEmail(null);

        var expectedMessage = "The Person E-Mail is null or empty!";

        // When / Act

        // Then / Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.createPerson(person), () -> "Empty E-Mail should have cause an IllegalArgumentException");

        assertEquals(expectedMessage, exception.getMessage(), () -> "Exception error message is incorrect!");
    }

    @DisplayName("When find all Person with list null Should throw exception")
    @Test
    void testeBuscaTodasAsPessoas_QuandoSucesso_DeveRetornarListaDePessoas() {
        // Given / Arrange
        service.setPersons(persons);

        persons = service.findAllPersons();

        // When / Act

        // Then / Assert
        assertNotNull(persons, () -> "Lista está vazia!");
    }

    @DisplayName("When find all Person with list is null Should throw exception")
    @Test
    void testeBuscaTodasAsPessoas_QuandoListaNull_DeveRetornarRuntimeExceptionEVerificarMensagem() {
        // Given / Arrange
        service.setPersons(new ArrayList<>());

        // When / Act
        String actual = "Não possui pessoas cadastradas!";

        // Then / Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findAllPersons(), () -> "A lista está vazia");
        assertEquals(actual, exception.getMessage(), () -> "Exception error message is incorrect!");
    }

    @DisplayName("When find all Person with list is null Should success")
    @Test
    void testeBuscaTodasPessoas_QuandoListaNull_DeveRetornarSucesso() {
        // Given / Arrange

        // When / Act

        // Then / Assert
        assertThrows(RuntimeException.class, () -> service.findAllPersons(), () -> "A lista não está vazia!");
    }

    @Test
    void testeBuscaPessoaPorID_QuandoSucesso_DeveRetornarUmObjetoPessoa(){
        service.setPersons(persons);
        Person person1 = service.findPersonById(1L);
        assertNotNull(person1, () -> "Pessoa não encontrada");
    }

    @Test
    void testeBuscaPessoaPorIDNaoValido_QuandoNaoEncontrado_DeveRuturnarIllegalArgumentException(){
        service.setPersons(persons);
        assertThrows(IllegalArgumentException.class, () -> service.findPersonById(5L), () -> "Pessoa encontrada!");

    }

    @Test
    void testeBuscaPessoaPorIDNaoValido_QuandoNaoEncontrado_DeveRuturnarIllegalArgumentExceptionVerificaMensagem(){
        service.setPersons(persons);

        String actual = "Não possui pessoas cadastradas ou não encontrou com este ID!";

        // Then / Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.findPersonById(5L), () -> "Pessoa encontrada");
        assertEquals(actual, exception.getMessage(), () -> "Não possui pessoas cadastradas ou não encontrou com este ID!");
    }

    @Test
    void testeExcluiPessoa_QuandoSucesso_DeveRemoverObjetoPessoaDaLista(){
        Long id = 1L;
        service.setPersons(persons);
        service.removePersonById(id);
        Person person1 = service.findPersonById(id).getFirstName().equals("John1") ? service.findPersonById(id) : null;
        assertNull(person1, () -> "Is non null");
    }

}
