package main;

import javax.swing.*;
import java.awt.*;

public class Main
{
	static final int framewidth=350;
	static final int frameheight=200;
	private static void createGUI()
	{


		JFrame frame = new LoginFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		frame.setLocation((width-framewidth)/2,(height-framewidth)/2);
		
		// 设置窗口的其他参数，如窗口大小
		frame.setSize(framewidth, frameheight);
		
		// 显示窗口
		frame.setVisible(true);
		
		
	}

	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				createGUI();
			}
		});


	}
}
