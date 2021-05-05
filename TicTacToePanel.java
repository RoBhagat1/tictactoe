/*********************************************************************************
Name: Rohan Bhagat
*********************************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TicTacToePanel extends JPanel implements ActionListener {

	private List<TicTacToeButton> ticTacToetButtonsList;

	private JButton newGameButton;
	private JButton exitGameButton;
	private JLabel currentScoreLabel;

	int score;

	// store 'x' or 'o' in an array
	String labels[];

	boolean playerWithLetterX;

	public TicTacToePanel() {

		// initialize tic tac toe labels
		labels = new String[9];
		ResetLabels();

		// create clickable UI buttons
		createButtons();

		// always start with letter X
		this.playerWithLetterX = true;  
		
		// set the score
		score = 0;

		//create the layout of the buttons and other panels
		createLayout();
	}

	private void ResetLabels()
	{
		// default rest to '?'
		for (int i=0; i < labels.length; i++)
			labels[i] = "?";
	}

	// start a new game
	public void newGame() 
	{
		ResetLabels();

		for(int index=0; index < ticTacToetButtonsList.size(); index++)
		{
			this.ticTacToetButtonsList.get(index).setText(labels[index]);
		}
		this.playerWithLetterX = true;
	}

	public void createButtons() {

		// create a button list for each spot
		this.ticTacToetButtonsList = new ArrayList<TicTacToeButton>();

		for(int index=0; index < labels.length; index++)
		{
			//create a new button for each spot, and add it to the list
			TicTacToeButton button = new TicTacToeButton(index);
			button.setFocusable(true);//enable focusing to accept key press events
			ticTacToetButtonsList.add(button);
			button.setText(labels[index]);
		
			//add a actionListener for each button
			button.addActionListener(this);
		}
	}

	//action listener for the buttons
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();//get the source

		if (source instanceof TicTacToeButton) {//check is alphabet button
			TicTacToeButton button = (TicTacToeButton) source;
			actionPerformOnClick(button);//perform the action on the button
		}
	}
	
	//perform action on  button
	public void actionPerformOnClick(TicTacToeButton button) {

		int index = button.getIndex();

		if ( labels[index].equals("?"))
		{
			if ( playerWithLetterX == true) 
			{
				labels[index] = "X";
			}
			else
			{
				labels[index] = "O";
			}

			button.setText(labels[index]);
		
			boolean isWinner = checkWinner();

			if  ( isWinner )
			{
				String s = String.format("Player with letter " + (playerWithLetterX? "X" : "O") + " won!");
				JOptionPane.showMessageDialog(null,s);
			}

			this.playerWithLetterX = !this.playerWithLetterX;  // flip flop between two players
		}
	}
	
	//if values match
	private boolean checkWinner() 
	{
		boolean isWinner = false;

		if ( 
			!labels[0].equals("?") && labels[1].equals(labels[0]) && labels[2].equals(labels[0]) // row 0
			||
			!labels[0].equals("?") && labels[3].equals(labels[0]) && labels[6].equals(labels[0]) // col 0
			||
			!labels[3].equals("?") && labels[4].equals(labels[3]) && labels[5].equals(labels[3]) // row 1
			||
			!labels[1].equals("?") && labels[4].equals(labels[1]) && labels[7].equals(labels[1]) // col 1
			||
			!labels[6].equals("?") && labels[7].equals(labels[6]) && labels[8].equals(labels[6]) // row 2
			||
			!labels[2].equals("?") && labels[5].equals(labels[2]) && labels[8].equals(labels[2]) // col 2
			||
			!labels[0].equals("?") && labels[4].equals(labels[0]) && labels[8].equals(labels[0]) // diagonal 0
			||
			!labels[6].equals("?") && labels[4].equals(labels[6]) && labels[2].equals(labels[6]) // diagonal 1
			)
			{
				isWinner = true;

				//Increment score, and set it to current score label
				score++;
				currentScoreLabel.setText(Integer.toString(score));
			}

		return isWinner;
	}
	
	
	//create layout for the game panel
	public void createLayout() {

		// create game panel using border layout
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);

		// create topLeft panel, add menuPanel inside it
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new FlowLayout());
		topLeft.setBackground(Color.white);

		// create menuPanel to store Top scores, New Game, and exit
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.white);
		menuPanel.setLayout(new BorderLayout());
		topLeft.add(menuPanel, BorderLayout.WEST);

		// create pause button
		newGameButton = new JButton("New Game");
		newGameButton.setPreferredSize(new Dimension(125, 50));// set size
		newGameButton.setFocusable(false);//disable focusing to avoid keypress events
		menuPanel.add(newGameButton, BorderLayout.CENTER);
		
		// add action listener to pause game
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Start New Game
				try {
					newGame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// create exit button
		exitGameButton = new JButton("Exit Game");
		exitGameButton.setPreferredSize(new Dimension(125, 50));// set size
		exitGameButton.setFocusable(false);//disable focusing to avoid keypress events
		menuPanel.add(exitGameButton, BorderLayout.SOUTH);
		
		// add action listener to make it exit when clicked
		exitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// exit system
				System.exit(0);
			}
		});
		

		// create topCenter panel
		JPanel topCenter = new JPanel();

		//topCenter.setLayout(new FlowLayout());
		topCenter.setLayout(new BoxLayout(topCenter, BoxLayout.PAGE_AXIS ));

		JPanel panelOne = new JPanel(); // hold first row (0,1,2)
		panelOne.setBackground(Color.white);
		JPanel panelTwo = new JPanel();// hold second row (3,4,5)
		panelTwo.setBackground(Color.white);
		JPanel panelThree = new JPanel();// hold third row (6,7,8)
		panelThree.setBackground(Color.white);


		for (int i=0; i < 3; i++) {//loop to initialize panel row 0
			panelOne.add(ticTacToetButtonsList.get(i));
		}

		for (int i=3; i < 6; i++) {//loop to initialize panel row 1
			panelTwo.add(ticTacToetButtonsList.get(i));
		}

		for (int i=6; i < 9; i++) {//loop to initialize panel row 2
			panelThree.add(ticTacToetButtonsList.get(i));
		}

		topCenter.add(panelOne);//add first panel to the top
		topCenter.add(panelTwo);//add second panel to bottom section
		topCenter.add(panelThree);//add second panel to bottom section		

		// create topRight panel for scorePanel
		JPanel topRight = new JPanel();
		topRight.setLayout(new BorderLayout());
		topRight.setBackground(Color.white);

		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.white);
		scorePanel.setLayout(new FlowLayout());

		// add player name to score panel
		JLabel staticScoreLableText = new JLabel("Current game score is: ");
		scorePanel.add(staticScoreLableText, FlowLayout.LEFT);

		// add currentScoreLabel to the score Panel
		currentScoreLabel = new JLabel(Integer.toString(score));
		scorePanel.add(currentScoreLabel, FlowLayout.CENTER);

		// add these to the topRight section
		topRight.add(scorePanel, BorderLayout.NORTH);

		//add all panels to the screen in correct Layout
		this.add(topLeft);
		this.add(topCenter);
		this.add(topRight);
	}
}