
package DEntity;

import enterprise.Tools;
import javax.swing.JTable;


public class EnEmployee implements DBData{
    
    private int Employee_ID;
    private String Full_Name;
    private String Address;
    private String Birth_date;
    private String Sex;
    private int Salary;
   
    private int Dep_Number;

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(int Employee_ID) {
        this.Employee_ID = Employee_ID;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getBirth_date() {
        return Birth_date;
    }

    public void setBirth_date(String Birth_date) {
        this.Birth_date = Birth_date;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }

    @Override
    public void add() {
        String insert = "INSERT INTO employee VALUES ("
                + Employee_ID + ","
                + "'" + Full_Name + "',"
                + "'" + Address + "',"
                + "'" + Birth_date + "',"
                + "'" + Sex + "',"
                + Salary + ","
                +Dep_Number + ")";
        
        boolean isAdd = EnDB.EnDataBase.Query(insert);
        if(isAdd){
            Tools.msgBox("Employee Is Added!");
        }
        
    }

    @Override
    public void delete() {
       String delete = "delete from employee"
               + " where Employee_ID=" + Employee_ID;
       
       boolean Deleted = EnDB.EnDataBase.Query(delete);
       
       if(Deleted){
       Tools.msgBox("Employee deleted!");
       }
    }

    @Override
    public void update() {
        String update = "update employee set "
                + "Full_Name='" + Full_Name + "',"
                + "Address='" + Address + "',"
                + "Birth_Date='" + Birth_date + "'," 
                + "Sex='" + Sex + "',"
                + "Salary=" + Salary +","
                + "Dep_Number=" + Dep_Number 
                + " where Employee_ID=" + Employee_ID;
        
        boolean isUpdt = EnDB.EnDataBase.Query(update);
        if(isUpdt){
            Tools.msgBox("Employee Updated!");
        }
    }

    @Override
    public String getAutoNumber() {
        return EnDB.EnDataBase.getAutoNumber("employee", "Employee_ID");
    }

    @Override
    public void getRowsOfDb(JTable table) {
        EnDB.EnDataBase.fillJTable("data_of_employee", table);
    }

    @Override
    public void getRowOfDb(JTable table) {
        String select = "select * from data_of_employee"
                + " where `Employee ID`="+ Employee_ID;
        EnDB.EnDataBase.fillJTable(select, table);
    }

    @Override
    public void getSpecfRows(String statemet, JTable table) {
        EnDB.EnDataBase.fillJTable(statemet, table);
    }

    
    public String getIDByName(String Full_name) {
        String select = "select Employee_ID from employee "
                + "where Full_name='" + Full_name +"'";
                
        String val = EnDB.EnDataBase.getDataOfTable(select).Items[0][0].toString();
        return val;
    }

    @Override
    public String getNameByID(String ID) {
        String select = "select Full_Name from employee"
                + " where Employee_ID=" + ID;
        String name = EnDB.EnDataBase.getDataOfTable(select).Items[0][0].toString();
        return name;
    }


    public int getDep_Number() {
        return Dep_Number;
    }

    public void setDep_Number(int Dep_Number) {
        this.Dep_Number = Dep_Number;
    }
    
}
