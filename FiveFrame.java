package gomoku;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class FiveFrame extends JFrame {
	public static final int WIDTH = 300;
	public static final int HEIGH = 400;

	private JPanel toolBar;// 工具栏里面有三个按钮
	private JButton startButton, backButton, exitButton;
	private DrawPanel drawPanel;// 棋盘的面板

	private JMenuBar menuBar;
	private JMenu sysMenu;
	private JMenuItem startMenuItem, backMenuItem, exitMenuItem;// 三个菜单
	private MyListener listener;// 设置一个监听对象

	public FiveFrame() {

	}

	public void init() {
		listener = new MyListener();

		this.setTitle("单机版五子棋游戏");

		toolBar = new JPanel();
		startButton = new JButton("开始");
		startButton.addActionListener(listener);// 给每一个按钮加监听事件

		backButton = new JButton("悔棋");
		backButton.addActionListener(listener);

		exitButton = new JButton("退出");
		exitButton.addActionListener(listener);

		drawPanel = new DrawPanel();

		menuBar = new JMenuBar();
		sysMenu = new JMenu("系统");
		startMenuItem = new JMenuItem("开始");
		startMenuItem.addActionListener(listener);// 给每一个按钮加监听事件

		backMenuItem = new JMenuItem("悔棋");
		backMenuItem.addActionListener(listener);

		exitMenuItem = new JMenuItem("退出");
		exitMenuItem.addActionListener(listener);

		this.setJMenuBar(menuBar);// 设置窗口的菜单栏
		menuBar.add(sysMenu);
		sysMenu.add(startMenuItem);
		sysMenu.add(backMenuItem);
		sysMenu.add(exitMenuItem);

		toolBar.add(startButton);
		toolBar.add(backButton);
		toolBar.add(exitButton);

		this.setLayout(new BorderLayout());
		this.add(toolBar, BorderLayout.NORTH);
		this.add(drawPanel, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);// 窗口大小不改变

		// this.setSize(WIDTH,HEIGH);
		// 让窗口出现在屏幕的中间，适当微调
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - 300,
				(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - 300);
		// 要窗口里面的组件是包紧
		pack();
		this.setVisible(true);
	}

	//
	private class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startButton || e.getSource() == startMenuItem) {
				drawPanel.restartGame();// 开始游戏
			}
			if (e.getSource() == backButton || e.getSource() == backMenuItem) {
				drawPanel.goback();// 悔棋
			}
			if (e.getSource() == exitButton || e.getSource() == exitMenuItem) {
				System.exit(0);// 退出
			}
		}
	}
}
