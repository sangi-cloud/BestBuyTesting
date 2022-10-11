package bestbuy.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import okio.Timeout;

public class BaseClass {
	public static void initializeDriver() throws IOException {
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Globaldata.properties");
		pro.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: pro.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\arun_ak_pms\\eclipse-workspace\\FireFox\\Browser\\geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\arun_ak_pms\\eclipse-workspace\\Selenium\\Browser\\msedgedriver.exe");
			WebDriver driv = new EdgeDriver();
		}

	}

	public static WebDriver driver;

	public static void browserLaunch(String browsername) {
		switch (browsername) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		}
	}

	public static WebDriver chromeLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	public static void urlLaunch(String url) {
		driver.get(url);

	}

	public static void impWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void sendKeys(WebElement e, String data) {
		e.sendKeys(data);

	}

	public static void click(WebElement e) {
		e.click();

	}

	public static String getText(WebElement e) {
		String txt = e.getText();
		return txt;

	}

	public static String getAttribute(WebElement e) {
		String attribute = e.getAttribute("value");
		return attribute;

	}

	public static void mouseOverActions(WebElement target) {
		Actions act = new Actions(driver);
		act.moveToElement(target).click().perform();
	}

	public static void dragAndDrop(WebElement from, WebElement to) {
		Actions act = new Actions(driver);
		act.dragAndDrop(from, to).perform();
	}

	public static void contextClick(WebElement target) {
		Actions act = new Actions(driver);
		act.contextClick(target).perform();

	}

	public static void tripleClick(WebElement target) {
		Actions act = new Actions(driver);
		act.doubleClick(target).click(target).perform();

	}

	public static void actionClick(WebElement target) {
		Actions act = new Actions(driver);
		act.click(target).perform();

	}

	public static void doubleClick(WebElement target) {
		Actions act = new Actions(driver);
		act.doubleClick(target).perform();

	}

	public static void sendKeys(String data) {
		Actions act = new Actions(driver);
		act.sendKeys(data).perform();

	}

	public static void exit() {

		driver.close();
	}

	public static void frame(int index) {
		driver.switchTo().frame(index);
	}

	public static void frame(String nameorid) {
		driver.switchTo().frame(nameorid);
	}

	public static void frame(WebElement e) {
		driver.switchTo().frame(e);
	}

	public static void defaultContent() {
		driver.switchTo().defaultContent();
	}

	public static void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public static void simpleAlert() {
		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public static void confirmAlert() {
		Alert alrt = driver.switchTo().alert();
		alrt.dismiss();
	}

	public static void promptAlert(String text) {
		Alert alert = driver.switchTo().alert();

		alert.sendKeys(text);
		alert.accept();

	}

	public static void selectByIndex(WebElement e, int index) {
		Select s = new Select(e);
		s.selectByIndex(index);
	}

	public static void selectByValue(WebElement e, String value) {
		Select s1 = new Select(e);
		s1.selectByValue(value);
	}

	public static void selectByVisibleText(WebElement e, String visibletext) {
		Select s2 = new Select(e);
		s2.selectByValue(visibletext);
	}

	public static void deselectById(WebElement e, int index) {
		Select s = new Select(e);
		s.deselectByIndex(index);

	}

	public static void deselectByValue(WebElement e, String value) {
		Select s = new Select(e);
		s.deselectByValue(value);
	}

	public static void deselectByVisibleText(WebElement e, String visibletext) {
		Select s = new Select(e);
		s.deselectByValue(visibletext);
	}

	public static void deselectAll(WebElement e) {
		Select s = new Select(e);
		s.deselectAll();

	}

	// ------------------1---------
	public static String getOptions(WebElement e) {
		Select s = new Select(e);
		List<WebElement> options = s.getOptions();
		for (int i = 0; i < options.size(); i++) {
			WebElement we = options.get(i);
			String text = we.getText();

		}
		return null;
	}

	// ---------------------2-----------
	public static void getAllSelected(WebElement e, int i) {
		Select s = new Select(e);
		List<WebElement> alls = s.getAllSelectedOptions();
		alls.get(i);
	}

	public static void scrollDown(WebElement down) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);

	}

	public static void scrollUp(WebElement up) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", up);

	}

	public static void jsSetAttribute(String data, WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + data + "')", e);

	}
	// ---------------------4-----------

	public static String jsGetAttribute(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object obj = js.executeScript("return arguments[0].getAttribute('value')", e);
		String text = obj.toString();
		return text;

	}

	public static void TakeScreenshotAsSecondsName() throws IOException {
		Date d = new Date();
		int s = d.getSeconds();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		String st = System.getProperty("user.dir");
		File to = new File(st + "\\src\\test\\resources\\" + s + ".png");
		FileUtils.copyFile(from, to);

	}

	public static String TakeScreenshotAs(String name, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\" + name + ".png";
		File to = new File(path);
		FileUtils.copyFile(from, to);
		return path;

	}

	public static void sleep(int sec) throws InterruptedException {
		Thread.sleep(sec);
	}

	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void navigateForward() {
		driver.navigate().forward();

	}

	public static void navigateBack() {
		driver.navigate().back();

	}

	public static void waitForAlert(int seconds) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		Alert alert = w.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}

	public static void waitTillVisibilityofElement(int seconds, WebElement locator) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.visibilityOf(locator));
	}

	public static void waittillFrames(int seconds, WebElement locator) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));

	}

	public static void waitTillElementToBeClickable(int seconds, WebElement locator) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitTillPageLoad(int seconds, WebElement locator) {
		FluentWait<WebDriver> w = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(50))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(Throwable.class);
		Alert until = w.until(ExpectedConditions.alertIsPresent());

	}

	public static void waittillurl(int seconds, WebElement locator, String urlcontains) {
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.urlContains(urlcontains));

	}

	public static void waitTillPageLoad() {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	public static void windowHandles(int index) throws InterruptedException {
		Set<String> allid = driver.getWindowHandles();
		List<String> li = new ArrayList<>();
		li.addAll(allid);
		sleep(5000);
		driver.switchTo().window(li.get(index));
	}

	public static void switchToFrameById(String id) {
		driver.switchTo().frame(id);
	}

	public static void switchToFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	public static void switchToFrameByName(String name) {
		driver.switchTo().frame(name);
	}

	public static void switchToFrameByWebElement(WebElement e) {
		driver.switchTo().frame(e);
	}

	public static void switchOutFromAllFrames() {
		driver.switchTo().defaultContent();
	}

	public static void SwitchFromFrametoInsideFrame(String nameorid) {
		driver.switchTo().frame(nameorid);
	}

	public static void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	public static String excelRead(String filename, String sheetname, int ro, int ce) throws IOException {
		String st = System.getProperty("user.dir");

		File f = new File(st + "\\src\\test\\resources\\" + filename + ".xlsx");
		FileInputStream fi = new FileInputStream(f);

		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet(sheetname);
		Row r = s.getRow(ro);
		Cell c = r.getCell(ce);

		int type = c.getCellType();
		String value = null;

		if (type == 1) {
			value = c.getStringCellValue();

		} else {
			if (DateUtil.isCellDateFormatted(c)) {
				Date d = c.getDateCellValue();

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				value = sdf.format(d);

			} else {
				double db = c.getNumericCellValue();
				long in = (long) db;
				value = String.valueOf(in);

			}
		}

		return value;
	}

	public static String excelCreate(String filename, String sheetname, int rowind, int cellind, String value)
			throws IOException {
		String st = System.getProperty("user.dir");

		File f = new File(st + "\\src\\test\\resources\\" + filename + ".xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s = w.createSheet(sheetname);
		Row r = s.createRow(rowind);
		Cell c = r.createCell(cellind);

		c.setCellValue(value);

		FileOutputStream fo = new FileOutputStream(f);
		w.write(fo);
		return value;
	}

	public static String excelupdate(String filename, String sheetname, int rowind, int cellind, String value1,
			String value2) throws IOException {
		String st = System.getProperty("user.dir");

		File f = new File(st + "\\src\\test\\resources\\" + filename + ".xlsx");
		FileInputStream fi = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(fi);
		Sheet s = w.getSheet(sheetname);
		Row r = s.getRow(rowind);
		Cell c = r.getCell(cellind);

		String s1 = c.getStringCellValue();
		if (s1.equals(value1)) {
			c.setCellValue(value2);
		}
		FileOutputStream fo = new FileOutputStream(f);
		w.write(fo);
		return value2;
	}

	public static void quit() {
		driver.quit();
	}

	public static void date() {
		Date d = new Date();

	}

	public static String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public static void toGetTableRows(String id) {
		WebElement table = driver.findElement(By.id(id));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 0; i < rows.size(); i++) {
			WebElement we = rows.get(i);
			String text = we.getText();
			System.out.println(text);

		}

	}

	public static void softAssertUrlContains(String data, String Message) {
		SoftAssert s = new SoftAssert();
		s.assertTrue(getCurrentUrl().contains(data), Message);
	}

	public static void softAssertEquals(WebElement Actual, String Expected, String Message) {
		SoftAssert s = new SoftAssert();
		s.assertEquals(getAttribute(Actual), Expected, Message);
	}

	public static void softAssertAll() {
		SoftAssert s = new SoftAssert();
		s.assertAll();

	}

	public static void HardAssertUrlContains(String Message, String Expected, WebElement Actual) {
		Assert.assertEquals(Message, Expected, getCurrentUrl());
	}

	public static void hardAssertEquals(String Message, String Expected, WebElement Actual) {
		Assert.assertEquals(Message, Expected, getAttribute(Actual));

	}

	public static void switchToChildwindow() {
		Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		String parentid = it.next();
		String childid = it.next();

		driver.switchTo().window(childid);
	}

	public static void switchToParentwindow() {
		Set<String> allid = driver.getWindowHandles();
		Iterator<String> it = allid.iterator();
		String parentid = it.next();
		String childid = it.next();

		driver.switchTo().window(parentid);
	}

	public static ArrayList<String> getDataFromExcel(String fileName, String sheetName, String cellName, String tcName)
			throws IOException {

		ArrayList<String> a = new ArrayList<>();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\" + fileName + ".xlsx");
		Workbook w = new XSSFWorkbook(fis);
		int column = 0;
		int k = 0;

		int numberOfSheets = w.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			if (w.getSheetName(i).equalsIgnoreCase(sheetName)) {
				Sheet s = w.getSheetAt(i);
				Iterator<Row> rows = s.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> celli = firstrow.cellIterator();
				while (celli.hasNext()) {
					Cell value = celli.next();
					if (value.getStringCellValue().equalsIgnoreCase(cellName)) {
						column = k;
					}
					k++;
				}
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(tcName)) {
						Iterator<Cell> ci = r.cellIterator();
						while (ci.hasNext()) {
							Cell c = ci.next();
							if (c.getCellType() == 1) {
								a.add(c.getStringCellValue());
							} else {
								SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
								if (DateUtil.isCellDateFormatted(c)) {
									a.add(sd.format(c.getDateCellValue()));
								} else {
									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

								}

							}
						}

					}

				}

			}

		}

		return a;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filename) throws IOException {
		File f= new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\jsondata\\"+filename+ ".json");
		
		String jsonContent = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
	
		List<HashMap<String,String>> data=mapper.readValue ( jsonContent, new TypeReference<List<HashMap<String,String>>>() {

		});

		return data;
		
	}
}
