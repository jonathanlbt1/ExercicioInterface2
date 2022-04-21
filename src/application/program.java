package application;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());

        System.out.print("Contract value: ");
        double value = sc.nextDouble();

        Contract contract = new Contract(number, date, value);
        ContractService cs = new ContractService(new PaypalService());

        System.out.print("Enter number of installments: ");
        int installments = sc.nextInt();

        cs.processContract(contract, installments);

        System.out.println("Installments: ");
        for(Installment it : contract.getInstallments()){
            System.out.println(it);
        }

        sc.close();
    }
}
