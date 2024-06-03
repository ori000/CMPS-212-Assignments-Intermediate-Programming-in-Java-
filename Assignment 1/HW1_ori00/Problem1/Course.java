public class Course {
    
private String courseName; // Name of the course
private Instructor instructor; // The instructor
private TextBook textBook; // The textbook

public Course(String name, Instructor instr, TextBook text){
    this.courseName = name;
    this.instructor = instr;
    this.textBook = text;
} //constructor

public String getName(){
    return courseName;
} // returns the name of the course

public Instructor getInstructor(){
    return instructor;
} // returns a copy of the instructor object.

public TextBook getTextBook(){
    return textBook;
} // returns a copy of the textBook object.

public String toString(){
    return String.format("Course: %S\nInstructor: %S\nTextBook: %S", getName(), getInstructor(), getTextBook());
}

}
