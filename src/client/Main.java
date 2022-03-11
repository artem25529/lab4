package client;

import ToFileHandOver.HandOver;
import ToFileHandOver.HandOverHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            HandOver handOverObj =  HandOverHelper.narrow(ncRef.resolve_str("ABC"));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Enter a string");
                String s = scanner.nextLine();
                if (s.equals("exit")) {
                    break;
                }
                handOverObj.handOver(s);
            }

            System.out.println("shutdown");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
