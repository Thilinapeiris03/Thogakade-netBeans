/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icet.thogakde.model;

/**
 *
 * @author HP 250
 */
public class Item {
    private String itemCode;
    private String name;
    private String barcode;
    private double qty;

    public Item() {
    }

    public Item(String itemCode, String name, String barcode,double qty) {
        this.itemCode = itemCode;
        this.name = name;
        this.barcode = barcode;
        this.qty=qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    
}
