package student_system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class test {
    public static void main(String[] args) {
        String str=get_code();
        System.out.println(str);
    }
    private static String get_code(){
        ArrayList<Character> list=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a'+i));
            list.add((char)('A'+i));
        }

        Random rm=new Random();
        for (int i = 0; i < 4; i++) {
            int a=rm.nextInt(list.size());
            sb.append(list.get(a));
        }

        int b=rm.nextInt(10);
        sb.append(b);

        char[] arr=sb.toString().toCharArray();

        //交换数字位置
        int c=rm.nextInt(arr.length);
        char temp=arr[c];
        arr[c]=arr[arr.length-1];
        arr[arr.length-1]=temp;

        return new String(arr);
        //这里arr.tostring与new string(arr)区别在哪，为什么arr.tostring传的是地址
    }
}
