package assn07_1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below

        // infite loop to go back to "Enter master password"
        while(true){
            System.out.println("Enter Master Password");
            String input = Scanner.nextLine();
            if(input.equals(passwordManager.MASTER_PASSWORD)){
                break;
            }
        }
        while(true){
            String input = scanner.nextLine();
            switch(input){
                case "New password":
                    String webName = scanner.nextLine();
                    String password = scanner.nextLine();
                    passwordManager.put(webName,password);
                    System.out.println("New password added");
                    break;
                case "Get password":
                    String webName = scanner.nextLine();
                    String password = passwordManager.get(webName);
                    if(password == null){
                        System.out.println("Account does not exist");
                    }else{
                        System.out.println(password);
                    }
                    break;
                case "Delete Account":
                    String webName = scanner.nextLine();
                    String removed = passwordManager.remove(webName);
                    if(removed == null){
                        System.out.println("Account does not exist");
                    }else{
                        System.out.println("Account deleted");
                    }
                    break;
                case "Get Accounts":
                    Set<String> accounts = passwordManager.keySet();
                    System.out.println("Your accounts:");
                    for(String account : accounts){
                        System.out.println(account);
                    }
                    break;
                case "Generate Random Password":
                    int len = scanner.nextInt();
                    String password = generateRandomPassword(len);
                    System.out.println(password);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command Not Found");
            }

        }


    }
}
