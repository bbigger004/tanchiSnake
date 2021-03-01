package snake;

import javax.swing.JFrame;

public class Msnake {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//添加新窗口并设置窗口位置
		JFrame frame = new JFrame();
		frame.setBounds(10,10,900,720);
		
		//设置窗口不可缩放 false
		frame.setResizable(false);
		
		//设置窗口关闭
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new MPanel());
		//在窗口中添加一个新的画布
		
		frame.setVisible(true);
		//设置窗口可见
		
		
	}

}
