/*
 *  Copyright 2020 北京渤远物流. All Rights Reserved.
 */

var host = "http://localhost:8080/";
var Ealt = new Eject()

function submitOrder() {
	//get value from page
	var carName = document.getElementById("carName").value;
	var carType = document.getElementById("carType").value;
	var fromPro = document.getElementById("fromLocPro").value;
	var fromCity = document.getElementById("fromLocCity").value;
	var toPro = document.getElementById("toLocPro").value;
	var toCity = document.getElementById("toLocCity").value;
	var cusName = document.getElementById("cusName").value;
	var cusPhone = document.getElementById("cusPhone").value;
	var description = document.getElementById("description").value;

	var fromLoc = fromPro + "-" + fromCity;
	var toLoc = toPro + "-" + toCity;

	//value check
	var errorMsg = "";
	if (isEmpty(carName)) {
		errorMsg = errorMsg + "请填写车型</br>";
	}

	if (isEmpty(carType)) {
		errorMsg = errorMsg + "请选择车型</br>";
	}

	if (isEmpty(fromPro)) {
		errorMsg = errorMsg + "请选择出发省份</br>";
	}

	if (isEmpty(fromCity)) {
		errorMsg = errorMsg + "请选择出发城市</br>";
	}

	if (isEmpty(toPro)) {
		errorMsg = errorMsg + "请选择到达省份</br>";
	}

	if (isEmpty(toCity)) {
		errorMsg = errorMsg + "请选择到达城市</br>";
	}

	if (isEmpty(cusPhone)) {
		errorMsg = errorMsg + "请填写您的电话</br>";
	}

	if (!isEmpty(cusPhone) && !isPhoneNum(cusPhone)) {
		errorMsg = errorMsg + "请填写正确的电话格式</br>";
	}

	if (!isEmpty(errorMsg)) {
		//show model return
		showAlert('输入错误', errorMsg);
		return;
	}

	//order model format
	var order = {
		orderSource: 0,
		orderType: 0,
		carType: carType,
		carName: carName,
		fromLocation: fromLoc,
		toLocation: toLoc,
		cusName: cusName,
		cusPhone: cusPhone,
		description: description
	};

    showLoading();

	//Build order model
	$.ajax({
		url: host + "order/create", // 目标资源
		cache: false, //true 如果当前请求有缓存的话，直接使用缓存。如果该属性设置为 false，则每次都会向服务器请求
		async: false, //默认是true，即为异步方式
		data: JSON.stringify(order),
		contentType: "application/json",
		dataType: "json", // 服务器响应的数据类型
		type: "POST", // 请求方式
		success: function(data) {
			showToast('下单成功');
			console.log(data)
		},
		complete: function(XMLHttpRequest, textStatus) {
			hideLoading();
		},
		error: function() {
			showToast('请求错误');
		}
	});

}

//show alert
function showAlert(title, message) {
    Ealt.Ealert({
        title: title,
        message: message
    })
}

//show confirm model
function showConfirm(title, message, success, cancel) {
    Ealt.Econfirm({
        title: title,
        message: message,
        define: function() {
            success();
        },
        cancel: function() {
            cancel();
        }
    })
}

//show toast
function showToast(message) {
    Ealt.Etoast(message, 3) //默认三秒
}

function isPhoneNum(phone) {
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	if (!myreg.test(phone)) {
		return false;
	}
	return true;
}


//判断字符是否为空的方法
function isEmpty(obj) {
	if (typeof obj == "undefined" || obj == null || obj == "") {
		return true;
	} else {
		return false;
	}
}

function showLoading() {

	showOverlay();
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

}

/**
 * Hide loading
 */
function hideLoading() {
	hideOverlay();
	loading.hideLoading();
}

/**
 * 显示遮罩层
 */
function showOverlay() {
	// 遮罩层宽高分别为页面内容的宽高
	$('.overlay').css({
		'height': $(document).height(),
		'width': $(document).width()
	});
	$('.overlay').show();
}

/**
 * Hide overlay
 */
function hideOverlay() {
	$('.overlay').hide();
}

