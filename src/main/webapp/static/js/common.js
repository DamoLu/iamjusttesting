var timeout, Common = {
        clearAction: function () {
            var form = $(this).parents("form");
            form.find("input[type='text']").val("");
            form.find("select[class='form-control']").val("");
            if($("select.select2").length > 0){
            	$("select.select2").each(function(i,e){$(e).select2("val","")});
            }
        },
        tokenCheck: function checkToken() {
            var token = $("#tokenGrant").val();
            if ("" == token || undefined == token || null == token) {
                return false;
            } else {
                $("#tokenCheck").val(token);
                $("#tokenGrant").val("");
                return true;
            }
        },
        alert: function (message, callback) {
            swal({
                title: message || ''
            }, callback || function () {
                });
        },
        warning: function (message, callback) {
            if (typeof(message) == "undefined" || message == "") {
                message = "系统异常，请联系管理员！";
            }
            swal({
                title: message || '',
                type: "warning"
            }, callback || function () {
                });
        },
        error: function (message, callback) {
            if (typeof(message) == "undefined" || message == "") {
                message = "系统异常，请联系管理员！";
            }
            swal({
                title: message || '',
                type: "error"
            }, callback || function () {
                });
        },
        exportAlert: function (message, callback) {
            swal({
                title: "当导出数据量比较大时，生成文件时间会稍长，请您耐心等待",
                type: "warning",
                confirmButtonText: "关闭",
            }, callback || function () {
                });
        },
        confirm: function (title, message, callback) {
            return swal({
                    title: title,
                    text: message,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是的",
                    cancelButtonText: "让我再考虑一下…",
                    closeOnConfirm: false,
                    closeOnCancel: true,
                    showLoaderOnConfirm: true
                },
                callback || function () {
                });

        },
        confirm2: function (title, message, btns, callback) {
            return swal({
                    title: title,
                    text: message,
                    type: "success",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: btns[0],
                    cancelButtonText: btns[1],
                    closeOnConfirm: false,
                    closeOnCancel: true,
                    showLoaderOnConfirm: true
                },
                callback || function () {
                });

        },
        confirm3: function (title, message, type, btns, callback) {
            return swal({
                    title: title,
                    text: message,
                    type: type || "success",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: btns[0],
                    cancelButtonText: btns[1],
                    closeOnConfirm: true,
                    closeOnCancel: true,
                },
                callback || function () {
                });

        },
        prompt: function (title, message, type, btns, callback) {
            return swal({
                title: title,
                text: message,
                type: type,
                showCancelButton: true,
                closeOnConfirm: false,
                confirmButtonText: btns[0],
                cancelButtonText: btns[1],
                animation: "slide-from-top"
            }, callback || function () {
                });
        },
        msg: function (title, message, callback) {
            //alert(message);
            swal({
                title: title || '',
                text: message || ''
            }, callback || function () {
                });
        },
        loading: function () {
            clearTimeout(timeout);
            if (layer != null) {
                index = layer.load(0);
            }
            timeout = setTimeout(function () {
                Common.closeLoading();
                Common.warning("加载画面超时");
            }, 10000);
        },
        closeLoading: function () {
            if (index != null) {
                layer.close(index);
            }
            clearTimeout(timeout);
        },

        // 生成tr
        makeTr: function (name, value, bold) {
            var tr = "<tr><td style='width:15%; line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:85%;' colspan='5' >" + (bold ? '<b>' : '') + (value || "") + (bold ? '</b>' : '') + "</td></tr>";
            return tr;
        },

        // 生成tr
        makeTr2: function (name, value, name2, value2, bold) {
            var tr = "<tr><td style='width:15%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:35%;' colspan='2' >" + (bold ? '<b>' : '') + (value || "") + (bold ? '</b>' : '') + "</td>";
            tr += "<td style='width:15%;  line-height:25px;height:25px;' align='right'>" + (bold ? '<b>' : '') + (name2 || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:35%;' colspan='2' >" + (bold ? '<b>' : '') + (value2 || "") + (bold ? '</b>' : '') + "</td></tr>";
            return tr;
        },

        // 生成tr
        makeTr2_1: function (name, value, name2, value2, bold) {
            var tr = "<tr><td style='width:15%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:65%;' colspan='3' >" + (bold ? '<b>' : '') + (value || "") + (bold ? '</b>' : '') + "</td>";
            tr += "<td style='width:10%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name2 || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:10%;' >" + (bold ? '<b>' : '') + (value2 || "") + (bold ? '</b>' : '') + "</td></tr>";
            return tr;
        },

        // 生成tr
        makeTr3: function (name, value, name2, value2, name3, value3, bold) {
            var tr = "<tr><td style='width:15%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:20%;'>" + (bold ? '<b>' : '') + (value || "") + (bold ? '</b>' : '') + "</td>";
            tr += "<td style='width:10%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name2 || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:30%;'>" + (bold ? '<b>' : '') + (value2 || "") + (bold ? '</b>' : '') + "</td>";
            tr += "<td style='width:10%;  line-height:25px;height:25px;'>" + (bold ? '<b>' : '') + (name3 || "") + (bold ? '</b>' : '') + "：</td>";
            tr += "<td style='width:10%;'>" + (bold ? '<b>' : '') + (value3 || "") + (bold ? '</b>' : '') + "</td></tr>";
            return tr;
        },

        // 序号格式化
        indexFormat: function (value, row, index) {
            return index + 1;
        },

        // 日期格式化
        dateFormat: function (value, type) {
            var dateStr = (value && value.toString().indexOf("-") > 0 && new Date(value).format(type || 'yyyy-MM-dd hh:mm:ss'))
                || (value && value.toString().indexOf("-") == 0 && (value = value.toString().length == 11 ? value * 1000 : value) && new Date(parseInt(value)).format(type || 'yyyy-MM-dd hh:mm:ss'))
                || (value && (value = value.toString().length == 10 ? value * 1000 : value) && new Date(parseInt(value)).format(type || 'yyyy-MM-dd hh:mm:ss')) || "";
            return dateStr;
        },

        // 日期格式化
        dateFormatYYYYMMDD: function (value) {
            var dateStr = "";
            if (value != null && value != "0" && value != 0 && value != "") {
                dateStr = (value && value.toString().indexOf("-") > 0 && new Date(value).format('yyyy-MM-dd'))
                    || (value && value.toString().indexOf("-") == 0 && (value = value.toString().length == 11 ? value * 1000 : value) && new Date(parseInt(value)).format('yyyy-MM-dd'))
                    || (value && (value = value.toString().length == 10 ? value * 1000 : value) && new Date(parseInt(value)).format('yyyy-MM-dd')) || "";
            }
            return dateStr;
        },

        // 日期格式化
        dateFormatYMDHMS: function (value) {
            var dateStr = (value && value.toString().indexOf("-") > 0 && new Date(value).format('yyyy-MM-dd hh:mm:ss'))
                || (value && value.toString().indexOf("-") == 0 && (value = value.toString().length == 11 ? value * 1000 : value) && new Date(parseInt(value)).format('yyyy-MM-dd hh:mm:ss'))
                || (value && (value = value.toString().length == 10 ? value * 1000 : value) && new Date(parseInt(value)).format('yyyy-MM-dd hh:mm:ss')) || "";
            return dateStr;
        },

        // 性别格式化
        genderFormat: function (value) {
            var genderStr = (value && value == 0 ? "男" : "女") || "";
            return genderStr;
        },

        // 婚姻状况格式化
        marryFormat: function (value) {
            var marryStr = (value && value == 0 ? "未婚" : "已婚") || "";
            return marryStr;
        },

        // 性别格式化
        sexFormat: function (value) {
            var sexStr = value && (value == 0 ? "女" : "男") || "-";
            return sexStr;
        },

        // 启用/禁用
        statusFormat: function (value) {
            var str = value == 0 ? "启用" : "禁用";
            return str;
        },

        // youwu
        isExistsFormat: function (value) {
            var str = value == 0 ? "有" : "无";
            return str;
        },

        // 外访/协防
        requestTypeFormat: function (value) {
            var str = value == 0 ? "外访" : "协访";
            return str;
        },

        // 金额格式化
        amountFormat: function (num) {
            num = num && num.toString().replace(/\$|\,/g, '');
            if (isNaN(num))
                num = "0";
            sign = (num == (num = Math.abs(num)));
            num = Math.floor(num * 100);
            cents = num % 100;
            num = Math.floor(num / 100).toString();
            if (cents < 10)
                cents = "0" + cents;
            for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
                num = num.substring(0, num.length - (4 * i + 3)) + ',' +
                    num.substring(num.length - (4 * i + 3));
            if (num == "0") {
                return "<span >￥0</span>";
            }
            return "￥" + (((sign) ? '' : '-') + num + '.' + cents);
        },
        // 金额格式化
        moneyFormat: function (s, n) {
            if (!s) return "-";
            n = n > 0 && n <= 20 ? n : 2;
            s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
            var l = s.split(".")[0].split("").reverse(),
                r = s.split(".")[1];
            var t = "";
            for (var i = 0; i < l.length; i++) {
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
            }
            var ol = 2 - r.length;
            if (ol > 0) {
                for (var j = 0; j < ol; i++) {
                    r = r + "0";
                }
            } else if (ol < 0) {
                r = r.substring(0, 3);
            }
            return t.split("").reverse().join("") + "." + r;
        },
        // 附件格式化
        attachmentFormat: function (attachment) {
            var attachmentStr = (attachment && "<a href='" + publishRoot + attachment.path + "'>" + attachment.name + "</a>") || "";
            return attachmentStr;
        },

        // 附件格式化
        agreementFormat: function (agreement) {
            var agreementStr = (agreement && "<a href='" + imageRoot + agreement + "'> 下载附件 </a>") || "";
            return agreementStr;
        },

        // 图片格式化
        imageFormat: function (value, row) {
            if (value != null && value.length > 0) {
                var imageStr = '<div class="lightBoxGallery">';
                imageStr += '<img alt="image" class="feed-photo" src="' + value + '">';
                imageStr += '</div>';
                return imageStr;
            } else {
                return "";
            }
        },

        // 图片格式化
        imageFormat2: function (value, row) {
            if (value != null && value.length > 0) {
                var imageStr = '<div class="lightBoxGallery"><a href="' + value + '" data-gallery="">';
                imageStr += '<img alt="image" class="feed-photo" src="' + value + '">';
                imageStr += '</a></div>';
                return imageStr;
            } else {
                return "";
            }
        },

        // 内容格式化
        commentFormat: function (value, row) {
            if (value != null && value.length > 0) {
                return value.length > 20 ? value.substring(0, 20) + "..." : value;
            } else {
                return "";
            }
        },

        // 显示图片的DIV
        getShowImageDiv: function () {
            var divStr = '<div id="blueimp-gallery" class="blueimp-gallery">';
            divStr += '  <div class="slides"></div>';
            divStr += ' <h3 class="title"></h3>';
            divStr += ' <a class="prev">‹</a>';
            divStr += ' <a class="next">›</a>';
            divStr += ' <a class="close">×</a>';
            divStr += ' <a class="play-pause"></a>';
            divStr += ' <ol class="indicator"></ol>';
            divStr += ' </div>';
            return divStr;
        },

        // 取得服务器url
        getRootPath: function () {
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            return (prePath + postPath);
        },

        // 取得服务器虚拟目录
        getPostPath: function () {
            var strPath = window.document.location.pathname;
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            return postPath;
        },

        substringFormatter: function (value, row) {
            if (value.length > 15) {
                return value.substring(0, 12) + "...";
            }
            return value
        },
        publishFormatter: function (value, row) {
            if (value == 1) {
                return "未发布";
            }
            return "已发布"
        },
        useFormatter: function (value, row) {
            if (value == 2 || value == 1) {
                return "<i class='fa fa-close'></i>";
            }
            return "<i class='fa fa-check'></i>"
        },
        selectSuppliersFormatter: function (value, row) {
            return [
                '<a class="fn-selectSuppliers" title="供应商选择">',
                '<i class="fa fa-group" aria-hidden="true"></i>',
                '</a>'
            ].join('');
        },
        formatterAmount: function (obj) {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/[^\d.]/g, "");
            //必须保证第一个为数字而不是.
            obj.value = obj.value.replace(/^\./g, "");
            //保证只有出现一个.而没有多个.
            obj.value = obj.value.replace(/\.{2,}/g, ".");
            //只能输入小数点后两位
            obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
            if (obj.value == "00") {
                obj.value = obj.value * 1;
            }
        },
        formatterInteger: function (obj) {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/\D/g, "");
            if (obj.value * 1 == 0) {
                obj.value = "";
            }
        },
        formatterIntegerWithZero: function (obj) {
            //先把非数字的都替换掉，除了数字和.
            obj.value = obj.value.replace(/\D/g, "");
            if (obj.value != '') {
                obj.value = obj.value * 1;
            }
            if (obj.value * 1 == 0) {
                obj.value = 0;
            }
        },
        ratingFormat: function (value, row) {
            var ratingStr = [];
            ratingStr.push('<input type="hidden" class="rating" readonly="readonly" value="' + value + '" data-stop="5"/>');
            ratingStr.push('<span class="label label-success"></span>');
            return ratingStr.join('');
        },
        // 刷新按钮的单击动作事件
        refreshClkAct: function () {
            window.location.reload();
        },

        getHeight: function () {
            return $(window).height() - 20;
        },
        filterTree: function (elem, filter) {
            $('li a', elem).not(".jstree-search").parent().css('display', filter ? 'none' : 'block');
            $('a.jstree-search', elem).parentsUntil(elem, 'li').css('display', 'block');
        },

        //-------------------------------------------------------------------------
        formatOrderType: function (value, row) {
            if (value == '1') {
                return "企业";
            }
            return "个人"
        },
        formatDateHour: function (value, row) {
            alert(row.nextInviteHour);
            return value;
        },
        // JSON 转换为 url
        parseParam: function (param) {
            var paramStr = "";
            if (param) {
                for (var i in param) {
                    if (param[i]) {
                        paramStr += "&" + i + "=" + encodeURIComponent(param[i]);
                    }
                }
                if (paramStr.indexOf("&") == 0) {
                    paramStr = paramStr.substring(1);
                }
            }
            return paramStr;
        },
        caseTypeFormat: function (value, row, index) {
            if (value == "0") {
                return "个贷";
            } else if (value == "1") {
                return "信用卡";
            } else if (value == "2") {
                return "产证";
            } else if (value == "3") {
                return "房贷";
            }
            return value;
        },
        //案件状态初始化
        caseStatusFormatter: function (value, row, index) {
            if (value == null) {
                return "";
            }
            var dateStr = "";
            if (value == "New") {
                dateStr = "New-未分配任务";
            } else if (value == "Fresh") {
                dateStr = "Fresh-新任务";
            } else if (value == "Search") {
                dateStr = "Search-正在查找中";
            } else if (value == "Search1") {
                dateStr = "Search1-确认联系方式有效，正在查找中";
            } else if (value == "Search2") {
                dateStr = "Search2-暂无有效联系方式，正在查找中";
            } else if (value == "Found1") {
                dateStr = "Found1-找到债务人本人或家人";
            } else if (value == "Found2") {
                dateStr = "Found2-找到联系人";
            } else if (value == "Found3") {
                dateStr = "Found3-找到与债务人不相关的联系人";
            } else if (value == "PTP") {
                dateStr = "PTP-承诺还款";
            } else if (value == "PTPn") {
                dateStr = "PTPn-跳票，违反承诺";
            } else if (value == "Check") {
                dateStr = "Check-检查付款";
            } else if (value == "Visit") {
                dateStr = "Visit-外访";
            } else if (value == "CPT1") {
                dateStr = "CPT1-已付款但不足一半";
            } else if (value == "CPT2") {
                dateStr = "CPT2-付款超过一半但未结清";
            } else if (value == "SK") {
                dateStr = "SK-查找中心处理中";
            } else if (value == "SF") {
                dateStr = "SF-搜索失败";
            } else if (value == "Return1" || value == "Closed1") {
                dateStr = value + "-案件未到期退回";
            } else if (value == "Return2" || value == "Closed2") {
                dateStr = value + "-银行要求退案的";
            } else if (value == "Return3" || value == "Pay Off") {
                dateStr = value + "-还清退案，指欠款全部还清";
            } else if (value == "Return4" || value == "Closed4") {
                dateStr = value + "-到期部分回收成功，指案子到期只收回部分金额";
            } else if (value == "Return5" || value == "Closed5") {
                dateStr = value + "-到期不成功，指案子到期未收回一分钱";
            } else {
                dateStr = "其他任务";
            }
            return dateStr;
        },
        //案件搜索状态初始化
        seekStatusFormatter: function (value, row, index) {
            if (value == null) {
                return "";
            }
            var dateStr = "";
            if (value == "New") {
                dateStr = "New-未分配任务";
            } else if (value == "Fresh") {
                dateStr = "Fresh-新任务";
            } else if (value == "Processing") {
                dateStr = "Processing-处理中";
            } else if (value == "Return1") {
                dateStr = "Return1-未成功退回";
            } else if (value == "Return2") {
                dateStr = "Return2-成功退回";
            } else if (value == "Return3") {
                dateStr = "Return3-查找无信息";
            } else {
                dateStr = "其他任务";
            }
            return dateStr;
        },

        diffDate: function (name, cardNo, idNo, startDate, endDate, text) {
            // 债务人”、“卡号”、“身份证号”均为空时，申请时间不允许为空
            if ((name == "" && cardNo == "" && idNo == "") && (startDate == "" || endDate == "")) {
                layer.msg(text + "：必须输入并且时间间隔不能超过2个月！");
                return false;
            }
            else {
                if (startDate != "" && endDate != "") {
                    if (moment(endDate) < moment(startDate)) {
                        layer.msg(text + "：开始日期不能大于结束日期！");
                        return false;
                    }
                    var startTime1 = moment(endDate);
                    var startTime2 = moment(startDate);
                    if (startTime1.diff(startTime2, 'month', true) > 2) {
                        layer.msg(text + "：时间间隔不能超过2个月！");
                        return false;
                    }
                }
            }
            return true;
        },
        diffDateMonth: function (name, cardNo, idNo, startDate, endDate, text, month) {
            // 债务人”、“卡号”、“身份证号”均为空时，申请时间不允许为空
            if ((name == "" && cardNo == "" && idNo == "") && (startDate == "" || endDate == "")) {
                layer.msg(text + "：必须输入并且时间间隔不能超过"+month+"个月！");
                return false;
            }
            else {
                if (startDate != "" && endDate != "") {
                    if (moment(endDate) < moment(startDate)) {
                        layer.msg(text + "：开始日期不能大于结束日期！");
                        return false;
                    }
                    var startTime1 = moment(endDate);
                    var startTime2 = moment(startDate);
                    if (startTime1.diff(startTime2, 'month', true) > month) {
                        layer.msg(text + "：时间间隔不能超过"+month+"个月！");
                        return false;
                    }
                }
            }
            return true;
        },
        getDate: function (str) {
            var starts = str.split("-");
            try {
                if (starts.length >= 3) {
                    var startDate = new Date().setFullYear(parseInt(starts[0]), parseInt(starts[1]) - 1, parseInt(starts[2]));
                    return startDate;
                }
            } catch (e) {
            }
            return new Date();
        },
        getDateDiff: function (start, end) {
            var starts = start.split("-");
            var ends = end.split("-");
            try {
                if (starts.length >= 3 && ends.length >= 3) {
                    var startDate = new Date().setFullYear(parseInt(starts[0]), parseInt(starts[1]) - 1, parseInt(starts[2]));
                    var endDate = new Date().setFullYear(parseInt(ends[0]), parseInt(ends[1]) - 1, parseInt(ends[2]));
                    var diff = endDate - startDate;
                    return diff;
                }
            } catch (e) {
            }
            return -1;
        },
    },

    Constants = {
        STATE_UNCHECK: "1",        // 未审核
        STATE_CHECKED: "2",        // 未审核
        STATE_REJECT: "3",        // 未审核
        STATE_COMPLATE: "4",        // 未审核
    },

    actionEvents = {
        'click .edit': function (e, value, row, index) {
            Events.modifyAction(row);
        },
        'click .remove': function (e, value, row, index) {
            Events.deleteAction(row);
        },
        'click .fn-modify': function (e, value, row, index) {
            Events.modifyAction([row]);
        },
        'click .fn-delete': function (e, value, row, index) {
            Events.deleteAction([row]);
        },
        'click .fn-approve': function (e, value, row, index) {
            Events.approveAction([row]);
        },
        'click .fn-refuse': function (e, value, row, index) {
            Events.refuseAction([row]);
        },
        'click .fn-push': function (e, value, row, index) {
            Events.pushAction([row]);
        },
        'click .fn-manage': function (e, value, row, index) {
            Events.manageAction([row]);
        },
        'click .fn-reset': function (e, value, row, index) {
            Events.resetAction([row]);
        },
        'click .fn-use': function (e, value, row, index) {
            Events.useAction([row]);
        },
        'click .fn-selectSuppliers': function (e, value, row, index) {
            Events.selectSuppliersAction([row]);
        },
        'click .fn-showall': function (e, value, row, index) {
            Events.showAllAction([row]);
        },
        'click .fn-Price': function (e, value, row, index) {
            Events.priceAction([row]);
        },
        'click .fn-special': function (e, value, row, index) {
            Events.goodsSpecialAction([row]);
        },
        'click .fn-cancel': function (e, value, row, index) {
            Events.cancelGoodsSpecialAction([row]);
        },
        'click .fn-auth': function (e, value, row, index) {
            Events.authClkAct([row]);
        },
        'click .fn-History': function (e, value, row, index) {
            Events.historyAction([row]);
        },
        'click .fn-itemInfo': function (e, value, row, index) {
            Events.itemInfoAction([row]);
        },
    };

Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}