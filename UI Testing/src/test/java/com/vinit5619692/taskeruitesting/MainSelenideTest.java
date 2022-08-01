package com.vinit5619692.taskeruitesting;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class MainSelenideTest {

    private static final String APP_URL="http://localhost:8000/";

    @BeforeAll
    static void beforeAll(){
        Configuration.browser = "chrome";
    }
    @BeforeEach
    void setUp(){
        Selenide.open(APP_URL);
    }

    // Create new task
    @Test
    void createTask(){
        Selenide.$("#addButtonMain").click();
        Selenide.$("#addTaskName").sendKeys("auto");
        Selenide.$("#addDateCompleted").sendKeys("2023-01-01");
        Selenide.$("#addButton").click();

    }

    // Delete an existing task
    @Test
    void deleteTask(){
        SelenideElement taskRecord = Selenide.$("#addButtonMain");
        taskRecord.sendKeys(Keys.TAB);
        WebElement taskRecord1 =  Selenide.switchTo().activeElement();
        taskRecord1.sendKeys(Keys.ENTER);// .pressEnter();
        Selenide.$("#removeButton").click();

    }

    // Edit an existing task
    @Test
    void editTask(){
        SelenideElement taskRecord = Selenide.$("#addButtonMain");
        taskRecord.sendKeys(Keys.TAB);
        WebElement taskRecord1 =  Selenide.switchTo().activeElement();
        taskRecord1.sendKeys(Keys.ENTER);// .pressEnter();
        Selenide.$("#editTaskName").sendKeys("autoUpdated");
        Selenide.$("#updateButton").click();

    }
}
