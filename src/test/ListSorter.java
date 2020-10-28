package test;

import java.util.*;

public class ListSorter {
    public static void main(String[] args) {
        List<Employee> emps = new ArrayList<>();
        emps.add(new Employee("张三",33,1800f));
        emps.add(new Employee("李四",55,3800f));
        emps.add(new Employee("王五",40,2300f));
        Collections.sort(emps, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                //工资按照从低到高排序
                //return (int)(o1.getSalary() - o2.getSalary());
                //年龄按照从大到小
                return (int)(o2.getAge() - o1.getAge());
            }
        });
        System.out.println(emps);
    }
}
