package utils;

import javax.swing.*;
import java.awt.*;

public class ZPanel extends JPanel {

    public ZPanel layout(LayoutManager manager)
    {
        this.setLayout(manager);
        return this;
    }

    //填充
    public ZPanel padding(int size){
        return padding(size,size,size,size);

    }
    public ZPanel padding(int top,int left,int bottom,int right){

        ZBorder.addPadding(this,top,left,bottom,right);
        return this;

    }

    public ZPanel margin(int size){
        return margin(size,size,size,size);
    }
    public ZPanel margin(int top,int left,int bottom,int right)
    {
        ZBorder.addMargin(this,top,left,bottom,right);
        return this;
    }
    public ZPanel maxSize(int w,int h)
    {
        this.setMaximumSize(new Dimension(w,h));
        return this;
    }

    public ZPanel maxWidth(int w)
    {
        Dimension size=this.getMaximumSize();//占满控件
        if(size==null)
            size=new Dimension(0,0);
        size.width=w;
        this.setMaximumSize(size);
        return this;
    }
    public ZPanel maxHeight(int h)
    {
        Dimension size=this.getMaximumSize();
        if(size==null)
            size=new Dimension(0,0);
        size.height=h;
        this.setMaximumSize(size);
        return this;
    }

    //最佳大小
    public ZPanel preferredSize(int w, int h)
    {
        this.setPreferredSize(new Dimension(w, h));
        return this;
    }

    public ZPanel preferredWidth(int w)
    {
        Dimension size = this.getPreferredSize();
        if(size == null)
            size = new Dimension(0,0);
        size.width = w;
        this.setPreferredSize( size);
        return this;
    }

    public ZPanel preferredHeight(int h)
    {
        Dimension size = this.getPreferredSize();
        if(size == null)
            size = new Dimension(0,0);
        size.height = h;
        this.setPreferredSize( size);
        return this;
    }

    //min size
    public ZPanel minSize(int w, int h)
    {
        this.setMinimumSize(new Dimension(w, h));
        return this;
    }

    public ZPanel minWidth(int w)
    {
        Dimension size = this.getMinimumSize();
        if(size == null)
            size = new Dimension(0,0);
        size.width = w;
        this.setMinimumSize( size);
        return this;
    }

    public ZPanel minHeight(int h)
    {
        Dimension size = this.getMinimumSize();
        if(size == null)
            size = new Dimension(0,0);
        size.height = h;
        this.setMinimumSize( size);
        return this;
    }

}