//city select
try {
	var sf = new Array();
	sf[0] = new Array("北京市", "东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
	sf[1] = new Array("上海市", "黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
	sf[2] = new Array("天津市", "和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
	sf[3] = new Array("重庆市", "万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁 |大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
	sf[4] = new Array("河北省", "石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
	sf[5] = new Array("山西省", "太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
	sf[6] = new Array("内蒙古自治区", "呼和浩特|包头|乌海|赤峰|呼伦贝尔盟|阿拉善盟|哲里木盟|兴安盟|乌兰察布盟|锡林郭勒盟|巴彦淖尔盟|伊克昭盟");
	sf[7] = new Array("辽宁省", "沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
	sf[8] = new Array("吉林省", "长春|吉林|四平|辽源|通化|白山|松原|白城|延边");
	sf[9] = new Array("黑龙江省", "哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河|大兴安岭");
	sf[10] = new Array("江苏省", "南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
	sf[11] = new Array("浙江省", "杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
	sf[12] = new Array("安徽省", "合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
	sf[13] = new Array("福建省", "福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
	sf[14] = new Array("江西省", "南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
	sf[15] = new Array("山东省", "济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
	sf[16] = new Array("河南省", "郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
	sf[17] = new Array("湖北省", "武汉|宜昌|荆州|襄樊|黄石|荆门|黄冈|十堰|恩施|潜江|天门|仙桃|随州|咸宁|孝感|鄂州");
	sf[18] = new Array("湖南省", "长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|湘西|张家界");
	sf[19] = new Array("广东省", "广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
	sf[20] = new Array("广西壮族自治区", "南宁|柳州|桂林|梧州|北海|防城港|钦州|贵港|玉林|南宁地区|柳州地区|贺州|百色|河池");
	sf[21] = new Array("海南省", "海口|三亚");
	sf[22] = new Array("四川省", "成都|绵阳|德阳|自贡|攀枝花|广元|内江|乐山|南充|宜宾|广安|达川|雅安|眉山|甘孜|凉山|泸州");
	sf[23] = new Array("贵州省", "贵阳|六盘水|遵义|安顺|铜仁|黔西南|毕节|黔东南|黔南");
	sf[24] = new Array("云南省", "昆明|大理|曲靖|玉溪|昭通|楚雄|红河|文山|思茅|西双版纳|保山|德宏|丽江|怒江|迪庆|临沧");
	sf[25] = new Array("西藏自治区", "拉萨|日喀则|山南|林芝|昌都|阿里|那曲");
	sf[26] = new Array("陕西省", "西安|宝鸡|咸阳|铜川|渭南|延安|榆林|汉中|安康|商洛");
	sf[27] = new Array("甘肃省", "兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳|临夏|甘南");
	sf[28] = new Array("宁夏回族自治区", "银川|石嘴山|吴忠|固原");
	sf[29] = new Array("青海省", "西宁|海东|海南|海北|黄南|玉树|果洛|海西");
	sf[30] = new Array("新疆维吾尔族自治区", "乌鲁木齐|石河子|克拉玛依|伊犁|巴音郭勒|昌吉|克孜勒苏柯尔克孜|博尔塔拉|吐鲁番|哈密|喀什|和田|阿克苏");
	sf[31] = new Array("香港特别行政区", "香港特别行政区");
	sf[32] = new Array("澳门特别行政区", "澳门特别行政区");
	sf[33] = new Array("台湾省", "台北|高雄|台中|台南|屏东|南投|云林|新竹|彰化|苗栗|嘉义|花莲|桃园|宜兰|基隆|台东|金门|马祖|澎湖");
	sf[34] = new Array("其它", "北美洲|南美洲|亚洲|非洲|欧洲|大洋洲");
} catch (e) {
	alert(e);
}

$(document).ready(function(e) {
	$("#fromLocPro").change(function() {
		try {
			var pro = $(this).val();
			var i, j, tmpcity = new Array();
			for (i = 0; i < sf.length; i++) {
				if (pro == sf[i][0].toString()) {
					tmpcity = sf[i][1].split("|");
					$("#fromLocCity").html("");
					for (j = 0; j < tmpcity.length; j++) {
						$("#fromLocCity").append("<option>" + tmpcity[j] + "</option>");
					}
				}
			}
		} catch (e) {
			alert(e);
		}
	});

	$("#toLocPro").change(function() {
		try {
			var pro = $(this).val();
			var i, j, tmpcity = new Array();
			for (i = 0; i < sf.length; i++) {
				if (pro == sf[i][0].toString()) {
					tmpcity = sf[i][1].split("|");
					$("#toLocCity").html("");
					for (j = 0; j < tmpcity.length; j++) {
						$("#toLocCity").append("<option>" + tmpcity[j] + "</option>");
					}
				}
			}
		} catch (e) {
			alert(e);
		}
	});
});

/**
 *
 * @authors liningning
 * @date    2019-08-07 15:32:21
 * @version $Id$
 */
function Eject(){
    var _this = this;
    // 全屏遮罩背景
    var qback = $('<div class="qback"></div>')
    // alert提示窗
    _this.Ealert = function(obj){
        var alertBox = $('<div class="alertBox"></div>')
        var alertHead = $('<div class="alertHead">'+obj.title+'</div>')
        var alertMes = $('<div class="alertMes">'+obj.message+'</div>')
        var alertBtn = $('<span class="alertBtn">确定</span>').on('click',function(){
            qback.remove();
            alertBox.remove();
        })
        alertBox.append(alertHead);
        alertBox.append(alertMes);
        alertBox.append(alertBtn);
        qback.append(alertBox);
        $('body').append(qback);
        alertBox.css({'marginTop':-alertBox.outerHeight()/2+'px'});
    }
    // confirm弹窗
    _this.Econfirm = function(obj){
        var confirmBox = $('<div class="alertBox"></div>')
        var confirmHead = $('<div class="alertHead">'+obj.title+'</div>')
        var confirmMes = $('<div class="alertMes">'+obj.message+'</div>')
        var confirmBtn = $('<span class="ConBtn">确定</span>').on('click',function(){
            qback.remove()
            confirmBox.remove()
            setTimeout(function(){
                obj.define()
            },100)
        })
        var confirmcancel = $('<span class="cancel">取消</span>').on('click',function(){
            qback.remove()
            confirmBox.remove()
            setTimeout(function(){
                obj.cancel()
            },100)
        })
        confirmBox.append(confirmHead);
        confirmBox.append(confirmMes);
        confirmBox.append(confirmBtn);
        confirmBox.append(confirmcancel);
        qback.append(confirmBox);
        $('body').append(qback);
        confirmBox.css({'marginTop':-confirmBox.outerHeight()/2+'px'});
    },
        // toast提示
        _this.Etoast = function(mes,time){
            var timer= null;
            var ToastBox = $('<div class="ToastBox">'+mes+'</div>')
            qback.append(ToastBox);
            $('body').append(qback);
            ToastBox.css({'marginTop':-ToastBox.outerHeight()/2+'px'});
            clearInterval(timer)
            timer = setInterval(function(){
                time--
                if(time<=0){
                    clearInterval(timer)
                    qback.remove()
                    ToastBox.remove()
                }
            },1000)
        }
}