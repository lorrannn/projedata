import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.time.Period;
import java.math.RoundingMode;

public class MyProgram {
    public static void main(String[] args) {

        ArrayList<Employee> allEmployees = new ArrayList<>();

        allEmployees.add(new Employee("Maria", LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"));
        allEmployees.add(new Employee("João", LocalDate.of(1990,05,12), new BigDecimal("2284.38"), "Operador"));
        allEmployees.add(new Employee("Caio", LocalDate.of(1961,05,02), new BigDecimal("9836.14"), "Coordenador"));
        allEmployees.add(new Employee("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"));
        allEmployees.add(new Employee("Alice", LocalDate.of(1995,01,05), new BigDecimal("2234.68"), "Recepcionista"));
        allEmployees.add(new Employee("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
        allEmployees.add(new Employee("Arthur", LocalDate.of(1993,03,31), new BigDecimal("4071.84"), "Contador"));
        allEmployees.add(new Employee("Laura", LocalDate.of(1994,07,8), new BigDecimal("3017.45"), "Gerente"));
        allEmployees.add(new Employee("Heloísa", LocalDate.of(2003,05,24), new BigDecimal("1606.85"), "Eletricista"));
        allEmployees.add(new Employee("Helena", LocalDate.of(1996,9,02), new BigDecimal("2799.93"), "Gerente"));


        // remover o funcionário "João"
        allEmployees.removeIf(employee -> employee.getName().equals("João"));

        // imprimir todos os funcionários
        System.out.println("Lista de Funcionários, exceto João: \n");
        for (Employee employee : allEmployees){
            System.out.println(employee);
        }

        // aumentar o salário de cada funcionário em 10%
        System.out.println("\nLista de Funcionários com acréscimo de salário: \n");
        for (Employee employee : allEmployees){
            BigDecimal newWage = employee.getWage().multiply(new BigDecimal("1.1"));
            employee.setWage(newWage);

            System.out.println(employee);
        }

        // Agrupar funcionários por função  

        Map<String, List<Employee>> employeesByRole = allEmployees.stream().collect(Collectors.groupingBy(Employee::getRole));

        employeesByRole.forEach((role, employees)->{
            System.out.println("\nFuncionários da função " + role + ":");
            employees.forEach(System.out::println);
            System.out.println();

        });

        // Aniversariantes dos meses 10 e 12

        List<Employee> birthdayEmployees = allEmployees.stream().filter(employee -> {
            int birthdayMonth = employee.getDateOfBirth().getMonthValue();

            return birthdayMonth == 10 || birthdayMonth == 12;
        })
        .collect(Collectors.toList());
        
        System.out.println("\nFuncionários aniversariantes dos meses 10 e 12:\n");
        birthdayEmployees.forEach(System.out::println);

    // Funcionário com a maior idade

    Employee oldestEmployee = allEmployees.stream().min(Comparator.comparing(Employee::getDateOfBirth)).orElse(null);
    int ageOldestEmployee = Period.between(oldestEmployee.getDateOfBirth(), LocalDate.now()).getYears();

    if(oldestEmployee != null){
        System.out.println("\nFuncionário mais velho:\n");
        System.out.println("Nome: " + oldestEmployee.getName() + ", idade: " + ageOldestEmployee);
    }

    // Funcionários em ordem alfabética

    List<Employee> orderedEmployees = allEmployees.stream().sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());

    System.out.println("\nFuncionários em ordem alfabética:\n");
    orderedEmployees.forEach(System.out::println);

    // Total dos salários
    BigDecimal totalWages = allEmployees.stream().map(Employee::getWage).reduce(BigDecimal.ZERO, BigDecimal::add);
    BigDecimal roundedTotalWage = totalWages.setScale(2, RoundingMode.HALF_UP);

    System.out.println("\nTotal dos salários: " + roundedTotalWage);

    // Salários Mínimos por Funcionário

    BigDecimal minWage = new BigDecimal("1212.00");
    System.out.println("\nLista de salários mínimos por funcionário: \n");

    allEmployees.forEach(employee -> {
        BigDecimal minWageEmployee = employee.getWage().divide(minWage, 2, RoundingMode.HALF_UP);

        System.out.println("nome: " + employee.getName() + ", salários mínimos: " + minWageEmployee);
    });

    }; 
}
