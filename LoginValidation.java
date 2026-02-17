import java.util.Scanner;
public class LoginValidation {

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);

        String  correctUsername = "Admin";
        int  correctpassword=1234;

        System.out.println("enter Username:");
        String Username=sc.nextLine();

        System.out.println("enter password:");
        int password=sc.nextInt();

        if(Username.equals(correctUsername) && password==correctpassword){
            System.out.println("login is succesfully done");
        }
            else{
            System.out.println("wrong Username and password");
            }

            sc.close();



        }



    }
