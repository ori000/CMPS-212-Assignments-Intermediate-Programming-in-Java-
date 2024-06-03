public class CourseDemo2 {
    
    public static void main(String[] args) {
        

        TextBook object2 = new TextBook("Calculus III", "George Thomas", "Pearson");
        TextBook TB = new TextBook(object2);
        
        
        Instructor o2 = new Instructor("Miller", "Michael", "R314");
        Instructor I = new Instructor(o2);

        Course C = new Course("MATH201", I, TB);

        System.out.println(C);
    }
}
