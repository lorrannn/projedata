import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;
import java.math.RoundingMode;

public class Employee extends Person {
    private BigDecimal wage;
    private String role;

    public Employee(String name, LocalDate dateOfBirth, BigDecimal wage, String role){
        super(name, dateOfBirth);
        this.wage = wage;
        this.role = role;
    }

    public BigDecimal getWage(){
        return wage;
    }

    public void setWage(BigDecimal newValue){
        this.wage = newValue;
    }

    public String getRole(){
        return role;
    }

    @Override

    public String toString(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        
        String formattedDate = getDateOfBirth().format(formatter);

        BigDecimal roundedWage = wage.setScale(2, RoundingMode.HALF_UP);
        String formattedWage = numberFormatter.format(roundedWage);



        return "Funcionário {nome: " + getName() + 
        ", data de nascimento: " + formattedDate +
        ", salário: " + formattedWage +
        ", função: " + role +
        "}";
    }
}