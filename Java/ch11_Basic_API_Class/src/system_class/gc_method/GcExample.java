package system_class.gc_method;

public class GcExample {
    public static void main(String[] args) {
        // Employee 타입 변수 선언
        Employee emp;

        // employee 객체 생성
        emp = new Employee(1);

        // Employee(1)은 쓰레기 객체가 된다.
        emp = null;

        emp = new Employee(2);

        // Employee(2)는 쓰레기 객체가 된다.
        emp = new Employee(3);

        System.out.print("emp가 최종적으로 참조하는 사원번호: ");
        System.out.println(emp.eno);

        // 쓰레기 수집기를 통해 쓰레기 객체를 메모리에서 제거시킴
        System.gc();
    }
}

class Employee {
    public int eno;

    public Employee(int eno) {
        this.eno = eno;
        System.out.println("Employee(" + eno + ") 가 메모리에 생성됨");
    }

    public void finalize() {
        System.out.println("Employee(" + eno + ") 이 메모리에서 제거됨");
    }
}
