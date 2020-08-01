
package DEntity;

import enterprise.Tools;
import javax.swing.JTable;

public class EnProject implements DBData{
    
    private int ProjectID;
    private String ProjectName;
    private int DepID;

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public int getDepID() {
        return DepID;
    }

    public void setDepID(int DepID) {
        this.DepID = DepID;
    }

    @Override
    public void add() {
        String insert = "insert into project values("
                + ProjectID + ","
                + "'" + ProjectName + "',"
                + DepID + ")";
        
        boolean add = EnDB.EnDataBase.Query(insert);
        if(add){
        Tools.msgBox("Project is added!");
        }
    }

    @Override
    public void delete() {
        String delete = "delete from project "
                + " where Pr_Number=" + ProjectID;
        boolean isDelete = EnDB.EnDataBase.Query(delete);
        if(isDelete){
            Tools.msgBox("Projekt is deleted!");
        }
    }

    @Override
    public void update() {
        String update = "update project set "
                + "Pr_Name='" + ProjectName + "',"
                + "Dep_Number=" + DepID
                + " where Pr_Number=" + ProjectID;
        
        boolean isUpdate = EnDB.EnDataBase.Query(update);
        if(isUpdate){
            Tools.msgBox("Project is updated!");
        }
    }

    @Override
    public String getAutoNumber() {
         return EnDB.EnDataBase.getAutoNumber("project", "Pr_Number");
    }

    @Override
    public void getRowsOfDb(JTable table) {
        EnDB.EnDataBase.fillJTable("data_of_project", table);
    }

    @Override
    public void getRowOfDb(JTable table) {
       String select = "select * from data_of_project"
               + " where `Project Number`=" + ProjectID;
       
       EnDB.EnDataBase.fillJTable(select, table);
    }

    @Override
    public void getSpecfRows(String statemet, JTable table) {
        EnDB.EnDataBase.fillJTable(statemet, table);
    }

    @Override
    public String getIDByName(String name) {
        String select = "select Pr_Number from project"
                + " where Pr_Name='" + name + "'";
        
        String value = (String) EnDB.EnDataBase.getDataOfTable(select).Items[0][0];
        
        return value;
    }

    @Override
    public String getNameByID(String ID) {
        String select = "select Pr_Name from project"
                + " where Pr_Number=" + ID;
        
        String name = (String)EnDB.EnDataBase.getDataOfTable(select).Items[0][0];
        
        return name;
        
    }
}
