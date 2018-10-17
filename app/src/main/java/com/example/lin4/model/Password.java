package com.example.lin4.model;

/**
 * Created by Administrator on 2018/2/15.
 */
import java.io.Serializable;


public class Password implements Serializable {

    private String[] pwd = new String[10];


    public Password() {
        int i;
        for (i = 0; i < 10; i++) {
            pwd[i] = "";
        }
    }

    public void ok(int[] a) {
        int i;
        int j;
        for (i = 0; i < 10; i++) {
            j = a[i];
            switch (i) {
                case 0:
                    pwd[j] = pwd[j] + "1";
                    break;
                case 1:
                    pwd[j] = pwd[j] + "2";
                    break;
                case 2:
                    pwd[j] = pwd[j] + "3";
                    break;
                case 3:
                    pwd[j] = pwd[j] + "4";
                    break;
                case 4:
                    pwd[j] = pwd[j] + "5";
                    break;
                case 5:
                    pwd[j] = pwd[j] + "6";
                    break;
                case 6:
                    pwd[j] = pwd[j] + "7";
                    break;
                case 7:
                    pwd[j] = pwd[j] + "8";
                    break;
                case 8:
                    pwd[j] = pwd[j] + "9";
                    break;
                case 9:
                    pwd[j] = pwd[j] + "0";
                    break;
                default:
                    break;
            }
        }
    }






    public void delete() {
        int i;
        for (i = 0; i < 10; i++) {
            if (pwd[i].length() >= 1) {
                pwd[i] = pwd[i].substring(0, pwd[i].length()-1);
            }
        }
    }



    public String getPwd(int i) {
        return pwd[i];
    }


}

