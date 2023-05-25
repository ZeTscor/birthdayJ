package bot.birthdayJ.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "birthdays")
public class Birthday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}


