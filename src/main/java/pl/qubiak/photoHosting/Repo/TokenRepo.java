package pl.qubiak.photoHosting.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.qubiak.photoHosting.Model.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByValue(String value);
}
