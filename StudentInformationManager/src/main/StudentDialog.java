package main;

import utils.ZColumnLayout;
import utils.ZPanel;
import utils.ZRowLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * 添加、编辑窗口
 */
public class StudentDialog extends JDialog {
    public JTextField idField=new JTextField(20);
    public JTextField nameField=new JTextField(20);
    public JComboBox<String> sexField=new JComboBox<>();
    public JTextField phoneField=new JTextField(20);
    public JTextField birthdayField=new JTextField(20);

    JButton okButton=new JButton("确定");
    //默认是取消
    private boolean retValue=false;
    public StudentDialog(JFrame owner){
        super(owner,"编辑框",true);
        this.setSize(300,300);
        //设置一个容器
        ZPanel root=new ZPanel();
        this.setContentPane(root);
        //竖直布局间隔为10
        root.setLayout(new ZColumnLayout(10));
        root.padding(10);//内边距为10

        //中间面板
        ZPanel center=new ZPanel();
        root.add(center,"1w");
        center.setLayout(new ZColumnLayout(10));
        //边框样式
        center.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        center.padding(10);

        //各个文本框
        if(true){
            ZPanel row=new ZPanel();
            center.add(row,"24px");
            //横向布局
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("学号"),"50px");
            row.add(idField,"1w");//占满剩余比例

        }
        if(true){
            ZPanel row=new ZPanel();
            center.add(row,"24px");
            //横向布局
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("姓名"),"50px");
            row.add(nameField,"1w");//占满剩余比例

        }
        if(true){
            ZPanel row=new ZPanel();
            center.add(row,"24px");
            //横向布局
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("性别"),"50px");
            row.add(sexField,"1w");//占满剩余比例
            sexField.addItem("女");
            sexField.addItem("男");
        }
        if(true){
            ZPanel row=new ZPanel();
            center.add(row,"24px");
            //横向布局
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("手机号"),"50px");
            row.add(phoneField,"1w");
        }
        if(true){
            ZPanel row=new ZPanel();
            center.add(row,"24px");
            //横向布局
            row.setLayout(new ZRowLayout(10));
            row.add(new JLabel("生日"),"50px");
            row.add(birthdayField,"1w");//占满剩余比例

        }

        //底下
        ZPanel bottom=new ZPanel();
        root.add(bottom,"30px");//底部区域占30px
        bottom.setLayout(new ZRowLayout(10));
        bottom.add(new JLabel(),"1w");
        bottom.add(okButton,"auto");

        //事件处理
        okButton.addActionListener((e)->{
            if(checkValid()){
                retValue=true;//按了确认
                setVisible(false);
            }
        });

    }

    /**
     * 返回值为true:表示用户点了确定
     * 返回值为false:表示用户叉掉了窗口，或者点了取消
     *
     */

    public boolean exec(){
        //相对owner居中显示
        Rectangle frmRect=this.getOwner().getBounds();
        int width=this.getWidth();
        int height=this.getHeight();
        int x=frmRect.x+(frmRect.width-width)/2;
        int y=frmRect.y+(frmRect.height-height)/2;
        this.setBounds(x,y,width,height);

        //显示确认窗口
        this.setVisible(true);
        return retValue;
    }
    // 设置初始值
    public void setValue(Student v)
    {
        idField.setEditable(false); // 学号不允许再编辑
        idField.setText(v.id);
        nameField.setText(v.name);
        sexField.setSelectedIndex(v.sex ? 1: 0); // 条件表达式
        phoneField.setText(v.cellphone);
        birthdayField.setText(v.birthday);
    }

    // 获取用户的输入
    public Student getValue()
    {
        Student v = new Student();
        v.id = idField.getText().trim();
        v.name = nameField.getText().trim();
        v.sex = sexField.getSelectedIndex() == 1;
        v.cellphone = phoneField.getText().trim();
        v.birthday = birthdayField.getText().trim();

        return v;
    }

    //检查输入有效性
    public boolean checkValid()
    {
        Student v=getValue();
        if(v.id.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"学号不得为空！");
            return false;
        }
        if(v.name.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"姓名不得为空！");
            return false;
        }
        return true;
    }
}
