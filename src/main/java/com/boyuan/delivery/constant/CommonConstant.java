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
         * User role id
         */
        public static final long USER_ROLE_ID = 1L;
    }

    /**
     * Status code
     */
    public static class Status{

        /**
         * Return 1001 if there is ERROR happened
         */
        public static final int ERROR = -1001;

        public static final int SUCCESS = -1000;
    }

}
