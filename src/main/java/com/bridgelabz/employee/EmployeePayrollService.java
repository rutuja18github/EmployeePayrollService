package com.bridgelabz.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {

	private List<EmployeePayrollData> employeePayrollList;

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	};

	public EmployeePayrollService() {
		this.employeePayrollList = new ArrayList<EmployeePayrollData>();
	}

	private void readEmployeePayroll(Scanner consoleINputReader) {
		System.out.println("Enter Employee ID: ");
		int id = consoleINputReader.nextInt();
		System.out.println("Enter Employee Name: ");
		String name = consoleINputReader.next();
		System.out.println("Enter Employee Salary: ");
		double salary = consoleINputReader.nextDouble();
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	private void writeEmployeePayroll(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			employeePayrollList.stream().forEach(System.out::println);
		else if (ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().write(employeePayrollList);
	}
	
	public long countEntries(IOService ioService) {
	    if (ioService.equals(IOService.FILE_IO))
	        return new EmployeePayrollFileIOService().countEntries();
	    return 0;
	}

	public void printData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().printData();
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Employee Payroll Service program");
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Scanner consoleINputReader = new Scanner(System.in);
		employeePayrollService.readEmployeePayroll(consoleINputReader);
		employeePayrollService.writeEmployeePayroll(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		employeePayrollService.countEntries(IOService.FILE_IO);
	}

}
