/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

function submitBlog(type) {
    var title = document.getElementById("title").value;
    var descr = document.getElementById("description").value;
    var content = document.getElementById("content").value;

    //value check
    var errorMsg = "";
    if (CommonUtils.isEmpty(title)) {
        errorMsg = errorMsg + "请填写标题</br>";
    }

    if (CommonUtils.isEmpty(descr)) {
        errorMsg = errorMsg + "请填写摘要</br>";
    }

    if (CommonUtils.isEmpty(content)) {
        errorMsg = errorMsg + "请填写内容</br>";
    }

    if (!CommonUtils.isEmpty(errorMsg)) {
        //show model return
        CommonUtils.showAlert('输入错误', errorMsg);
        return;
    }

    //blog model format
    var blog = {
        title: title,
        description: descr,
        blogType: type,
        content: content
    };

    CommonUtils.showLoading();

    $.ajax({
        url: host + "blog/create", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        data: JSON.stringify(blog),
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "POST", // 请求方式
        success: function (data, status, xhr) {
            if (data.resultCode == 100) {
                CommonUtils.showToast("创建成功");
                setTimeout("location.reload()", 1500);
            } else {
                CommonUtils.showToast("创建失败: " + data.resultMessage);
            }

        },
        complete: function () {
            CommonUtils.hideLoading();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            CommonUtils.showToast('请求错误');
        }
    });
}