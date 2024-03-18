package student_system;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
       ArrayList<User> list=new ArrayList<>();
       loop: while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出");
            Scanner sc=new Scanner(System.in);
            String choose=sc.next();
            switch (choose){
                case "1"->login(list);
                case "2"-> register(list);
                case "3"-> miss_password(list);
                case "4"->{
                    System.out.println("退出成功");break loop;
                }
                default->System.out.println("没有这个选项");
            }
        }
    }

    private static void miss_password(ArrayList<User>list) {
        System.out.println("忘记密码");
    }

    private static void register(ArrayList<User>list) {
        Scanner sc=new Scanner(System.in);
        String username;
        String password1;
        String idcard;
        String phonenumber;
        //验证用户名
        while (true) {
            System.out.println("请输入用户名");
             username=sc.next();
            boolean flag=checkusername(list,username);
            if (flag) {
                System.out.println("用户名格式满足条件,请输入密码");break;
            }else {
                System.out.println("用户名格式不满足条件或已存在，请重新输入");
                continue;
            }
        }
        //验证密码
        while (true) {
            System.out.println("密码请输入两次，两次一致即可");
            System.out.println("请输入第一次的密码");
             password1=sc.next();
            System.out.println("请输入第二次的密码");
            String password2=sc.next();
            if (password2.equals(password1)) {
                System.out.println("密码设置成功，请输入身份证号");break;
            }else {
                System.out.println("两次密码不一致，请重新输入密码");
            }
        }
        //验证身份证号
        while (true) {

             idcard=sc.next();
            boolean flag=checkuseridcard(idcard);
            if (flag) {
                System.out.println("身份证号正确，请输入手机号码");break;
            }else {
                System.out.println("身份证号不正确，请重新输入");
            }
        }
        //验证手机号码
        while (true) {
             phonenumber=sc.next();
            boolean flag=checkphonenumber(phonenumber);
            if (flag) {
                System.out.println("手机号码正确，请稍等");break;
            }else {
                System.out.println("手机号码不正确，请重新输入");
            }
        }

        User new_user=new User(username,password1,idcard,phonenumber);
        list.add(new_user);
        System.out.println("数据成功录入，已注册成功");

        //打印当前集合里的所有信息
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getUserIdcard()+" "+user.getPhonenumber()+" ");
        }
    }

    private static boolean checkphonenumber( String phonenumber) {
        //判断长度是否是11位
        if(phonenumber.length()!=11){
            return false;
        }
        //验证第一次是否是0
        if(phonenumber.charAt(0)==0){
            return false;
        }

        //判断是否全为数字
        for (int i = 0; i < phonenumber.length(); i++) {
            char k=phonenumber.charAt(i);
            if(!(k>='0'&&k<='9')){
                return false;
            }
        }

        return true;
    }

    private static boolean checkuseridcard( String idcard) {

        //验证长度是否是18位
        if(idcard.length()!=18){
            return false;
        }
        //验证第一次是否是0
        if(idcard.charAt(0)==0){
            return false;
        }

        //验证前17位是否是数字
        for (int i = 0; i < idcard.length()-1; i++) {
            char k=idcard.charAt(i);
            if(!(k>='0'&&k<='9')){
                return false;
            }
        }

        //判断最后一位是否是数字或大小写的x
        char k=idcard.charAt(idcard.length()-1);
        if(!((k>='0'&&k<='9')||(k=='x')||(k=='X'))){
            return false;
        }

        return true;
    }

    private static boolean checkusername(ArrayList<User>list,String username) {
        //判断用户名长度是否符合要求
        if(username.length()<3||username.length()>15){
            return false;
        }

        //判断用户名是否是字母和数字组合
        for (int i = 0; i < username.length(); i++) {
            char k=username.charAt(i);
            if (!((k>='a'&&k<='z')||(k>='A'&&k<='Z')||(k>='0'&&k<='9'))) {
                return false;
            }
        }

        //判断用户名是否是纯数字
        int count=0;
        for (int i = 0; i < username.length(); i++) {
            char k=username.charAt(i);
            if (k>'0'&&k<'9') {
                count++;
            }
        }
        if (count == username.length()) {
            return false;
        }

        //判断用户名是否唯一
        if(contions_username(list,username)){
            return false;
        }

        return true;
    }

    private static boolean contions_username(ArrayList<User>list,String username) {
        for (int i = 0; i < list.size(); i++) {
            User user=list.get(i);
            String database_username=user.getUsername();
            if (database_username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static void login(ArrayList<User>list) {
        Scanner sc=new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名");
            String username=sc.next();
            boolean flag1=contions_username(list,username);
            if (!flag1) {
                System.out.println("用户名不存在，请先注册");return;
            }
            System.out.println("请输入密码");
            String password=sc.next();

            while (true) {
                String code=get_code();
                System.out.println("请输入验证码,当前验证码为"+code);
                String user_code=sc.next();
                if (user_code.equalsIgnoreCase(code)){
                    System.out.println("验证码输入正确");break;
                }else {
                    System.out.println("验证码输入错误,请重新输入");
                }
            }

            User user=new User(username,password,null,null);
            boolean flag=check_userinfo(list, user);
            if (flag){
                System.out.println("用户名和密码正确，登录成功");return;
            }else {
                System.out.println("用户名或密码错误，登录失败");
                if(i==2){
                    System.out.println("您输入错误次数过多，当前用户名被锁定");return;
                }else {
                    System.out.println("您已经输入错误"+(i+1)+ "次，还剩下"+(2-i)+"次机会");
                }
            }
        }


    }

    private static boolean check_userinfo(ArrayList<User>list,User user) {
        for (int i = 0; i < list.size(); i++) {
            User grip_user=list.get(i);
            if ((grip_user.getUsername().equals(user.getUsername()))&&(grip_user.getPassword().equals(user.getPassword()))) {
                return true;
            }
        }
        return false;
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
