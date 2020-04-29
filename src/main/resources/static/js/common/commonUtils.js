/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

const CommonUtils = {
    loginDefaultUser() {
        var defaultUser = {
            username: "tourist",
            password: "123456"
        };


        //Build order model
        $.ajax({
            url: host + "user/login", // 目标资源
            cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
            async: false, //默认是true，即为异步方式
            data: JSON.stringify(defaultUser),
            contentType: "application/json",
            dataType: "json", // 服务器响应的数据类型
            type: "POST", // 请求方式
            success: function (data, status, xhr) {
                if (xhr.status == 200) {
                    sessionStorage.setItem("isLogin", true);
                    console.log(data)
                }
            },
            error: function () {
                this.showToast('请求错误, 请重试!');
                console.log("Tourist login error");
            }
        });
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