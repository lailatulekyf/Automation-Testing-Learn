package tests;

import core.BaseTest;
import core.DriverManager;
import core.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.CartPage;
import saucedemo.DashboardPage;
import saucedemo.LoginPage;

public class CartTests extends BaseTest {

    @Test(priority = 1, groups = {"smoke"}, description = "Verifikasi user bisa menghapus produk dari halaman keranjang (Remove)")
    public void testRemoveProductFromCart() {
        Log.info("Memulai test: Hapus produk dari CartPage");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickAddToCart();
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertTrue(cartPage.isProductInCart(), "Produk seharusnya ada di keranjang sebelum dihapus!");

        Log.info("Mengklik tombol Remove pada produk di keranjang");
        cartPage.clickRemove();

        Assert.assertTrue(cartPage.isCartEmpty(), "Keranjang seharusnya kosong setelah produk dihapus!");
        Log.info("Test Berhasil: Produk sukses dihapus dari keranjang!");
    }

    @Test(priority = 2, groups = {"smoke"}, description = "Verifikasi user bisa kembali berbelanja dari halaman keranjang")
    public void testContinueShoppingFromCart() {
        Log.info("Memulai test: Continue Shopping dari CartPage");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Log.info("Mengklik tombol Continue Shopping");
        cartPage.clickContinueShopping();

        Assert.assertTrue(dashboardPage.isProductsTitleDisplayed(), "User seharusnya kembali ke halaman Products!");
        Log.info("Test Berhasil: Tombol Continue Shopping berfungsi dengan baik!");
    }

    @Test(priority = 3, groups = {"smoke"}, description = "Verifikasi kondisi halaman keranjang saat diakses tanpa menambahkan produk (Keranjang Kosong)")
    public void testAccessCartWhenEmpty() {
        Log.info("Memulai test: Akses CartPage langsung saat keranjang kosong");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        Log.info("Navigasi langsung ke keranjang tanpa klik Add to Cart");
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Log.info("Memverifikasi elemen item tidak tampil di keranjang");
        Assert.assertTrue(cartPage.isCartEmpty(), "Halaman keranjang seharusnya kosong!");

        Log.info("Test Berhasil: Halaman keranjang kosong berhasil ditangani tanpa error!");
    }

    @Test(priority = 4, groups = {"smoke"}, description = "Verifikasi atribut produk (Nama, Harga, dan Kuantitas) sesuai di halaman keranjang")
    public void testValidateProductDetailsInCart() {
        Log.info("Memulai test: Validasi detail nama, harga, dan kuantitas produk di CartPage");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickAddToCart();
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Log.info("Memeriksa informasi detail produk di dalam keranjang");

        Assert.assertEquals(cartPage.getCartItemName(), "Sauce Labs Backpack", "Nama produk di keranjang tidak sesuai!");
        Assert.assertEquals(cartPage.getCartItemPrice(), "$29.99", "Harga produk di keranjang tidak sesuai!");
        Assert.assertEquals(cartPage.getCartQuantity(), "1", "Kuantitas produk di keranjang seharusnya 1!");

        Log.info("Test Berhasil: Informasi detail produk di dalam keranjang valid!");
    }

    @Test(priority = 5, groups = {"smoke"}, description = "Verifikasi perilaku sistem saat mencoba checkout dengan keranjang kosong")
    public void testCheckoutWithEmptyCart() {
        Log.info("Memulai test: Klik Checkout saat keranjang kosong (Edge Case)");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("standardUser"), config.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        dashboardPage.clickCartIcon();

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertTrue(cartPage.isCartEmpty(), "Keranjang harus dalam keadaan kosong!");

        Log.info("Mengklik tombol Checkout pada kondisi keranjang kosong");
        cartPage.clickCheckout();

        // Di SauceDemo, klik checkout pada keranjang kosong tetap diarahkan ke form Checkout Step One
        Log.info("Test Berhasil: Navigasi tombol checkout pada keranjang kosong ter-handle!");
    }
}