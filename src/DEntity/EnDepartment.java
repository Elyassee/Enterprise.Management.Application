
package DEntity;

import enterprise.Tools;
import javax.swing.JTable;


public class EnDepartment implements DBData{
    
    private int Dep_Number;
    private String Dep_Name;

    public int getDep_Number() {
        return Dep_Number;
    }

    public void setDep_Number(int Dep_Number) {
        this.Dep_Number = Dep_Number;
    }

    public String getDep_Name() {
        return Dep_Name;
    }

    public void setDep_Name(String Dep_Name) {
        this.Dep_Name = Dep_Name;
    }

    @Override
    public void add() {
        String insert = "insert into department values(" + Dep_Number + ","
                + "'" + Dep_Name + "')";
        boolean isAdded = EnDB.EnDataBase.Query(insert);
        if(isAdded){
        Tools.msgBox("Department is Added!");
        }
    }

    @Override
    public void delete() {
        String delete = "delete from department where Dep_Number=" + Dep_Number;
        boolean isDeleated = EnDB.EnDataBase.Query(delete);
        if(isDeleated){
        Tools.msgBox("Department is deleted!");
        }
        
    }

    @Override
    public void update() {
        String update = "update department set "+ "Dep_Name='" + Dep_Name + "'"
                        + " where Dep_Number=" + Dep_Number;
        
        boolean isUpdated = EnDB.EnDataBase.Query(update);
        if(isUpdated){
        Tools.msgBox("Department is updated!");
        }
    }

    @Override
    public String getAutoNumber() {
        return EnDB.EnDataBase.getAutoNumber("department", "Dep_Number");
         
    }

    @Override
    public void getRowsOfDb(JTable table) {
        EnDB.EnDataBase.fillJTable("data_of_department", table);
    }

    @Override
    public void getRowOfDb(JTable table) {
        String select = "select * from data_of_department " + " where `Department Number`=" + Dep_Number;
        EnDB.EnDataBase.fillJTable(select, table);
    }

    @Override
    public void getSpecfRows(String statemet, JTable table) {
        EnDB.EnDataBase.fillJTable(statemet, table);
    }

    @Override
    public String getIDByName(String name) {
        String select = "select Dep_Number from department " + "where Dep_Name='" + name + "'";
        String strVal = (String)EnDB.EnDataBase.getDataOfTable(select).Items[0][0];
        return strVal;
     }

    @Override
    public String getNameByID(String ID) {
        String select = "select Dep_Name from department " + "where Dep_Number=" + ID;
        String strName = (String)EnDB.EnDataBase.getDataOfTable(select).Items[0][0];
        return strName;
    }
    
}
