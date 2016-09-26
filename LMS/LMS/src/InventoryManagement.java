import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class InventoryManagement extends Object {
	
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";
   //  Database credentials
    static final String USER = "cetienne";
    static final String PASS = "welcome1";
    
    static InventoryManagement lib = new InventoryManagement();
    
	//private List<Item> collection;

	public InventoryManagement(){
		//
	}
	
//	public void addCollectionItem(Item item){
//		collection.add(item);
//	}
	
	public void addItem(Item item){
		Connection conn = null;
		CallableStatement stmt = null;
			
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    String SQL = "{call item_upsert (?,?,?,?,?,?,?,?,?,?,?,?)}";
		    stmt = conn.prepareCall(SQL);

		    stmt.setString(1, item.getItemID());
		    stmt.setString(2, item.getTitle());
		    stmt.setString(3, item.getISBN());
		    stmt.setInt(4, item.getYear());
		    stmt.setString(5, item.getPublisher());
		    stmt.setString(6, item.getCategory());
		    stmt.setString(7, item.getMediaCondition());
		    stmt.setInt(8, item.getCopies());
		    stmt.setBoolean(9, item.getForSale());
		    stmt.setString(10, item.getAuthorID());
		    stmt.setString(11, item.getAuthorName());
		    java.sql.Date sqlDate = new java.sql.Date(item.getAuthorDOB());
		    stmt.setDate(12, sqlDate);

		    int myresult = stmt.executeUpdate();
		    
		    System.out.println("Item has been inserted!" + myresult);

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public  List<Item> SearchInventory(int ans, String Keywords) {

		List<Item> collection = new ArrayList<Item>();

		Connection conn = null;
		Statement stmt = null;
		
		String SQL = "SELECT a.ItemID, a.Title, a.ISBN, a.Year, a.Publisher, a.Category, a.MediaCondition, a.Copies, a.ForSale, c.AuthorID, c.Name, c.DOB " +
					 "from INVENTORY a " +
					 "INNER JOIN MEDIA_AUTHOR b on b.ItemID = a.ItemID " +
					 "INNER JOIN AUTHOR c on c.AuthorID = b.AuthorID ";

		switch (ans) {
		case 1:
			SQL = SQL + "WHERE a.Title like '%" + Keywords + "%'";
			//System.out.println("SQL1: " + SQL);
			break;

		case 2:
			SQL = SQL + "WHERE c.Name like '%" + Keywords + "%'";
			//System.out.println("SQL2: " + SQL);
			break;
		}
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    stmt = conn.createStatement();
		    
		    ResultSet rs = stmt.executeQuery(SQL);


		      //STEP 5: Extract data from result set
		      while(rs.next()){
		    	  
		    	  //System.out.println("I have records!!!");
		    	  
		    	  Item item = new Item();
		         //Retrieve by column name
		    	item.setItemID(rs.getString("ItemID"));
		  		item.setTitle(rs.getString("Title"));
				item.setISBN(rs.getString("ItemID"));
				item.setYear(rs.getInt("ItemID"));
				item.setPublisher(rs.getString("Publisher"));
				item.setCategory(rs.getString("Category"));
				item.setMediaCondition(rs.getString("MediaCondition"));
				item.setCopies(rs.getInt("Copies"));
				item.setForSale(rs.getBoolean("ForSale"));
				item.setAuthorName(rs.getString("Name"));
				item.setAuthorDOB(rs.getLong("DOB"));
				
				collection.add(item);
		      }
		      rs.close();
		      stmt.close();
		      conn.close();   

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		//System.out.println(collection.isEmpty());
		return collection;	
	}		
	
	public Item getItem(String ItemID, List<Item> col) {
		Item i = new Item();
	    for(Item d : col){
	    	//System.out.println("\nIn the collection loop - " + d.getItemID());
	        if(d.getItemID() != null && d.getItemID().contains(ItemID)) {
	           i = d;
	           break;
	        }
	    }
	    return i;
	}
	
	public void ViewItem(Item item){
		if (item.getItemID() != null){
			System.out.format("%-20s%-50s", "\nItemID: ", item.getItemID());
			System.out.format("%-20s%-50s", "\nTitle: ", item.getTitle());
			System.out.format("%-20s%-50s", "\nISBN: ", item.getISBN());
			System.out.format("%-20s%-50s", "\nYear: ", item.getYear());
			System.out.format("%-20s%-50s", "\nPublisher: ", item.getPublisher());
			System.out.format("%-20s%-50s", "\nCategory: ", item.getCategory());
			System.out.format("%-20s%-50s", "\nMediaCondition: ", item.getMediaCondition());
			System.out.format("%-20s%-50s", "\nCopies: ", item.getCopies());
			System.out.format("%-20s%-50s", "\nForSale: ", item.getForSale());
			System.out.format("%-20s%-50s", "\nAuthorID: ", item.getAuthorID());
			System.out.format("%-20s%-50s", "\nAuthorName: ", item.getAuthorName());
			System.out.format("%-20s%-50s", "\nAuthorDOB: ", item.getAuthorDOB());
			System.out.println("\n\n");
		}
	}
	

	public void DeleteItem(Item id){
//		Item item = new Item();
//		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		Connection conn = null;
		CallableStatement stmt = null;
		

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		    String SQL = "{call item_delete (?,?)}";
		    stmt = conn.prepareCall(SQL);

		    stmt.setString(1, id.getItemID());
		    stmt.setString(2, id.getAuthorID());

		    int myresult = stmt.executeUpdate();
		    
		    System.out.println("Item has been delete!" + myresult);

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}	
	
	public void UpdateItem(Item id){
		try {

				Connection conn = null;
				CallableStatement stmt = null;
				
				try {
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    conn = DriverManager.getConnection(DB_URL,USER,PASS);
				    String SQL = "{call item_upsert (?,?,?,?,?,?,?,?,?,?,?,?)}";
				    stmt = conn.prepareCall(SQL);
				    
				    //System.out.println("ItemID before update: " + id.getItemID());

				    stmt.setString(1, id.getItemID());
				    stmt.setString(2, id.getTitle());
				    stmt.setString(3, id.getISBN());
				    stmt.setInt(4, id.getYear());
				    stmt.setString(5, id.getPublisher());
				    stmt.setString(6, id.getCategory());
				    stmt.setString(7, id.getMediaCondition());
				    stmt.setInt(8, id.getCopies());
				    stmt.setBoolean(9, id.getForSale());
				    stmt.setString(10, id.getAuthorID());
				    stmt.setString(11, id.getAuthorName());
				    java.sql.Date sqlDate = new java.sql.Date(id.getAuthorDOB());
				    stmt.setDate(12, sqlDate);

				    int myresult = stmt.executeUpdate();
				    
				    System.out.println("Item has been update!" + myresult);

				} catch (SQLException ex) {
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
				
	
		}
		catch (Exception ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    //System.out.println("SQLState: " + ex.getSQLState());
		    //System.out.println("VendorError: " + ex.getErrorCode());
		}
	}	
	

}


