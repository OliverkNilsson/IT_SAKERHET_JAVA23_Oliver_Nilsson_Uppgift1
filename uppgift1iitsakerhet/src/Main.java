import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();
        int choice = 0;

        while(choice != 4) {
            System.out.println("-------------");
            System.out.println("1. Create account. 2. Show accounts. 3. Delete account. 4. Close program.");
            System.out.print("Choose a option: ");
            choice = Integer.parseInt(scanner.next());
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Adress: ");
                    String adress = scanner.nextLine();
                    db.addUser(email, password, name, adress);
                    break;
                case 2:
                    System.out.println("Enter name: ");
                    String showName = scanner.nextLine();
                    db.showUser(showName);
                    break;
                case 3:
                    System.out.println("Enter email to delete account: ");
                    String deleteEmail = scanner.nextLine();
                    db.deleteUser(deleteEmail);
                    break;
                case 4:
                    scanner.close();
                    break;
            }
        }
    }
}