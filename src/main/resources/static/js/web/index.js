/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";

function loadNews() {
    $.ajax({
        url: host + "blog/getBlogsPage", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        data: JSON.stringify(order),
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "POST", // 请求方式
        success: function (data) {
            if (data.resultCode == 100) {
                CommonUtils.showToast('下单成功');
            } else if (data.resultCode == -201) {
                CommonUtils.showToast('订单已存在, 请勿重复提交');
            } else {
                CommonUtils.showToast('下单失败, 请稍后再试');
            }
            console.log(data)
        },
        complete: function (XMLHttpRequest, textStatus) {
            CommonUtils.hideLoading();
        },
        error: function () {
            CommonUtils.showToast('请求错误');
        }
    });
}

/**
 * Load news list and case list
 */
$(document).ready(function (e) {

});

