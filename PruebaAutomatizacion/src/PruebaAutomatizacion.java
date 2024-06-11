import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class PruebaAutomatizacion {
	
	static WebDriver driver;
	static AFIPPage afipPage;
	static RegistroExcel regis;
	static ArrayList<RegistroExcel> regisList;
	static ArrayList<String> list;
	static long inicio;
	static long fin;
	static String excelPath = "C:\\Users\\tomas\\eclipse-workspace-2024\\PruebaAutomatizacion\\resources\\excel\\FacturasAT.xlsx";
	static String chromeDriverPath = "C:\\Users\\tomas\\eclipse-workspace-2024\\PruebaAutomatizacion\\resources\\chromeDriver\\chromedriver.exe";

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		//inicializo
		regis = new RegistroExcel();
		regisList = regis.readExcelAndSaveInList(excelPath, "Hoja1");
		
		afipPage = new AFIPPage(driver);
		driver = afipPage.chromeDriverConnection(chromeDriverPath);
		driver.manage().window().maximize();
		afipPage.visit("https://auth.afip.gob.ar/contribuyente_/login.xhtml");
		
		
		//INGRESAR CUIL Y CONTRASEÑA
		String cuil="";
		String contra="";
		
		
		afipPage.singIn(cuil, contra);
		afipPage.clickOnComprobantes();
		
		//run
		list = new ArrayList<String>();
		inicio = System.currentTimeMillis();
		for (int i = 0; i < regisList.size(); i++) {
			
			
			
			if(afipPage.ingresarPrimerosDatos(i)) {
				list.add(afipPage.DatosDeFacturacion(regisList.get(i)));
				
				afipPage.visit("https://fe.afip.gob.ar/rcel/jsp/menu_ppal.jsp");
				
			}
			else {
				list.add("NO FACTURADO");
				i=list.size();
			}
		}
		fin = System.currentTimeMillis();
		
		//end
		driver.quit();
		regis = new RegistroExcel();
		regis.writeInformsInExcel("C:\\Users\\tomas\\eclipse-workspace-2024\\PruebaAutomatizacion\\resources\\excel\\FacturasAT.xlsx", "Hoja1", list);
		
		long milisegundos = fin - inicio;
		
		long minutos = milisegundos / 60000;
		
		System.out.println("\n\n------------------------------------------------------------------------------");
		if(minutos > 0)
		{
			System.out.println("\nTiempo total para hacer "+ regisList.size() +" facturas: " + minutos + " Minutos");
			System.out.println("Cantidad promedio de facturas hechas por minuto: " + regisList.size() / minutos);
		}
		else
		{
			System.out.println("\nTiempo total para hacer "+ regisList.size() +" facturas: " + " menos de 1 minuto");
		}
		
	}

}
