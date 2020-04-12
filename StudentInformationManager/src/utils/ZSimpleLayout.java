package utils;

import java.awt.*;

/**
 * 手动布局
 */
public abstract class ZSimpleLayout implements LayoutManager {
    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }

    //扣除边框宽度，得到内部的矩形
    //rect:容器大小
    //Inserts:容器的边框宽度
    protected Rectangle excludeInserts(Rectangle rect,Insets insets)
    {
        Rectangle r=new Rectangle();
        r.x+=insets.left;
        r.y+=insets.top;
        r.width-=(insets.left+insets.right);
        r.height-=(insets.top+insets.bottom);
        return r;

    }
}
