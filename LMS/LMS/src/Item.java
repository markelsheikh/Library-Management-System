import java.util.Date;


public class Item {
	
//	private int isbn;
//	private String title, author;
//	private double price;
	
	private String ItemID;
	private String title;
	private String ISBN;
	private int year;
	private String publisher;
	private String category;
	private String MediaCondition;
	private int copies;
	private boolean ForSale;
	private String AuthorID;
	private String AuthorName;
	private long AuthorDOB;
	
        
	public String getItemID() { return ItemID; };
	public String getTitle() { return title; };
	public String getISBN() { return ISBN; };
	public int getYear() { return year; };
	public String getPublisher() { return publisher; };
	public String getCategory() { return category; };
	public String getMediaCondition() { return MediaCondition; };
	public int getCopies() { return copies; };
	public boolean getForSale() { return ForSale; };
	public String getAuthorID() { return AuthorID; };
	public String getAuthorName() { return AuthorName; };
	public long getAuthorDOB() { return AuthorDOB; };
	
	public void setItemID(String ItemID) { this.ItemID = ItemID;};
	public void setTitle(String title) { this.title = title;};
	public void setISBN(String ISBN) { this.ISBN = ISBN;};
	public void setYear(int year) { this.year = year;};
	public void setPublisher(String publisher) { this.publisher = publisher;};
	public void setCategory(String category) { this.category = category;};
	public void setMediaCondition(String MediaCondition) { this.MediaCondition = MediaCondition;};
	public void setCopies(int copies) { this.copies = copies;};
	public void setForSale(boolean ForSale) { this.ForSale = ForSale;};
	public void setAuthorID(String AuthorID) { this.AuthorID = AuthorID;};
	public void setAuthorName(String AuthorName) { this.AuthorName = AuthorName;};
	public void setAuthorDOB(long AuthorDOB) { this.AuthorDOB = AuthorDOB;};
	
	
	public Item(){
		ItemID = null;        
		title = null;         
		ISBN = null;          
		year = 0;             
		publisher = null;     
		category = null;      
		MediaCondition = null;
		copies = 0;           
		ForSale = false;      
		AuthorID = null;      
		AuthorName = null;    
	}
	
	public Item(String ItemID,
			String title,
			String ISBN,
			int year,
			String publisher,
			String category,
			String MediaCondition,
			int copies,
			boolean ForSale,
			String AuthorID,
			String AuthorName,
			long AuthorDOB){
		
		this.ItemID = ItemID; 
		this.title = title; 
		this.ISBN = ISBN; 
		this.year = year; 
		this.publisher = publisher; 
		this.category = category; 
		this.MediaCondition = MediaCondition; 
		this.copies = copies; 
		this.ForSale = ForSale; 
		this.AuthorID = AuthorID; 
		this.AuthorName = AuthorName; 
		this.AuthorDOB = AuthorDOB; 
		
	}
	

	
	@Override
	public String toString(){
		return 	"\nItemID: " + ItemID +
				"\nTitle: " + title +
				"\nISBN: " + ISBN +
				"\nYear: " + year +
				"\nPublisher: " + publisher +
				"\nCategory: " + category +
				"\nMediaCondition: " + MediaCondition +
				"\nCopies: " + copies +
				"\nForSale: " + ForSale +
				"\nAuthorID: " + AuthorID +
				"\nAuthorName: " + AuthorName +
				"\nAuthorDOB: " + AuthorDOB;
	}
	
}
