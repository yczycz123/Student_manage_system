package student_system;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
       loop: while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出");
            Scanner sc=new Scanner(System.in);
            String choose=sc.next();
            switch (choose){
                case "1":
                    login();break;
                case "2":
                    register();break;
                case "3":
                    miss_password();break;
                case "4": {
                    System.out.println("退出成功");break loop;
                }
                default:
                    System.out.println("没有这个选项");break;
            }
        }
    }

    private static void miss_password() {
        System.out.println("忘记密码");
    }

    private static void register() {
        System.out.println("注册");
    }

    private static void login() {
        System.out.println("登录");
    }
}
