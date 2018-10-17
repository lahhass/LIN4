package com.example.lin4.model;

/**
 * Created by Administrator on 2018/2/15.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Set {

    private List<Integer> pattern = new ArrayList<Integer>();    //ͼ��˳��

    public Set() {
        random();
    }

    public void random() {
        for (int i = 0; i < 10; i++)
            pattern.add(i);
        Collections.shuffle(pattern);
    }

    public int[] setDisplay() {     //取出pattern顺序
        int[] pt_show = new int[10];
        for (int i = 0; i < pattern.size(); i++) {
            pt_show[i] = pattern.get(i);
        }
        return pt_show;
    }

    public void setLeft() {     //左移一位
        int a;
        a = pattern.get(0);
        pattern.remove(0);
        pattern.add(a);
    }

    public void setRight() {    //右移一位
        Collections.rotate(pattern, 1);
    }


}
