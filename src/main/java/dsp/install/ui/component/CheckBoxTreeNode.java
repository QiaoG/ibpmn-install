package dsp.install.ui.component;

import lombok.Data;

import javax.swing.tree.DefaultMutableTreeNode;

/**
* Author GQ
* Date:2019/1/8
* Time:9:55 PM
*/
@Data
public class CheckBoxTreeNode extends DefaultMutableTreeNode{

    protected boolean selected;

    public CheckBoxTreeNode()
    {
        this(null);
    }

    public CheckBoxTreeNode(Object userObject)
    {
        this(userObject, true, false);
    }

    public CheckBoxTreeNode(Object userObject, boolean allowsChildren, boolean isSelected)
    {
        super(userObject, allowsChildren);
        this.selected = isSelected;
    }

    public void setSelected(boolean isSelected) {
        selected = isSelected;
        if(isSelected){
            if (children != null) {
                for(Object obj : children)
                {
                    CheckBoxTreeNode node = (CheckBoxTreeNode)obj;
                    if(isSelected != node.isSelected())
                        node.setSelected(isSelected);
                }
            }
            CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;
            if (pNode != null) {
                int index = 0;
                for(; index < pNode.children.size(); ++ index)
                {
                    CheckBoxTreeNode pChildNode = (CheckBoxTreeNode)pNode.children.get(index);
                    if(!pChildNode.isSelected())
                        break;
                }
                if(index == pNode.children.size())
                {
                    if(pNode.isSelected() != isSelected)
                        pNode.setSelected(isSelected);
                }
            }
        }else{
            if (children != null) {
                int index = 0;
                for(; index < children.size(); ++ index)
                {
                    CheckBoxTreeNode childNode = (CheckBoxTreeNode)children.get(index);
                    if(!childNode.isSelected())
                        break;
                }
                // 从上向下取消的时候
                if(index == children.size())
                {
                    for(int i = 0; i < children.size(); ++ i)
                    {
                        CheckBoxTreeNode node = (CheckBoxTreeNode)children.get(i);
                        if(node.isSelected() != isSelected)
                            node.setSelected(isSelected);
                    }
                }
            }

            CheckBoxTreeNode pNode = (CheckBoxTreeNode)parent;
            if(pNode != null && pNode.isSelected() != isSelected)
                pNode.setSelected(isSelected);
        }
    }


}
