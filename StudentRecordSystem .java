import java.io.*;
import java.util.*;
class Record
{
    static Scanner sc = new Scanner(System.in);
    static File file = new File("students.txt");
    public static void createFile() {
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error occurred.");
        }
    }
    public static void writeRecord() {
        try (FileWriter fw = new FileWriter(file)) {
            System.out.print("Enter Roll No: ");
            String roll = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Marks: ");
            String marks = sc.nextLine();
            fw.write(roll + "," + name + "," + marks + "\n");
            System.out.println("Record written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
    public static void readRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Student Records ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("------------------------");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
    public static void appendRecord() {
        try (FileWriter fw = new FileWriter(file, true)) {
            System.out.print("Enter Roll No: ");
            String roll = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Marks: ");
            String marks = sc.nextLine();

            fw.write(roll + "," + name + "," + marks + "\n");
            System.out.println("Record appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending file.");
        }
    }
    public static void updateRecord() {
        try {
            List<String> records = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
            br.close();

            System.out.print("Enter Roll No to update: ");
            String rollNo = sc.nextLine();
            boolean found = false;

            for (int i = 0; i < records.size(); i++) {
                String[] parts = records.get(i).split(",");
                if (parts[0].equals(rollNo)) {
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    String newMarks = sc.nextLine();
                    records.set(i, rollNo + "," + newName + "," + newMarks);
                    found = true;
                    break;
                }
            }

            if (found) {
                FileWriter fw = new FileWriter(file);
                for (String rec : records) {
                    fw.write(rec + "\n");
                }
                fw.close();
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("Record not found.");
            }
        } catch (IOException e) {
            System.out.println("Error updating file.");
        }
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Create File");
            System.out.println("2. Write Record");
            System.out.println("3. Read Records");
            System.out.println("4. Append Record");
            System.out.println("5. Update Record");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: createFile(); break;
                case 2: writeRecord(); break;
                case 3: readRecords(); break;
                case 4: appendRecord(); break;
                case 5: updateRecord(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
