package gomoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener {
	public static final int MARGIN = 30;// 边距
	public static final int GRID_SPAN = 35;// 网格大小为35
	public static final int ROWS = 15;// 横线15条
	public static final int COLS = 15;// 竖线15条

	private int x_index, y_index;// 默认值都为0
	private boolean isBlack = true;// 默认颜色为黑
	private Chess chessList[] = new Chess[(ROWS + 1) * (COLS + 1)];// 定义装棋子的数组
	private int chessCount = 0;// 棋子的个数
	private boolean gameOver = false;// 游戏是否结束的标识

	public DrawPanel() {
		super();
		this.setBackground(Color.pink);
		// this.repaint();
		this.addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 画棋盘
		for (int i = 0; i <= ROWS; i++) {// 画横线
			g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
		}
		for (int i = 0; i <= COLS; i++) {// 画竖线
			g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
		}

		// 画棋子

		for (int i = 0; i < chessCount; i++) {
			int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
			int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
			g.setColor(chessList[i].getColor());
			g.fillOval(xPos - Chess.DIAMETER / 2, yPos - Chess.DIAMETER / 2, Chess.DIAMETER, Chess.DIAMETER);
			// 最后一颗棋子上面画红框
			if (i == chessCount - 1) {
				g.setColor(Color.red);
				g.drawRect(xPos - Chess.DIAMETER / 2, yPos - Chess.DIAMETER / 2, Chess.DIAMETER, Chess.DIAMETER);
			}
		}
	}
	//

	/**
	 * getPreferredSize()重写，是设置当前的组件大小时最佳的大小，自动调用
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(MARGIN * 2 + GRID_SPAN * ROWS, MARGIN * 2 + GRID_SPAN * COLS);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		x_index = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		y_index = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
		System.out.println("(" + x_index + "," + y_index + ")");

		// 先判断是不是一个可用的棋子
		// 1.游戏结束不能下棋子
		if (gameOver) {
			return;
		}
		// 2.棋盘外面不可以下棋子
		if (x_index < 0 || x_index > COLS || y_index < 0 || y_index > ROWS) {
			return;
		}
		// 3.这个位置有棋子存在不可以下棋
		if (findChess(x_index, y_index)) {
			return;
		}
		Chess ch = new Chess(x_index, y_index, isBlack ? Color.black : Color.white);
		chessList[chessCount++] = ch;
		System.out.println("棋子的个数:" + chessCount);
		this.repaint();

		// 每按下一个棋子判断是否赢棋
		if (isWin()) {
			String msg = String.format("恭喜您,%s赢了！", isBlack ? "黑棋" : "白棋");
			JOptionPane.showMessageDialog(this, msg);
			gameOver = true;
		}

		isBlack = !isBlack;// 初始是黑色给它变为白色

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// 位置上是否有棋子
	private boolean findChess(int x, int y) {
		for (Chess c : chessList) {
			if (c != null && c.getX() == x && c.getY() == y) {
				return true;
			}
		}
		return false;
	}

	// 得到棋盘上的棋子(某个位置是不是有棋子)
	private Chess getChess(int x, int y, Color color) {
		for (Chess c : chessList) {
			if (c != null && c.getX() == x && c.getY() == y && c.getColor() == color) {
				return c;
			}
		}

		return null;
	}

	// 赢棋的方法，需要四个方向上的其中一个满足五子连珠就可以赢棋
	private boolean isWin() {
		// 四个方向的话就是或者关系
		return search1() || search2() || search3() || search4();
	}

	// 第一个方向（西南-东北）
	private boolean search1() {
		// 计数，连续五个
		int continueCount = 1;// 连续棋子的个数，初始值为一
		// 斜向上寻找(x变大,y变小)
		for (int x = x_index + 1, y = y_index - 1; x <= COLS && y >= 0; x++, y--) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 斜向下寻找(x变小，y变大)
		for (int x = x_index - 1, y = y_index + 1; x >= 0 && y < ROWS; x--, y++) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 五子是不是连珠
		if (continueCount >= 5) {
			return true;
		} else {
			continueCount = 1;
		}

		return false;
	}

	// 第二个方向（西北-东南）
	private boolean search2() {
		// 计数，连续五个
		int continueCount = 1;// 连续棋子的个数，初始值为一
		// 斜向上寻找(x变小,y变小)
		for (int x = x_index - 1, y = y_index - 1; x >= 0 && y >= 0; x--, y--) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 斜向下寻找(x变大，y变大)
		for (int x = x_index + 1, y = y_index + 1; x <= COLS && y < ROWS; x++, y++) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 五子是不是连珠
		if (continueCount >= 5) {
			return true;
		} else {
			continueCount = 1;
		}

		return false;
	}

	// 第三个方向（北-南）
	private boolean search3() {
		// 计数，连续五个
		int continueCount = 1;// 连续棋子的个数，初始值为一
		// 向上寻找(x不变,y变小)
		for (int y = y_index - 1; y >= 0; y--) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x_index, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 向下寻找(x不变，y变大)
		for (int y = y_index + 1; y < ROWS; y++) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x_index, y, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 五子是不是连珠
		if (continueCount >= 5) {
			return true;
		} else {
			continueCount = 1;
		}
		return false;
	}

	// 第四个方向（西-东）
	private boolean search4() {
		// 计数，连续五个
		int continueCount = 1;// 连续棋子的个数，初始值为一
		// 向右寻找(x变大,y不变)
		for (int x = x_index + 1; x <= COLS; x++) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y_index, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 向左寻找(x变小，y不变)
		for (int x = x_index - 1; x >= 0; x--) {
			Color c = isBlack ? Color.black : Color.white;
			if (getChess(x, y_index, c) != null) {
				continueCount++;
			} else {
				break;
			}
		}
		// 五子是不是连珠
		if (continueCount >= 5) {
			return true;
		} else {
			continueCount = 1;
		}
		return false;
	}

	// 重新开始游戏
	public void restartGame() {
		// 清除棋子
		for (int i = 0; i < chessList.length; i++) {
			chessList[i] = null;
		}
		// 恢复游戏相关的变量
		isBlack = true;
		gameOver = false;
		chessCount = 0;

		this.repaint();// 重新画棋盘

	}

	// 悔棋的方法
	public void goback() {
		// 棋盘中没有棋子，不能悔棋
		if (chessCount == 0) {
			return;
		}
		// 最后一个棋子置为空，棋子数减1
		chessList[chessCount - 1] = null;// 悔的棋子为空
		chessCount--;// 棋子总数减1

		if (chessCount > 0) {
			x_index = chessList[chessCount - 1].getX();
			y_index = chessList[chessCount - 1].getY();
		}
		isBlack = !isBlack;
		this.repaint();
	}
}
