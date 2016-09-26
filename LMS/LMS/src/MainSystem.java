import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


public class MainSystem {

	/**
	 * 
	 */
	static InventoryManagement lib = new InventoryManagement();
	static List<Item> collection = new ArrayList<Item>();
	static Scanner in = new Scanner(System.in);
	static Boolean running = true;
	static Boolean searching = true;
	static Boolean viewing = true;
	static Boolean updating = true;
    static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


	public static void main(String[] args) throws ParseException {
		while (running) {
			System.out.println("\nEnter 1 add an item."
					+ "\nEnter 2 search items"
					+ "\nEnter 3 browse items"
					+ "\nEnter 4 to exit");

			int answer5 = in.nextInt();
			switch (answer5) {
			case 4:
				running = false;
				break;
			case 3:
				//saveAndQuit();
				break;
			case 2:
				searching = true;
				SearchInventory();
				//System.out.println(lib.LibraytoString());
				break;
			case 1:
				addItem();
				break;
			}
		}
		System.exit(0);
	}

	private static void addItem() throws ParseException {
		Item item = new Item();
				
//		System.out.println("Enter ItemID: ");
//		item.setItemID(in.next());
	 
//		System.out.println("Enter title: ");
//		item.setTitle(in.next());
//	 
//		System.out.println("Enter ISBN: ");
//		item.setISBN(in.next());
//	 
//		System.out.println("Enter year: ");
//		item.setYear(Integer.parseInt(in.next()));
//	 
//		System.out.println("Enter publisher: ");
//		item.setPublisher(in.next());
//	 
//		System.out.println("Enter category: ");
//		item.setCategory(in.next());
//	 
//		System.out.println("Enter MediaCondition: ");
//		item.setMediaCondition(in.next());
//	 
//		System.out.println("Enter copies: ");
//		item.setCopies(Integer.parseInt(in.next()));
//	 
//		System.out.println("Enter ForSale: ");
//		item.setForSale(Boolean.parseBoolean(in.next()));
//	 
////		System.out.println("Enter AuthorID: ");
////		item.setAuthorID(in.next());
//	 
//		System.out.println("Enter AuthorName: ");
//		item.setAuthorName(in.next());
//	 
//		System.out.println("Enter AuthorDOB: ");
//		item.setAuthorDOB(formatter.parse(in.next()));

		//Item b = new Item(isbn, title, author, price);
		//lib.addItem(item);
		
		item.setTitle("Apex & Visualforce Controllers");
		item.setISBN("687521");
		item.setYear(Integer.parseInt("2013"));
		item.setPublisher("Salesforce.com, Inc.");
		item.setCategory("Programming");
		item.setMediaCondition("New");
		item.setCopies(Integer.parseInt("3"));
		item.setForSale(Boolean.parseBoolean("0"));
		item.setAuthorName("Salesforce");
		item.setAuthorDOB(2141978);
		
		lib.addItem(item);
	}

	private static void SearchInventory() {

		System.out.println("\nEnter 1 to search by Title"
				+ "\nEnter 2 to search by Author Name");

		int ans = in.nextInt();
		System.out.println("\nEnter search Keyword(s)");
		String Keywords = in.next();
		
		collection = lib.SearchInventory(ans, Keywords);
		
		//System.out.println(collection.isEmpty());
		while (searching) {
			 System.out.println(CollectiontoString(collection));
			System.out.println("\nEnter Item ID to view, update, or delete an Item"
					+ "\nEnter 0 to go back to main menu");

			int answer2 = in.nextInt();
			switch (answer2) {
			case 0:
				searching = false;
				lib = new InventoryManagement();
				break;
			default:
				//System.out.println("\nBefore ID");
				String id = Integer.toString(answer2);
				//System.out.println("\nID Enterred - " + id);
				Item item = lib.getItem(id, collection);
				if (item.getItemID() != null){
					ManageItem(item);
				}else{
					System.out.println("Item ID: " + id + " does not exist in your search results!" + 
									"\nPlease enter a valid Item ID.\n");
				}
				break;
			}
		}		
	}	
	
	private static void ManageItem(Item id) {
		//System.out.println("\nBefore Item toString - " + id.getItemID());
		
		//System.out.println("\nAfter Item toString - " + id.getItemID());
		while (viewing) {
			in = new Scanner(System.in);
			lib.ViewItem(id);
			System.out.println("\nEnter 1 to update item"
					+ "\nEnter 2 to delete item"
					+ "\nEnter 0 to go back to previous menu");

			int answer3 = in.nextInt();
			switch (answer3) {
			case 0:
				viewing = false;
				break;

			case 1:
				UpdateItem(id);
				break;
			case 2:
				System.out.println("\nAre you sure you want to delete Item# " + id.getItemID()
						+ "\nEnter Y for (yes) or N for (No)");
				String hold = in.next();
				System.out.println("Hold: " + hold);
				if (hold.equals("Y")){
					System.out.println("I'M IN!!!");
					lib.DeleteItem(id);
					viewing = false;
				}
				break;				
			}
		}			
	}
	

	

