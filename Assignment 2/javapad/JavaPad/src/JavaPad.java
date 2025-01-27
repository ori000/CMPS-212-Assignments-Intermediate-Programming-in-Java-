import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class JavaPad extends JFrame{
	
	static File file;
	
	private JTextArea textArea;
	private JButton Save, Load, SaveAs, Open, New, Quit, caseChange;
	JFrame frame = new JFrame();

	public JavaPad(){
		
		
		
		frame.setSize(600,600);
		frame.setResizable(true);
		frame.setTitle("Microstuff JavaPad XP");
		
		JavaPadListener listener = new JavaPadListener();
		
		JPanel buttons = new JPanel();
		JPanel Label = new JPanel();
		JPanel Area = new JPanel();
		
		Save = new JButton("Save");
		Save.addActionListener(listener);
		Save.setBackground(Color.YELLOW);
		Load = new JButton("Load");
		Load.addActionListener(listener);
		Load.setBackground(Color.ORANGE);
		SaveAs = new JButton("Save As");
		SaveAs.addActionListener(listener);
		SaveAs.setBackground(Color.GREEN);
		Open = new JButton("Open");
		Open.addActionListener(listener);
		Open.setBackground(Color.CYAN);
		New = new JButton("New");
		New.addActionListener(listener);
		New.setBackground(Color.GRAY);
		Quit = new JButton("Quit");
		Quit.addActionListener(listener);
		Quit.setBackground(Color.RED);
		caseChange = new JButton("CapSwitch");
		caseChange.addActionListener(listener);
		caseChange.setBackground(Color.MAGENTA);
		
		
		
		JLabel label = new JLabel();
		label.setText("MicroStuff: We Can Do It!");
		BorderLayout bl = new BorderLayout();
		frame.add(label, BorderLayout.SOUTH);
		
		textArea = new JTextArea(15,25);
		
		
		JScrollPane scroll = new JScrollPane(textArea);
		
		buttons.add(Save);
		buttons.add(Load);
		buttons.add(SaveAs);
		buttons.add(Open);
		buttons.add(New);
		buttons.add(Quit);
		buttons.add(caseChange);
		
		Label.add(label);
		
		Area.add(scroll);
		
		frame.add(buttons, BorderLayout.NORTH);
		frame.add(Label, BorderLayout.SOUTH);
		
		frame.add(scroll);
		frame.add(scroll, BorderLayout.CENTER);
		
		
		
		
		
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public String getText() {return textArea.getText();}
	public void fileReader(String file) {
		
		try {
			Scanner input = new Scanner(new FileReader(file));
			
			
			
			while(input.hasNextLine()) {
				textArea.setText(file);
			}
			
			} catch(Exception e) { System.out.println("Error");}
			
	}
	public void fileWriter(File file) {
		
		try {
		PrintWriter fw = new PrintWriter(new FileWriter(JavaPad.file));
	 
	    fw.write(textArea.getText()); fw.flush();} catch(Exception e) {
	    	System.out.println("An Error Occurred. Choose a file to save into.");}
	    
	}
	
	class JavaPadListener implements ActionListener{
		
		JFileChooser fileChooser = new JFileChooser();
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton sourceButton = (JButton) e.getSource();
			
			if(sourceButton.equals(Save)) {
				fileWriter(JavaPad.file);
			}
			
			if(sourceButton.equals(New)) {textArea.setText("");}
			
			if(sourceButton.equals(SaveAs)) {
				int result = fileChooser.showSaveDialog(frame);
				
				if(result == fileChooser.APPROVE_OPTION) {
					file = (fileChooser.getSelectedFile());
					fileWriter(JavaPad.file);
				}
			}
			
			if(sourceButton.equals(Quit)) {
				
				int quit = JOptionPane.showConfirmDialog(null, "Quitting: Save?", "Quit", JOptionPane.YES_NO_OPTION);
				
				if(quit == JOptionPane.YES_OPTION) {
					fileWriter(JavaPad.file);
					
					frame.dispose();
				}
				else if(quit == JOptionPane.NO_OPTION) {
					
					frame.dispose();}
					
			}
			if(sourceButton.equals(Open)) {
				int open = fileChooser.showOpenDialog(frame);
				if(open == fileChooser.APPROVE_OPTION)
				JavaPad.file = (fileChooser.getSelectedFile());
				
				
				
			}
			if(sourceButton.equals(Load)) {
				try {
					Scanner inside = new Scanner(new FileReader(JavaPad.file));
					
					String texts = "";
					while(inside.hasNextLine()) {
						texts+=inside.nextLine()+"\n";
					}
						
					textArea.setText(texts);
						
				} catch (Exception e1) {
					
					System.out.println("Error. Make sure that you Open,via Open button, a file to load its text onto the textArea.");
				}
			}
			if(sourceButton.equals(caseChange)) { 
				if(textArea.getText().isEmpty())System.out.println("Error, make sure that the text is not empty and that the first index is an alphabet letter.");
				
				else if(textArea.getText().charAt(0) >= 97 && textArea.getText().charAt(0) <= 122)
				textArea.setText(textArea.getText().toUpperCase());
				
				else if(textArea.getText().charAt(0) >= 65 && textArea.getText().charAt(0) <= 90)
				textArea.setText(textArea.getText().toLowerCase());
				}
			
			
			
			
		}
		
		
	} 
}
