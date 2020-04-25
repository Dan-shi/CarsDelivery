/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

const Eject = {
    // 全屏遮罩背景
    // _qback: $('<div class="qback"></div>'),
    // alert提示窗
    Ealert(obj) {
        var qback= $('<div class="qback"></div>')
        var alertBox = $('<div class="alertBox"></div>')
        var alertHead = $('<div class="alertHead">' + obj.title + '</div>')
        var alertMes = $('<div class="alertMes">' + obj.message + '</div>')
        var alertBtn = $('<span class="alertBtn">确定</span>').on('click', function () {
            qback.remove();
            alertBox.remove();
        })
        alertBox.append(alertHead);
        alertBox.append(alertMes);
        alertBox.append(alertBtn);
        qback.append(alertBox);
        $('body').append(qback);
        alertBox.css({'marginTop': -alertBox.outerHeight() / 2 + 'px'});
    },
// confirm弹窗
    Econfirm(obj) {
        var qback= $('<div class="qback"></div>')
        var confirmBox = $('<div class="alertBox"></div>')
        var confirmHead = $('<div class="alertHead">' + obj.title + '</div>')
        var confirmMes = $('<div class="alertMes">' + obj.message + '</div>')
        var confirmBtn = $('<span class="ConBtn">确定</span>').on('click', function () {
            qback.remove()
            confirmBox.remove()
            setTimeout(function () {
                obj.define()
            }, 100)
        })
        var confirmcancel = $('<span class="cancel">取消</span>').on('click', function () {
            qback.remove()
            confirmBox.remove()
            setTimeout(function () {
                obj.cancel()
            }, 100)
        })
        confirmBox.append(confirmHead);
        confirmBox.append(confirmMes);
        confirmBox.append(confirmBtn);
        confirmBox.append(confirmcancel);
        qback.append(confirmBox);
        $('body').append(qback);
        confirmBox.css({'marginTop': -confirmBox.outerHeight() / 2 + 'px'});
    },
    // toast提示
    Etoast(mes, time) {
        var qback= $('<div class="qback"></div>')
        var timer = null;
        var ToastBox = $('<div class="ToastBox">' + mes + '</div>')
        qback.append(ToastBox);
        $('body').append(qback);
        ToastBox.css({'marginTop': -ToastBox.outerHeight() / 2 + 'px'});
        clearInterval(timer)
        timer = setInterval(function () {
            time--
            if (time <= 0) {
                clearInterval(timer)
                qback.remove()
                ToastBox.remove()
            }
        }, 1000)
    }
}