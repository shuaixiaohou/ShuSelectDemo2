package com.housaiying.shuselectdemo2.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.housaiying.shuselectdemo2.R;
import com.housaiying.shuselectdemo2.adapter.TreeAdapter;
import com.housaiying.shuselectdemo2.bean.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by housaiying on 2020/1/8
 */
public class TreeListView extends ListView {
    public List<Node> mNodeList;
    ListView treelist = null;
    TreeAdapter ta = null;
    private List<Node> checkList;

    public TreeListView(final Context context, List<Node> res) {
        super(context);
        treelist = this;
        treelist.setFocusable(false);
        treelist.setBackgroundColor(0xffffff);
        treelist.setFadingEdgeLength(0);
        treelist.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        treelist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ((TreeAdapter) parent.getAdapter()).ExpandOrCollapse(position);
            }
        });
        initNode(context, initNodRoot(res), true, -1, -1, 1);
    }

    // 使用 onMeasure 方法，来解决尺寸高度的问题，以及事件冲突的问题；
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST
        );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * context响应监听的上下文
     * root已经挂好树的根节点
     * hasCheckBox是否整个树有复选框
     * tree_ex_id展开iconid -1会使用默认的
     * tree_ec_id收缩iconid -1会使用默认的
     * expandLevel初始展开等级
     */
    public List<Node> initNodRoot(List<Node> res) {
        ArrayList<Node> list = new ArrayList<Node>();
        ArrayList<Node> roots = new ArrayList<Node>();
        Map<String, Node> nodemap = new LinkedHashMap<String, Node>();
        for (int i = 0; i < res.size(); i++) {
            Node nr = res.get(i);
            nodemap.put(nr.getCurId(), nr);// 生成map树
        }
        Set<String> set = nodemap.keySet();
        Collection<Node> collections = nodemap.values();
        Iterator<Node> iterator = collections.iterator();
        while (iterator.hasNext()) {// 添加所有根节点到root中
            Node n = iterator.next();
            if (!set.contains(n.getParentId()))
                roots.add(n);
            list.add(n);
        }
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                Node m = list.get(j);
                if (m.getParentId().equals(n.getCurId())) {
                    n.addNode(m);
                    m.setParent(n);
                    m.setParents(n);
                } else if (m.getCurId().equals(n.getParentId())) {
                    m.addNode(n);
                    n.setParent(m);
                    m.setParents(m);
                }
            }
        }
        return roots;
    }

    public void initNode(Context context, List<Node> root, boolean hasCheckBox,
                         int tree_ex_id, int tree_ec_id, int expandLevel) {
        ta = new TreeAdapter(context, root);
        //获取
        mNodeList = ta.all;
        // 设置整个树是否显示复选框
        ta.setCheckBox(true);
        // 设置展开和折叠时图标
        int tree_ex_id_ = (tree_ex_id == -1) ? R.mipmap.down_icon : tree_ex_id;
        int tree_ec_id_ = (tree_ec_id == -1) ? R.mipmap.right_icon : tree_ec_id;
        ta.setCollapseAndExpandIcon(tree_ex_id_, tree_ec_id_);
        // 设置默认展开级别
        ta.setExpandLevel(expandLevel);
        this.setAdapter(ta);
    }

    /* 返回当前所有选中节点的List数组 */
    public List<Node> getSelect() {
        Log.d("get", ta.getSelectedNode().size() + "");
        return ta.getSelectedNode();
    }

    public void setSelect(List<String> allSelect) {
        ta.setSelectedNode(allSelect);
    }
}