import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener, WindowListener, KeyListener {
    private Container container;
    private JTextField displayField;//结果显示区
    private JButton button_plus_minus;//+/-
    private JButton button_c;//CE建
    private JButton button_0;//0-9
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton button_6;
    private JButton button_7;
    private JButton button_8;
    private JButton button_9;
    private JButton button_plus;//+号
    private JButton button_minus;//-号
    private JButton button_mult;// *号
    private JButton button_div;// /号
    private JButton button_point;//.号
    private JButton button_equal;//=号
    private JButton button_modulo;//%号
    private double result;//保存计算结果
    private boolean start = true;//判断是否为数字的开始
    private String lastOperator;
    private boolean input_newnum;//判断计算之后再输入

    public Calculator() {//初始化，布局设置，按钮添加事件监听器
        super("Calculator");
        input_newnum = true;
        this.setLocation(600,100);//设置窗口在屏幕起始点的位置
        this.setSize(470,645);//设置窗口长宽
        this.setResizable(true);//支持拉伸窗口
        this.setLayout(new GridLayout(6,1,4,4));
        this.setBackground(new Color(46,47,50));
        displayField = new JTextField(20);
        displayField.setBackground(new Color(46,47,50));
        displayField.setBorder(new EmptyBorder(0,0,0,0));//边框去除
        displayField.setFont(new Font("宋体", Font.PLAIN,40));
        displayField.setForeground(Color.WHITE);//字体颜色
        displayField.setHorizontalAlignment(JTextField.RIGHT);//设置右对齐
        displayField.setText("0");
        this.add(displayField);

        //桌面布局和颜色字体设置
        JPanel m_panel_1 = new JPanel(new GridLayout(1,4,4,4));
        m_panel_1.setBackground(new Color(46,47,50));
        button_c = new JButton("AC");
        setButtonStyle_darkgray(button_c);
        button_plus_minus = new JButton("+/-");
        setButtonStyle_darkgray(button_plus_minus);
        button_modulo = new JButton("%");
        setButtonStyle_darkgray(button_modulo);
        button_div = new JButton("÷");
        setButtonStyle_Orange(button_div);
        m_panel_1.add(button_c);
        m_panel_1.add(button_plus_minus);
        m_panel_1.add(button_modulo);
        m_panel_1.add(button_div);
        this.add(m_panel_1);

        JPanel m_panel_2 = new JPanel(new GridLayout(1,4,4,4));
        m_panel_2.setBackground(new Color(46,47,50));
        button_7 = new JButton("7");
        setButtonStyle_lightgray(button_7);
        button_8 = new JButton("8");
        setButtonStyle_lightgray(button_8);
        button_9 = new JButton("9");
        setButtonStyle_lightgray(button_9);
        button_mult = new JButton("×");
        setButtonStyle_Orange(button_mult);
        m_panel_2.add(button_7);
        m_panel_2.add(button_8);
        m_panel_2.add(button_9);
        m_panel_2.add(button_mult);
        this.add(m_panel_2);

        JPanel m_panel_3 = new JPanel(new GridLayout(1,4,4,4));
        m_panel_3.setBackground(new Color(46,47,50));
        button_4 = new JButton("4");
        setButtonStyle_lightgray(button_4);
        button_5 = new JButton("5");
        setButtonStyle_lightgray(button_5);
        button_6 = new JButton("6");
        setButtonStyle_lightgray(button_6);
        button_minus = new JButton("-");
        setButtonStyle_Orange(button_minus);
        m_panel_3.add(button_4);
        m_panel_3.add(button_5);
        m_panel_3.add(button_6);
        m_panel_3.add(button_minus);
        this.add(m_panel_3);

        JPanel m_panel_4 = new JPanel(new GridLayout(1,4,4,4));
        m_panel_4.setBackground(new Color(46,47,50));
        button_1 = new JButton("1");
        setButtonStyle_lightgray(button_1);
        button_2 = new JButton("2");
        setButtonStyle_lightgray(button_2);
        button_3 = new JButton("3");
        setButtonStyle_lightgray(button_3);
        button_plus = new JButton("+");
        setButtonStyle_Orange(button_plus);
        m_panel_4.add(button_1);
        m_panel_4.add(button_2);
        m_panel_4.add(button_3);
        m_panel_4.add(button_plus);
        this.add(m_panel_4);

        button_0 = new JButton("0");
        setButtonStyle_lightgray(button_0);
        button_point = new JButton(".");
        setButtonStyle_lightgray(button_point);
        button_equal = new JButton("=");
        setButtonStyle_Orange(button_equal);
        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gridbag = new GridBagLayout();
        JPanel m_panel_5 = new JPanel(gridbag);
        m_panel_5.setBackground(new Color(46,47,50));
        c.insets = new Insets(0,0,0,2);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 2;
        c.weighty = 1;
        addButton(button_0, gridbag, c, m_panel_5);//button_0占2格
        c.weightx = 0.5;
        c.insets = new Insets(0,2,0,2);
        addButton(button_point, gridbag, c, m_panel_5);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,2,0,0);
        addButton(button_equal, gridbag, c, m_panel_5);
        this.add(m_panel_5);

        //按钮ActionListener注册
        button_plus_minus.addActionListener(this);
        button_c.addActionListener(this);
        button_0.addActionListener(this);
        button_1.addActionListener(this);
        button_2.addActionListener(this);
        button_3.addActionListener(this);
        button_4.addActionListener(this);
        button_5.addActionListener(this);
        button_6.addActionListener(this);
        button_7.addActionListener(this);
        button_8.addActionListener(this);
        button_9.addActionListener(this);
        button_plus.addActionListener(this);
        button_minus.addActionListener(this);
        button_mult.addActionListener(this);
        button_div.addActionListener(this);
        button_point.addActionListener(this);
        button_equal.addActionListener(this);
        button_modulo.addActionListener(this);

        this.addWindowListener(new WindowAdapter() {//内嵌函数, 退出窗口方法注册
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
        this.addKeyListener(this);
        this.setVisible(true);//显示窗口
    }

    private void setButtonStyle_darkgray(JButton button) {
        button.setBackground(new Color(66,66,66));
        setButtonOtherSettings(button);
    }

    private void setButtonStyle_Orange(JButton button) {
        button.setBackground(new Color(242,162,60));
        setButtonOtherSettings(button);
    }

    private void setButtonStyle_lightgray(JButton button) {
        button.setBackground(new Color(97,97,100));
        setButtonOtherSettings(button);
    }

    private void setButtonOtherSettings(JButton button) {
        button.setForeground(new Color(253,241,225));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font("宋体",Font.PLAIN,25));
    }

    private void addButton(JButton button, GridBagLayout gridbag, GridBagConstraints c, JPanel panel) {
        gridbag.setConstraints(button, c);
        panel.add(button);
    }

    private void calculate(double num) {//计算
        switch (lastOperator) {
            case "+":
                result += num;
                break;
            case "-":
                result -= num;
                break;
            case "×":
                result *= num;
                break;
            case "÷":
                result /= num;
                break;
            case "%":
                result %= num;
                break;
        }
    }

    private void input_0_9(String input) {
        String text = displayField.getText();
        if (start) {
            if (text.equals("0") || input_newnum) {
                displayField.setText(input);
                input_newnum = false;
            }
            else {
                displayField.setText(text + input);
            }
        }
        else if (input_newnum) {//继续计算时第一次输入
            displayField.setText(input);
            input_newnum = false;
        }
        else {
            if (text.equals("0"))
                displayField.setText(input);
            else
                displayField.setText(text + input);
        }
        button_c.setText("C");//修改button_c按钮样式
    }

    private void input_operator(double num, String input) {//输入运算符
        if (start) {//还未开始计算，第一个数字的输入
            result = num;
            displayField.setText("0");
            start = false;
        }
        else {
            calculate(num);
            displayField.setText("" + result);//输出计算结果
            input_newnum = true;
        }
        lastOperator = input;
    }

    //ActionListener和WindowListener接口函数重载
    public void actionPerformed(ActionEvent e) {//按钮单击事件处理
        if (e.getSource().equals(button_0) || e.getSource().equals(button_1) || e.getSource().equals(button_2) ||
                e.getSource().equals(button_3) || e.getSource().equals(button_4) || e.getSource().equals(button_5) ||
                e.getSource().equals(button_6) || e.getSource().equals(button_7) || e.getSource().equals(button_8) ||
                e.getSource().equals(button_9)) {//0-9处理
            String input = e.getActionCommand();//得到按钮上的字符
            input_0_9(input);
        }
        else if (e.getSource().equals(button_point)) {// .
            if (!displayField.getText().contains("."))//只有字符串内不含.才可以输入
                displayField.setText(displayField.getText() + '.');
        }
        else if (e.getSource().equals(button_plus_minus)) {//+/-
            String text = displayField.getText();
            if (!text.equals("")) {//文本不为空
                if (Double.parseDouble(text) < 0)
                    displayField.setText(text.substring(1, text.length()));
                else
                    displayField.setText("-" + text);
            }
        }
        else if (e.getSource().equals(button_c)) {//清零键
            if (displayField.getText().equals("0")) {//清空所有缓存的结果，重新开始
                result = 0;
                start = true;
                input_newnum = true;
            }
            else {//不为0时仅仅清空当前输入的数据，同时button_c显示"AC"
                displayField.setText("0");
                button_c.setText("AC");
            }
        }
        else if (e.getSource().equals(button_plus) || e.getSource().equals(button_minus) ||
                e.getSource().equals(button_mult) || e.getSource().equals(button_div) ||
                e.getSource().equals(button_modulo)) {// + - × ÷ %
            String input = e.getActionCommand();//得到按钮上的字符
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getSource().equals(button_equal)) {// =号，输出结果
            calculate(Double.parseDouble(displayField.getText()));
            displayField.setText("" + result);
            start = true;
            input_newnum = true;
        }
    }
    public void windowClosing(WindowEvent e) {//关闭窗口
        System.exit(0);
    }
    public void windowOpened(WindowEvent e) { }
    public void windowActivated(WindowEvent e) { }
    public void windowDeactivated(WindowEvent e) { }
    public void windowClosed(WindowEvent e) { }
    public void windowIconified(WindowEvent e) { }
    public void windowDeiconified(WindowEvent e) { }

    //KeyListener实现部分
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 61 && e.isShiftDown()) {// +
            String input = "+";
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getKeyCode() == 56 && e.isShiftDown()) {// ×
            String input = "×";
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getKeyCode() == 53 && e.isShiftDown()) {// %
            String input = "%";
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getKeyCode() == 47) {// ÷
            String input = "÷";
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getKeyCode() == 45) {// -
            String input = "-";
            double num = Double.parseDouble(displayField.getText());//将字符串转换为浮点数
            input_operator(num, input);
        }
        else if (e.getKeyCode() >= 48 && e.getKeyCode() <=57) {//得到0-9的键盘码
            String input = "" + (e.getKeyCode() - 48);
            input_0_9(input);
        }
        else if (e.getKeyCode() == 46) {// .
            if (!displayField.getText().contains("."))//只有字符串内不含.才可以输入
                displayField.setText(displayField.getText() + '.');
        }
        else if (e.getKeyCode() == 8) {//退格
            String text = displayField.getText();
            if (!text.equals("0")) {
                if (text.length() == 1)
                    displayField.setText("0");
                else
                    displayField.setText(text.substring(0, text.length() - 1));
            }
        }
        else if (e.getKeyCode() == 61 || e.getKeyCode() == 10) {// =号或者回车，输出结果
            calculate(Double.parseDouble(displayField.getText()));
            displayField.setText("" + result);
            start = true;
            input_newnum = true;
        }
    }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }

    public static void main(String args[]) {
        Calculator m_calculator = new Calculator();
    }
}
