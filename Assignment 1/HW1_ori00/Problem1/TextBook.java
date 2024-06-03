public class TextBook {

private String title; // Title of the book
private String author; // Author's last name
private String publisher; // Name of publisher

public TextBook(String textTitle, String auth,String pub){

    this.title = textTitle;
    this.author = auth;
    this.publisher = pub;
} //constructor

public TextBook(TextBook object2){
    this.title = object2.getTitle();
    this.author = object2.getAuthor();
    this.publisher = object2.getPublisher();
} //copy constructor

public void set(String textTitle, String auth,String pub){

    this.title = textTitle;
    this.author = auth;
    this.publisher = pub;
} //setter

public String getTitle(){ return title;}
public String getAuthor(){ return author;}
public String getPublisher(){ return publisher;}

public String toString(){

    return String.format("%S\n%S\n%S", getTitle(), getAuthor(), getPublisher());
}

//The following toString() method is redundant but we can use it.

/*public String toString(){

    return String.format("Title Name: %S\nAuthor: %S\nPublisher: %S", getTitle(), getAuthor(), getPublisher());
}*/

}