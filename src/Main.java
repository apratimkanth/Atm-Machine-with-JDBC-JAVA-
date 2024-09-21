import java.sql.*;
import java.util.Scanner;
import java.util.*;

class Credential{
    int id;
    int pin;
    String FIRST_Name;
    String SECOND_Name;
    int BALANCE;
}

class SqlOperation
{
    public static String getName(int accid){
        try {
            String query="select * from accountname where ID="+accid+";";
            Connection connection = DriverManager.getConnection(Main.url, Main.username, Main.password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            String Name=null;
            while (rs.next()){
                int id = rs.getInt("ID");
                Name = rs.getString("NAME");
            }

            rs.close();
            connection.close();
            st.close();
            return Name;

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());

        }
        return null;
    }
    public static ArrayList<Credential> getCredential(int accid,int accpin){
        ArrayList<Credential>credArray=new ArrayList<Credential>();
        try {
            String query="select * from accountdetail where ID="+accid+" and PIN="+accpin+";";
            Connection connection = DriverManager.getConnection(Main.url, Main.username, Main.password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                Credential cred=new Credential();
                cred.id = rs.getInt("ID");
                cred.pin = rs.getInt("PIN");
                cred.FIRST_Name = rs.getString("FIRST_NAME");
                cred.SECOND_Name = rs.getString("SECOND_NAME");
                cred.BALANCE = rs.getInt("BALANCE");
                credArray.add(cred);
            }

            rs.close();
            connection.close();
            st.close();

        }
        catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());

        }
        return credArray;
    }
    public static int updateBal(){
        try {
            String query="UPDATE accountdetail " +
                    "SET BALANCE ="+Main.BALANCE+
                    " WHERE ID ="+Main.Id+" and "+"PIN = "+Main.Pin+";";
            Connection connection = DriverManager.getConnection(Main.url, Main.username, Main.password);
            Statement st = connection.createStatement();
            int affectedrow= st.executeUpdate(query);
            connection.close();
            st.close();
            return affectedrow;

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());

        }
        return 0;
    }
}
class Menu{
    public static void menu(){
        System.out.println("1.] for Balance : ");
        System.out.println("2.] for Cash Debit : ");
        System.out.println("3.] for Cash Credit : ");
        System.out.println("4.] for Exit : ");
        Scanner sc2=new Scanner(System.in);
        int choice=sc2.nextInt();
        switch (choice){
            case 1:
                getBalance();
                break;
            case 2:
                cashDebit();
                break;
            case 3:
                cashCredit();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid Choice");
        }
        sc2.close();
    }
    private static void getBalance(){
        System.out.println(Main.BALANCE);
        menu();
    }
    private static void cashDebit(){
        Scanner sc2=new Scanner(System.in);
        int dbal=sc2.nextInt();
        Main.BALANCE=Main.BALANCE-dbal;
        getBalance();
    }
    private static void cashCredit(){
        Scanner sc2=new Scanner(System.in);
        int balance=sc2.nextInt();
        Main.BALANCE=Main.BALANCE+balance;
        getBalance();
    }
}
public class Main {
    // Database URL
    final static String url = "jdbc:mysql://localhost:3306/javaproject";
    // Database credentials
    final static String username = "root";
    final static String password = "Password@mysql#123";
    static int Id;
    static int Pin;
    static String FIRST_Name;
    static String SECOND_Name;
    static int BALANCE;
    public static  void main(String[] args){
        try{
            Class.forName("com.mysql. jdbc. Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Credential>credArray=new ArrayList<Credential>();
        int incFlag=1;
        System.out.println("========Welcome========");
        System.out.println("========Enter your Card Detail========");
        Scanner sc1=new Scanner(System.in);
        int accNumber= sc1.nextInt();
        String Name=SqlOperation.getName(accNumber);
        if(Name==null) {
            System.out.println("Enter the Correct Credential");
            System.exit(0);
        }
        System.out.println("========Welcome "+Name+"========");
        System.out.println("========Enter the 4 digit Pin========");
        int pin=sc1.nextInt();
        while(Integer.toString(pin).length()>4 || Integer.toString(pin).length()<4){
            if(incFlag==3){
                System.out.println("========Your Atm is Blocked For 24 hr========");
                System.exit(0);
            }
            incFlag++;
            System.out.println("========Incorrect Pin, Enter the Correct Pin========");
            pin=sc1.nextInt();
        }
        credArray=SqlOperation.getCredential(accNumber,pin);
        if(credArray.isEmpty()){
            System.out.println("Incorrect Pin");
            System.exit(0);
        }
        Id=credArray.getFirst().id;
        Pin=credArray.getFirst().pin;
        FIRST_Name=credArray.getFirst().FIRST_Name;
        SECOND_Name=credArray.getFirst().SECOND_Name;
        BALANCE=credArray.getFirst().BALANCE;

        System.out.println("========Welcome "+FIRST_Name+" "+SECOND_Name+"========");
        Menu.menu();
        sc1.close();
        int affectedRow=SqlOperation.updateBal();
        if(affectedRow==0){
            System.out.println("Updation Failed");
        }
    }
}
