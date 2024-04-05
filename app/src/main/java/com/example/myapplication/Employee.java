package com.example.myapplication;

public class Employee {
    public String employeeID;
    public String name;
    public String department;
    public boolean isHR;
    public String DOJ;
    public String email;
    public String mobile;
    public String perNum;
    public String linkedIN;
    public String offAddress;
    public String offName;
    public String status;

    public Employee(int type){
        if (type == 1){
            employeeID="123";
            name="Vinayak Tripathi";
            department="House of Product";
            isHR=false;
            DOJ="22";
            email="vin@ob.com";
            mobile="1234567898";
            perNum="5678123412";
            linkedIN="https://www.linkedin.com/in/harsh-ganatra";
            offAddress="Sohna rd";
            offName="OneBanc Delhi";
            status="Completed";
        } else if (type == 2) {
            employeeID="123";
            name="Harsh Ganatra";
            department="House of Product";
            isHR=false;
            DOJ="22";
            email="har@ob.com";
            mobile="1234567898";
            perNum="5678123412";
            linkedIN="https://www.linkedin.com/in/harsh-ganatra";
            offAddress="Sohna rd";
            offName="OneBanc Delhi";
            status="Verified";
        } else if (type == 3) {
            employeeID="123";
            name="Tanvi A";
            department="House of People";
            isHR=true;
            DOJ="22";
            email="tan@ob.com";
            mobile="1234567898";
            perNum="5678123412";
            linkedIN="https://www.linkedin.com/in/harsh-ganatra";
            offAddress="Sohna rd";
            offName="OneBanc Delhi";
            status="Completed";
        } else if (type == 4) {
            employeeID="123";
            name="Alexis";
            department="House of People";
            isHR=true;
            DOJ="22";
            email="";
            mobile="1234567898";
            perNum="5678123412";
            linkedIN="https://www.linkedin.com/in/harsh-ganatra";
            offAddress="Sohna rd";
            offName="OneBanc Delhi";
            status="Incomplete";
        }

    }

}
