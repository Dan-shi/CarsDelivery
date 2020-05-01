/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";

function loadBlog(type) {

    $.ajax({
        url: host + "blog/getBlogsPage?isActive=true&blogType=" + type + "&pageNum=1", // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "GET", // 请求方式
        success: function (data) {
            if (data.resultCode == 100) {
                buildBlogElements(data.resultBody, type);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if (XMLHttpRequest.status == 403) {
                sessionStorage.setItem("isLoginError", "true");
            }
        }
    });
}

function buildBlogElements(blogs, type) {
    if (type == 0) {
        buildNewsElements(blogs);
    }

    if (type == 1) {
        buildCasesElements(blogs);
    }
}

function buildNewsElements(blogs) {
    var leftNews = $("#indexNewsLeft");
    var rigntNews = $("#indexNewsRight");
    var imageUrl = "/images/tp_54.jpg";
    var newsImage = $("#newsImg").attr("src", imageUrl);
    for (i = 0; i < 2; i++) {
        leftNews.append(getNewsElement(blogs[i]));
    }

    for (i = 2; i < 6; i++) {
        rigntNews.append(getNewsElement(blogs[i]))
    }

}

function buildCasesElements(blogs) {
    var caseList = $("#idxCases");
    for (i = 0; i < 4; i++) {
        caseList.append(getCaseElement(blogs[i], i + 1));
    }
}

function getNewsElement(blog) {
    var createTime = blog.createTime.substring(0, 10);
    var day = createTime.substring(8, 10);
    var yearMonth = createTime.substring(0, 7).replace('-', '.');

    return $('<li class="xwl"><a href="' + "/boyuan/newsDetail?" + blog.blogId + '">' +
        '<ol class="riqi">' + day + '<br/><span style=" color:#ccc; font-size:14px;">' + yearMonth + '</span></ol>' +
        '<ol class="wenti"><span class="idxNewsTitle">' + blog.title + '</span></ol>' +
        '<ol class="daan"><span class="idxNewsDesc">' + blog.description + '</span></ol>' +
        '</a></li>');
}

function getCaseElement(blog, i) {
    var imageUrl = "/images/cases/cases_" + i + ".jpg";
    var href = "/boyuan/newsDetail?" + blog.blogId;
    return $('<li>' +
        '<div class="anlitp"><a href="' + href + '">' +
        '<img src="' + imageUrl + '"/><span>查看案例<br />服务热线：400-6566-856</span>' +
        '</a></div>' +
        '<div class="anlinr">' +
        '<div style=" width:100%; text-align:center;font-size:18px; font-weight:bolder; line-height:35px;">' + blog.title + '</div>' +
        '<div style=" font-size:14px; line-height:20px; padding:10px; text-align: center;height: 30px; overflow: hidden;"><a href="' + href + '">' + blog.description + '</a></div>' +
        '</div>' +
        '<div class="anli_link"><div class="hq"><a href="/boyuan/makeOrder">获取报价</a></div><div class="zx"><a href="/boyuan/makeOrder">在线咨询</a></div></div>' +
        '</li>');
}

/**
 * Load news list and case list
 */
$(document).ready(function (e) {
    var isLogin = sessionStorage.getItem("isLogin");
    if (!(isLogin == "true")) {
        CommonUtils.loginDefaultUser();
    }
    for (i = 0; i < 3; i++) {
        loadBlog(0);
        if (sessionStorage.getItem("isLoginError") == "true") {
            sessionStorage.setItem("isLoginError", "false");
            CommonUtils.loginDefaultUser();
            CommonUtils.sleep(300, "Login retry");
            continue;
        }
        loadBlog(1);
        break;
    }

});

