/*********************************************************************************
Name: Rohan Bhagat
*********************************************************************************/
import java.awt.*;

import javax.swing.*;

//class to make Tic Tac Toe-buttons
public class TicTacToeButton extends JButton{
	//character of the button
	private int index;
	//constructor
	public TicTacToeButton(int index)
	{
		this.index = index;
		super.setFont(new Font("Segoe Script", Font.BOLD, 40));//set font
		super.setBorder(BorderFactory.createRaisedBevelBorder());//make a raised border
		super.setPreferredSize(new java.awt.Dimension(80, 80));//set dimensions
		super.setText(Integer.toString(index));//set the text
		super.setOpaque(true);//set it to opaque
		super.setBorderPainted(false);//don't paint border
	}

	public int getIndex() {
		return index;
	}

	public void setText(String x_or_o) 
	{
		super.setText(x_or_o);//set the text
	}
}
