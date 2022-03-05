package com.java8streams;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Practise {

    private static void groupByDepartment(List<Employee> list) {
        Map<String, List<Employee>> result = list.stream().collect(groupingBy(Employee::getDep));
        System.out.println("groupByDepartment: " + result);
    }

    private static void groupByDepartmentV2(List<Employee> list) {
        Map<String, Set<String>> result = list.stream().collect(groupingBy(Employee::getDep, mapping(Employee::getName, toSet())));
        System.out.println("groupByDepartmentV2: " + result);
    }

    // Toughest Implementation
    private static void groupByDepartmentHighestSalary(List<Employee> list) {
        Map<String, String> result = list.stream()
                .collect(groupingBy(Employee::getDep,
                        mapping(Function.identity(),
                                maxBy(Comparator.comparingInt(Employee::getSalary)))))
                .entrySet().stream()
                .collect(
                        toMap(o -> o.getKey(),
                                o -> o.getValue().isPresent() ? o.getValue().get().getName() + ":" + o.getValue().get().getSalary() : null));
        System.out.println("groupByDepartmentHighestSalary: " + result);
    }

    // Easy Implementation
    private static void groupByDepartmentLowestSalary(List<Employee> list) {
        Map<String, Integer> result = list.stream()
                .collect(groupingBy(Employee::getDep,
                        mapping(Function.identity(), collectingAndThen(minBy(Comparator.comparingInt(Employee::getSalary)), o -> o.get().getSalary()))));

        // Easiest and clean Implementation
        Map<String, String> newResult = list.stream().collect(groupingBy(Employee::getDep,
                collectingAndThen(minBy(Comparator.comparing(Employee::getSalary)),
                        o -> o.get().getName() + ":" + o.get().getSalary())));

        System.out.println("groupByDepartmentLowestSalary: " + result);
        System.out.println("groupByDepartmentLowestSalary: " + newResult);
    }

    private static void groupByDepartmentGet2ndHighestSalary(List<Employee> list) {
        System.out.println(list.stream().collect(groupingBy(Employee::getDep)).entrySet().stream()
                .collect(toMap(
                        o -> o.getKey(), o -> o.getValue().stream().sorted(Comparator.comparingInt(Employee::getSalary).thenComparing(Employee::getName).reversed()).skip(1).findFirst().get()
                )));
    }

    private static void countByDepartment(List<Employee> list) {
        Map<String, Long> result = list.stream().collect(groupingBy(Employee::getDep, counting()));
        System.out.println("countByDepartment: " + result);
    }

    private static void countByDepartmentInteger(List<Employee> list) {
        Map<String, Integer> result = list.stream().collect(groupingBy(Employee::getDep, collectingAndThen(counting(), Long::intValue)));
        System.out.println("countByDepartment: " + result);
    }

    public static void main(String[] args) {
        List<Employee> list = createEmpList();
        System.out.println(list);
        groupByDepartment(list);
        groupByDepartmentV2(list);
        countByDepartment(list);
        groupByDepartmentHighestSalary(list);
        groupByDepartmentLowestSalary(list);
        groupByDepartmentGet2ndHighestSalary(list);
    }

    private static List<Employee> createEmpList() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee.Builder().setName("Cutu").setDep("HR").setId(1).setSalary(60).build());
        list.add(new Employee.Builder().setName("Poppa").setDep("Engg").setId(2).setSalary(44).build());
        list.add(new Employee.Builder().setName("Pep").setDep("Admin").setId(3).setSalary(74).build());
        list.add(new Employee.Builder().setName("Paa").setDep("Engg").setId(4).setSalary(86).build());
        list.add(new Employee.Builder().setName("Aksha").setDep("Admin").setId(5).setSalary(25).build());
        list.add(new Employee.Builder().setName("Bholu").setDep("HR").setId(6).setSalary(23).build());
        list.add(new Employee.Builder().setName("Pepa").setDep("HR").setId(7).setSalary(23).build());
        list.add(new Employee.Builder().setName("Bidona").setDep("Admin").setId(8).setSalary(45).build());
        list.add(new Employee.Builder().setName("Pepe").setDep("Engg").setId(9).setSalary(86).build());
        list.add(new Employee.Builder().setName("Popwa").setDep("Engg").setId(10).setSalary(34).build());
        return list;
    }

}

class Employee {
    private String name;
    private int id;
    private String dep;
    private int salary;

    private Employee(String name, int id, String dep, int salary) {
        this.name = name;
        this.id = id;
        this.dep = dep;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDep() {
        return dep;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dep='" + dep + '\'' +
                ", salary=" + salary +
                '}';
    }

    // Builder Pattern to create the instance of the class
    public static class Builder {
        String name;
        int id;
        String dep;
        int salary;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDep(String dep) {
            this.dep = dep;
            return this;
        }

        public Builder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            return new Employee(this.name, this.id, this.dep, this.salary);
        }
    }
}
