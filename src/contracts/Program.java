package contracts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import contracts.entities.Department;
import contracts.entities.HourContract;
import contracts.entities.Worker;
import contracts.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner scan = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.print("Entre com o nome do departamento: ");
		String departmentName = scan.nextLine();
		System.out.println("Entre com os dados do funcionário: ");
		System.out.print("Name: ");
		String name = scan.nextLine();
		System.out.print("Level: ");
		String level = scan.nextLine();
		level = level.toUpperCase();
		System.out.print("Salário base R$: ");
		Double baseSalary = scan.nextDouble();

		Worker worker = new Worker(name, WorkerLevel.valueOf(level), baseSalary, new Department(departmentName));

		System.out.print("Informe o número de contratos do trabalhador " + name + ": ");
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.println("Ente com o contrato #" + (i + 1));
			System.out.print("Data (DD/MM/YYYY): ");
			Date date = sdf.parse(scan.next());
			System.out.print("Valor por hora R$: ");
			Double valuePerHour = scan.nextDouble();
			System.out.print("Duração (horas): ");
			Integer hours = scan.nextInt();
			scan.nextLine();

			HourContract contract = new HourContract(date, valuePerHour, hours);
			worker.addContract(contract);

		}

		System.out.print("Entre com o mês e ano para calculo da renda (MM/YYYY): ");
		String monthAndYear = scan.nextLine();

		int month = Integer.parseInt(monthAndYear.substring(0, 2));
		int year = Integer.parseInt(monthAndYear.substring(3));

		double income = worker.income(year, month);
		System.out.println(worker);

		System.out.println("Renda para " + monthAndYear + " R$: " + String.format("%.2f", income));

		scan.close();
	}

}
