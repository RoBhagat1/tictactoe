/*********************************************************************************
Name: Rohan Bhagat
*********************************************************************************/
import javax.swing.*;
import java.awt.*;

//main class for creating screen and game frame
public class TicTacToeScreen extends JFrame {
	
	public TicTacToeScreen () {
		setTitle("Welcome to Tic Tac Toe Game!");

		//create game panel and add it to the frame
		JPanel panel = new TicTacToePanel();
		this.add(panel);

		//position the frame 
		centerWindow();

		//exit it on closing screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void centerWindow() {
		//get screen dimensions, and then set the bounds to the system screen size
		Toolkit systemToolkit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = systemToolkit.getScreenSize();

		//set game to full screen
		setBounds(0, 0, screenDimensions.width/2, screenDimensions.height/2);
		
		//OK for users to resize game
		setResizable(true);
	}

}
