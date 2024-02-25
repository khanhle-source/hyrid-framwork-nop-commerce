package pageObject.sauceLab;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sauceLab.pageUIs.ProductPageUI;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class ProductPageObject extends BasePage {
    WebDriver driver;
    public ProductPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemInProductSortDropdown (String textItem) {
        waitForElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDefaultDropdown(driver,ProductPageUI.SORT_DROPDOWN, textItem );
    }

    public boolean isProductNameSortByAsc () {
        //khai bao ra 1 ArrayList de chua cac product name
        ArrayList<String> productUIList = new ArrayList<>();

        //Lay ra het tat ca cac product name
        List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        // Dung vong lap de getText va add vao ArrayList tren
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
            System.out.println ("Product Name o UI: " + productName.getText());
        }

        // tao ra 1 ArrayList va copy item o ArrayList cu
        ArrayList<String> productSortList = new ArrayList<String>();
        for(String product : productUIList) {
            productSortList.add(product);
        }

        // Sort ArrayList moi
        Collections.sort(productSortList);
        for (String productName : productSortList) {
            System.out.println ("Product Name sau khi sort ASC: " + productName);
        }

        //So sanh 2 ArrayList
        return productSortList.equals(productUIList);
    }

    public boolean isProductNameSortByDes () {
        //khai bao ra 1 ArrayList de chua cac product name
        ArrayList<String> productUIList = new ArrayList<>();

        //Lay ra het tat ca cac product name
        List<WebElement> productNameText = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        // Dung vong lap de getText va add vao ArrayList tren
        for (WebElement productName : productNameText) {
            productUIList.add(productName.getText());
        }

        // tao ra 1 ArrayList va copy item o ArrayList cu
        ArrayList<String> productSortList = new ArrayList<String>();
        for(String product : productUIList) {
            productSortList.add(product);
        }

        // Sort ArrayList moi
        Collections.sort(productSortList);

        // Reverse ArrayList moi
        Collections.reverse(productSortList);

        for (String productName : productSortList) {
            System.out.println ("Product Name sau khi sort DES: " + productName);
        }


        //So sanh 2 ArrayList
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByAsc () {
        //khai bao ra 1 ArrayList de chua cac product price
        ArrayList<Float> productUIList = new ArrayList<Float>();

        //Lay ra het tat ca cac product price
        List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        // Dung vong lap de getText va add vao ArrayList tren
        for (WebElement productPrice : productPriceText) {
            productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
            System.out.println ("Product Price o UI: " + productPrice.getText());
        }

        // tao ra 1 ArrayList va copy item o ArrayList cu
        ArrayList<Float> productSortList = new ArrayList<Float>();
        for(Float product : productUIList) {
            productSortList.add(product);
        }

        // Sort ArrayList moi
        Collections.sort(productSortList);
        for (Float productName : productSortList) {
            System.out.println ("Product Price sau khi sort ASC: " + productName);
        }

        //So sanh 2 ArrayList
        return productSortList.equals(productUIList);
    }

    public boolean isProductPriceSortByDes () {
//khai bao ra 1 ArrayList de chua cac product price
        ArrayList<Float> productUIList = new ArrayList<Float>();

        //Lay ra het tat ca cac product price
        List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        // Dung vong lap de getText va add vao ArrayList tren
        for (WebElement productPrice : productPriceText) {
            productUIList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
        }

        // tao ra 1 ArrayList va copy item o ArrayList cu
        ArrayList<Float> productSortList = new ArrayList<Float>();
        for(Float product : productUIList) {
            productSortList.add(product);
        }

        // Sort ArrayList moi
        Collections.sort(productSortList);
        // Reverse ArrayList moi
        Collections.reverse(productSortList);

        for (Float productName : productSortList) {
            System.out.println ("Product Price sau khi sort DES: " + productName);
        }

        //So sanh 2 ArrayList
        return productSortList.equals(productUIList);
    }

    public boolean isProductNameSortByAscLamda () {
        List <WebElement> elementList = getListWebElement (driver, ProductPageUI.PRODUCT_NAME_TEXT);
        List <String> names = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
        List <String> sortedNames = new ArrayList<String>(names);
        Collections.sort(sortedNames);
        return names.equals(sortedNames);
    }

    public boolean isProductNameSortByDesLamda () {
        List <WebElement> elementList = getListWebElement (driver, ProductPageUI.PRODUCT_NAME_TEXT);
        List <String> names = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
        List <String> sortedNames = new ArrayList<String>(names);
        Collections.sort(sortedNames);
        Collections.reverse(sortedNames);
        return names.equals(sortedNames);
    }
}
