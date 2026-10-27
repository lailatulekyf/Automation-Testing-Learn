package saucedemo;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private WebElement cartItem;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//button[text()='Remove']")
    private WebElement removeButton;

    // Locator tambahan untuk validasi item di keranjang
    @FindBy(className = "inventory_item_name")
    private WebElement cartItemName;

    @FindBy(className = "inventory_item_price")
    private WebElement cartItemPrice;

    @FindBy(className = "cart_quantity")
    private WebElement cartQuantity;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart() {
        try {
            waitForElementToBeVisible(cartItem);
            return cartItem.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRemove() {
        waitForElementToBeVisible(removeButton);
        removeButton.click();
    }

    public void clickContinueShopping() {
        waitForElementToBeVisible(continueShoppingButton);
        continueShoppingButton.click();
    }

    public boolean isCartEmpty() {
        try {
            return !cartItem.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public void clickCheckout() {
        waitForElementToBeVisible(checkoutButton);
        checkoutButton.click();
    }


    public String getCartItemName() {
        waitForElementToBeVisible(cartItemName);
        return cartItemName.getText();
    }

    public String getCartItemPrice() {
        waitForElementToBeVisible(cartItemPrice);
        return cartItemPrice.getText();
    }

    public String getCartQuantity() {
        waitForElementToBeVisible(cartQuantity);
        return cartQuantity.getText();
    }
}