import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Stadium> staList = new Vector<Stadium>();
	Vector<Performer> perList = new Vector<Performer>(); //��ȷ����С��list
	Vector<Concert> conList = new Vector<Concert>();
	JMenu Menu0 = new JMenu("�ļ�"); // �˵���                                  //������ѡ�Ĳ˵�
	JMenuItem menuOpen = new JMenuItem("�������ļ�");   //�˵���
	JMenuItem menuSave = new JMenuItem("���������ļ�");
	JMenuItem menuChangePwd = new JMenuItem("���Ĺ�������");
	JMenuItem menuExit = new JMenuItem("�˳�ϵͳ");
	JMenu Menu1 = new JMenu("��Ϣά��");
	JMenuItem staMenu = new JMenuItem("������Ϣά��");
	JMenuItem PerMenu = new JMenuItem("������Ϣά��");
	JMenuItem ConMenu = new JMenuItem("�ݳ�����Ϣά��");
	JMenu Menu2 = new JMenu("���ݲ�ѯ");
	JMenuItem conQueryMenu = new JMenuItem("�ݳ�����Ϣ��ѯ");
	JMenuItem priceQueryMenu = new JMenuItem("��Ʊ��Ϣ��ѯ");

	public static void main(String[] args) {
		LoginFrame f = new LoginFrame();
		f.setVisible(true);                         //���ÿɼ�
	}

	Main() {
		super();
		JMenuBar menuBar = new JMenuBar();          // �˵���
		this.setJMenuBar(menuBar);
		staMenu.addActionListener(this);
		staMenu.setEnabled(false);
		Menu1.add(staMenu);
		PerMenu.addActionListener(this);              //��Ӽ�����
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
		Menu2.add(conQueryMenu);                    //��Ӳ�ѯ�Ĳ˵�
		priceQueryMenu.addActionListener(this);
		priceQueryMenu.setEnabled(false);
		Menu2.add(priceQueryMenu);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);

		this.setTitle("�ݳ�����Ʊ����ϵͳ");                //������ĿΪ���ݳ�����Ʊ����ϵͳ��
		this.setResizable(false);//���ñ߿��ܵ���
		this.setSize(700, 320);//���ô�С
		this.setLocationRelativeTo(this.getOwner());//���ô��������ָ�������λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����û��ڴ˴����Ϸ��� ��close��ʱĬ��ִ�еĲ���ʹ�� System exit �����˳�Ӧ�ó���

	}

	void readStaFile() {
		try {
			FileInputStream fis = new FileInputStream("Stadium.txt");//һ���ļ����Ķ���fisָ��txt�ļ�
			InputStreamReader dis = new InputStreamReader(fis);//����һ���ļ�������
			BufferedReader reader = new BufferedReader(dis); //������һ�������������ַ�������
			String s;
			while ((s = reader.readLine()) != null) {//readLine()��ȡһ�����ݺ�����¶���ı���s�� Ȼ�����ж�s�ǲ���Ϊnull���գ�
				Stadium sta = new Stadium();//����һ���³���
				String[] temp = s.split(" ");//
				sta.setStaNo(temp[0]);//���õ�һ�����
				sta.setStaCity(temp[1]);//���õڶ���Ϊ����
				sta.setStaName(temp[2]);//���ó�������
				staList.add(sta);//
			}
			reader.close();//reader�رգ��ͷ��ڴ�
			dis.close();//dis�رգ��ͷ��ڴ�
			fis.close();//fis�رգ��ͷ��ڴ�
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeStaFile() {
		try {
			FileOutputStream fos = new FileOutputStream("Stadium.txt");//ʹ���ֽ���д�ļ�
			OutputStreamWriter dos = new OutputStreamWriter(fos);
			BufferedWriter writer = new BufferedWriter(dos);
			for (int i = 0; i < staList.size(); i++) {
				Stadium sta = (Stadium) staList.get(i);
				writer.write(sta.getStaNo() + " " + sta.getStaCity() + " " + sta.getStaName() + "\n");//д������������
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
			OutputStreamWriter dos = new OutputStreamWriter(fos);//һ��һ���ȡ����
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

	void readConcertFile() {//�������ֻ��ĵ�
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

	void writeConcertFile() {//��������
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

	void showStaPanel() {//��ʾ���ݴ���
		StaPanel staPanel = new StaPanel();
		staPanel.staList = this.staList;
		this.add(staPanel, BorderLayout.CENTER);//���þ���
		staPanel.showStadium(0);
		this.setVisible(true);//���ÿɼ�
	}

	void showPerformerPanel() {//��ʾ���˴���
		PerformerPanel PerformerPanel = new PerformerPanel();
		PerformerPanel.perList = this.perList;
		this.add(PerformerPanel, BorderLayout.CENTER);
		PerformerPanel.showPerformer(0);
		this.setVisible(true);
	}

	void showConcertPanel() {//��ʾ�ݳ����ѯ����
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
		this.setVisible(true);//���ÿɼ�
	}

	void showPriceQueryPanel() {//��ʾ�۸��ѯ����
		PriceQueryPanel priceQPanel = new PriceQueryPanel();
		priceQPanel.staList = this.staList;
		priceQPanel.perList = this.perList;
		priceQPanel.conList = this.conList;
		this.add(priceQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {//��������ʵ������
		if (e.getSource() == staMenu) {
			this.getContentPane().removeAll();//�Ƴ�ȫ������ֹ��������ظ�
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
			this.showPriceQueryPanel();//��ʾ�ݳ���۸񴰿�
		}
		if (e.getSource() == menuOpen) {//�˵���
			this.staList.removeAllElements();
			this.conList.removeAllElements();
			this.perList.removeAllElements();
			this.readStaFile();
			staMenu.setEnabled(true);//��ť�ɰ�
			this.readPerformerFile();
			PerMenu.setEnabled(true);
			this.readConcertFile();
			ConMenu.setEnabled(true);
			menuSave.setEnabled(true);
			conQueryMenu.setEnabled(true);//��ѯ�ݳ���˵�
			priceQueryMenu.setEnabled(true);//��ѯ�۸�˵�
			JOptionPane.showMessageDialog(null,
					"���Ѿ��ɹ������ݣ�\nѧ����Ϣ" + staList.size() + "��\n�γ���Ϣ" + perList.size() + "��\n�ɼ���Ϣ" + conList.size() + "��",
					"��", JOptionPane.INFORMATION_MESSAGE);//��ʾ�Ի���Ϊ��������
		}
		if (e.getSource() == menuSave) {//�˵�����
			this.writeStaFile();
			this.writePerformerFile();
			this.writeConcertFile();
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ��������ݣ�\n������Ϣ" + staList.size() + "��\n������Ϣ" + perList.size()
					+ "��\n�ݳ�����Ʊ��Ϣ" + conList.size() + "��", "����", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource() == menuChangePwd) {
			String s = JOptionPane.showInputDialog(null, "������������", "����Ա�����޸�", JOptionPane.PLAIN_MESSAGE);
			if (s == null)
				return;
			s = s.trim();
			if (s.length() == 0) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "�ݳ�����Ʊ����ϵͳ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String clearText = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";//�����Ӧ��һ��
			String cipherText = "UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			String resultText = "";
			for (int i = 0; i < s.length(); i++) {//��������
				char c = s.charAt(i);
				if (clearText.indexOf("" + c) == -1) {
					JOptionPane.showMessageDialog(null, "�����а����Ƿ��ַ�", "�ݳ�����Ʊ����ϵͳ", JOptionPane.ERROR_MESSAGE);//��ʾ��Ϣ�Ի���Ϊ����������
					return;
				} else {
					resultText += "" + cipherText.charAt(clearText.indexOf("" + c));
				}
			}
			try {//�����쳣���룬����
				FileOutputStream fos = new FileOutputStream("Password.txt");//���������������ĵ��������޸�����
				OutputStreamWriter dos = new OutputStreamWriter(fos);
				BufferedWriter writer = new BufferedWriter(dos);
				writer.write(resultText);//�������ı�
				writer.close();
				dos.close();
				fos.close();//�رգ���ջ���
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ���", "�ݳ�����Ʊ����ϵͳ", JOptionPane.INFORMATION_MESSAGE);//��ʾ��Ϣ�Ի���Ϊ����������
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == menuExit) {//�˳��˵�
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class ConcertPanel extends JPanel implements ActionListener {//�̳�
	private JTextField staNo = new JTextField();  //���
	private JTextField PerformerNo = new JTextField(); // �ݳ������
	private JTextField ConTime = new JTextField(); // �ݳ���ʱ��
	private JTextField ConName = new JTextField(); // �ݳ�������
	private JTextField ConPrice = new JTextField(); // �ݳ���۸�

	Vector<Concert> conList = new Vector<Concert>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "��һ��", "��һ��", "��һ��", "���һ��", "���", "�޸�", "ɾ��" };
	private JButton[] btn = new JButton[btnStr.length];

	ConcertPanel() {//�ݳ��ᴰ��
		this.setLayout(null);
		// ��ʾ���ݱ��
		JLabel lb1 = new JLabel("���ݱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(110, 10, 100, 20);
		this.add(staNo);
		// ��ʾ���˱��
		JLabel lb2 = new JLabel("���˱�ţ�");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		PerformerNo.setBounds(110, 50, 100, 20);
		this.add(PerformerNo);
		// ��ʾ�ݳ���ʱ��
		JLabel lb3 = new JLabel("�ݳ���ʱ�䣺");
		lb3.setBounds(30, 90, 200, 20);
		this.add(lb3);
		ConTime.setBounds(110, 90, 200, 20);
		this.add(ConTime);
		// ��ʾ�ݳ�������
		JLabel lb4 = new JLabel("�ݳ������ƣ�");
		lb4.setBounds(30, 130, 300, 20);
		this.add(lb4);
		ConName.setBounds(110, 130, 300, 20);
		this.add(ConName);
		// ��ʾ�ݳ���Ʊ��
		JLabel lb5 = new JLabel("�ݳ���Ʊ�ۣ�");
		lb5.setBounds(30, 170, 300, 20);
		this.add(lb5);
		ConPrice.setBounds(110, 170, 300, 20);
		this.add(ConPrice);

		for (int i = 0; i < btn.length; i++) {//���ð�ť�Ĵ�С����Ӽ�����
			btn[i] = new JButton(btnStr[i]);
			btn[i].setBounds(30 + i * 90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showConcert(int offset) {//�õ��ݳ��������ĵ���Ϣ
		Concert Concert = (Concert) conList.get(offset);
		this.staNo.setText(Concert.getStaNo());
		this.PerformerNo.setText("" + Concert.getPerformerNo());
		this.ConTime.setText("" + Concert.getConTime());
		this.ConName.setText("" + Concert.getConName());
		this.ConPrice.setText("" + Concert.getConPrice());
	}

	public void actionPerformed(ActionEvent e) {
		count = this.conList.size();
		if (e.getSource() == this.btn[0]) {//��һ����ť��ʾ����
			this.showConcert(0);
			current = 0;
		}
		if (e.getSource() == this.btn[1] && current > 0) {//��һ����ť
			this.showConcert(current - 1);
			current = current - 1;
		}
		if (e.getSource() == this.btn[2] && current < count - 1) {//��һ����ť
			this.showConcert(current + 1);
			current = current + 1;
		}
		if (e.getSource() == this.btn[3]) {//���һ����ť
			this.showConcert(count - 1);
			current = count - 1;
		}
		if (e.getSource() == this.btn[4]) {//��Ӱ�ť
			if (this.inserting == 0) {//�ı����Զ����
				this.staNo.setText("");
				this.PerformerNo.setText("");
				this.ConTime.setText("");
				this.ConName.setText("");
				this.ConPrice.setText("");
				btn[4].setText("����");//��ԭ��Ӱ�ť�ĳɱ���
				btn[5].setText("ȡ��");//��ԭ�޸İ�ť��Ϊȡ����ť
				this.inserting = 1;
			} else {
				Concert Concert = new Concert();//�������
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
				conList.add(Concert);
				count++;
				current = count - 1;
				btn[4].setText("���");
				this.inserting = 0;
			}
			for (int i = 0; i < btn.length; i++) {
				if (i == 4 || i == 5)
					continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
		}
		if (e.getSource() == this.btn[5]) {//ȡ����ť
			if (this.inserting == 0) {
				Concert Concert = (Concert) conList.get(current);
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
			} else {
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for (int i = 0; i < btn.length; i++) {
					if (i == 4 || i == 5)
						continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting = 0;
				this.showConcert(current);
			}
		}
		if (e.getSource() == this.btn[6]) {//ɾ����ť
			if (count == 0)
				return;
			conList.remove(current);//ͨ��remove����ţ�����ʽ����ɾ����֮�����Ļ������α�С��Ҳ����˵������������ģ�
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
class StaPanel extends JPanel implements ActionListener {//�ೡ�ݴ��ڼ̳�
	private JTextField staNo = new JTextField(); // ���ݱ��
	private JTextField staCity = new JTextField(); //���� ����
	private JTextField staName = new JTextField(); //���� ����
	Vector<Stadium> staList = new Vector<Stadium>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "��һ��", "��һ��", "��һ��", "���һ��", "���", "�޸�", "ɾ��" };
	private JButton[] btn = new JButton[btnStr.length];

	StaPanel() {//���ݴ���
		this.setLayout(null);//���ñ߿�
		// ��ʾ���
		JLabel lb1 = new JLabel("���ݱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(100, 10, 100, 20);
		this.add(staNo);
		// ��ʾ����
		JLabel lb2 = new JLabel("���У�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		staCity.setBounds(100, 60, 100, 20);
		this.add(staCity);
		// ��ʾ����
		JLabel lb3 = new JLabel("�������ƣ�");
		lb3.setBounds(30, 110, 300, 20);
		this.add(lb3);
		staName.setBounds(100, 110, 300, 20);
		this.add(staName);

		for (int i = 0; i < btn.length; i++) {//���ð�ť��λ��
			btn[i] = new JButton(btnStr[i]);
			btn[i].setBounds(30 + i * 90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showStadium(int offset) {//��ʾ����
		Stadium sta = (Stadium) staList.get(offset);
		this.staNo.setText(sta.getStaNo());
		this.staCity.setText(sta.getStaCity());
		this.staName.setText(sta.getStaName());

	}

	public void actionPerformed(ActionEvent e) {//��ť���㷨����һ����ť
		count = this.staList.size();
		if (e.getSource() == this.btn[0]) {
			this.showStadium(0);
			current = 0;
		}
		if (e.getSource() == this.btn[1] && current > 0) {//�ڶ�����ť
			this.showStadium(current - 1);
			current = current - 1;
		}
		if (e.getSource() == this.btn[2] && current < count - 1) {//��������ť
			this.showStadium(current + 1);
			current = current + 1;
		}
		if (e.getSource() == this.btn[3]) {//���ĸ���ť
			this.showStadium(count - 1);
			current = count - 1;
		}
		if (e.getSource() == this.btn[4]) {//�������ť������Ϊ�գ�����ť�������Ϊ�����ȡ��
			if (this.inserting == 0) {
				this.staNo.setText("");
				this.staCity.setText("");
				this.staName.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting = 1;
			} else {
				Stadium sta = new Stadium();
				sta.setStaNo(this.staNo.getText().trim());
				sta.setStaCity(this.staCity.getText().trim());
				sta.setStaName(this.staName.getText().trim());
				staList.add(sta);
				count++;
				current = count - 1;
				btn[4].setText("���");
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
				btn[4].setText("���");
				btn[5].setText("�޸�");
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
class PerformerPanel extends JPanel implements ActionListener {//������̳мӼ�����
	private JTextField PerformerNo = new JTextField(); // ѧ��
	private JTextField PerformerName = new JTextField(); // �γ�����
	Vector<Performer> perList = new Vector<Performer>();
	int count = 0, current = 0, inserting = 0;

	private String[] btnStr = { "��һ��", "��һ��", "��һ��", "���һ��", "���", "�޸�", "ɾ��" };
	private JButton[] btn = new JButton[btnStr.length];

	PerformerPanel() {//���˴���
		this.setLayout(null);
		// ��ʾ���˱��
		JLabel lb1 = new JLabel("���˱�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		PerformerNo.setBounds(100, 10, 100, 20);
		this.add(PerformerNo);
		// ��ʾ��������
		JLabel lb2 = new JLabel("����������");
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

	void showPerformer(int offset) {//��ʾ����
		Performer Performer = (Performer) perList.get(offset);
		this.PerformerNo.setText(Performer.getPerformerNo());
		this.PerformerName.setText(Performer.getPerformerName());
	}

	public void actionPerformed(ActionEvent e) {//������в���
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
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting = 1;
			} else {
				Performer Performer = new Performer();
				Performer.setPerformerNo(this.PerformerNo.getText().trim());
				Performer.setPerformerName(this.PerformerName.getText().trim());
				perList.add(Performer);
				count++;
				current = count - 1;
				btn[4].setText("���");
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
				btn[4].setText("���");
				btn[5].setText("�޸�");
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
		searchBy.addItem("���������Ʋ�ѯ");
		searchBy.addItem("�����ݱ�Ų�ѯ");
		//searchBy.addItem("�����˲�ѯ");
		searchBy.addItem("�����в�ѯ");
		// searchBy.addItem("���ݳ������Ʋ�ѯ");
		subPanel.add(searchBy);
		subPanel.add(keyword);
		JButton btn = new JButton("��ѯ");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		String str="���ݱ��\t����\t��������\t\t����\t�ݳ���ʱ��\t\t\t�ݳ�������\t\t\t\tƱ��\n";
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
		subPanel.add(new JLabel("Ʊ�ۣ�"));
		subPanel.add(keyword);
		JButton btn = new JButton("��ѯ");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (keyword.getText().length() != 7) {
			JOptionPane.showMessageDialog(null, "������Ʊ��", "�ݳ�����Ʊ����ϵͳ", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String str="���ݱ��\t����\t��������\t\t����\t�ݳ���ʱ��\t\t\t�ݳ�������\t\t\t\tƱ��\n";
		Concert course = null;
		Stadium score = null;
		

		this.result.setText(str);
	}
}

class Stadium {//������
	private String staNo; // ���
	private String staCity; // ����
	private String staName; // �������� 

	Concert Concert; // �ݳ���

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

class Performer {//���ر��������Ϣ
	private String PerformerNo; // �ݳ�����
	private String PerformerName; // �ݳ�������

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

class Concert {//�ݳ�����
	private String staNo; // ���ݱ��
	private String PerformerNo; //�ݳ�����
	private String conTime;//�ݳ���ʱ��
	private String conName;//�ݳ�������
	private String conPrice;//�ݳ���۸�

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {//�������ֻ������Ϣ
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
class LoginFrame extends JFrame implements ActionListener{//�̳е�½
	JTextField username=new JTextField(10);//�û���
	JPasswordField pwd=new JPasswordField(10);//����
	LoginFrame(){//��½
		this.setTitle("ϵͳ��¼");//������ĿΪϵͳ��½
		this.setResizable(false);//���ò��ı�ߴ�
		this.setSize(250,130);//���ô�С
		this.setLocationRelativeTo(this.getOwner());//���ô��������ָ����λ�á� 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رհ�ť���˳�����
		
		JLabel lb1=new JLabel("�û�����");//��ǩ1Ϊ�û���
		JLabel lb2=new JLabel("�ܡ��룺");//��ǩ2Ϊ����
		JButton btn=new JButton("��¼");//��ťΪ��½��ť
		btn.addActionListener(this);//��Ӽ�����
		Container c=this.getContentPane();//�ڰѻ�ȡ��ֵ����������
		c.setLayout(null);//��ʾContainerҪʹ�þ��Բ���
		lb1.setBounds(30, 10, 100, 20);//���ñ߽�
		c.add(lb1);//��Ӱ�ť
		username.setBounds(120, 10, 100, 20);
		username.addActionListener(this);
		c.add(username);//����û���
		lb2.setBounds(30, 40, 100, 20);
		c.add(lb2);//��Ӱ�ť
		pwd.setBounds(120, 40, 100, 20);
		pwd.addActionListener(this);
		c.add(pwd);//�������
		btn.setBounds(80, 70, 90, 20);
		c.add(btn);
		this.setVisible(true);//���ÿɼ�
		this.username.requestFocus();//�������û���������ڵ�����������Ŀؼ���
	}
	
	public void actionPerformed(ActionEvent e) {//��Ӽ�����
		if(e.getSource()==this.username){
			this.pwd.requestFocus();//���������뽹����ڵ�����������Ŀؼ���
			return;
		}
		try {//�쳣����
			FileInputStream fis;
			fis = new FileInputStream("Password.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			if((s=reader.readLine())!=null){//�ж�������ȷ���
				if(username.getText().trim().equals("admin")){//�ַ�ת�� 
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
						JOptionPane.showMessageDialog(null, "����д�����벻��ȷ", "�û���¼", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "����д���û�������ȷ", "�û���¼", JOptionPane.WARNING_MESSAGE);
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
