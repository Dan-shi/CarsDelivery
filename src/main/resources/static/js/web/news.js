/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var totalPageNum = 1;
var host = "http://localhost:8080/";

function loadPageNum() {
    $.ajax({
        url: host + "blog/getPageCount?isActive=true&blogType=0", // 目标资源
        cache: true, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "GET", // 请求方式
        success: function (data) {
            if (data.resultCode == 100) {
                buildPageNum(data.resultBody, 1);
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

function buildPageNum(totalNum, curNum) {
    totalNum = Number(totalNum);
    curNum = Number(curNum);
    totalPageNum = totalNum;
    var pageWrapper = $("#pages");
    //清空所有子节点
    pageWrapper.empty();

    if (curNum > 1) {
        var lastPage = $('<li style=" width:75px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (curNum - 1) + ')">&lt;&nbsp;上一页</a></li>')
        pageWrapper.append(lastPage);
    }

    if (curNum < 4) {
        for (var i = 1; i < totalNum + 1; i++) {
            if (i > 4) {
                break
            }
            if (i == curNum) {
                var current = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a class="active">' + curNum + '</a></li>');
                pageWrapper.append(current);
                continue;
            }
            var pageNum = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + i + ')">' + i + '</a></li>');
            pageWrapper.append(pageNum);
        }
    }

    if (curNum >= 4 && totalNum > curNum) {
        var pageNum = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (curNum - 2) + ')">' + (curNum - 2) + '</a></li>');
        pageWrapper.append(pageNum);
        pageNum = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (curNum - 1) + ')">' + (curNum - 1) + '</a></li>');
        pageWrapper.append(pageNum);
        var current = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a>' + curNum + '</a class="active"></li>');
        pageWrapper.append(current);
        pageNum = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (curNum + 1) + ')">' + (curNum + 1) + '</a></li>');
        pageWrapper.append(pageNum);
    }

    if (curNum >= 4 && totalNum == curNum) {
        for (var i = 0; i < 4; i++) {
            if (i == 4) {
                var current = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a class="active">' + curNum + '</a></li>');
                pageWrapper.append(current);
                continue;
            }
            var pageNum = $('<li style=" width:30px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (totalNum - 3 + i) + ')">' + (totalNum - 3 + i) + '</a></li>');
            pageWrapper.append(pageNum);
        }
    }

    if (totalNum > 4 && (totalNum - curNum) > 2) {
        pageWrapper.append($('<li><a>...</a></li>'));
    }

    if (totalNum != curNum) {
        var nextPage = $('<li style=" width:75px; height:30px; border:#CCC 1px solid;"><a onclick="loadBlog(' + (curNum + 1) + ')">下一页&nbsp;&gt;</a></li>')
        pageWrapper.append(nextPage);
    }


}

function loadBlog(pageNum) {

    $.ajax({
        url: host + "blog/getBlogsPage?isActive=true&blogType=0&pageNum=" + pageNum, // 目标资源
        cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
        async: false, //默认是true，即为异步方式
        contentType: "application/json",
        dataType: "json", // 服务器响应的数据类型
        type: "GET", // 请求方式
        success: function (data) {
            if (data.resultCode == 100) {
                buildNewsElements(data.resultBody);
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

    //rebuild page number
    buildPageNum(totalPageNum, pageNum);
}

function buildNewsElements(blogs) {
    var newsList = $("#newsList");
    //clear news list
    newsList.empty();
    for (i = 0; i < 6; i++) {
        if (blogs[i]) {
            newsList.append(getNewsElement(blogs[i]));
        }
    }

}

function getNewsElement(blog) {
    var createTime = blog.createTime.substring(0, 10);
    var day = createTime.substring(8, 10);
    var yearMonth = createTime.substring(0, 7).replace('-', '.');

    return $('<li><a href="/boyuan/newsDetail?blogId=' + blog.blogId + '">' +
        '<ol class="riqia"><span>' + day + '</span><br/><span style=" color:#ccc; font-size:14px;">' + yearMonth + '</span></ol>' +
        '<ol class="wentia">' + blog.title + '</ol>' +
        '<ol class="daana">' + blog.description + '</ol>' +
        '</a></li>');
}

/**
 * Load news list and case list
 */
$(document).ready(function (e) {
    var isLogin = sessionStorage.getItem("isLogin");
    if (!(isLogin == "true")) {
        CommonUtils.loginDefaultUser();
    }
    for (var i = 0; i < 3; i++) {
        loadPageNum();
        if (sessionStorage.getItem("isLoginError") == "true") {
            sessionStorage.setItem("isLoginError", "false");
            CommonUtils.loginDefaultUser();
            CommonUtils.sleep(300, "Login retry");
            continue;
        }
        break;
    }

});