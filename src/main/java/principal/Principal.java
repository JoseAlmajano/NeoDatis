package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.CredentialException;

import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

import pojo.Departamento;
import pojo.Empleado;
import pojo.Utilidad;

public class Principal {
	
	static ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private static ODB odb;
	private static Scanner sc;
	private static int recogerOpcion;

	public static void main(String[] args) throws IOException {
		
		Utilidad.leerCsv(listaDepartamentos, listaEmpleados);
		 odb = ODBFactory.open("C:\\PRUEBAS\\empresaAlmajano.neodatis");
		 mostrarMenu();
		 elegirOpcion();
		
		odb.close();
	}

	private static void resultadoConsulta() {
		System.out.println("Nombres de los empleados que trabajan en el departamento 10."); 
		
		ICriterion crit1 = Where.equal("departamento", 10);
		CriteriaQuery cq1 = new CriteriaQuery(Empleado.class, crit1);
		Objects<Empleado>listaEmpleados = odb.getObjects(cq1);
			
			for(Empleado empl : listaEmpleados) {
				System.out.println("\n nombre --->" + empl.getNombreE());
			}
		
		
		System.out.println(" Número de empleados del departamento de Ventas.");
		
		//ICriterion crit2 = Where.equal("departamento", "ventas");
		Values valores = odb.getValues(new ValuesCriteriaQuery(Departamento.class).count("ventas"));
		
		
		System.out.println(" Por cada departamento, el número de empleados.");
	}

	private static void visualizar() {
		
		System.out.println("Mostramos todos los datos de Departamentos y Empleados....");
		
		IQuery query = new CriteriaQuery(Departamento.class);
					for(Departamento departamento : listaDepartamentos) {
				System.err.println("Codigo :" + departamento.getCodigoD() + "**" + "Nombre :" + departamento.getNombreD() + "**" + "Localiadad :" + departamento.getLocalidad());
			}
					
					
		for(Departamento departamento : listaDepartamentos) {
			ICriterion crit = Where.equal("departamento", departamento.getCodigoD());
		IQuery query2 = new CriteriaQuery(Empleado.class, crit);
		
					for(Empleado empleado : listaEmpleados) {
				System.err.println("Codigo :" + empleado.getCodigoE() + "**" + "Nombre :" + empleado.getNombreE() + "**" + "Apellidos :" + empleado.getApellidos() + "**" + "Puesto :" +  empleado.getPuesto()
				 + "**" + "Salario :" +	empleado.getSalario()  + "**" + "Departamento :" +	departamento.getNombreD());
			}
		}
	}

	private static void elegirOpcion() {
		switch (recogerOpcion) {
		case 1:
			guardarDatos();
			break;
			
		case 2:
			visualizar();
			break;
			
		case 3:
			resultadoConsulta();
			break;
			
		case 4:
			System.exit(0);
			break; 
			
		default:
			break;
		}
	}

	private static void guardarDatos() {
		System.out.println("Datos guardados con éxito...");
		
		for(Departamento departamento: listaDepartamentos) {
			
			odb.store(departamento);
		}
		
		for(Empleado empleado: listaEmpleados) {
					
					odb.store(empleado);
				}
	}

	private static void mostrarMenu() {
		sc = new Scanner(System.in);
		
		System.out.println("Elija opcion: \n"
				+ "1.Guardar NeoDatis \n"
				+ "2.Visualizar \n"
				+ "3.Resultado Consultas \n"
				+ "4.Salir \n" );
		recogerOpcion = sc.nextInt();
	}

}
