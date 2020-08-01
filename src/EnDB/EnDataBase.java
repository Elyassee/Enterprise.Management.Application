package EnDB;

import enterprise.Tools;
import enterprise.Tools.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EnDataBase {
    
    private static String url = "";
    public  static String dbName = "enterprise_database";
    private static Connection con;
    
    private static void setURL(){
        url="jdbc:mysql://localhost:3306/" + dbName;
            }
    
    private static void setConnection(){
        try {
            setURL();
            con = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean checkUserAndPsswd(String username, String password){
    setConnection();
        try {
            Statement stmt = con.createStatement();
            String check = "select * from users where " +
                    "username='" + username + "' and " +
                    "pass='" + password + "'";
            stmt.executeQuery(check);
            while (stmt.getResultSet().next()){
                return true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean Query(String sqlStatement){ //for insert update and delete
        try {
            setConnection();
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatement);
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Tools.msgBox(ex.getMessage());
            //Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            
    }
    
    public static String getAutoNumber(String tableName, String columnName){ //get auto number for ID's
    
        try {
            setConnection();
           Statement stmt = con.createStatement();
        
    String autoStr = "select max(" + columnName +")+1 as autonumber"
            + " from " + tableName;
    stmt.executeQuery(autoStr);
    String num = "";
    while(stmt.getResultSet().next()){
        num = stmt.getResultSet().getString("autonumber");
    }
    con.close();
    if(num == null || "".equals(num)){
        return "1";
    }
    else{
    return num;
    }
    } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }
    
    }
    
    
    public static Table getDataOfTable(String statement){
        
       Tools t = new Tools();
        try {
            setConnection();
            Statement stmt = con.createStatement();
            ResultSet rs;      
            rs = stmt.executeQuery(statement);
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();
            Table table = t.new Table(c);
            while(rs.next()){
                Object row[] = new Object[c];
                for(int i=0; i<c; i++){
                    row[i] = rs.getString(i+1);
                }
                table.addNewRow(row);
            }
            con.close();
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
            return t.new Table(0);
            }
    }
    
    public static void fillCombo(String tableName, String columnName, JComboBox combo){
        try {
        setConnection();
        Statement stmt = con.createStatement();
        ResultSet rs;
        
        String selectStr = "select "+ columnName + " from " + tableName;
            rs = stmt.executeQuery(selectStr);
            rs.last();
            int c = rs.getRow();
            rs.beforeFirst();
            String values[] = new String[c];
            int i=0;
            while(rs.next()){
                values[i] = rs.getString(1);
                i++;
            }
            con.close();
            combo.setModel(new DefaultComboBoxModel(values));
            
        } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public static void fillJTable(String tableNameOrStatement, JTable table){
    
        try {
            setConnection();
            Statement stmt = con.createStatement();
             ResultSet rs;
             String selectStr;
    
    if("select ".equals(tableNameOrStatement.substring(0,7).toLowerCase())){
        selectStr = tableNameOrStatement;
    }
    else {
    selectStr = "select * from " + tableNameOrStatement;
    }
    rs = stmt.executeQuery(selectStr);
    ResultSetMetaData rsmd = rs.getMetaData();
    int c = rsmd.getColumnCount();
    
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    
    Vector row = new Vector();
    model.setRowCount(0);
    
    while(rs.next()){
        row = new Vector(c);
        for(int i=1; i<=c; i++){
            row.add(rs.getString(i));
        }
        model.addRow(row);
    }
    if(table.getColumnCount() !=c){
        Tools.msgBox("the JTable Columns count are not equal to the query cplumns count");
    }
         con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EnDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
}