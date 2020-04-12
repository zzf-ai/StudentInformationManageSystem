package main;

import utils.ZColumnLayout;
import utils.ZPanel;
import utils.ZRowLayout;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    JTextField userField=new JTextField("Administrator");
    JTextField idField=new JTextField();

    JButton loginButton=new JButton("登录");
    JButton exitButton=new JButton("退出");
    public LoginFrame(){
        this.setTitle("管理员登录");
        setSize(500,500);
        ZPanel root=new ZPanel();
        this.setContentPane(root);
        root.padding(10);
        root.setLayout(new BorderLayout());
        root.add(initCenter(),BorderLayout.CENTER);

        loginButton.addActionListener((e)->{

            if(check()){
                //System.out.println("登录成功");
                this.setVisible(false);

                SwingUtilities.invokeLater(new Runnable() {
                    public void run()
                    {
                        createMyFrame();
                    }
                });
            }
        });

        exitButton.addActionListener((e)->{
            System.exit(0);
        });

    }

    //切换到主界面
    private static void createMyFrame()
    {

        int mywidth=500;
        int myheight=400;
        //JFrame frame = new MyFrame("Swing Demo");
        JFrame frame = new MyFrame("学生信息管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        int width=screenSize.width;
        int height=screenSize.height;
        frame.setLocation((width-mywidth)/2,(height-myheight)/2);

        // 设置窗口的其他参数，如窗口大小
        frame.setSize(mywidth, myheight);

        // 显示窗口
        frame.setVisible(true);


    }

    private ZPanel initCenter() {

        //中间部分
        ZPanel center=new ZPanel();
        center.setLayout(new ZColumnLayout(10));

        if(true){
            ZPanel row=new ZPanel();
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("用户名"),"50px");

            userField.setEditable(false);
            row.add(userField,"1w");

            center.add(row);
        }
        if(true){
            ZPanel row=new ZPanel();
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("学号"),"50px");

            row.add(idField,"1w");

            center.add(row);
        }

        if(true){
            ZPanel row=new ZPanel();
            row.setLayout(new ZRowLayout(30));
            row.add(loginButton);
            row.add(exitButton);
            center.add(row);
        }
        return center;
    }

    private boolean check(){
        String id=idField.getText();
        if(id.isEmpty()){
            JOptionPane.showMessageDialog(this,"学号不能为空");
            return false;
        }
        else if(!id.equals("181549347")){
            JOptionPane.showMessageDialog(this,"你没有管理员权限");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(this,"登录成功");
            return true;
        }
    }
}
