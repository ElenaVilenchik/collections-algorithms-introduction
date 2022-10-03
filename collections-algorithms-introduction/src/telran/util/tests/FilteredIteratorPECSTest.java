package telran.util.tests;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import telran.util.FilteredIteratorPECS;

class Person {
	String name;

	Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}

class Employee extends Person {
	int payment;

	Employee(String name, int payment) {
		super(name);
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Employee [payment=" + payment + ", name=" + name + "]";
	}
}

class Manager extends Employee {
	int bonus;

	Manager(String name, int payment, int bonus) {
		super(name, payment);
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return "Manager [bonus=" + bonus + ", payment=" + payment + ", name=" + name + "]";
	}
}

public class FilteredIteratorPECSTest {

	// The idea is PECS principle (Producer Extends Consumer Super):
	// Low-level Iterator is "producer", so it could be of type <? extends Employee>
	// Predicate is "consumer", so it could be of type <? super Employee>
	// So, we are able to build FilteredIterator<Employee> from Iterator<Manager>
	// and Predicate<Person>

	public static void main(String[] args) {
		Iterator<Manager> srcIter = Arrays.asList(new Manager("AAA", 1000, 500), new Manager("BBB", 500, 1000),
				new Manager("CCC", 500, 500), new Manager("DDD", 1000, 1000)).iterator();

		Predicate<Person> filter = new Predicate<Person>() {
			@Override
			public boolean test(Person val) {
				return val.name.compareTo("C") < 0;
			}
		};

		FilteredIteratorPECS<Employee> iter = new FilteredIteratorPECS<>(srcIter, filter);
		updatePayment(iter, 1.2);
	}

	private static void updatePayment(Iterator<Employee> iter, double coefficient) {
		while (iter.hasNext()) {
			Employee emp = iter.next();
			emp.payment *= coefficient;
			System.out.println(emp);
		}
	}

}
