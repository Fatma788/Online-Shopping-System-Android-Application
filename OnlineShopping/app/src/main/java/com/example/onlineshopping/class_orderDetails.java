package com.example.onlineshopping;

class class_orderDetails {
    public int OrdID;
    public int ProID;
    public int Quantity;
    public double TotalPrice;
    public String ProName;
    public double Price;

    public class_orderDetails(int _OrdID, int _ProID, int _Quantity,double _TotalPrice, String _ProName, double _Price) {
        this.OrdID = _OrdID;
        this.ProID = _ProID;
        this.Quantity = _Quantity;
        this.TotalPrice = _TotalPrice;
        this.Price = _Price;
        this.ProName = _ProName;
    }
}