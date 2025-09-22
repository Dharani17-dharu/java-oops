import java.util.Scanner;
public class ElectricityBill{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    int consumerNo;
    String consumerName;
    int prevReading, currReading;
    String connType;
    double amount;
    int units;
       

        System.out.print("Enter Consumer Number: ");
        consumerNo = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.print("Enter Consumer Name: ");
        consumerName = sc.nextLine();

        System.out.print("Enter Previous Month Reading: ");
        prevReading = sc.nextInt();

        System.out.print("Enter Current Month Reading: ");
        currReading = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.print("Enter Connection Type (domestic/commercial): ");
        connType = sc.nextLine().toLowerCase();

        units = currReading - prevReading;

        if (connType.equals("domestic")) {
            if (units <= 100)
                amount = units * 1;
            else if (units <= 200)
                amount = 100 * 1 + (units - 100) * 2.5;
            else if (units <= 500)
                amount = 100 * 1 + 100 * 2.5 + (units - 200) * 4;
            else
                amount = 100 * 1 + 100 * 2.5 + 300 * 4 + (units - 500) * 6;
        } else if (connType.equals("commercial")) {
            if (units <= 100)
                amount = units * 2;
            else if (units <= 200)
                amount = 100 * 2 + (units - 100) * 4.5;
            else if (units <= 500)
                amount = 100 * 2 + 100 * 4.5 + (units - 200) * 6;
            else
                amount = 100 * 2 + 100 * 4.5 + 300 * 6 + (units - 500) * 7;
        } else {
            System.out.println("Invalid connection type.");
            return;
        }

        System.out.println("\n--- Electricity Bill ---");
        System.out.println("Consumer No     : " + consumerNo);
        System.out.println("Consumer Name   : " + consumerName);
        System.out.println("Connection Type : " + connType);
        System.out.println("Units Consumed  : " + units);
        System.out.println("Bill Amount     : Rs. " + amount);
    }
}

