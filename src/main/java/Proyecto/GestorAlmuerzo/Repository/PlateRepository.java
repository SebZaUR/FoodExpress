package proyecto.gestoralmuerzo.repository;

import proyecto.gestoralmuerzo.model.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface que nos permite conectar con la base de datos.
 *
 * @author Sebastian Zamora
 * @author Johann Amaya
 * @author Cesar Amaya
 * @author Christian Duarte
 * @version 14/11/2023
 */
@Repository
public interface PlateRepository extends JpaRepository<Plate, Long> {

    List<Plate> findByCategoriesId(Long categoryId);
}
