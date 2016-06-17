import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Stadium> staList = new Vector<Stadium>();
	Vector<Performer> perList = new Vector<Performer>(); //不确定大小的list
	Vector<Concert> conList = new Vector<Concert>();
	JMenu Menu0 = new JMenu("文件"); // 菜单类                                  //下拉可选的菜单
	JMenuItem menuOpen = new JMenuItem("打开数据文件");   //菜单条
	JMenuItem menuSave = new JMenuItem("保存数据文件");
	JMenuItem menuChangePwd = new JMenuItem("更改管理密码");
	JMenuItem menuExit = new JMenuItem("退出系统");
	JMenu Menu1 = new JMenu("信息维护");
	JMenuItem staMenu = new JMenuItem("场馆信息维护");
	JMenuItem PerMenu = new JMenuItem("艺人信息维护");
	JMenuItem ConMenu = new JMenuItem("演唱会信息维护");
	JMenu Menu2 = new JMenu("数据查询");
	JMenuItem conQueryMenu = new JMenuItem("演唱会信息查询");
	JMenuItem priceQueryMenu = new JMenuItem("门票信息查询");

	public static void main(String[] args) {
		LoginFrame f = new LoginFrame();
		f.setVisible(true);                         //设置可见
	}

	Main() {
		super();
		JMenuBar menuBar = new JMenuBar();          // 菜单栏
		this.setJMenuBar(menuBar);
		staMenu.addActionListener(this);
		staMenu.setEnabled(false);
		Menu1.add(staMenu);
		PerMenu.addActionListener(this);              //添加监听器
		PerMenu.setEnabled(false);
		Menu1.add(PerMenu);
		ConMenu.addActionListener(this);
		ConMenu.setEnabled(false);
		Menu1.add(ConMenu);
		menuOpen.addActionListener(this);
		Menu0.add(menuOpen);
		menuSave.addActionListener(this);
		menuSave.setEnabled(false);
		Menu0.add(menuSave);
		Menu0.addSeparator();
		Menu0.add(menuChangePwd);
		menuChangePwd.addActionListener(this);
		Menu0.addSeparator();
		menuExit.addActionListener(this);
		Menu0.add(menuExit);
		conQueryMenu.addActionListener(this);
		conQueryMenu.setEnabled(false);
		Menu2.add(conQueryMenu);                    //添加查询的菜单
		priceQueryMenu.addActionListener(this);
		priceQueryMenu.setEnabled(false);
		Menu2.add(priceQueryMenu);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);

		this.setTitle("演唱会门票管理系统");                //设置题目为“演唱会门票管理系统”
		this.setResizable(false);//设置边框不能调整
		this.setSize(700, 320);//设置大小
		this.setLocationRelativeTo(this.getOwner());//设置窗口相对于指定组件的位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置用户在此窗体上发起 “close”时默认执行的操作使用 System exit 方法退出应用程序

	}

	void readStaFile() {
		try {
			FileInputStream fis = new FileInputStream("Stadium.txt");//一个文件流的对象fis指向txt文件
			InputStreamReader dis = new InputStreamReader(fis);//建立一个文件输入流
			BufferedReader reader = new BufferedReader(dis); //建立了一个带缓冲区的字符输入流
			String s;
			while ((s = reader.readLine()) != null) {//readLine()读取一行数据后放入新定义的变量s中 然后再判断s是不是为null（空）
				Stadium sta = new Stadium();//键入一个新场地
				String[] temp = s.split(" ");//
				sta.setStaNo(temp[0]);//设置第一个编号
				sta.setStaCity(temp[1]);//设置第二个为城市
				sta.setStaName(temp[2]);//设置场地名称
				staList.add(sta);//
			}
			reader.close();//reader关闭，释放内存
			dis.close();//dis关闭，释放内存
			fis.close();//fis关闭，释放内存
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeStaFile() {
		try {
			FileOutputStream fos = new FileOutputStream("Stadium.txt");//使用字节流写文件
			OutputStreamWriter dos = new OutputStreamWriter(fos);
			BufferedWriter writer = new BufferedWriter(dos);
			for (int i = 0; i < staList.size(); i++) {
				Stadium sta = (Stadium) staList.get(i);
				writer.write(sta.getStaNo() + " " + sta.getStaCity() + " " + sta.getStaName() + "\n");//写入括号内内容
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readPerformerFile() {
		try {
			FileInputStream fis = new FileInputStream("Performer.txt");
			InputStreamReader dis = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(dis);
			String s;
			while ((s = reader.readLine()) != null) {
				Performer Performer = new Performer();
				String[] temp = s.split(" ");
				Performer.setPerformerNo(temp[0]);
				Performer.setPerformerName(temp[1]);
				perList.add(Performer);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writePerformerFile() {
		try {
			FileOutputStream fos = new FileOutputStream("Performer.txt");
			OutputStreamWriter dos = new OutputStreamWriter(fos);//一点一点读取数据
			BufferedWriter writer = new BufferedWriter(dos);
			for (int i = 0; i < perList.size(); i++) {
				Performer Performer = (Performer) perList.get(i);
				writer.write(Performer.getPerformerNo() + " " + Performer.getPerformerName() + "\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readConcertFile() {//导入音乐会文档
		try {
			FileInputStream fis;
			fis = new FileInputStream("Concert.txt");
			InputStreamReader dis = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(dis);
			String s;
			while ((s = reader.readLine()) != null) {
				Concert Concert = new Concert();
				String[] temp = s.split(" ");
				Concert.setStaNo(temp[0]);
				Concert.setPerformerNo(temp[1]);
				Concert.setConTime(temp[2]);
				Concert.setConName(temp[3]);
				Concert.setConPrice(temp[4]);
				conList.add(Concert);

			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeConcertFile() {//保存输入
		try {
			FileOutputStream fos = new FileOutputStream("Concert.txt");
			OutputStreamWriter dos = new OutputStreamWriter(fos);
			BufferedWriter writer = new BufferedWriter(dos);
			for (int i = 0; i < conList.size(); i++) {
				Concert Concert = (Concert) conList.get(i);
				writer.write(Concert.getStaNo() + " " + Concert.getPerformerNo() + " " + Concert.getConTime() + " "
						+ Concert.getConName() + " " + Concert.getConPrice() + "\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void showStaPanel() {//显示场馆窗口
		StaPanel staPanel = new StaPanel();
		staPanel.staList = this.staList;
		this.add(staPanel, BorderLayout.CENTER);//设置居中
		staPanel.showStadium(0);
		this.setVisible(true);//设置可见
	}

	void showPerformerPanel() {//显示艺人窗口
		PerformerPanel PerformerPanel = new PerformerPanel();
		PerformerPanel.perList = this.perList;
		this.add(PerformerPanel, BorderLayout.CENTER);
		PerformerPanel.showPerformer(0);
		this.setVisible(true);
	}

	void showConcertPanel() {//显示演唱会查询窗口
		ConcertPanel ConcertPanel = new ConcertPanel();
		ConcertPanel.conList = this.conList;
		this.add(ConcertPanel, BorderLayout.CENTER);
		ConcertPanel.showConcert(0);
		this.setVisible(true);
	}

	void showConQueryPanel() {
		ConQueryPanel conQPanel = new ConQueryPanel();
		conQPanel.staList = this.staList;
		conQPanel.perList = this.perList;
		conQPanel.conList = this.conList;
		this.add(conQPanel, BorderLayout.CENTER);
		this.setVisible(true);//设置可见
	}

	void showPriceQueryPanel() {//显示价格查询窗口
		PriceQueryPanel priceQPanel = new PriceQueryPanel();
		priceQPanel.staList = this.staList;
		priceQPanel.perList = this.perList;
		priceQPanel.conList = this.conList;
		this.add(priceQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {//监听器的实现内容
		if (e.getSource() == staMenu) {
			this.getContentPane().removeAll();//移除全部，防止面板内容重复
			this.showStaPanel();
		}
		if (e.getSource() == PerMenu) {
			this.getContentPane().removeAll();
			this.showPerformerPanel();
		}
		if (e.getSource() == ConMenu) {
			this.getContentPane().removeAll();
			this.showConcertPanel();
		}
		if (e.getSource() == conQueryMenu) {
			this.getContentPane().removeAll();
			this.showConQueryPanel();
		}
		if (e.getSource() == priceQueryMenu) {
			this.getContentPane().removeAll();
			this.showPriceQueryPanel();//显示演唱会价格窗口
		}
		if (e.getSource() == menuOpen) {//菜单打开
			this.staList.removeAllElements();
			this.conList.removeAllElements();
			this.perList.removeAllElements();
			this.readStaFile();
			staMenu.setEnabled(true);//按钮可按
			this.readPerformerFile();
			PerMenu.setEnabled(true);
			this.readConcertFile();
			ConMenu.setEnabled(true);
			menuSave.setEnabled(true);
			conQueryMenu.setEnabled(true);//查询演唱会菜单
			priceQueryMenu.setEnabled(true);//查询价格菜单
			JOptionPane.showMessageDialog(null,
					"您已经成功打开数据：\n学生信息" + staList.size() + "条\n课程信息" + perList.size() + "条\n成绩信息" + conList.size() + "条",
					"打开", JOptionPane.INFORMATION_MESSAGE);//显示对话框为括号内容
		}
		if (e.getSource() == menuSave) {//菜单保存
			this.writeStaFile();
			this.writePerformerFile();
			this.writeConcertFile();
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n场馆信息" + staList.size() + "条\n艺人信息" + perList.size()
					+ "条\n演唱会门票信息" + conList.size() + "条", "保存", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource() == menuChangePwd) {
			String s = JOptionPane.showInputDialog(null, "请输入新密码", "管理员密码修改", JOptionPane.PLAIN_MESSAGE);
			if (s == null)
				return;
			s = s.trim();
			if (s.length() == 0) {
				JOptionPane.showMessageDialog(null, "密码不能为空！", "演唱会门票管理系统", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String clearText = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//密码对应下一行
			String cipherText = "UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			String resultText = "";
			for (int i = 0; i < s.length(); i++) {//密码运算
				char c = s.charAt(i);
				if (clearText.indexOf("" + c) == -1) {
					JOptionPane.showMessageDialog(null, "密码中包含非法字符", "演唱会门票管理系统", JOptionPane.ERROR_MESSAGE);//显示消息对话框为括号中内容
					return;
				} else {
					resultText += "" + cipherText.charAt(clearText.indexOf("" + c));
				}
			}
			try {//捕获异常代码，尝试
				FileOutputStream fos = new FileOutputStream("Password.txt");//输入流导入密码文档，设置修改密码
				OutputStreamWriter dos = new OutputStreamWriter(fos);
				BufferedWriter writer = new BufferedWriter(dos);
				writer.write(resultText);//保存结果文本
				writer.close();
				dos.close();
				fos.close();//关闭，清空缓存
				JOptionPane.showMessageDialog(null, "密码修改成功！", "演唱会门票管理系统", JOptionPane.INFORMATION_MESSAGE);//显示消息对话框为括号中内容
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == menuExit) {//退出菜单
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class ConcertPanel extends JPanel implements ActionListener {//继承
	private JTextField staNo = new JTextField();  //编号
	private JTextField PerformerNo = new JTextField(); // 演唱会代号
	private JTextField ConTime = new JTextField(); // 演唱会时间
	private JTextField ConName = new JTextField(); // 演唱会名称
	private JTextField ConPrice = new JTextField(); // 演唱会价格

	Vector<Concert> conList = new Vector<Concert>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "第一个", "上一个", "下一个", "最后一个", "添加", "修改", "删除" };
	private JButton[] btn = new JButton[btnStr.length];

	ConcertPanel() {//演唱会窗口
		this.setLayout(null);
		// 显示场馆编号
		JLabel lb1 = new JLabel("场馆编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(110, 10, 100, 20);
		this.add(staNo);
		// 显示艺人编号
		JLabel lb2 = new JLabel("艺人编号：");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		PerformerNo.setBounds(110, 50, 100, 20);
		this.add(PerformerNo);
		// 显示演唱会时间
		JLabel lb3 = new JLabel("演唱会时间：");
		lb3.setBounds(30, 90, 200, 20);
		this.add(lb3);
		ConTime.setBounds(110, 90, 200, 20);
		this.add(ConTime);
		// 显示演唱会名称
		JLabel lb4 = new JLabel("演唱会名称：");
		lb4.setBounds(30, 130, 300, 20);
		this.add(lb4);
		ConName.setBounds(110, 130, 300, 20);
		this.add(ConName);
		// 显示演唱会票价
		JLabel lb5 = new JLabel("演唱会票价：");
		lb5.setBounds(30, 170, 300, 20);
		this.add(lb5);
		ConPrice.setBounds(110, 170, 300, 20);
		this.add(ConPrice);

		for (int i = 0; i < btn.length; i++) {//设置按钮的大小，添加监听器
			btn[i] = new JButton(btnStr[i]);
			btn[i].setBounds(30 + i * 90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showConcert(int offset) {//得到演唱会的相关文档信息
		Concert Concert = (Concert) conList.get(offset);
		this.staNo.setText(Concert.getStaNo());
		this.PerformerNo.setText("" + Concert.getPerformerNo());
		this.ConTime.setText("" + Concert.getConTime());
		this.ConName.setText("" + Concert.getConName());
		this.ConPrice.setText("" + Concert.getConPrice());
	}

	public void actionPerformed(ActionEvent e) {
		count = this.conList.size();
		if (e.getSource() == this.btn[0]) {//第一个按钮显示数据
			this.showConcert(0);
			current = 0;
		}
		if (e.getSource() == this.btn[1] && current > 0) {//上一个按钮
			this.showConcert(current - 1);
			current = current - 1;
		}
		if (e.getSource() == this.btn[2] && current < count - 1) {//下一个按钮
			this.showConcert(current + 1);
			current = current + 1;
		}
		if (e.getSource() == this.btn[3]) {//最后一个按钮
			this.showConcert(count - 1);
			current = count - 1;
		}
		if (e.getSource() == this.btn[4]) {//添加按钮
			if (this.inserting == 0) {//文本框自动清空
				this.staNo.setText("");
				this.PerformerNo.setText("");
				this.ConTime.setText("");
				this.ConName.setText("");
				this.ConPrice.setText("");
				btn[4].setText("保存");//将原添加按钮改成保存
				btn[5].setText("取消");//将原修改按钮改为取消按钮
				this.inserting = 1;
			} else {
				Concert Concert = new Concert();//添加数据
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
				conList.add(Concert);
				count++;
				current = count - 1;
				btn[4].setText("添加");
				this.inserting = 0;
			}
			for (int i = 0; i < btn.length; i++) {
				if (i == 4 || i == 5)
					continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
		}
		if (e.getSource() == this.btn[5]) {//取消按钮
			if (this.inserting == 0) {
				Concert Concert = (Concert) conList.get(current);
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
			} else {
				btn[4].setText("添加");
				btn[5].setText("修改");
				for (int i = 0; i < btn.length; i++) {
					if (i == 4 || i == 5)
						continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting = 0;
				this.showConcert(current);
			}
		}
		if (e.getSource() == this.btn[6]) {//删除按钮
			if (count == 0)
				return;
			conList.remove(current);//通过remove（编号）的形式进行删除，之后后面的会编号依次变小（也就是说编号总是连续的）
			count--;
			if (count == 0) {
				this.staNo.setText("");
				this.PerformerNo.setText("");
				this.ConTime.setText("");
				this.ConName.setText("");
				this.ConPrice.setText("");
			} else {
				if (current > count - 1) {
					this.showConcert(current - 1);
					current = current - 1;
				} else
					this.showConcert(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class StaPanel extends JPanel implements ActionListener {//类场馆窗口继承
	private JTextField staNo = new JTextField(); // 场馆编号
	private JTextField staCity = new JTextField(); //场馆 城市
	private JTextField staName = new JTextField(); //场馆 名称
	Vector<Stadium> staList = new Vector<Stadium>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "第一个", "上一个", "下一个", "最后一个", "添加", "修改", "删除" };
	private JButton[] btn = new JButton[btnStr.length];

	StaPanel() {//场馆窗口
		this.setLayout(null);//设置边框
		// 显示编号
		JLabel lb1 = new JLabel("场馆编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(100, 10, 100, 20);
		this.add(staNo);
		// 显示城市
		JLabel lb2 = new JLabel("城市：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		staCity.setBounds(100, 60, 100, 20);
		this.add(staCity);
		// 显示名称
		JLabel lb3 = new JLabel("场馆名称：");
		lb3.setBounds(30, 110, 300, 20);
		this.add(lb3);
		staName.setBounds(100, 110, 300, 20);
		this.add(staName);

		for (int i = 0; i < btn.length; i++) {//设置按钮的位置
			btn[i] = new JButton(btnStr[i]);
			btn[i].setBounds(30 + i * 90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showStadium(int offset) {//显示场地
		Stadium sta = (Stadium) staList.get(offset);
		this.staNo.setText(sta.getStaNo());
		this.staCity.setText(sta.getStaCity());
		this.staName.setText(sta.getStaName());

	}

	public void actionPerformed(ActionEvent e) {//按钮的算法，第一个按钮
		count = this.staList.size();
		if (e.getSource() == this.btn[0]) {
			this.showStadium(0);
			current = 0;
		}
		if (e.getSource() == this.btn[1] && current > 0) {//第二个按钮
			this.showStadium(current - 1);
			current = current - 1;
		}
		if (e.getSource() == this.btn[2] && current < count - 1) {//第三个按钮
			this.showStadium(current + 1);
			current = current + 1;
		}
		if (e.getSource() == this.btn[3]) {//第四个按钮
			this.showStadium(count - 1);
			current = count - 1;
		}
		if (e.getSource() == this.btn[4]) {//第五个按钮设置项为空，将按钮五和六改为保存和取消
			if (this.inserting == 0) {
				this.staNo.setText("");
				this.staCity.setText("");
				this.staName.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting = 1;
			} else {
				Stadium sta = new Stadium();
				sta.setStaNo(this.staNo.getText().trim());
				sta.setStaCity(this.staCity.getText().trim());
				sta.setStaName(this.staName.getText().trim());
				staList.add(sta);
				count++;
				current = count - 1;
				btn[4].setText("添加");
				this.inserting = 0;
			}
			for (int i = 0; i < btn.length; i++) {
				if (i == 4 || i == 5)
					continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
		}

		if (e.getSource() == this.btn[5]) {
			if (this.inserting == 0) {
				Stadium sta = (Stadium) staList.get(current);
				sta.setStaNo(this.staNo.getText().trim());
				sta.setStaCity(this.staCity.getText().trim());
				sta.setStaName(this.staName.getText().trim());

			} else {
				btn[4].setText("添加");
				btn[5].setText("修改");
				for (int i = 0; i < btn.length; i++) {
					if (i == 4 || i == 5)
						continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting = 0;
				this.showStadium(current);
			}
		}
		if (e.getSource() == this.btn[6]) {
			if (count == 0)
				return;
			staList.remove(current);
			count--;
			if (count == 0) {
				this.staNo.setText("");
				this.staCity.setText("");
				this.staName.setText("");
			} else {
				if (current > count - 1) {
					this.showStadium(current - 1);
					current = current - 1;
				} else
					this.showStadium(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class PerformerPanel extends JPanel implements ActionListener {//艺人类继承加监听器
	private JTextField PerformerNo = new JTextField(); // 学号
	private JTextField PerformerName = new JTextField(); // 课程名称
	Vector<Performer> perList = new Vector<Performer>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "第一个", "上一个", "下一个", "最后一个", "添加", "修改", "删除" };
	private JButton[] btn = new JButton[btnStr.length];

	PerformerPanel() {//艺人窗口
		this.setLayout(null);
		// 显示艺人编号
		JLabel lb1 = new JLabel("艺人编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		PerformerNo.setBounds(100, 10, 100, 20);
		this.add(PerformerNo);
		// 显示艺人姓名
		JLabel lb2 = new JLabel("艺人姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		PerformerName.setBounds(100, 60, 100, 20);
		this.add(PerformerName);

		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(btnStr[i]);
			btn[i].setBounds(30 + i * 90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showPerformer(int offset) {//显示艺人
		Performer Performer = (Performer) perList.get(offset);
		this.PerformerNo.setText(Performer.getPerformerNo());
		this.PerformerName.setText(Performer.getPerformerName());
	}

	public void actionPerformed(ActionEvent e) {//对其进行操作
		count = this.perList.size();
		if (e.getSource() == this.btn[0]) {
			this.showPerformer(0);
			current = 0;
		}
		if (e.getSource() == this.btn[1] && current > 0) {
			this.showPerformer(current - 1);
			current = current - 1;
		}
		if (e.getSource() == this.btn[2] && current < count - 1) {
			this.showPerformer(current + 1);
			current = current + 1;
		}
		if (e.getSource() == this.btn[3]) {
			this.showPerformer(count - 1);
			current = count - 1;
		}
		if (e.getSource() == this.btn[4]) {
			if (this.inserting == 0) {
				this.PerformerNo.setText("");
				this.PerformerName.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting = 1;
			} else {
				Performer Performer = new Performer();
				Performer.setPerformerNo(this.PerformerNo.getText().trim());
				Performer.setPerformerName(this.PerformerName.getText().trim());
				perList.add(Performer);
				count++;
				current = count - 1;
				btn[4].setText("添加");
				this.inserting = 0;
			}
			for (int i = 0; i < btn.length; i++) {
				if (i == 4 || i == 5)
					continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
		}

		if (e.getSource() == this.btn[5]) {
			if (this.inserting == 0) {
				Performer Performer = (Performer) perList.get(current);
				Performer.setPerformerNo(this.PerformerNo.getText().trim());
				Performer.setPerformerName(this.PerformerName.getText().trim());
			} else {
				btn[4].setText("添加");
				btn[5].setText("修改");
				for (int i = 0; i < btn.length; i++) {
					if (i == 4 || i == 5)
						continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting = 0;
				this.showPerformer(current);
			}
		}
		if (e.getSource() == this.btn[6]) {
			if (count == 0)
				return;
			perList.remove(current);
			count--;
			if (count == 0) {
				this.PerformerNo.setText("");
				this.PerformerName.setText("");
			} else {
				if (current > count - 1) {
					this.showPerformer(current - 1);
					current = current - 1;
				} else
					this.showPerformer(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class ConQueryPanel extends JPanel implements ActionListener {
	JComboBox searchBy = new JComboBox();
	JTextField keyword = new JTextField(10);
	JTextArea result = new JTextArea();
	Vector<Stadium> staList = new Vector<Stadium>();
	Vector<Performer> perList = new Vector<Performer>();
	Vector<Concert> conList = new Vector<Concert>();

	ConQueryPanel() {
		this.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout());
		searchBy.addItem("按场馆名称查询");
		searchBy.addItem("按场馆编号查询");
		//searchBy.addItem("按艺人查询");
		searchBy.addItem("按城市查询");
		// searchBy.addItem("按演唱会名称查询");
		subPanel.add(searchBy);
		subPanel.add(keyword);
		JButton btn = new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		String str="场馆编号\t城市\t场馆名称\t\t艺人\t演唱会时间\t\t\t演唱会名称\t\t\t\t票价\n";
		for(int i=0;i<staList.size();i++){
			Stadium sta=(Stadium)this.staList.get(i);
			if(this.searchBy.getSelectedIndex()==0){
				if(sta.getStaName().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+sta.getStaNo()+"\t"+sta.getStaCity()+"\t"+sta.getStaName()+"\t\t";
				}else
					continue;
			}if(this.searchBy.getSelectedIndex()==1){
				if(sta.getStaNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+sta.getStaNo()+"\t"+sta.getStaCity()+"\t"+sta.getStaName()+"\t\t";
				}else
					continue;
			}if(this.searchBy.getSelectedIndex()==3){
				if(sta.getStaCity().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+sta.getStaNo()+"\t"+sta.getStaCity()+"\t"+sta.getStaName()+"\t\t";
				}else
					continue;
			}	
			for(int j=0;j<conList.size();j++){
				Concert con=(Concert)conList.get(j);
				if(con.getStaNo().equals(sta.getStaNo())){
					str=str+this.getPerformerName(con.getPerformerNo())+"\t"+con.getConTime()+"\t\t"+con.getConName()+"\t"+con.getConPrice();
					}
			}
			str+="\n";
		}
		this.result.setText(str);
	}

	String getPerformerName(String performerNo) {
		for (int i = 0; i < perList.size(); i++) {
			Performer per = (Performer) this.perList.get(i);
			if (performerNo.equals(per.getPerformerNo())) {
				return per.getPerformerName();
			}
		}
		return null;
	}
}

@SuppressWarnings("serial")
class PriceQueryPanel extends JPanel implements ActionListener {
	JTextField keyword = new JTextField(10);
	JTextArea result = new JTextArea();
	Vector<Stadium> staList = new Vector<Stadium>();
	Vector<Performer> perList = new Vector<Performer>();
	Vector<Concert> conList = new Vector<Concert>();

	PriceQueryPanel() {
		this.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout());
		subPanel.add(new JLabel("票价："));
		subPanel.add(keyword);
		JButton btn = new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (keyword.getText().length() != 7) {
			JOptionPane.showMessageDialog(null, "请输入票价", "演唱会门票管理系统", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String str="场馆编号\t城市\t场馆名称\t\t艺人\t演唱会时间\t\t\t演唱会名称\t\t\t\t票价\n";
		Concert course = null;
		Stadium score = null;
		

		this.result.setText(str);
	}
}

class Stadium {//场馆类
	private String staNo; // 编号
	private String staCity; // 城市
	private String staName; // 场馆名称 

	Concert Concert; // 演唱会

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}

	public String getStaCity() {
		return staCity;
	}

	public void setStaCity(String staCity) {
		this.staCity = staCity;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

}

class Performer {//返回表演类的信息
	private String PerformerNo; // 演唱会编号
	private String PerformerName; // 演唱会名称

	public String getPerformerNo() {
		return PerformerNo;
	}

	public void setPerformerNo(String PerformerNo) {
		this.PerformerNo = PerformerNo;
	}

	public String getPerformerName() {
		return PerformerName;
	}

	public void setPerformerName(String PerformerName) {
		this.PerformerName = PerformerName;
	}
}

class Concert {//演唱会类
	private String staNo; // 场馆编号
	private String PerformerNo; //演唱会编号
	private String conTime;//演唱会时间
	private String conName;//演唱会名称
	private String conPrice;//演唱会价格

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {//返回音乐会类的信息
		this.staNo = staNo;
	}

	public String getPerformerNo() {
		return PerformerNo;
	}

	public void setPerformerNo(String performerNo) {
		PerformerNo = performerNo;
	}

	public String getConTime() {
		return conTime;
	}

	public void setConTime(String conTime) {
		this.conTime = conTime;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getConPrice() {
		return conPrice;
	}

	public void setConPrice(String conPrice) {
		this.conPrice = conPrice;
	}

}
@SuppressWarnings("serial")
class LoginFrame extends JFrame implements ActionListener{//继承登陆
	JTextField username=new JTextField(10);//用户名
	JPasswordField pwd=new JPasswordField(10);//密码
	LoginFrame(){//登陆
		this.setTitle("系统登录");//设置题目为系统登陆
		this.setResizable(false);//设置不改变尺寸
		this.setSize(250,130);//设置大小
		this.setLocationRelativeTo(this.getOwner());//设置窗口相对于指定的位置。 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭按钮，退出程序
		
		JLabel lb1=new JLabel("用户名：");//标签1为用户名
		JLabel lb2=new JLabel("密　码：");//标签2为密码
		JButton btn=new JButton("登录");//按钮为登陆按钮
		btn.addActionListener(this);//添加监听器
		Container c=this.getContentPane();//在把获取的值，附给容器
		c.setLayout(null);//表示Container要使用绝对布局
		lb1.setBounds(30, 10, 100, 20);//设置边界
		c.add(lb1);//添加按钮
		username.setBounds(120, 10, 100, 20);
		username.addActionListener(this);
		c.add(username);//添加用户名
		lb2.setBounds(30, 40, 100, 20);
		c.add(lb2);//添加按钮
		pwd.setBounds(120, 40, 100, 20);
		pwd.addActionListener(this);
		c.add(pwd);//添加密码
		btn.setBounds(80, 70, 90, 20);
		c.add(btn);
		this.setVisible(true);//设置可见
		this.username.requestFocus();//把输入用户名焦点放在调用这个方法的控件上
	}
	
	public void actionPerformed(ActionEvent e) {//添加监听器
		if(e.getSource()==this.username){
			this.pwd.requestFocus();//把输入密码焦点放在调用这个方法的控件上
			return;
		}
		try {//异常处理
			FileInputStream fis;
			fis = new FileInputStream("Password.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			if((s=reader.readLine())!=null){//判断密码正确与否
				if(username.getText().trim().equals("admin")){//字符转换 
					boolean isCorrect=true;
					char[] ch1=pwd.getPassword();
					char[] ch2=s.toCharArray();
					if(ch1.length==ch2.length){
						for(int i=0;i<ch1.length;i++){
							if(clearText.indexOf(ch1[i])!=cipherText.indexOf(ch2[i])){
								isCorrect=false;
								break;
							}
						}
					}else{
						isCorrect=false;
					}
					if(isCorrect){
						this.setVisible(false);
						this.dispose();
						Main f=new Main();
						f.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "您填写的密码不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "您填写的用户名不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					this.username.requestFocus();
				}
			}
			reader.close();
			dis.close();
			fis.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
