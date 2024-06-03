
public class RunJavaPad {

	public static void main(String[] args) {
		
		new JavaPad();

	}

}

/*VERY IMPORANT:

First begin by clicking the SaveAs button so you create a new file other wise you will get an error when Saving since no file exists.
To Load, first you have to open the file that you want, then load its content. Otherwise you will get an error since Java doesn't know
what file to load since we have not specified anything yet.

Those errors prove the logic behind this code and are not bugs in my code.



Also, I used OOP cleverly here by extending the JavaPad from JFrame and by implementing the ActionListener in the inner class JavaPadListener.

I did so to reduce redundancy. It's a more time-efficient and cooler way than creating classes for all as mentioned in the lecture.

Lastly, the capSwitch button works by UpperCapping the lowCap letters in the textArea and vice versa. It does so by checking the first char
to know whether the user make it a fully lowCapped text or not.....

*/