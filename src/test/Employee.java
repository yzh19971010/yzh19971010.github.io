package test;

public class Employee implements Comparable<Employee> {
    private String ename;
    private Integer age;
    private Float salary;

    @Override
    public String toString() {
        return "Employee{" + "ename='" + ename + '\'' + ", age=" + age + ", salary=" + salary + '}';
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Employee(String ename, Integer age, Float salary) {
        this.ename = ename;
        this.age = age;
        this.salary = salary;
    }

    public Employee(){

    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Employee o) {
        return this.getAge().compareTo(o.getAge());
    }
}
