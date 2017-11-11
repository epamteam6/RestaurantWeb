package com.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class LanguageService {

    private static LanguageService instance = new LanguageService();

    public static LanguageService getInstance() {

        if (instance == null)
            instance = new LanguageService();
        return instance;
    }

    private LanguageService() {
    }

    /**
     * Specify language for localisation
     */
    public enum Language {
        EN, //english
        RU, //russian
    }

    /**
     * Add page types, according .jsp file name you've created.
     * Place localisations in .properties file with the same name.
     */
    public enum PageType {

        // General pages
        INDEX,
        ABOUT,
        SUCCESS,

        // Session pages
        SESSION_ERROR,
        SESSION_JOIN,
        SESSION_LOGIN,
        SESSION_LOGIN_ERROR,
        SESSION_LOGOUT,

        // Admin pages
        ADMIN_BILL_CREATION,
        ADMIN_CONFIRMATION,
        ADMIN_PAID_ORDERS,
        ADMIN_READY_ORDERS,

        // User pages
        USER_CREATE_ORDER,
        USER_CREATED_ORDERS,
        USER_DONE_ORDERS,
        USER_PAYMENT,

        // Services
        FOOTER,
        HEADER,
        HEADER_ALL,
    }

    /**
     * Constructs path where localisation for page placed
     *
     * @param language
     * @param type
     * @return
     */
    private static Properties getPageProperties(Language language, PageType type) {

        try {
            Properties properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream("localisations/" +
                    type.toString().toLowerCase() + ".properties"),
                    Charset.forName("Windows-1251"))); // Configure it if needed

            return properties;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns string, specified for element of view
     *
     * @param language
     * @param type
     * @param elementType
     * @return
     */
    public static String elementName(Language language, PageType type, String elementType) {

        String elementName;

        try {
            elementName = getPageProperties(language, type).getProperty(elementType);

            if (elementName == null)
                throw new IllegalArgumentException();

        } catch (NullPointerException | IllegalArgumentException e) {

            //todo - Log it
            e.printStackTrace();

            if (e instanceof NullPointerException) {
                elementName = "<-Missing window-> "
                        + type + " for "
                        + language.toString().toLowerCase();
            } else {
                elementName = "<-Missing element-> "
                        + type + "->" + elementType
                        + " for " + language.toString().toLowerCase();
            }
        }

        return elementName;
    }
}
