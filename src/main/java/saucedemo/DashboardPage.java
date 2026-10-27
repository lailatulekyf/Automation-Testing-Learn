package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;

    // Locator untuk mengecek apakah kita benar-benar di halaman Products
    private By appLogo = By.className("app_logo");
    private By productsTitle = By.xpath("//*[contains(text(),'Products')]");
    private By cartIcon = By.id("shopping_cart_container");
    private By burgerIcon = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

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

    // Method untuk mengecek apakah teks "Products" / judul muncul (Cepat & Anti-Lelet)
    public boolean isProductsTitleDisplayed() {
        try {
            return driver.findElements(productsTitle).size() > 0 && driver.findElement(productsTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Method untuk mengecek apakah ikon keranjang muncul
    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    // Method untuk mengecek apakah ikon burger muncul
    public boolean isBurgerIconDisplayed() {
        return driver.findElement(burgerIcon).isDisplayed();
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
        try {
            driver.findElement(addToCartButton).click();
        } catch (Exception e) {
            System.out.println("Produk sudah ditambahkan sebelumnya.");
        }
    }

    // Method untuk mengklik ikon keranjang belanja
    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    // Method untuk mengecek apakah badge angka di keranjang muncul (menandakan barang masuk)
    public boolean isCartBadgeDisplayed() {
        try {
            return driver.findElement(cartBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // --- Method Khusus Logout ---

    // Perbarui method clickBurgerMenu di DashboardPage.java
    public void clickBurgerMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(burgerIcon)).click();
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}