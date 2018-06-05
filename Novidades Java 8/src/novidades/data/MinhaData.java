package novidades.data;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;

public class MinhaData {
	public static void main(String[] args) {
		 
		// Data Atual
		LocalDateTime hoje = LocalDateTime.now();
		System.out.println("Data/Hora Atual=" + hoje);
 
		// Criando uma data passa os valores desejados
		LocalDate natal2018 = LocalDate.of(2014, Month.DECEMBER, 25);
		System.out.println("Natal de 2014=" + natal2018);
 
		// Criando uma data inválida 31 abril - Não existe, pois abril só tem 30
		// dias
		try {
			LocalDate abril31_2018 = LocalDate.of(2018, Month.APRIL, 31);
			System.out.println(abril31_2018);
		} catch (DateTimeException e) {
			System.err.println(e.getMessage());
		}
 
		// Data/Hora atual no EUA Chicago, Veja todas a zonas disponíve em  ZoneId javadoc		
		LocalDateTime chicago = LocalDateTime.now(ZoneId.of("America/Chicago"));
		System.out.println("A data/hora corrento em Chicago "+ZoneId.of("America/Chicago")+" é =" + chicago);
 
		//Retocedento os dias para obter uma data a partir da data base 01/01/1970
		LocalDate dataBase = LocalDate.ofEpochDay(365);
		System.out.println("365 dias a partir da data baase (01/01/1970)= " + dataBase);
 
		//Obter o dia pelo número sequencial no ano.
		LocalDate centessimoDia2018 = LocalDate.ofYearDay(2018, 100);
		System.out.println("100º dia de 2018=" + centessimoDia2018);
	}
}
