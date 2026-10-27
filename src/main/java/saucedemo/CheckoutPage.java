package saucedemo;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement successMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void inputCustomerInfo(String firstName, String lastName, String postalCode) {
        waitForElementToBeVisible(firstNameField);
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        waitForElementToBeVisible(continueButton);
        continueButton.click();
    }

    public void clickFinish() {
        waitForElementToBeVisible(finishButton);
        finishButton.click();
    }

    public boolean isCheckoutSuccessful() {
        try {
            waitForElementToBeVisible(successMessage);
            return successMessage.isDisplayed() && successMessage.getText().contains("Thank you for your order");
        } catch (Exception e) {
            return false;
        }
    }
}