	private static void UpdateItem(Item id){
		try {
		while (updating) {
			//in = new Scanner(System.in);
			if (id.getItemID() != null){
				//System.out.format("%-20s%-50s", "\n   ItemID: ", item.getItemID());
				System.out.format("%-20s%-50s", "\n01. Title: ", id.getTitle());
				System.out.format("%-20s%-50s", "\n02. ISBN: ", id.getISBN());
				System.out.format("%-20s%-50s", "\n03. Year: ", id.getYear());
				System.out.format("%-20s%-50s", "\n04. Publisher: ", id.getPublisher());
				System.out.format("%-20s%-50s", "\n05. Category: ", id.getCategory());
				System.out.format("%-20s%-50s", "\n06. MediaCondition: ", id.getMediaCondition());
				System.out.format("%-20s%-50s", "\n07. Copies: ", id.getCopies());
				System.out.format("%-20s%-50s", "\n08. ForSale: ", id.getForSale());
				//System.out.format("%-20s%-50s", "\n   AuthorID: ", item.getAuthorID());
				System.out.format("%-20s%-50s", "\n09. AuthorName: ", id.getAuthorName());
				System.out.format("%-20s%-50s", "\n10. AuthorDOB: ", id.getAuthorDOB());
				System.out.println("\n\n");
//			} else {
//				updating = false;
			}
				
			System.out.println("\nEnter the line number you wish to modify!"
					+ "\nEnter -1 to save your changes"
					+ "\nEnter 0 to go back to previous menu");
			//System.out.println("NEXT IN1" + in.next());
			int answer4 = in.nextInt();
			//System.out.println("NEXT IN2");
			//System.exit(0);
			switch (answer4) {
			case -1:
				lib.UpdateItem(id);
				updating = false;
				break;
			
			case 0:
				updating = false;
				break;
			case 1:
				in.nextLine();
				System.out.println("Enter new Title: ");
				id.setTitle(in.nextLine());
				break;
			case 2:
				System.out.println("Enter new ISBN: ");
				id.setISBN(in.next());
				break;		
			case 3:
				System.out.println("Enter new year: ");
				id.setYear(Integer.parseInt(in.next()));
				break;
			case 4:
				in.nextLine();
				System.out.println("Enter new publisher: ");
				id.setPublisher(in.nextLine());
				break;
			case 5:
				in.nextLine();
				System.out.println("Enter new category: ");
				id.setCategory(in.nextLine());
				break;
			case 6:
				System.out.println("Enter new MediaCondition: ");
				id.setMediaCondition(in.next());
				break;
			case 7:
				System.out.println("Enter new copies: ");
				id.setCopies(Integer.parseInt(in.next()));
				break;
			case 8:
				System.out.println("Enter new ForSale: ");
				id.setForSale(Boolean.parseBoolean(in.next()));
				break;
			case 9:
				in.nextLine();
				System.out.println("Enter new AuthorName: ");
				id.setAuthorName(in.nextLine());
				break;
			case 10:
				System.out.println("Enter new AuthorDOB: ");
				java.util.Date d;
				try {
					d = formatter.parse(in.next());
					id.setAuthorDOB(d.getTime());
					break;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}		
		}
		catch (Exception ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    //System.out.println("SQLState: " + ex.getSQLState());
		    //System.out.println("VendorError: " + ex.getErrorCode());
		}
		

	}
	
	public static String CollectiontoString(List<Item> col) {
		String linebreak = "\n";
		//System.out.println("Item ID\tAuthor Name\t\tTitle\t\tYear\tPublisher\tCopies");
		System.out.format("%-8s%-20s%-40s%-5s%-20s%-6s", "Item ID","Author Name","Title","Year","Publisher","Copies\n");
		Iterator<Item> i = col.iterator();
		while(i.hasNext()){
			Item b = (Item) i.next();
			//System.out.println(b.getItemID()+"\t"+b.getAuthorName()+"\t\t"+b.getTitle()+"\t\t"+b.getYear()+"\t"+b.getPublisher()+"\t"+b.getCopies());
			System.out.format("%-8s%-20s%-40s%-5s%-20s%-6s", b.getItemID(),b.getAuthorName(),b.getTitle(),+b.getYear(),b.getPublisher(),b.getCopies());
			System.out.format("%-1s",linebreak);
		}
		return linebreak;
	}	

}
