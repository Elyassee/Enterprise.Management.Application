
package DEntity;

import enterprise.Tools;
import javax.swing.JTable;

public class EnWorkOn implements DBData{
    
    private int EmployeeID;
    private int projectID;

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public void add() {
        String insert ="insert into workon values("
                + EmployeeID+ ","
                +projectID + ")";
        
        boolean isAdd = EnDB.EnDataBase.Query(insert);
        if(isAdd){
            Tools.msgBox("Work on is added!");
        }
    }

    @Override
    public void delete() {
        String delete = "delete from workon where "
                +"Employee_ID=" + EmployeeID
                + " and "
                + "Pr_Number="+ projectID;
        
        boolean isDelete = EnDB.EnDataBase.Query(delete);
        if(isDelete){
            Tools.msgBox("Work on is deleted!");
        }
        
    }

    @Override
    public void update() {
        Tools.msgBox("cannot be updated!");
    }

    @Override
    public String getAutoNumber() {
         Tools.msgBox("cannot get autonumber!");
         return"";
    }

    @Override
    public void getRowsOfDb(JTable table) {
        EnDB.EnDataBase.fillJTable("data_of_workon", table);
    }

    @Override
    public void getRowOfDb(JTable table) {
        String select = "select * from data_of_workon where "
                +"`Employee ID`=" + EmployeeID
                + " and "
                + "Project_Number=" + projectID;
        EnDB.EnDataBase.fillJTable(select, table);
                
    }

    @Override
    public void getSpecfRows(String statemet, JTable table) {
        EnDB.EnDataBase.fillJTable(statemet, table);
    }

    @Override
    public String getIDByName(String name) {
         Tools.msgBox("cannot get the ID!");
         return"";
    }

    @Override
    public String getNameByID(String ID) {
         Tools.msgBox("cannot get the Name!");
         return"";
    }
    
}
