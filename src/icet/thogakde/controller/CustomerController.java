/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icet.thogakde.controller;

import icet.thogakde.db.DBConnection;
import icet.thogakde.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP 250
 */
public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        /*Connection connection = DBConnection.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String SQL="Insert into Customer Values('"+customer.getId()+"','"+customer.getName()+"','"+customer.getAddress()+"',"+customer.getSalary()+")";
        int num= stm.executeUpdate(SQL);*/
        
        String SQL="Insert into Customer Values(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getSalary());
        return pstm.executeUpdate()>0; 
    }

    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
       
        String SQL="Select * From Customer where id='"+id+"'";
        Statement stm = connection.createStatement();
        ResultSet rst= stm.executeQuery(SQL);
       
        return rst.next() ? new Customer(id, rst.getString("name"), rst.getString("address"),rst.getDouble("salary")): null;

    }

    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        String SQL="Update Customer Set name=? , address=? , salary=? where id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        //pstm.setObject(1, customer.getId());
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getSalary());
        pstm.setObject(4, customer.getId());
        return pstm.executeUpdate()>0; 
    }
    
    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete from Customer where id='"+id+"'")>0;
    }

    public static Customer[] getAllCustomer() throws ClassNotFoundException, SQLException {
        String SQL="Select * From Customer";
        Connection connection= DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        int count = 0;
        while(rst.next()){
            count++;
        }
        Customer[] customerArray= new Customer[count];
        rst.beforeFirst();
        for (int i = 0; i < count; i++) {
            rst.next();
            customerArray[i]=new Customer(rst.getString("id"), rst.getString("Name"), rst.getString("Address"), rst.getDouble("Salary"));
        }
        return customerArray;
    }
}
