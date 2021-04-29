package pl.qubiak.photoHosting.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.qubiak.photoHosting.Model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
