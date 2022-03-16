package client;

import ToFileHandOver.HandOver;
import ToFileHandOver.HandOverHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            HandOver handOverObj =  HandOverHelper.narrow(ncRef.resolve_str("ABC"));

            while (true) {
                switch (menu()) {
                    case 1:
                        handOverObj.handOver();
                        break;
                    case 2:
                        System.exit(0);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int menu() {
        System.out.println("1 - handover file info");
        System.out.println("2 - exit");
        System.out.println();

        int res;

        while (true) {
            System.out.print("Your choice: ");
            if (!scanner.hasNext("[12]")) {
                scanner.next();
                System.out.println("Reenter value pls");
            } else {
                res = scanner.nextInt();
                break;
            }
        }
        return res;
    }
}
