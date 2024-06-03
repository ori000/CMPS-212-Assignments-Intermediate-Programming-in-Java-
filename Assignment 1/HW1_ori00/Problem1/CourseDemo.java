public class CourseDemo {
    
    public static void main(String[] args) {
        
        TextBook TB = new TextBook("Harry Potter", "J.K. Rowling", "Bloomsbury Publishing");
        Instructor I = new Instructor("Ivans", "John", "R654");
        Course C = new Course("CMPS212", I, TB);

        System.out.println(C);
    }
}
