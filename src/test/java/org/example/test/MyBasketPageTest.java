package org.example.test;

import org.junit.jupiter.api.*;
import org.example.base.BaseTest;
import org.example.pages.DetailPage;
import org.example.pages.HomePage;
import org.example.pages.MyBasketPage;
import org.example.pages.SearchPage;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(4)

public class MyBasketPageTest extends BaseTest {

    SearchPage searchPage;
    HomePage homePage;
    DetailPage detailPage;
    MyBasketPage myBasketPage;

    public MyBasketPageTest(){

        this.searchPage = new SearchPage(webDriver);
        this.homePage = new HomePage(webDriver);
        this.detailPage = new DetailPage(webDriver);
        this.myBasketPage = new MyBasketPage(webDriver);

    }

    @BeforeEach
    public void precondition(){

        homePage.homePageCheck();

        searchPage.readDataFromExcelForFirst();
        searchPage.typeDataToSearchBarFirst();
        searchPage.cleanToSearchBar();
        searchPage.readDataFromExcelForSecond();
        searchPage.typeDataToSearchBarSecond();
        searchPage.sendKeyDataWithEnter();
        searchPage.chooseRandomShirt();

        detailPage.getAndWriteDetailsToTxt();
        detailPage.addProductToBasket();


    }
    @Test
    @Order(1)
    public void checkTheBasket() throws InterruptedException {

        myBasketPage.openTheBasket();
        myBasketPage.comparisonOfProductPriceAndBasketPrice();
        myBasketPage.increaseNumberProducts();
        myBasketPage.productNumberCheck();
        Assertions.assertTrue(myBasketPage.deleteProductsFromBasketAndCheck());


    }



}
