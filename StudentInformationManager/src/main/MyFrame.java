package main;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ZZFJSON;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class MyFrame extends JFrame {
    JPanel root=new JPanel();
    JTable table=null;
    //dataList:维护所有的记录，tableModel:要显示出来的记录
    List<Student> dataList=new ArrayList<>();
    DefaultTableModel tableModel=new DefaultTableModel();
    //基本控件
    JButton addButton,deleteButton,editButton,selectButton,backButton;
    JTextField selectField=new JTextField();
    public MyFrame(String title){

        super(title);
        this.setContentPane(root);
        root.setLayout(new BorderLayout());
        //表格初始化
        initTable();
        //工具栏初始化
        initToolBar();
        //加载文件
        loadData();
    }
    private void initTable(){
        //创建JTable,直接重写isCellEditable()，设为不可编辑
        table=new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        JScrollPane scrollPane=new JScrollPane(table);
        root.add(scrollPane,BorderLayout.CENTER);

        //添加到主界面
        table.setFillsViewportHeight(true);//设置充满当前布局
        table.setRowSelectionAllowed(true);//整行选择
        table.setRowHeight(30);//设置行高
        //表格头部
        tableModel.addColumn("学号");
        tableModel.addColumn("姓名");
        tableModel.addColumn("性别");
        tableModel.addColumn("出生日期");
        tableModel.addColumn("手机号");

        table.getColumnModel().getColumn(2).setCellRenderer(new SexColumnRender());
        table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
        table.getColumnModel().getColumn(0).setPreferredWidth(110);//设置宽度

    }
    private void initToolBar()
    {
        JToolBar toolBar=new JToolBar();
        root.add(toolBar,BorderLayout.PAGE_START);
        toolBar.setFloatable(false);//设置不浮动

        //添加按钮
        addButton=createToolButton("添加","add.png");
        toolBar.add(addButton);
        addButton.addActionListener((e)->{

            Add();

        });
        //删除按钮
        deleteButton=createToolButton("删除","delete.png");
        toolBar.add(deleteButton);
        deleteButton.addActionListener((e)->{

            delete();
        });

        //编辑按钮
        editButton=createToolButton("编辑","edit.png");
        toolBar.add(editButton);
        editButton.addActionListener((e)->{

            edit();
        });

        //查询
        toolBar.addSeparator(new Dimension(40,10));//分隔符
        selectButton=createToolButton("查询","select.png");
        toolBar.add(selectField);
        toolBar.add(selectButton);
        selectField.setMaximumSize(new Dimension(120,30));
        selectButton.addActionListener((e)->{

            select();
        });

        //返回
        backButton=createToolButton("返回","back.png");
        toolBar.add(backButton);
        backButton.addActionListener((e)->{

            back();
        });

    }

    private JButton createToolButton(String text,String icon) {
        //图标
        String imagePath="/icons/"+icon;
        URL imageURL=getClass().getResource(imagePath);

        //创建按钮
        JButton button=new JButton(text);
        button.setToolTipText(text);
        button.setIcon(new ImageIcon(imageURL));
        button.setFocusPainted(false);
        return button;


    }

    //获取表格控件中的一条记录的值
    private Student getTableRow(int row)
    {
        Student s=new Student();
        s.id=(String) tableModel.getValueAt(row,0);
        s.name=(String) tableModel.getValueAt(row,1);
        s.sex=(Boolean) tableModel.getValueAt(row,2);
        s.birthday=(String) tableModel.getValueAt(row,3);
        s.cellphone=(String) tableModel.getValueAt(row,4);
        return s;
    }

    //设置表格控件中的一条记录
    private void setTableRow(Student s,int row)
    {
        tableModel.setValueAt(s.id,row,0);
        tableModel.setValueAt(s.name,row,1);
        tableModel.setValueAt(s.sex,row,2);
        tableModel.setValueAt(s.birthday,row,3);
        tableModel.setValueAt(s.cellphone,row,4);
    }

    //向TableModel添加记录
    private void addTableRow(Student item)
    {
        Vector<Object> rowData=new Vector<>();
        rowData.add(item.id);
        rowData.add(item.name);
        rowData.add(item.sex);
        rowData.add(item.birthday);
        rowData.add(item.cellphone);
        tableModel.addRow(rowData);//添加一行
    }

    //向dataList添加一条记录
    private void addToDataList(Student s){
        dataList.add(s);
    }
    //修改一条记录
    private void updateToDataList(String id,Student s)
    {
        for(int i=0;i<dataList.size();i++)
        {
            Student item=dataList.get(i);
            if(item.id.equals(id))
            {
                dataList.set(i,s);
            }
        }
    }

    //从dataList中删除一条记录
    private void removeFromDataList(String id)
    {
        //迭代
        Iterator<Student> iter=dataList.iterator();
        while(iter.hasNext()){
            Student s=iter.next();
            if(s.id.equals(id)){
                iter.remove();
                break;
            }
        }
    }


    //添加数据
    private void Add()
    {
        StudentDialog dialog=new StudentDialog(this);
        if(dialog.exec()){
            Student stu=dialog.getValue();//得到一条记录
            addToDataList(stu);//添加到dataList
            addTableRow(stu);//添加到tableModel

            saveData();

        }
    }

    //保存数据
    private void saveData()
    {
        //构造JSON数组
        JSONArray array=new JSONArray();
        for(int i=0;i<dataList.size();i++){
            Student s=dataList.get(i);
            JSONObject j1=new JSONObject();
            j1.put("id",s.id);
            j1.put("name",s.name);
            j1.put("sex",s.sex);
            j1.put("birthday",s.birthday);
            j1.put("cellPhone",s.cellphone);
            array.put(j1);
        }
        //将JSON对象保存到文件
        File file=new File("studentsdata.json");
        try{
            ZZFJSON.toFile(array,file,"UTF-8");

        }catch (Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
            e.printStackTrace();
        }
    }

    //删除;可多选删除
    private void delete(){
        //获取选中的行的索引
        int[] rows=table.getSelectedRows();
        if(rows.length==0)
            return;
        //弹出对话框确认
        int select=JOptionPane.showConfirmDialog(this,"是否确认删除？","确认",JOptionPane.YES_NO_OPTION);
        if(select!=0) return;//0号才确定
        //从后往前删除
        for(int i=rows.length-1;i>=0;i--)
        {
            int row=rows[i];
            //按学号，从datalist中删除该条记录
            String id=(String) tableModel.getValueAt(row,0);
            removeFromDataList(id);

            //从tableModel中删除该条记录
            tableModel.removeRow(row);
        }
        saveData();//重新保存
    }

    //编辑
    private void edit()
    {
        //获取选中的行的索引
        int[] rows=table.getSelectedRows();
        if(rows.length==0)
            return;
        //取得选中的行
        int row=rows[0];//只编辑选中的第一行
        Student s=getTableRow(row);

        //弹出编辑对话框
        StudentDialog dialog=new StudentDialog(this);
        //设置初始值
        dialog.setValue(s);
        if(dialog.exec())
        {
            Student stu=dialog.getValue();
            //更新到Modelview
            setTableRow(stu,row);
            //更新到dataList
            updateToDataList(stu.id,stu);

            saveData();//更新保存到文件
        }
    }

    //返回
    private void back(){
        tableModel.setRowCount(0);//清空
        for(Student s:dataList)
        {
            addTableRow(s);
        }
        this.addButton.setEnabled(true);
        return;
    }
    //查询
    private void select(){
        //获取用户输入查找的关键字
        String key=selectField.getText().trim();
        if(key.length()==0){
            back();
        }
        //把符合条件的记录显示在表格里
        tableModel.setRowCount(0);//清空
        for(Student s:dataList)
        {
            if(s.name.indexOf(key)>=0){
                addTableRow(s);//加入Modelview
            }
        }
        //添加按钮禁用
        this.addButton.setEnabled(false);

    }

    //加载数据
    private void loadData()
    {
        File file=new File("students.json");
        if(!file.exists())
            return;
        JSONArray array=null;
        try{
            array=(JSONArray)ZZFJSON.fromFile(file,"UTF-8");
        }catch (Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
            e.printStackTrace();
            return;
        }

        //显示到表格
        dataList.clear();
        tableModel.setRowCount(0);//清空
        for(int i=0;i<array.length();i++){
            JSONObject j1=array.getJSONObject(i);
            Student s=new Student();
            s.id=j1.getString("id");
            s.name=j1.getString("name");
            s.sex=j1.getBoolean("sex");
            s.cellphone=j1.getString("cellphone");
            s.birthday=j1.getString("birthday");

            //加入前端数据管理
            addToDataList(s);
            addTableRow(s);
        }
    }


}
