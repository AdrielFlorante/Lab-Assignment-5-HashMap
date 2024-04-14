/*
 * Today I learned a valuable lesson of always using trim() when handling .csv files
 * All my previous failures was due to deptemp.csv column 2 for empNo's containing spaces,
 * And when they were compared to emp.csv in EmployeeDA, their empNo had no spaces
 * Therefore it couldn't find the correct employee
 * My logic was correct, my data handling was terrible.
 *
 * - Adriel Cedrick B. Florante, 2BSCS-1
 */

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepartmentDA {
    private HashMap<String, Employee> employeeMap;
    public static double salary = 0;

    public DepartmentDA() {

        try {
            Scanner departmentFile = new Scanner(new FileReader("src/dep.csv"));
            String depCode;

            while (departmentFile.hasNext()) {
                employeeMap = new HashMap<>();
                double totalSalary = 0;

                String depFileLineData = departmentFile.nextLine();
                String[] depFileSplit = depFileLineData.split(",");

                depCode = depFileSplit[0].trim();

                Department department = new Department();
                department.setDepCode(depCode);
                department.setDepName(depFileSplit[1].trim());

                // departments.put(depCode, department);

                readDepEmp(department);

                // departments.put(depCode, department);

                for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
                    totalSalary += entryMap.getValue().getSalary();
                }

                department.setDepTotalSalary(totalSalary);

                // other loop for depEmpFile
                Scanner depEmpFile = new Scanner(new FileReader("src/deptemp.csv"));

                while (depEmpFile.hasNext()) {

                    String depEmpFileLineData = depEmpFile.nextLine();
                    String[] depEmpFileSplit = depEmpFileLineData.split(",");

                    depCode = depEmpFileSplit[0].trim();
                    String empNo = depEmpFileSplit[1].trim();
                    salary = Double.parseDouble(depEmpFileSplit[2].trim());


                    if (depCode.equals(department.getDepCode())) {
                        // Initialize EmployeeDA here with empNo
                        EmployeeDA employeeDA = new EmployeeDA(empNo);

                        HashMap<String, Employee> empMap = employeeDA.getEmployeeMap();

                        // hopefully this fixes it
                        department.getEmployeeMap().putAll(empMap);

                        department.setDepTotalSalary(department.getDepTotalSalary() + salary);
                    }

                }

                depEmpFile.close();

                printDepartment(department);
            }



            departmentFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }

    public void readDepEmp(Department department) {
        // EmployeeDA employeeDA = new EmployeeDA(department.getDepCode());
        EmployeeDA employeeDA = new EmployeeDA(department.getDepCode());
        department.setEmployeeMap(employeeDA.getEmployeeMap());

    }

    /* here lies myDysfunction, the method that helped me debug my program o7
    public void myDysfunction(department) {

    }
     */

    public void printDepartment(Department department) {
        System.out.println("Department code: " + department.getDepCode());
        System.out.println("Department name: " + department.getDepName());
        System.out.println("Department total salary: " + department.getDepTotalSalary());

        System.out.println("---------------------Details-------------------------");
        System.out.println("EmpNo\t\tEmployee Name\t\t\t\tSalary");

        for (Map.Entry<String, Employee> entryMap : department.getEmployeeMap().entrySet()) {
            System.out.print(entryMap.getValue().getEmpNo() + "\t\t\t");
            System.out.print(entryMap.getValue().getLastName() + "\t\t\t");
            System.out.print(entryMap.getValue().getFirstName() + "\t\t");
            System.out.println("\t" + entryMap.getValue().getSalary());
        }

        System.out.println("\n");

    }

}
