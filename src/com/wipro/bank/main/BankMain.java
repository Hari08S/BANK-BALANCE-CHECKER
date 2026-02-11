package com.wipro.bank.main;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.service.BankService;
import com.wipro.bank.util.InsufficientFundsException;
public class BankMain {
    public static void main(String[] args) {
        try {
            BankService bankService = new BankService();
            System.out.println(bankService.checkBalance("103"));
            TransferBean bean = new TransferBean();
            bean.setFromAccountNumber("103");
            bean.setToAccountNumber("102");
            bean.setAmount(15);
            bean.setDateofTransaction(new java.util.Date());
            System.out.println(bankService.transfer(bean));
        } catch (InsufficientFundsException e) {
            System.out.println(e);
        }
    }
}
