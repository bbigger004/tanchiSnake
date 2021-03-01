package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MPanel extends JPanel implements KeyListener, ActionListener{
	//将所有要用的图片导入
	ImageIcon  title = new ImageIcon("title.jpg");
	ImageIcon  body = new ImageIcon("body.png");
	ImageIcon  up = new ImageIcon("up.png");
	ImageIcon  down = new ImageIcon("down.png");
	ImageIcon  right = new ImageIcon("right.png");
	ImageIcon  left = new ImageIcon("left.png");
	ImageIcon  food = new ImageIcon("food.png");
	
	//定义蛇的长度
	int len =3;
	//定义分数
	int score = 0;
	//定义蛇的身体坐标数组
	int [] snakex = new int [750];
	int [] snakey = new int [750];
	//定义用于判断添加不同朝向蛇头的字符串
	String fx ="R";//方向 ：R L U D 
	//记录目前是否为启动状态
	boolean isStarted = false;
	//记录蛇头和身体是否重叠以判断游戏结束
	boolean isFailed = false;
	Timer timer = new Timer(100,this);
	//定义食物变量
	int foodx;
	int foody;
	Random rand = new  Random();
	
	public MPanel() {
		initSnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		//继承超类中的所有方法
		super.paintComponent(g);
		//设置画布的背景颜色
		this.setBackground(Color.white);
		//将标题图片添加进画布
		title.paintIcon(this,g,25,11);
		//填充色彩块儿
		g.fillRect(25, 75, 850, 600);
		
		//显示分数长度
		g.setColor(Color.white);
		g.drawString("Len: "+len, 750, 35);
		g.drawString("SCore: "+score, 750, 50);

		//通过判断字符串fx的值来添加不同朝向的蛇头
		if(fx=="R") {
			right.paintIcon(this,g,snakex[0],snakey[0]);
		}else if(fx=="L") {
			left.paintIcon(this,g,snakex[0],snakey[0]);
		}else if(fx=="U") {
			up.paintIcon(this,g,snakex[0],snakey[0]);
		}else if(fx=="D") {
			down.paintIcon(this,g,snakex[0],snakey[0]);
		}
		
		//通过for循环添加蛇的身体进入画布
		for(int i =1;i<len ;i++) {
			body.paintIcon(this,g,snakex[i],snakey[i]);
		}
		
		food.paintIcon(this, g, foodx, foody);
		
		if(isStarted == false) {//判断是否出现开始提示
		//重新设置画笔的颜色
		g.setColor(Color.white);
		//设置字体
		g.setFont(new Font("Times new Roman", Font.BOLD, 40));
		//放上开始提示
		g.drawString("Press Space to Star", 300, 300);
		}
		
		if(isFailed == true) {//判断是否失败
			//重新设置画笔的颜色
			g.setColor(Color.RED);
			//设置字体
			g.setFont(new Font("Times new Roman", Font.BOLD, 40));
			//放上开始提示
			g.drawString("Failed: Press Space to Restar", 200, 300);
		}
		
	}
	
	public void initSnake() {
		//初始化蛇的方法
		score=0;
		fx ="R";
		len = 3;
		snakex[0] = 100;
		snakey[0] =	100;
		snakex[1] =	75;
		snakey[1] = 100;
		snakex[2] = 50;
		snakey[2] = 100;
		//初始化食物
		foodx = 25 + 25 * rand.nextInt(34);
		foody = 75 + 25 * rand.nextInt(24);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if (isFailed) {
				isFailed = false;
				initSnake();
			}else {
				isStarted  = !isStarted;
			}
			repaint();
		}else if (keyCode == KeyEvent.VK_LEFT) {
			fx = "L" ;
		}else if (keyCode == KeyEvent.VK_RIGHT) {
			fx = "R" ;
		}else if (keyCode == KeyEvent.VK_UP) {
			fx = "U" ;
		}else if (keyCode == KeyEvent.VK_DOWN) {
			fx = "D" ;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (isStarted &&!isFailed) {
			for(int i = len-1;i>0;i--) {
				snakex[i] = snakex[i-1];
				snakey[i] = snakey[i-1];
			}
			if(fx== "R") {
				snakex[0] = snakex[0] +25;
				if(snakex[0]>850) snakex[0] = 25;
			}else if (fx == "L") {
				snakex[0] = snakex[0] -25;
				if(snakex[0]<25) snakex[0] = 850;
			}else if (fx == "U") {
				snakey[0] = snakey[0] -25;
				if(snakey[0]<75) snakey[0] = 650;
			}else if (fx == "D") {
				snakey[0] = snakey[0] +25;
				if(snakey[0]>650) snakey[0] = 75;
			}
			
			if (snakex[0]==foodx &&snakey[0]==foody) {
				len++;
				score +=10;
				foodx = 25 + 25 * rand.nextInt(34);
				foody = 75 + 25 * rand.nextInt(24);
			}
			
			for (int i = 1; i < len; i++) {
				if (snakex[i]==snakex[0]&&snakey[i]==snakey[0]	) {
					isFailed = true;
				}
				
			}	
			
			repaint();
		}
		
		
		timer.start();
	}
}
