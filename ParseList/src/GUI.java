import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame{
	public String filePath;
	public String fileName;
	
	
	public GUI() {
		JFrame jFrame = new JFrame("周版本生成器 v1.118");
		
		
		jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		flowLayout.setHgap(20);
		flowLayout.setVgap(20);
		
		JPanel jPanel = new JPanel(flowLayout);
		
		JLabel dateLable = new JLabel("周版本日期");
		//dateLable.setBounds(1, 1, 50, 50);
		jPanel.add(dateLable);
		
		JTextField dateText = new JTextField("20191030");
		dateText.setBounds(1, 1, 50, 50);
		jPanel.add(dateText);
		
		jFrame.add(jPanel);
		
		
		JButton fileChooseButton = new JButton("选择文档");
		jPanel.add(fileChooseButton);
		
		fileChooseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File("C:\\Users\\sisi.song"));
				//jFileChooser.setCurrentDirectory(new File("C:\\Users\\zhezheng.wang\\eclipse-workspace\\ParseList"));
				
				int value = jFileChooser.showOpenDialog(null);
				if(value == JFileChooser.APPROVE_OPTION) {
					filePath = jFileChooser.getSelectedFile().getParent();
					fileName = jFileChooser.getSelectedFile().getName();
					JOptionPane.showMessageDialog(null, "已选择：" + filePath + "\\" + fileName);
				}
			}
		});

		
		
		JButton runButton = new JButton("运行");
		runButton.setBounds(1, 1, 50, 50);
		jPanel.add(runButton);
		
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(fileName == null || filePath == null) {
					JOptionPane.showMessageDialog(null, "出错了");
					return;
				}
				
				ParseDocument.Date = dateText.getText();
				ParseDocument.srcFilePath = filePath + "\\" + fileName;

				String tName = fileName.replace(".docx", "");
				
				//System.out.println(ParseDocument.srcFilePath);
				System.out.println(fileName + " " + tName);
				
				ParseDocument.targetFilePath = filePath + "\\" + tName + "-success.docx";
				ParseDocument.errorTargetFilePath = filePath + "\\" + tName + "-Error" + ".docx";
				
				ParseDocument p = new ParseDocument();
				
				p.run();
				
				JOptionPane.showMessageDialog(null, "已经完成");
			}
		});
		jFrame.setBounds(400, 300, 400, 100);
		jFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
	
}
