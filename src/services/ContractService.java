package services;

import entities.Contract;
import entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

     private OnlinePaymentServices onlinePaymentServices;

     public ContractService(OnlinePaymentServices onlinePaymentServices) {
          this.onlinePaymentServices = onlinePaymentServices;
     }

     public void processContract(Contract contract, int months) {
          double basicQuota = contract.getTotalValue() / months;
          for (int i=1; i<=months; i++){
               double updatedQuota = basicQuota + onlinePaymentServices.interest(basicQuota, i);
               double fullQuota = updatedQuota + onlinePaymentServices.paymentFee(updatedQuota);
               Date dueDate = addMonths(contract.getDate(), i);
               contract.getInstallments().add(new Installment(dueDate, fullQuota));
          }
     }

     private Date addMonths(Date date, int N) {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          calendar.add(Calendar.MONTH, N);
          return calendar.getTime();
     }

}
