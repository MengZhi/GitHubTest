import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GreetingClient extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	public static void main(String [] args) throws IOException
	   {    new GreetingClient();
			String sentence; 
			String modifiedSentence; 
			String empty;
			Socket clientSocket;
			
			while(true){
			
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
						
				clientSocket = new Socket("localhost", 6789); 
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
				
				sentence = inFromUser.readLine(); 
				System.out.println(sentence);
				if(sentence == "q \n") break;
				outToServer.writeBytes(sentence + '\n'); 
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence); 
				clientSocket.close(); 
			}
			clientSocket.close(); 
			System.out.println("terminated!");
			
			
			 }
	
	private JFrame frame;
    private JTextArea viewArea;
    private JTextField viewField;
    private JButton button1;
    private JButton button2;
    private JLabel jlable;
    private JTextField MyName;
    public GreetingClient(){
        frame = new JFrame("Chat Room");
        viewArea = new JTextArea(10, 50);
        viewField = new JTextField(50);
        jlable= new JLabel();
        jlable.setText("online");
        button1 = new JButton("Send");
        button2 = new JButton("Quit");
        MyName = new JTextField();
        MyName.setColumns(9);
        MyName.setText("Goblin");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,1));
        panel.add(jlable);
        panel.add(MyName);
        panel.add(button1);
        panel.add(button2);
        JScrollPane sp = new JScrollPane(viewArea);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add("Center",sp);
        frame.add("East",panel);
        frame.add("South",viewField);
        frame.setSize(500,250);
        frame.setVisible(true);
        button1.addMouseListener((MouseListener) this);
        button2.addMouseListener((MouseListener) this);
        }
 
    public void mouseClicked(MouseEvent evt){
        String message = "";
        message=MyName.getText()+viewField.getText();
        if(evt.getSource()==button1){
            viewArea.setText(viewArea.getText()+message+ "\n") ;
            }
        if(evt.getSource()==button2){
            message = "Quit";
            viewArea.setText(message);
            viewField.setText("");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
     
    public void mousePressed(MouseEvent evt){ }
    public void mouseReleased(MouseEvent evt){ }
    public void mouseEntered(MouseEvent e){ }
    public void mouseExited(MouseEvent e){ }
     
    }
    
