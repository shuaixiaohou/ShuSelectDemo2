package com.housaiying.shuselectdemo2.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.housaiying.shuselectdemo2.R;
import com.housaiying.shuselectdemo2.bean.Dept;
import com.housaiying.shuselectdemo2.bean.Node;
import com.housaiying.shuselectdemo2.bean.Person;
import com.housaiying.shuselectdemo2.utils.TreeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by housaiying on 2020/1/8
 */
public class MainActivity extends AppCompatActivity {
    List<Node> list = new ArrayList<Node>();
    private TreeListView listView;
    private ConstraintLayout constraintLayout, rl;
    private com.housaiying.shuselectdemo2.adapter.TreeAdapter mTreeAdapter;
    private Node node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.conten);
        Context context = MainActivity.this;
        rl = new ConstraintLayout(context);
        rl.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        listView = new TreeListView(context, initNodeTree());
        listView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        constraintLayout.addView(listView);
    }

    public List<Node> initNodeTree() {
//       parentId为 -1表示为根节点,curId为当前节点，parentId为1表示父节点为curId=1,dept_id不能和person_id一样，一样会不显示数据
        List<Dept> ld = new ArrayList<>();
        ld.add(new Dept("-1", "1", "1"));
        ld.add(new Dept("-1", "2", "2"));
        ld.add(new Dept("1", "3", "3"));
        ld.add(new Dept("3", "4", "4"));
        ld.add(new Dept("1", "5", "5"));
        ld.add(new Dept("1", "6", "6"));
        ld.add(new Dept("1", "7", "7"));
        ld.add(new Dept("3", "8", "8"));
        ld.add(new Dept("1", "9", "9"));
        ld.add(new Dept("1", "10", "10"));
        ld.add(new Dept("1", "11", "11"));
        ld.add(new Dept("3", "12", "12"));
        ld.add(new Dept("1", "13", "13"));
        ld.add(new Dept("1", "14", "14"));
        ld.add(new Dept("1", "15", "15"));
        ld.add(new Dept("3", "16", "16"));
        List<Person> lp = new ArrayList<>();
        lp.add(new Person("1", "17", "张三"));
        lp.add(new Person("1", "18", "张三2"));
        lp.add(new Person("2", "19", "张三3"));
        lp.add(new Person("3", "20", "张三4"));
        lp.add(new Person("4", "21", "张三5"));
        lp.add(new Person("4", "22", "张三6"));
        lp.add(new Person("4", "23", "张三7"));
        lp.add(new Person("10", "24", "张三"));
        lp.add(new Person("11", "25", "张三2"));
        lp.add(new Person("12", "26", "张三3"));
        lp.add(new Person("13", "27", "张三4"));
        lp.add(new Person("14", "28", "张三5"));
        lp.add(new Person("15", "29", "张三6"));
        lp.add(new Person("16", "30", "张三7"));
        for (Dept d : ld) {
            Node node = new Node();
            node.setDept(d);
            list.add(node);
        }
        for (Person p : lp) {
            Node node = new Node();
            node.setPerson(p);
            list.add(node);
        }
        return list;
    }

    /**
     * 显示选中数据
     */
    public void clicShow(View v) {
        StringBuffer sb = new StringBuffer();
        final List<Node> allNodes = initNodeTree();
        for (int i = 0; i < allNodes.size(); i++) {
            if (allNodes.get(i).isChecked()) {
                sb.append(allNodes.get(i).getName() + ",");
            }
        }
        String strNodesName = sb.toString();
        if (!TextUtils.isEmpty(strNodesName)) {
            Toast.makeText(this, strNodesName.substring(0, strNodesName.length() - 1), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "您还没有选择数据哦！", Toast.LENGTH_LONG).show();
        }
    }
}