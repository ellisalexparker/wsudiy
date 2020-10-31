package wsudiy;

public class Videos {
	
	protected String title;
	protected String description;
	protected String url;
	protected String date;
	protected String diy_question;
	protected String author;
 
    public Videos() {
    	
    }
 
    public Videos(String url) {
        this.url = url;
    }
    
    public Videos(String title, String description, String date, String diy_question, String author) {
    	
    }
    
    public Videos(String url, String title, String description, String date, String diy_question, String author) {
    	this(title, description, date, diy_question, author);
    	this.url = url;
    	this.title = title;
    	this.description = description;
    	this.date = date;
    	this.diy_question = diy_question;
    	this.author = author;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public void setUrl(String url) {
    	this.url = url;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public void setTitle(String title) {
    	this.title = title;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getDate() {
    	return date;
    }
    
    public void setDate(String date) {
    	this.date = date;
    }
    
    public String getDiyQuestion() {
    	return diy_question;
    }
    
    public void setDiyQuestion(String diy_question) {
    	this.diy_question = diy_question;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
}