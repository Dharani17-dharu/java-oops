mport java.util.Scanner;

class Employee {
    protected String name;
    protected String employeeId;
    protected double basicSalary;

    public Employee(String name, String employeeId, double basicSalary) throws InvalidSalaryException {
        if (basicSalary < 0) {
            throw new InvalidSalaryException("Basic salary cannot be negative");
        }
        this.name = name;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
    }

    public double calculateSalary() {
        return basicSalary;
    }

    public void displaySalarySlip() {
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Basic Salary: " + basicSalary);
    }
}

class Manager extends Employee {
    private double allowance;

    public Manager(String name, String employeeId, double basicSalary, double allowance) throws InvalidSalaryException {
        super(name, employeeId, basicSalary);
        if (allowance < 0) {
            throw new InvalidSalaryException("Allowance cannot be negative");
        }
        this.allowance = allowance;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + allowance;
    }

    @Override
    public void displaySalarySlip() {
        super.displaySalarySlip();
        System.out.println("Allowance: " + allowance);
        System.out.println("Total Salary: " + calculateSalary());
    }
}

class Developer extends Employee {
    private double bonus;

    public Developer(String name, String employeeId, double basicSalary, double bonus) throws InvalidSalaryException {
        super(name, employeeId, basicSalary);
        if (bonus < 0) {
            throw new InvalidSalaryException("Bonus cannot be negative");
        }
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + bonus;
    }

    @Override
    public void displaySalarySlip() {
        super.displaySalarySlip();
        System.out.println("Bonus: " + bonus);
        System.out.println("Total Salary: " + calculateSalary());
    }
}

class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input for Manager
            System.out.println("Enter Manager details:");
            System.out.print("Name: ");
            String mName = scanner.nextLine();
            System.out.print("Employee ID: ");
            String mId = scanner.nextLine();
            System.out.print("Basic Salary: ");
            double mBasicSalary = scanner.nextDouble();
            System.out.print("Allowance: ");
            double allowance = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            Manager manager = new Manager(mName, mId, mBasicSalary, allowance);

            // Input for Developer
            System.out.println("\nEnter Developer details:");
            System.out.print("Name: ");
            String dName = scanner.nextLine();
            System.out.print("Employee ID: ");
            String dId = scanner.nextLine();
            System.out.print("Basic Salary: ");
            double dBasicSalary = scanner.nextDouble();
            System.out.print("Bonus: ");
            double bonus = scanner.nextDouble();

            Developer developer = new Developer(dName, dId, dBasicSalary, bonus);

            // Display Salary Slips
            System.out.println("\nManager Salary Slip:");
            manager.displaySalarySlip();

            System.out.println("\nDeveloper Salary Slip:");
            developer.displaySalarySlip();

        } catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter correct data types.");
        } finally {
            scanner.close();
        }
    }
}

