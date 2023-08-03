/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icet.thogakde.controller;

import icet.thogakde.db.DBConnection;
import icet.thogakde.model.Customer;
import icet.thogakde.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP 250
 */
public class ItemController {
    public static boolean addItem(Item item) throws ClassNotFoundException, SQLException{
        String SQL="Insert into Item Values(?,?,?,?)";
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(SQL);
        pstm.setObject(1, item.getItemCode());
        pstm.setObject(2, item.getName());
        pstm.setObject(3, item.getBarcode());
        pstm.setObject(4, item.getQty());
        System.err.println(item.getQty());
        return pstm.executeUpdate()>0;
        
    }
    
    public static Item searchItem(String itemCode) throws ClassNotFoundException, SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
       
        String SQL="Select * From Item Where itemcode='"+itemCode+"'";
        Statement stm = connection.createStatement();
        ResultSet rst= stm.executeQuery(SQL);
       
        return rst.next() ? new Item(itemCode, rst.getString("name"), rst.getString("barcode"),rst.getDouble("quantity")): null;

    }
    
    public static boolean updateItem(Item item) throws SQLException, ClassNotFoundException{
        String SQL="Update Item Set name=? , barcode=? , quantity=? where itemcode=?";
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pstm=connection.prepareStatement(SQL);
        
        pstm.setObject(1, item.getName());
        pstm.setObject(2, item.getBarcode());
        pstm.setObject(3, item.getQty());
        pstm.setObject(4, item.getItemCode());
        return pstm.executeUpdate()>0;
    }

    public static Item[] getAllItem() throws ClassNotFoundException, SQLException {
        String SQL="Select * From Item";
        Connection connection= DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        int count = 0;
        while(rst.next()){
            count++;
        }
        Item[] itemArray= new Item[count];
        rst.beforeFirst();
        for (int i = 0; i < count; i++) {
            rst.next();
            itemArray[i]=new Item(rst.getString("Itemcode"), rst.getString("Name"), rst.getString("Barcode"), rst.getDouble("Quantity"));
        }
        return itemArray;
    }
}
