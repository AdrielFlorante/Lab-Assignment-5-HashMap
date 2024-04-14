import java.util.HashMap;
import java.util.Map;

public class EmployeeDATest {
    public static void main(String[] args) {
        // Create an instance of EmployeeDA with a specific employee number
        String empNo = "123"; // Replace with a valid employee number from your data
        EmployeeDA employeeDA = new EmployeeDA(empNo);

        // Get the employee map and print its contents
        HashMap<String, Employee> employeeMap = employeeDA.getEmployeeMap();
        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            Employee employee = entry.getValue();
            System.out.println("Employee Number: " + employee.getEmpNo());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("--------------------------");
        }
    }
}