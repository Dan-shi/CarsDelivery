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

        /**
         * Order management
         */
        public static final String ADMIN_ORDER = "orders";
        /**
         * News management
         */
        public static final String ADMIN_NEWS = "news";

        /**
         * Case management
         */
        public static final String ADMIN_CASE = "cases";
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

        public static final String WEB_ORDER = "makeOrder";

        public static final String WEB_ABOUT = "about";

        public static final String WEB_CASES = "cases";

        public static final String WEB_NEWS = "news";

        public static final String WEB_NEWS_DETAIL = "newsDetail";

        public static final String WEB_ERROR = "error";

    }

    /**
     * UserRoleInfo name
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
     * User permission
     */
    public static class Permission {
        /**
         * View permission
         */
        public static final String VIEW = "VIEW";

        /**
         * Admin permission
         */
        public static final String ADMIN = "ADMIN";

        /**
         * Search permission
         */
        public static final String SEARCH = "SEARCH";

        /**
         * Create order
         */
        public static final String CREATE_ORDER = "CREATE_ORDER";

        /**
         * Could view own user info and register
         */
        public static final String VIEW_USER = "VIEW_USER";

        /**
         * Register a new user
         */
        public static final String CREATE_USER = "CREATE_USER";
    }

    /**
     * Page setting
     */
    public static class Page {

        /**
         * Page size
         */
        public static final int limit = 8;
    }

    /**
     * Blog type
     */
    public static class BlogType {
        /**
         * News type
         */
        public static final int NEWS = 0;

        /**
         * Cases type
         */
        public static final int CASES = 1;
    }
}
