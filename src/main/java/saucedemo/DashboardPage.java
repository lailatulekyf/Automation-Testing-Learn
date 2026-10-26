package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    // Locator untuk mengecek apakah kita benar-benar di halaman Products
    private By appLogo = By.className("app_logo");
    private By productsTitle = By.xpath("//*[contains(text(),'Products')]");
    private By cartIcon = By.id("shopping_cart_container");

    // Locator untuk interaksi produk dan halaman detail
    private By firstProductTitle = By.xpath("(//div[@class='inventory_item_name '])[1]");
    private By backToProductsButton = By.id("back-to-products");
    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");

    // Constructor OOP (wajib ada untuk meneruskan driver)
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method untuk mengecek apakah logo teks "Swag Labs" muncul
    public boolean isAppLogoDisplayed() {
        return driver.findElement(appLogo).isDisplayed();
    }

    // Method untuk mengecek apakah teks "Products" / judul muncul
    public boolean isProductsTitleDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    // Method untuk mengecek apakah ikon keranjang muncul
    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    // Method untuk klik nama produk pertama
    public void clickFirstProduct() {
        driver.findElement(firstProductTitle).click();
    }

    // Method untuk mengecek apakah kita sudah berada di halaman detail (tombol Back to products muncul)
    public boolean isBackToProductsButtonDisplayed() {
        try {
            return driver.findElement(backToProductsButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method untuk klik tombol Add to Cart pada produk Sauce Labs Backpack
    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    // Method untuk mengecek apakah badge angka di keranjang muncul (menandakan barang masuk)
    public boolean isCartBadgeDisplayed() {
        try {
            return driver.findElement(cartBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}