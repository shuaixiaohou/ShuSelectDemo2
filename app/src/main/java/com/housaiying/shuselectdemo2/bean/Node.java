package com.housaiying.shuselectdemo2.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by housaiying on 2020/1/8
 */
public class Node implements Serializable {
    private Node parent = null; // 父节点
    private List<Node> childrens = new ArrayList<Node>();//子节点
    private String name;//节点显示值
    private boolean isChecked = false; //是否被选中
    private boolean isExpand = true;//是否处于扩展状态
    private boolean hasCheckBox = true;//是否有复选框
    //父节点集合
    private List<Node> parents = new ArrayList<>();
    private Person person;
    private Dept dept;

    public Node() {
    }

    /**
     * 返回自己的id
     *
     * @return
     **/
    public String getCurId() {
        if (dept != null) {
            return dept.getDept_id();
        }
        if (person != null) {
            return person.getPerson_id();
        }
        return null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * 得到父节点
     */
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getParentId() {
        if (dept != null) {
            return dept.getParentID();
        }
        if (person != null) {
            return person.getDep_id();
        }
        return null;
    }

    public List<Node> getParents() {
        return parents;
    }

    public void setParents(Node node) {
        if (node != null) {
            if (!parents.contains(node)) {
                parents.add(node);
            }
        }
    }

    /**
     * 得到子节点
     *
     * @return
     */
    public List<Node> getChildrens() {
        return childrens;
    }

    /**
     * pandu是否根节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null ? true : false;
    }

    /**
     * 是否被选中
     *
     * @return
     */
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * 是否是展开状态
     *
     * @return
     */
    public boolean isExplaned() {
        return isExpand;
    }

    /**
     * 设置展开状态
     *
     * @param isExplaned
     */
    public void setExplaned(boolean isExplaned) {
        this.isExpand = isExplaned;
    }

    /**
     * 是否有复选框
     *
     * @return
     */
    public boolean hasCheckBox() {
        return hasCheckBox;
    }

    /**
     * 设置是否有复选框
     *
     * @param hasCheckBox
     */
    public void setHasCheckBox(boolean hasCheckBox) {
        this.hasCheckBox = hasCheckBox;
    }

    /**
     * 得到节点值
     *
     * @return
     */
    public String getName() {
        if (dept != null) {
            return dept.getName();
        }
        if (person != null) {
            return person.getPersonName();
        }
        return name;
    }

    /**
     * 增加一个子节点
     *
     * @param node
     */
    public void addNode(Node node) {
        if (!childrens.contains(node)) {
            childrens.add(node);
        }
    }

    /**
     * 移除一个子节点
     *
     * @param node
     */
    public void removeNode(Node node) {
        if (childrens.contains(node))
            childrens.remove(node);
    }

    /**
     * 移除指定位置的子节点
     *
     * @param location
     */
    public void removeNode(int location) {
        childrens.remove(location);
    }

    /**
     * 清除所有子节点
     */
    public void clears() {
        childrens.clear();
    }

    /**
     * 判断给出的节点是否当前节点的父节点
     *
     * @param node
     * @return
     */
    public boolean isParent(Node node) {
        if (parent == null) return false;
        if (parent.equals(node)) return true;
        return parent.isParent(node);
    }

    /**
     * 递归获取当前节点级别
     *
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    /**
     * 父节点是否处于折叠的状态
     *
     * @return
     */
    public boolean isParentCollapsed() {
        if (parent == null) return false;
        if (!parent.isExplaned()) return true;
        return parent.isParentCollapsed();
    }

    /**
     * 是否叶节点（没有展开下级的节点）
     *
     * @return
     */
    public boolean isLeaf() {
        return childrens.size() < 1 ? true : false;
    }
}