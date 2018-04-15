package seleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import com.csvreader.CsvReader;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class test {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String info=new String();
  String stringGitAddress=new String();
  @Before
  public void setUp() throws Exception {
	  	System.setProperty("webdriver.chrome.driver","E:/JAVAѧϰ/test/chromedriver.exe"); // �˴�PATH�滻Ϊ���chromedriver����·��
	    driver = new ChromeDriver();
  }
  
  @Test
  public void test1() throws Exception {   
    File file = new File("E:/input.xls");  
    try {  
        // ��������������ȡExcel getAbsolutePath()�ļ��ľ���·��
        InputStream is = new FileInputStream(file.getAbsolutePath());  
        // jxl�ṩ��Workbook��  
        Workbook wkb = Workbook.getWorkbook(is);  
        // Excel��ҳǩ����  
        int size = wkb.getNumberOfSheets();  
        for (int i = 0; i < size; i++) {  
        // ÿҳǩ����һ��Sheet����  
        Sheet sheet = wkb.getSheet(i);  
        // sheet.getRows()���ظ�ҳ��������  
           for (int j = 0; j < sheet.getRows(); j++) {  
                    String stringId = sheet.getCell(0, j).getContents(); 
                    String password = stringId.substring(4);
                    stringGitAddress = sheet.getCell(1, j).getContents(); 
                    baseUrl = "https://psych.liebes.top/";
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    driver.get(baseUrl + "/st");
                    driver.findElement(By.id("username")).clear();
                    driver.findElement(By.id("username")).sendKeys(stringId);
                    driver.findElement(By.id("password")).clear();
                    driver.findElement(By.id("password")).sendKeys(password);
                    driver.findElement(By.id("submitButton")).click();
                    info = driver.findElement(By.tagName("p")).getText();
                    if(info.equals(stringGitAddress)){
                    	 assertEquals(info,stringGitAddress);
                        System.out.println(stringId+"ͨ��");                    	
                    }else{
                    	System.out.println(stringId+"��ͨ��");
                    	}
            }  
           wkb.close();
        }  
    } catch (FileNotFoundException e) {  
        e.printStackTrace();  
    } catch (BiffException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  
 
    

}

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}