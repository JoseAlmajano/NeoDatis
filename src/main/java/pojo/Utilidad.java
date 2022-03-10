package pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Utilidad {
	
	public static void leerCsv(ArrayList<Departamento> listaDepartamentos, ArrayList<Empleado> listaEmpleados) throws IOException {
		
		BufferedReader brDepartamentos = Files.newBufferedReader(Paths.get("C:\\PRUEBAS\\Departamentos.csv"));
		Stream<String> lineaDepartamentos = brDepartamentos.lines();
		lineaDepartamentos.forEach(elemento->{
			String[] cacho = elemento.split(",");
			listaDepartamentos.add(new Departamento(Integer.parseInt(cacho[0]), cacho[1], cacho[2]));
			
		});
		
		BufferedReader brEmpleados = Files.newBufferedReader(Paths.get("C:\\PRUEBAS\\Empleados.csv"));
		Stream<String> lineaEmpleados = brEmpleados.lines();
		lineaEmpleados.forEach(elemento->{
			String[] cacho = elemento.split(",");
			listaEmpleados.add(new Empleado(Integer.parseInt(cacho[0]), cacho[1], cacho[2], cacho[3],Float.parseFloat(cacho[4]), Integer.parseInt(cacho[5])));
			
		});
		
	}
}
