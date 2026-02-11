package com.wipro.bank.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import com.wipro.bank.bean.TransferBean;
import com.wipro.bank.util.DBUtil;
public class BankDAO {
    public int generateTransactionId() {
        String query = "SELECT NVL(MAX(TRANSACTION_ID),0)+1 FROM TRANSFER_TBL";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public boolean validateAccount(String accountNumber) {
        String query = "SELECT 1 FROM ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public float findBalance(String accountNumber) {
        String query = "SELECT BALANCE FROM ACCOUNT_TBL WHERE ACCOUNT_NUMBER = ?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateBalance(String accountNumber, float newBalance) {
        String query = "UPDATE ACCOUNT_TBL SET BALANCE = ? WHERE ACCOUNT_NUMBER = ?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setFloat(1, newBalance);
            ps.setString(2, accountNumber);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean transferMoney(TransferBean bean) {
        int txnId = generateTransactionId();
        String query =
            "INSERT INTO TRANSFER_TBL " +
            "(TRANSACTION_ID, ACCOUNT_NUMBER, BENIFICIARY_ACCOUNT_NUMBER, TRANSACTION_DATE, TRANSACTION_AMOUNT) " +
            "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, txnId);
            ps.setString(2, bean.getFromAccountNumber());
            ps.setString(3, bean.getToAccountNumber());
            ps.setDate(4, new Date(bean.getDateofTransaction().getTime()));
            ps.setFloat(5, bean.getAmount());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
