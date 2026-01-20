package com.comcast.routes;

public class Routes {
    public static final String BASE_URI = "https://fakestoreapi.com/";

    // Product
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCT_WITH_LIMIT = "/products?limit={limit}";
    public static final String GET_PRODUCT_SORTED = "/products?sort={order}";
    public static final String GET_ALL_CATEGORIES = "/products/categories";
    public static final String GET_PRODUCT_BY_CATEGORY = "/products/category/{category}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";


    // Cart

    public static final String GET_ALL_CARTS = "/carts";
    public static final String GET_CART_BY_ID = "/carts/{id}";
    public static final String GET_CART_WITH_LIMIT = "/carts?limit={limit}";
    public static final String GET_CART_SORTED = "/carts?sort={order}";
    public static final String GET_CART_WITHIN_DATE_RANGE = "/carts?startDate={startDate}&endDate={endDate}";
    public static final String GET_CART_BY_USER_ID = "/carts/user/{userId}";
    public static final String ADD_NEW_CART = "/carts";
    public static final String UPDATE_CART = "/carts/{id}";
    public static final String DELETE_CART = "/carts/{id}";
    

    private Routes() {}
}
