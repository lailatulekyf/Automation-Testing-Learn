package tests;

import core.BaseTest;
import core.DriverManager;
import core.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.LoginPage;
import saucedemo.DashboardPage;

public class DashboardTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke"}, description = "Verifikasi user berhasil login dan melihat elemen-elemen utama di dashboard")
    public void testDashboardElementsDisplayed() {
        Log.info("Memulai test: Verifikasi elemen dashboard SauceDemo");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

        Log.info("Memeriksa kemunculan logo 'Swag Labs', judul 'Products', dan ikon keranjang");

        Assert.assertTrue(dashboardPage.isAppLogoDisplayed(), "Logo 'Swag Labs' seharusnya muncul!");
        Assert.assertTrue(dashboardPage.isProductsTitleDisplayed(), "Judul 'Products' seharusnya muncul!");
        Assert.assertTrue(dashboardPage.isCartIconDisplayed(), "Ikon keranjang seharusnya muncul!");

        Log.info("Test Berhasil: Semua elemen dashboard sukses divalidasi!");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Verifikasi user bisa mengklik produk dan melihat halaman detail produk")
    public void testClickProductDetail() {
        Log.info("Memulai test: Klik produk untuk melihat halaman detail");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

        Log.info("Mengklik produk pertama pada daftar produk");
        dashboardPage.clickFirstProduct();

        Log.info("Memverifikasi bahwa halaman berpindah ke detail produk (tombol Back to products muncul)");
        Assert.assertTrue(dashboardPage.isBackToProductsButtonDisplayed(), "Tombol 'Back to products' seharusnya muncul di halaman detail!");

        Log.info("Test Berhasil: Halaman detail produk sukses diakses!");
    }

    @Test(priority = 3, groups = {"smoke"}, description = "Verifikasi user bisa menambahkan produk ke keranjang belanja")
    public void testAddProductToCart() {
        Log.info("Memulai test: Tambah produk ke keranjang (Add to Cart)");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());

        Log.info("Mengklik tombol Add to Cart pada produk");
        dashboardPage.clickAddToCart();

        Log.info("Memverifikasi bahwa produk berhasil masuk ke keranjang (badge keranjang muncul)");
        Assert.assertTrue(dashboardPage.isCartBadgeDisplayed(), "Badge angka pada keranjang seharusnya muncul setelah produk ditambahkan!");

        Log.info("Test Berhasil: Produk sukses dimasukkan ke keranjang!");
    }
}