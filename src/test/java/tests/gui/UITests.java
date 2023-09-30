package tests.gui;


import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import utils.configuration.ReadProperties;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static utils.TestUtils.generateString;


public class UITests extends BaseTest {
    Logger logger = LogManager.getLogger(UITests.class);

    @Description("Verifies the presence of a pop-up message.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "тест на проверку всплывающего сообщения.")
    public void PopUpMessageTest() {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(), ReadProperties.password());

        myProjectsPage.checkPopUpMessage();

        String expectedText = "View comments";
        SelenideElement elementText = $
                (By.xpath("//*[contains(text(),'View comments')]"));
        String actualText = elementText.getText();
        Assert.assertEquals(actualText.contains(actualText),
                expectedText.contains(expectedText), "There is no symbols View comments");

        logger.info("PopUpMessageTest. The 'View comments' pop-up message is present.");
    }

    @Description("Verifies the process of adding project.")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Тест на создание сущности")
    public void CreationEntityTest  () {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(), ReadProperties.password());

        myProjectsPage.createProject(ReadProperties.name());
        Assert.assertTrue(myProjectsPage.getProject().isEnabled());

        logger.info(" CreationEntityTest. Entity Myproject was created.");
    }
    @Description("Checking the input field for boundary values.")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "тест на проверку поля для ввода на граничные значения.")
    public void BoundaryValuesTest (){

        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());
        myProjectsPage.openProject();
        myProjectsPage.setProjectName(generateString(101));
        int actualLength = myProjectsPage.getProjectName().length();
        Assert.assertEquals(actualLength, 100);
        myProjectsPage.setProjectName(generateString(100));
        actualLength = myProjectsPage.getProjectName().length();
        Assert.assertEquals(actualLength, 100);
        myProjectsPage.setProjectName(generateString(50));
        actualLength = myProjectsPage.getProjectName().length();
        Assert.assertEquals(actualLength, 50);

        logger.info("BoundaryValuesTest. The maximum number of input characters field is 100.");
    }
    @Description("Сhecks whether it is possible to enter data exceeding the permissible limits.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = " Test for input of data exceeding permissible limits")
    public void ExceedingMaxTest (){
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());
        myProjectsPage.openProject();
        String inputNAme = generateString(110);
        myProjectsPage.setProjectName(inputNAme);
        String actualNAme = myProjectsPage.getProjectName();
        Assert.assertNotEquals(actualNAme,inputNAme);

        logger.info("ExceedingMaxTest. There is impossible to enter more characters than 100.");
    }

    @Description("Check for successful display of the dialog box.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на отображение диалогового окна.")
    public void DialogBoxTest()
    {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());

        myProjectsPage.showDialog();
        Assert.assertTrue(myProjectsPage.getDialogBox().isEnabled());
        logger.info("DialogBoxTest. Dialog box is displayed.");
    }
    @Description("Check for successful file upload.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на загрузку файла.")
    public void LoadFileTest()
    {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());
        myProjectsPage.loadFile();
        Assert.assertTrue(myProjectsPage.getDownloadedPicture().isEnabled());
        logger.info("LoadFileTest. File is load.");

    }
    @Description("Use incorrect data test.")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на использование некорректных данных.")
    public void IncorrectDataTest()
    {
        open(ReadProperties.getUrl());
        String inputEmail = "abeganskaya98gmail.com";
        myProjectsPage.setIncorrectEmail(inputEmail);
        Assert.assertFalse(myProjectsPage.getProjectsPage().isDisplayed());
        logger.info("IncorrectDataTest.");

    }
    @Description("Reproduce defect test")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Тест на воспроизведение дефекта. Проект не найден")
    public void ReproduceDefectTest()
    {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());
        Assert.assertTrue(myProjectsPage.getDefectProject().isEnabled());
        logger.info("Reproduce defect test.");

    }

    @Test(description = "Тест на удаление сущности.")
    public void DeleteEntityTest()
    {
        open(ReadProperties.getUrl());
        loginStep.successLogin(ReadProperties.email(),ReadProperties.password());
        myProjectsPage.deleteProject();
        Assert.assertFalse(myProjectsPage.getProject().isDisplayed());
        logger.info("DeleteEntityTest. Project is delete.");

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }
}