import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String name;
    private LocalDate dateOfBirth;

    public Person (String name, LocalDate dateOfBirth){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName(){
        return name;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
}