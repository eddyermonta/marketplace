package com.nexsys.marketplace.util;

public class Constants {

    // Private constructor to hide the implicit public one
    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // URL de la API de Platzi
    public static final String PLATZI_API_URL = "https://api.escuelajs.co/api/v1";
    public static final String PLATZI_API_PRODUCTS = "/products";
    public static final String PLATZI_API_CATEGORIES = "/categories";

    // URL de la API de Nexsys
    public static final String NEXSYS_V1 = "/nexsys/v1";

    //SWAGGER DOCS
    public static final String SWAGGER_TITLE = "Marketplace API";
    public static final String SWAGGER_VERSION = "1.0";
    public static final String SWAGGER_DESCRIPTION = "API para gestionar productos y categorías en el marketplace";

    //CATEGORIA BDD
    public static final String CATEGORIA_BDD = "categoria_nexsys";
    public static final String C_ID = "c_id";

    //PRODUCTO BDD
    public static final String PRODUCTO_BDD = "producto_nexsys";
    public static final String P_ID = "p_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String IMAGE = "image";

    //PRODUCTO JSON
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";

    //CATEGORIA JSON
    public static final String NAME = "name";
}
