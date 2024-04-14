import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class EmployeeDA {
    private HashMap<String, Employee> employeeMap;

    public EmployeeDA(String empNo) {
        try {
            Scanner employeeFile = new Scanner(new FileReader("src/emp.csv"));

            employeeMap = new HashMap<>();

            Integer key = 0; // hlkasjdlsdaaaa

            // loop through employeeFile
            while (employeeFile.hasNext()) {
                String empFileLineData = employeeFile.nextLine();
                String[] empFileSplit = empFileLineData.split(",");

                if (empFileSplit[0].equals(empNo)) {
                    Employee employee = new Employee();

                    employee.setEmpNo(empNo);
                    employee.setLastName(empFileSplit[1].trim());
                    employee.setFirstName(empFileSplit[2].trim());
                    employee.setSalary(DepartmentDA.salary);
                    // employee.setJob(empFileSplit[3]); // for setting the job, not needed in this activity... this might've been causing me a lot of issues with unused columns

                    employeeMap.put(empNo+key, employee);
                    key++;
                }
            }

            employeeFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }

    public HashMap<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

}
