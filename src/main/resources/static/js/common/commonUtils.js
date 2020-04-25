/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

const CommonUtils = {
    /**
     * show alert
     * @param title
     * @param message
     */
    showAlert(title, message) {
        Eject.Ealert({
            title: title,
            message: message
        })
    },

    /**
     * show toast
     * @param message
     */
    showToast(message) {
        Eject.Etoast(message, 3) //默认三秒
    },


    /**
     * 判断字符是否为空的方法
     * @param obj
     * @returns {boolean}
     */
    isEmpty(obj) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    },

    /**
     * check phone number format
     * @param phone
     * @returns {boolean}
     */
    isPhoneNum(phone) {
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            return false;
        }
        return true;
    },

    /**
     * show loading icon
     */
    showLoading() {
        this.showOverlay();
        /**
         * 参数：支持字符串以及对象传参
         * 传值为字符串 默认第一种样式，提示文字为默认loading...
         * 传值为对象时
         * 1.type 类型，只支持五种，可扩展  必填项
         * 2.tip  提示文字  非必填
         * 3.showTip 是否显示提示文字，默认为true  非必填
         * 使用方法
         * loading.showLoading()
         * loading.hideLoading()
         */
        loading.showLoading({
            type: 5,
            showTip: false
        })

    },

    /**
     * Hide loading
     */
    hideLoading() {
        this.hideOverlay();
        loading.hideLoading();
    },

    /**
     * 显示遮罩层
     */
    showOverlay() {
        var overlay = $('<div id="bynOverlay" class="overlay"></div>');
        // 遮罩层宽高分别为页面内容的宽高
        overlay.css({
            'height': $(document).height(),
            'width': $(document).width()
        });
        $('body').append(overlay);
        overlay.show();
    },

    /**
     * Hide overlay
     */
    hideOverlay() {
        $("#bynOverlay").remove();
    }

}