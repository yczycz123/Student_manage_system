package student_system;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static void main(String[] args) {
        ArrayList<Student> list=new ArrayList<>();
     loop:   while (true) {
            System.out.println("-------------欢迎来到黑马学生管理系统----------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：退出");
            System.out.print("请输入您的选择:");
            Scanner sc=new Scanner(System.in);
            String choose=sc.next();
            switch (choose){
                case "1": addStudent(list);break;
                case "2": delectStudent();break;
                case "3": changeStudent();break;
                case "4": selectStudent(list);break;
                case "5": {
                    System.out.println("退出");
                //    System.exit(0);//停止虚拟机运行
                    break loop; //跳出while循环，因为while循环有一个loop标号
                }
                default: System.out.println("没有此选项");break;
            }
        }
    }


    //添加学生
    public static void addStudent(ArrayList<Student>list){
        while (true) {
            Scanner sc=new Scanner(System.in);
            System.out.println("请输入学生id");
            String id=sc.next();
            System.out.println("请输入学生姓名");
            String name=sc.next();
            System.out.println("请输入学生年龄");
            int age=sc.nextInt();
            System.out.println("请输入学生家庭住址");
            String address=sc.next();


            //判断id是否唯一
            boolean id_flag= id_contions(list,id);
            if(id_flag){
                Student stu=new Student(id,name,age,address);
                list.add(stu);
                System.out.println("学生信息添加完毕");
                break;
            }else {
                System.out.println("该学生的id已经存在，请重新录入");
            }
        }
    }

    //删除学生
    public static void delectStudent(){
        System.out.println("删除学生");
    }

    //修改学生
    public static void changeStudent(){
        System.out.println("修改学生");
    }

    //查询学生
    public static void selectStudent(ArrayList<Student>list){
        if (list.size() == 0) {
            System.out.println("当前无学生信息，请添加学生后再查询");
            return;//结束方法
        }

        System.out.println("id\t\t姓名\t\t年龄\t\t家庭住址");
        for (int i = 0; i < list.size(); i++) {
            Student stu=list.get(i);
            System.out.println(stu.getId()+"\t\t"+stu.getName()+"\t\t"+stu.getAge()+"\t\t"+stu.getAddress());
        }
    }

    public static boolean id_contions(ArrayList<Student>list,String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu=list.get(i);
            String stu_id=stu.getId();
            if(stu_id.equals(id)){
                return  false;
            }
        }
        return true;
    }
}
