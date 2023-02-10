package com.nopcommerce.user;

public class Topic9_StringFormat {
    public static  String ADDRESS_LINK = "//div[@class='listbox']//li[contains(@class, 'customer-addresses')]";
    public static  String REWARD_POINT_LINK = "//div[@class='listbox']//li[contains(@class, 'reward-points')]";
    public static  String PRODUCT_REVIEW_LINK = "//div[@class='listbox']//li[contains(@class, 'customer-review')]";

    public static String DYNAMIC_LINK_BY_PAGE_NAME = "//div[@class='listbox']//li[contains(@class,'%s')]";
    public static void main (String[] args) {
        clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "customer-addresses");
        clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "reward-points");
        clickToLink(DYNAMIC_LINK_BY_PAGE_NAME, "customer-review");
    }
    public static void clickToLink (String locator) {
        System.out.println("Click to: " +locator);
    }
/*
    // 1 tham so dong
    public static void clickToLink (String dynamicLocator, String pageName) {
        String locator = String.format(dynamicLocator, pageName);
        System.out.println("Click to: " +locator);
    }
    // 2 tham so dong
    public static void clickToLink (String dynamicLocator, String areaName, String pageName) {
        String locator = String.format(dynamicLocator, areaName, pageName);
        System.out.println("Click to: " +locator);
    }
*/
    // 1 - n tham so dong
    public static void clickToLink (String dynamicLocator, String... params) {
        String locator = String.format(dynamicLocator, (Object[]) params);
        System.out.println("Click to: " +locator);
    }
}
