package utils;

import model.Employee;

public enum Preferences {
    INSTANCE;
    private Employee employee = new Employee();

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
