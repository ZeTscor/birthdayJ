package bot.birthdayJ.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    List<Birthday> findAllByDate(LocalDate date);
}
