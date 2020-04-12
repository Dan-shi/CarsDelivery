package com.boyuan.delivery.constant;

public class CommonConstant {
    /**
     * This constant class is used for mapping url to admin page
     */
    public static class AdminUrlMapping {
        /**
         * Admin ui location in templates
         */
        public static final String ADMIN_PREFIX = "admin/";
        /**
         * Login page
         */
        public static final String ADMIN_LOGIN = "adminLogin";
    }

    /**
     * This constant class is used for mapping url to web ui
     */
    public static class WebUrlMapping {
        /**
         * Web ui location in templates
         */
        public static final String WEB_PREFIX = "boyuan/";
        /**
         * Index page
         */
        public static final String WEB_INDEX = "index";
    }

    /**
     * UserRole name
     */
    public static class UserRole {
        /**
         * User role
         */
        public static final String USER = "USER";

        /**
         * Admin role
         */
        public static final String ADMIN = "ADMIN";
    }

    /**
     * Page setting
     */
    public static class Page {

        /**
         * Page size
         */
        public static final int limit = 10;
    }

}
