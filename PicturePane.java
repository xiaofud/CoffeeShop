package exercise;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PicturePane extends JPanel {
	private Image src; //图片
	private Image fitPic; //压缩尺寸后的图片
	public PicturePane() {
		addMouseListener(new ChoosePic()); //双击选择图片
	}
	
	@Override //绘图操作
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (fitPic != null){
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(fitPic, 0, 0, this);
		}
		g.dispose();
	}
	
	
	public class ChoosePic extends MouseAdapter{
		public void mouseClicked(MouseEvent e) { 
			if (e.getClickCount() == 2){ //双击
				JFileChooser fileChooser = new JFileChooser();
				int val = fileChooser.showOpenDialog(PicturePane.this);
				if (val == JFileChooser.APPROVE_OPTION){
					try {
						src = ImageIO.read(new FileInputStream(fileChooser.getSelectedFile()));
						double rate  = (double) PicturePane.this.getHeight() / PicturePane.this.getWidth(); // 这里也是要注意 类型转换 不然 分子小于分母的时候 得出的
																											// 的结果会使 0 
						//JOptionPane.showMessageDialog(null, "width = " + PicturePane.this.getHeight());
						fitPic = src.getScaledInstance(PicturePane.this.getWidth(), (int) (PicturePane.this.getWidth() * rate ), Image.SCALE_DEFAULT);
						PicturePane.this.repaint();
					} catch (IOException e1) {
						//图片打开错误
					}
				}
					
			}
		}
	
	}
}
