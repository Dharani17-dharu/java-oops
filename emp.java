import java.util.Scanner;
class Employee {
    String empName, empId, address, mailId, mobileNo;
    double basicPay, grossSalary, netSalary;
    void getDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee Name: ");
        empName = sc.nextLine();
        System.out.print("Enter Employee ID: ");
        empId = sc.nextLine();
        System.out.print("Enter Address: ");
        address = sc.nextLine();
        System.out.print("Enter Mail ID: ");
        mailId = sc.nextLine();
        System.out.print("Enter Mobile Number: ");
        mobileNo = sc.nextLine();
        System.out.print("Enter Basic Pay: ");
        basicPay = sc.nextDouble();
    }
    void displayPaySlip(String designation, double DA_percent, double HRA_percent, double PF_percent, double SCF_percent) {
        double DA = (DA_percent / 100) * basicPay;
        double HRA = (HRA_percent / 100) * basicPay;
        double PF = (PF_percent / 100) * basicPay;
        double SCF = (SCF_percent / 100) * basicPay;

        grossSalary = basicPay + DA + HRA;
        netSalary = grossSalary - (PF + SCF);

        System.out.println("\n------------------ Pay Slip ------------------");
        System.out.println("Name: " + empName);
        System.out.println("ID: " + empId);
        System.out.println("Address: " + address);
        System.out.println("Email: " + mailId);
        System.out.println("Mobile: " + mobileNo);
        System.out.println("Designation: " + designation);
        System.out.println("Basic Pay: " + basicPay);
        System.out.println("DA: " + DA);
        System.out.println("HRA: " + HRA);
        System.out.println("PF: " + PF);
        System.out.println("Staff Club Fund: " + SCF);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Net Salary: " + netSalary);
        System.out.println("---------------------------------------------\n");
    }
}
class Programmer extends Employee {
    void generatePaySlip() {
        getDetails();
        if (basicPay < 15000 || basicPay > 20000) {
            System.out.println("Basic pay not in range for Programmer (15000 - 20000).");
            return;
        }
        displayPaySlip("Programmer", 97, 10, 12, 1);
    }
}
class AssistantProfessor extends Employee {
    void generatePaySlip() {
        getDetails();
        if (basicPay < 20001 || basicPay > 30000) {
            System.out.println("Basic pay not in range for Assistant Professor (20001 - 30000).");
            return;
        }
        displayPaySlip("Assistant Professor", 110, 20, 12, 5);
    }
}
class AssociateProfessor extends Employee {
    void generatePaySlip() {
        getDetails();
        if (basicPay < 30001 || basicPay > 40000) {
            System.out.println("Basic pay not in range for Associate Professor (30001 - 40000).");
            return;
        }
        displayPaySlip("Associate Professor", 130, 30, 12, 10);
    }
}
class Professor extends Employee {
    void generatePaySlip() {
        getDetails();
        if (basicPay <= 40000) {
            System.out.println("Basic pay not in range for Professor (> 40000).");
            return;
        }
        displayPaySlip("Professor", 140, 40, 12, 15);
    }
}
public class emp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Designation to Generate Pay Slip:");
        System.out.println("1. Programmer");
        System.out.println("2. Assistant Professor");
        System.out.println("3. Associate Professor");
        System.out.println("4. Professor");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                new Programmer().generatePaySlip();
                break;
            case 2:
                new AssistantProfessor().generatePaySlip();
                break;
            case 3:
                new AssociateProfessor().generatePaySlip();
                break;
            case 4:
                new Professor().generatePaySlip();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

​

