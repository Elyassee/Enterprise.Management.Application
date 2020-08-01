
package DEntity;

import javax.swing.JTable;


public interface DBData {
    
    
    public void add();
    public void delete();
    public void update();
    public String getAutoNumber();
    public void getRowsOfDb(JTable table);
    public void getRowOfDb(JTable table); 
    public void getSpecfRows(String statemet, JTable table);
    public String getIDByName(String name);
    public String getNameByID(String ID);
    
    
    
}
