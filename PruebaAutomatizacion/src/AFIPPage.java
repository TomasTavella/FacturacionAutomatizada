import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AFIPPage extends Base {
	
	
	

	
	public AFIPPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	By cuilLocator = By.id("F1:username");
	By siguienteBtnLocator = By.id("F1:btnSiguiente");
	By contrasenaLocator = By.id("F1:password");
	By ingresarBtnLocator = By.id("F1:btnIngresar");
	
	By pageLocator1 = By.xpath("//img[@src = 'frameworkAFIP/v1/img/logo_afip.png']");
	By misServiciosLocator = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div/ul/li[3]");
	//By comprobantesEnLineaLocator = By.xpath("//*[@id=\"root\"]/div/main/div[2]/section[2]/div/div/div[11]");
	By comprobantesEnLineaLocator = By.xpath("//*[@id=\"serviciosMasUtilizados\"]/div/div/div/div[1]/a/div/h3");
	
	
	//novolveramostrar
	
	By guPaKaBtnLocator = By.xpath("//*[@id=\"contenido\"]/form/table/tbody/tr[4]/td/input[2]");
	By generComprBtnLocator = By.id("btn_gen_cmp");
	By cerrarBtnLocator = By.id("novolveramostrar");
	By puntVentTextLocator = By.xpath("//*[@id=\"contenido\"]/form/div/div/table/tbody/tr[1]/th");
	By ddlPtoVentLocator = By.id("puntodeventa");
	By ddlTipoComprLocator = By.id("universocomprobante");
	By continuarBtnLocator = By.xpath("//*[@id=\"contenido\"]/form/input[2]");
	
	By fechaLocator = By.id("fc");
	By conceptoLocator = By.id("idconcepto");
	
	
	By condFrentIVALocator = By.id("idivareceptor");
	By tipoDocLocator = By.id("idtipodocreceptor");
	By dniLocator = By.id("nrodocreceptor");
	By nombreLocator = By.id("razonsocialreceptor");
	By contadoCheckLocator = By.id("formadepago1");
	By continuarBtn2Locator = By.xpath("//*[@id=\"formulario\"]/input[2]");
	
	
	
	By descripcionLocator = By.id("detalle_descripcion1");
	By precioLocator = By.id("detalle_precio1");
	By alicIVALocator = By.id("detalle_tipo_iva1");
	By continuarBtn3Locator = By.xpath("//*[@id=\"contenido\"]/form/input[8]");
	
	By confirmarBtnLocator = By.id("btngenerar");
	By comprGeneradTextLocator = By.xpath("//*[@id=\"botones_comprobante\"]/b");
	By menuBtnLocator = By.xpath("//*[@id=\"contenido\"]/table/tbody/tr[2]/td/input");
	

	

	
	
	public void singIn(String cuil, String contra){
		

		WebDriverWait ewait= newWait();
		try {
			ewait.until(ExpectedConditions.elementToBeClickable(cuilLocator));	
			type(cuil, cuilLocator);
			click(siguienteBtnLocator);
			ewait.until(ExpectedConditions.elementToBeClickable(contrasenaLocator));
			type(contra, contrasenaLocator);
			click(ingresarBtnLocator);
			
		}catch (org.openqa.selenium.TimeoutException e) {
			System.out.print("Error"+ e.getMessage());
			
		}
			
	}
		
			
	
	
	
	public void clickOnComprobantes() throws InterruptedException {
		
		WebDriverWait ewait= newWait();
		try {
			//ewait.until(ExpectedConditions.presenceOfElementLocated(pageLocator1));
			//click(misServiciosLocator);
			ewait.until(ExpectedConditions.presenceOfElementLocated(comprobantesEnLineaLocator));
			click(comprobantesEnLineaLocator);
			Thread.sleep(5000);
			ArrayList<String> tabs = getWindowHandles();
			switchToWindow(tabs, 1);
			click(guPaKaBtnLocator);
			
		}catch (org.openqa.selenium.TimeoutException e) {
			System.out.print("Error"+ e.getMessage());
			
		}
	}
	
	public boolean ingresarPrimerosDatos(int num) throws InterruptedException {
		
		WebDriverWait ewait= newWait();
		System.out.print(num+1 + " || ");
		try {
			ewait.until(ExpectedConditions.elementToBeClickable(generComprBtnLocator));
			click(generComprBtnLocator);
			ewait.until(ExpectedConditions.textToBe(puntVentTextLocator, "Punto de Ventas a utilizar"));
			dropDownList(ddlPtoVentLocator, " 00003-Ayacucho 107 - San Antonio De Padua, Buenos Aires");
			Thread.sleep(500);
			dropDownList(ddlTipoComprLocator, "Factura B");
			
			if(num == 0) {
				ewait.until(ExpectedConditions.elementToBeClickable(cerrarBtnLocator));
				click(cerrarBtnLocator);
			}
			
			click(continuarBtnLocator);
			return true;
			
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.print("Error"+ e.getMessage());
			return false;
		}
	}
	
	
	@SuppressWarnings("finally")
	public String DatosDeFacturacion(RegistroExcel regis) {
		
		WebDriverWait ewait= newWait();
		String inform = new String();
		try {
			ewait.until(ExpectedConditions.elementToBeClickable(fechaLocator));
			String s=regis.getFecha();
//			System.out.print(s);
			//AGREGAR VALIDACION DE SI HAY FECHA.
			type(s,fechaLocator);
			dropDownList(conceptoLocator," Productos");
			click(continuarBtnLocator);
			ewait.until(ExpectedConditions.elementToBeClickable(condFrentIVALocator));
			dropDownList(condFrentIVALocator, " Consumidor Final");
			if(regis.precioMasDeMill()) {
				dropDownList(tipoDocLocator, "DNI");
				type(regis.getDoc(), dniLocator);
			}
			click(contadoCheckLocator);
			Thread.sleep(100);
			click(continuarBtn2Locator);
			ewait.until(ExpectedConditions.elementToBeClickable(descripcionLocator));
			type(regis.getDescrip(), descripcionLocator);
			type(regis.getPrecio(), precioLocator);
			regis.mostrar();
			if(regis.es21Porc())
			{
				dropDownList(alicIVALocator, " 21%");
			}
			else
			{
				dropDownList(alicIVALocator, " 10,5%");
			}
			//Thread.sleep(5000);

			click(continuarBtn3Locator);
			//ewait.until(ExpectedConditions.elementToBeClickable(confirmarBtnLocator));
			Thread.sleep(1000);
			//click(confirmarBtnLocator);
			//AcceptAlert();
			//ewait.until(ExpectedConditions.textToBe(comprGeneradTextLocator, "Comprobante Generado"));
			inform="FACTURADO";
			
			
		} 
		
		catch (org.openqa.selenium.TimeoutException e) 
		{
			inform="NO FACTURADO";
			System.out.print("Error1: "+ e.getMessage());
			

		}
		catch (InterruptedException e) 
		{
			System.out.print("Error2: "+ e.getMessage());
			inform="NO FACTURADO";

		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			System.out.print("Error3: "+ e.getMessage());
			inform="NO FACTURADO";

		}
		finally 
		{
			System.out.println(inform+"\n");
			return inform;
		}
		
	}
	
	
	

}

