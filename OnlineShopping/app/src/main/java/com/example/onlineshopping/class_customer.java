package com.example.onlineshopping;

class class_customer {
    public int CustID;
    public int Gender;
    public String CutName;
    public String Password;
    public String Birthdate;
    public String Job;

    public class_customer() {
    }

    public class_customer(int _CustID, int _Gender, String _CutName, String _Password, String _Birthdate, String _Job) {
        this.Birthdate = _Birthdate;
        this.CustID = _CustID;
        this.CutName = _CutName;
        this.Gender = _Gender;
        this.Password = _Password;
        this.Job = _Job;
    }
}
