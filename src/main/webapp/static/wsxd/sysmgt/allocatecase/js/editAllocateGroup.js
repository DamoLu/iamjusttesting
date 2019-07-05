
function checkIsNotEmpty(text) {
    return text !== null && text !== undefined && text !== "";
}
 var checkIsNotNullOrEmpty = checkIsNotEmpty;
function arrayFind(arr, fn) {
    for (var i = 0; i < arr.length; i++) {
        if (fn(arr[i], i) === true) {
            return arr[i];
        }
    }
    return null;
}

var caseScopeList = [];
var layerIndex,
    /* 事件 */
    Events = {
        changeRole: function (target, preUserId) {
            // var changeValue =$(this).find("option:selected").attr("value");
            var changeValue =$(target).val();
            var changeUsername = "";
            $("#role-select")
                .find("option")
                .each(function(index, op){
                    if ($(op).val() === changeValue) {
                        changeUsername = $(op).text();
                    }
                });
            var postUrl = ctx + "/sysmgt/allocatecase/wsxdAllocateGroup/changeRole";
            if(checkIsNotNullOrEmpty(changeValue)){
                postUrl = postUrl+'?role='+changeValue;
            }
            $.ajax({
                type: "GET",
                url: postUrl,
                contentType: "application/json; charset=utf-8",
                dataType: "json",

            }).done(function(result){
                $('#odvs-select').empty();
                Page.searchUserPreGroups(result.userList, changeValue,changeUsername);
                var userList = Page.userPreGroups;
                var optionString = '';
                var len = userList.length;
                userList.forEach(function (group) {
                    var optionGroup = len > 1 ? '<optgroup label='+ group.name +'>' : '';

                    group.list.forEach(function (item) {
                        var realName = item.realName + (checkIsNotNullOrEmpty(item.officeName) ? ('----' + item.officeName) :
                            checkIsNotNullOrEmpty(item.companyName) ? ('----' + item.companyName) : '');
                        optionGroup += '<option title='+item.realName +' value=' + item.userName +'>'+realName+'</option>'
                    })
                    optionGroup += (len > 1 ? "</optgroup>" : '');
                    optionString += optionGroup
                });
                $('#odvs-select')
                    .append(optionString)
                    .selectpicker('refresh')
                    .selectpicker('val', preUserId);
            })
        },

        saveOdvsAction: function () {
            //存放到父页面的hidden域中
            //获取选择了的用户名和真实姓名
            var selectedOdvsName = $("button[data-id ='odvs-select']").attr('title');
            var selectedItem = $('#odvs-select').val();
            if(checkIsNotNullOrEmpty(selectedOdvsName)&&selectedOdvsName!="请选择用户" && selectedItem !=null) {
                $('#odvsName').val(selectedOdvsName);
                $('#odvs').val(selectedItem.join('|'))
            } else{
                $('#odvsName').val(null);
                $('#odvs').val(null);
            }
            layer.close(layerIndex)
        },
        addOdvsNameAction: function (ctxEL) {
            var odvs = "?odvs=" + encodeURIComponent($("#odvs").val());
            // var loadingIndex = layer.load(0, { shade: false })
            layerIndex = layer.open({
                type: 1,
                title: "添加组员",
                zIndex: 8888,
                shadeClose: false, // 开启点击遮罩关闭层
                resize: true,
                area: ["700px", "500px"],
                content: $("#addOdvsNameTpl").html(),
                cancel: function (index, layero) {
                    layer.close(index);
                },
                success: function ($dom, index) {
                    $dom.find(".selectpicker").selectpicker({});
                    var defaultVal = $("#odvs").val();
                    var defaultSelectedItems;
                    if (defaultVal === "" || defaultVal === undefined || defaultVal === null) {
                        defaultSelectedItems = []
                    } else {
                        defaultSelectedItems = defaultVal.split("|");
                    }
                    Page.initRoleList();
                    Page.userPreGroups = [];
                    Page.filterRestUserGroup(defaultSelectedItems);
                    Events.changeRole($("#role-select")[0], defaultSelectedItems);
                },
                end: function () {
                    $('#odvsName').trigger('blur');
                }
            });
        },
        addCaseScopeAction: function (ctxEl) {
            // var odvs = "?odvs=" + encodeURIComponent($("#odvs").val());
            var loadingIndex = layer.load(0, { shade: false })
            layerIndex = layer.open({
                type: 1,
                title: "案件范围",
                zIndex: 8888,
                shadeClose: false, // 开启点击遮罩关闭层
                resize: true,
                area: ["700px", "500px"],
                content: $("#addCaseScopeTpl").html(),
                cancel: function (index, layero) {
                    layer.close(index);
                },
                success: function ($dom, index) {
                    $dom.find(".selectpicker").selectpicker({});
                    Page.fetchAppOrgList(loadingIndex, Page.setDefaultContent)
                }
            });
        },
        saveAction: function () {
            //urgent handle
            // Common.alert("暂不支持保存");
            // return;
            var result = Page.validform.check();
            if (result === false) {
                return;
            }

            Common.confirm("保存", "确认要保存这条信息吗？", function (isConfirm) {
                if (isConfirm) {
                    var group = {
                        id : $('#allocateGroupId').val(),
                        groupName : $('#groupName').val(),
                        odvsName : $('#odvsName').val(),
                        odvs : $('#odvs').val(),
                        minOverdueDay : $('#minOverdueDay').val(),
                        maxOverdueDay : $('#maxOverdueDay').val(),
                        status : $('#status').val(),
                        wsxdAllocateGroupScopeList : caseScopeList
                    };
                    $.ajax({
                        type: "POST",
                        url: ctx + '/sysmgt/allocatecase/wsxdAllocateGroup/saveGroup',
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(group) ,
                        success : function (result) {
                            if (result.success) {
                                Common.alert("保存成功", function () {
                                    window.location.href = ctx + "/sysmgt/allocatecase/wsxdAllocateGroup/list";
                                    // parent.Events.searchAction();

                                    // var index = parent.layer.getFrameIndex(window.name);
                                    // parent.layer.close(index);
                                });
                            } else {
                                switch (result.code) {
                                    case "-5" :
                                        Common.error(result.msg);
                                        break;
                                    case "-6" :
                                        Common.error('逾期范围已重叠');
                                        break;
                                    case "-7" :
                                        Common.error('一个用户只能出现在一个处理组');
                                        break;
                                    default:
                                        var msg = result.message || "保存失败";
                                        Common.error(msg);
                                        Page.validform.check();
                                }
                            }
                        },
                        error: function () {
                            Common.error("保存失败");
                        }

                    });
                }
            });
        },
        /**
         * 弹出层单选框点击事件
         */
        radioClickedAction: function(ctxEl) {
            var itemInfo = $(ctxEl).data('itemInfo');
            var caseScopeData = Page.caseScopeDataDisplay;
            for (var i = 0; i < caseScopeData.length; i++) {
                var caseGroup = caseScopeData[i];
                if (caseGroup.value === itemInfo.groupId) {
                    var list = caseGroup.list
                    list.forEach(function (item) {
                        if (item.buValue === itemInfo.item.buValue) {
                            item.checked = false;
                        }
                    });
                }
            }
            itemInfo.item.checked = $(ctxEl).prop('checked');
            itemInfo.targetItem.checked = itemInfo.item.checked
            itemInfo.targetItem.cmValue = itemInfo.item.cmValue
        },
        selectedCaseScopeDeletedAction: function(ctxEL) {
            var $target = $(ctxEL).parent();
            var caseInfo = $target.data('caseInfo');
            caseInfo.item.checked = false
            caseInfo.targetItem.checked = caseInfo.item.checked
            caseInfo.targetItem.cmValue = caseInfo.item.cmValue
        }
    },
    /* 画面对象 */
    Page = {
        userPreGroups:[],
        validOdvsform: {},
        appOrgList: null,
        caseScopeData: [],
        caseScopeDataDisplay: [],
        allCustomerManagers: [
            {
                value: '2',
                label: '全部'
            },
            {
                value: '0',
                label: '仅公共池'
            },
            {
                value: '1',
                label: '不含公共池'
            }
        ],
        roleList: [],
        // validform: {},

        initRoleList: function () {
            if (Page.roleList.length) {
                var optionString = '<option value="">请选择</option>';
                $('#role-select').empty();
                Page.roleList.forEach(function (role) {
                    optionString += "<option title=" + role.name + " value=" + role.id + ">" + role.name + "</option>";
                });
                $('#role-select')
                    .append(optionString)
                    .selectpicker("refresh")
            } else {
                $.ajax({
                    type: 'GET',
                    url: ctx + '/sysmgt/allocatecase/wsxdAllocateGroup/getRoleList',
                    dataType: "json"
                }).done(function (res) {
                    if (Page.roleList.length) {
                        return
                    }
                    if (res.success && Array.isArray(res.result)) {
                        Page.roleList = res.result;
                        Page.initRoleList()
                    } else {
                        Common.alert(res.message);
                    }
                }).fail(function (err) {
                    if (Page.roleList.length) {
                        return
                    }
                    Common.alert(err.message);
                })
            }
        },
        // 初始化Validator
        initValidator: function () {
            Page.validform = $(".registerform").Validform({
                tiptype: function (msg, o, cssctl) {
                    if (o.obj.is("#minOverdueDay") || o.obj.is("#maxOverdueDay")) {
                        var objtip = o.obj
                            .parent()
                            .siblings(".Validform_checktip");
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    } else if (!o.obj.is("form")) {
                        var objtip = o.obj.siblings(".Validform_checktip");
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    }
                },
                datatype: {
                    groupName: function (gets) {
                        if (!checkIsNotEmpty(gets)) {
                            return false;
                        }
                        return true;
                    },
                    odvsName: function (gets) {
                        if (!checkIsNotEmpty(gets)) {
                            return false;
                        }
                        return true;
                    },
                    minOverdueDay: function (gets, $this) {
                        var reg = /^[1-9]{1}[0-9]{0,3}$/;
                        var ret = reg.test(gets)
                        if (/^[1-9]{1}[0-9]{4,}$/.test(gets)) {
                            return '不能超过9999'
                        }
                        var maxVal = $('#maxOverdueDay').val();
                        if (
                            ret === true &&
                            reg.test(maxVal) &&
                            (parseInt(maxVal) < parseInt(gets))
                        ) {
                            ret = '逾期下限不能大于逾期上限!';
                        }
                        if ($this.data('customrecheck') === true) {
                            $this.data('customrecheck', false);
                        } else {
                            maxVal !== "" && setTimeout(function () {
                                $('#maxOverdueDay').prop('validform_lastval', '').data('customrecheck', true).trigger('blur')
                            });
                        }
                        return ret;
                    },
                    maxOverdueDay: function (gets, $this) {
                        var reg = /^[1-9]{1}[0-9]{0,3}$/;
                        var ret = reg.test(gets)
                        if (/^[1-9]{1}[0-9]{4,}$/.test(gets)) {
                            return '不能超过9999'
                        }
                        var minVal = $('#minOverdueDay').val();
                        if (
                            ret === true &&
                            reg.test(minVal) &&
                            (parseInt(minVal) > parseInt(gets))
                        ) {
                            ret = '逾期上限不能小于逾期下限!';
                        }
                        if ($this.data('customrecheck') === true) {
                            $this.data('customrecheck', false);
                        } else {
                            minVal !== "" && setTimeout(function () {
                                $('#minOverdueDay').prop('validform_lastval', '').data('customrecheck', true).trigger('blur')
                            });

                        }
                        return ret;
                    },
                    appOrgList: function (gets) {
                        if (!checkIsNotEmpty(gets)) {
                            return false;
                        }
                        return true;
                    },
                    status: function (gets) {
                        if (gets === "-1") {
                            return false;
                        }
                        return true;
                    }
                },
                ajaxPost: false
            });
            Page.validOdvsform = $(".registerform").Validform({
                tiptype: function (msg, o, cssctl) {
                    if (!o.obj.is("form")) {
                        var objtip = o.obj.siblings(".Validform_checktip");
                        cssctl(objtip, o.type);
                        objtip.text(msg);
                    }
                },
                ajaxPost: true,
            });
        },

        initCaseScope: function(){
            var listVal = $('#WsxdAllocateGroupScopeList').val();
            if(listVal === "" || listVal === undefined || listVal === null){
                return;
            }
            caseScopeList =JSON.parse( $('#WsxdAllocateGroupScopeList').val());
            var caseGroupArr =[];
            caseScopeList.forEach(function (item) {
                delete item.createDate;
                delete item.updateDate;
                var arr =[];
                arr.push(item.appOrgName);
                arr.push(item.departmentName);
                switch (item.hasCommonPool) {
                    case '0' : arr.push("仅含公共池");break;
                    case '1' : arr.push("不含公共池");break;
                    case '2' : arr.push("全部");break;
                }
                caseGroupArr.push(arr.join('--'));
            })
            $('#appOrgList').val(caseGroupArr.join(' , '));
        },

        // 事件初期化
        initEvents: function () {
            // 查询按钮按下事件
            $(".fn-Save").click(Events.saveAction);
            $(document).on('click', "#addOdvsNameBtn", function () {
                Events.addOdvsNameAction(this);
            });
            $(document).on("click", "#addCaseScopeBtn", function () {
                Events.addCaseScopeAction(this);
            });
            $(document).on("change", "#filterdCaseScope input[type='radio']", function () {
                Events.radioClickedAction(this);
                Page.showSelectedCaseScope()
            });
            $(document).on("click", "#boxSelectedCaseScope .search-choice-close", function () {
                Events.selectedCaseScopeDeletedAction(this);
                Page.showFilteredCaseScope();
                Page.showSelectedCaseScope();
            });
            var preAppOrg = null;
            $(document).on("shown.bs.select", "#selCooperativeAgency", function () {
                preAppOrg = $(this).val();
            });
            $(document).on("hidden.bs.select", "#selCooperativeAgency", function () {
                var currentVal = $(this).val();
                if (preAppOrg === null && currentVal === null) {
                    return;
                }
                if (
                    Array.isArray(preAppOrg) &&
                    Array.isArray(currentVal) &&
                    preAppOrg.length === currentVal.length
                ) {
                    var currentValMap = {};
                    var flag = true;
                    currentVal.forEach(function (item) {
                        currentValMap[item] = true;
                    })
                    for (var i = 0; i < preAppOrg.length; i++) {
                        if (currentValMap[preAppOrg[i]] !== true) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag === true) {
                        return;
                    }
                }
                var nameSelected = $("#selBusinessUnit").val();
                currentVal = currentVal === null ? [] : currentVal;
                nameSelected = nameSelected === null ? [] : nameSelected

                Page.handleBusinessUnitSel(currentVal, nameSelected);

            });
            var preBusinessUnit = null;
            $(document).on("shown.bs.select", "#selBusinessUnit", function () {
                preBusinessUnit = $(this).val();
            });
            $(document).on("hidden.bs.select", "#selBusinessUnit", function () {
                var currentVal = $(this).val();
                if (preBusinessUnit === null && currentVal === null) {
                    return;
                }
                if (
                    Array.isArray(preBusinessUnit) &&
                    Array.isArray(currentVal) &&
                    preBusinessUnit.length === currentVal.length
                ) {
                    var currentValMap = {};
                    var flag = true;
                    currentVal.forEach(function (item) {
                        currentValMap[item] = true;
                    })
                    for (var i = 0; i < preBusinessUnit.length; i++) {
                        if (currentValMap[preBusinessUnit[i]] !== true) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag === true) {
                        return;
                    }
                }
                Page.handleCaseScopeUpdate()

            });
            $(document).on('change', '#selCustomerManager', function () {
                Page.handleCaseScopeUpdate()
            });
            // 保存 案件范围
            $(document).on('click', '#btnSaveCaseScope', function () {
                var ret = [];
                var caseScopeData = Page.caseScopeData;
                var custormerManagers = Page.allCustomerManagers;
                var custormerManagersMap = {};
                custormerManagers.forEach(function (item) {
                    custormerManagersMap[item.value] = item.label
                })
                caseScopeData.forEach(function (group) {
                    var list = group.list;
                    list.forEach(function (item) {
                        if (item.checked === true) {
                            ret.push({
                                appOrg: group.value,
                                appOrgName: group.label,
                                departmentId: item.value,
                                departmentName: item.label,
                                hasCommonPool: item.cmValue,
                                hasCommonPoolLabel: custormerManagersMap[item.cmValue]
                            })
                        }
                    })
                });
                if(ret.length === 0){
                    return Common.alert("案件范围为空");
                }
                caseScopeList = ret;
                $('#appOrgList').val(ret.map(function (item) {
                    return [item.appOrgName, item.departmentName, item.hasCommonPoolLabel].join('----')
                }).join('|'));
                layer.close(layerIndex);
                Page.forceValidItem();
                //
            });
            // 查询按钮按下事件
            var preRoleValue = null
            $(document).on('shown.bs.select', "#role-select", function () {
                preRoleValue = $(this).val();
            })
            $(document).on('hidden.bs.select', "#role-select", function () {
                if (preRoleValue === $(this).val()){
                    return;
                }
                var nameSelected = $('#odvs-select').val()

                Page.filterRestUserGroup(nameSelected)

                Events.changeRole(this, nameSelected)
            })
            $(document).on('click', '#saveOdvsBtn', function () {
                Events.saveOdvsAction()
            })

        },
        forceValidItem: function () {
            $('#appOrgList').trigger('blur')
        },
        setDefaultContent: function () {
            // 1 获取回显内容
            var defaultItems = caseScopeList

            var appOrgSelected = [];
            var selectedItems = [];
            var caseScopeData = [];
            var caseScopeDataMap = {}


            defaultItems.forEach(function (item) {
                if (appOrgSelected.indexOf(item.appOrg) === -1) {
                    appOrgSelected.push(item.appOrg);
                }
                selectedItems.push(item.departmentId);
                if (caseScopeDataMap[item.appOrg] === undefined) {
                    caseScopeDataMap[item.appOrg] = {
                        value: item.appOrg,
                        label: item.appOrgName,
                        list: []
                    }
                }
                caseScopeDataMap[item.appOrg].list.push({
                    value: item.departmentId,
                    label: item.departmentName,
                    checked: true,
                    cmValue: item.hasCommonPool
                })
            })
            Object.keys(caseScopeDataMap).forEach(function (key) {
                caseScopeData.push(caseScopeDataMap[key])
            })
            Page.caseScopeData = caseScopeData
            Page.handleOrgAppSel(appOrgSelected);
            Page.handleBusinessUnitSel(appOrgSelected, selectedItems);
            Page.handleCustomerManagerSel();
            Page.handleCaseScopeUpdate()

        },
        handleCustomerManagerSel: function () {
            var allCustomerManagers = Page.allCustomerManagers;
            var optionString = '<option value="">请选择</option>';
            $('#selCustomerManager').empty();
            allCustomerManagers.forEach(function (group) {
                optionString += "<option title=" + group.label + " value=" + group.value + ">" + group.label + "</option>";
            });
            $('#selCustomerManager')
                .append(optionString)
                .selectpicker("refresh")
        },
        /**
         * @param {Array} appOrgSelected 已选中合作机构 没有则为空数组
         */
        handleOrgAppSel: function (appOrgSelected) {
            var appOrgList = Page.appOrgList;
            var optionString = "";

            $("#selCooperativeAgency").empty();
            appOrgList.forEach(function (group) {
                optionString += "<option title=" + group.label + " value=" + group.value + ">" + group.label + "</option>";
            });
            $("#selCooperativeAgency")
                .append(optionString)
                .selectpicker("refresh")
                .selectpicker("val", appOrgSelected);
        },
        /**
         * @param {Array} appOrgSelected  已选中合作机构 没有则为空数组
         * @param {*} selectedItems 已选择的事业部 没有则为空数组
         */
        handleBusinessUnitSel: function (appOrgSelected, selectedItems) {
            var appOrgSelectedMap = {};
            var selectedItemMap = {};
            var appOrgList = Page.appOrgList;
            var ret = [];
            // 如果合作机构为空，就显示所有事业部列表
            if (appOrgSelected.length === 0) {
                appOrgSelected = appOrgList.map(function (item) {
                    return item.value
                })
            }
            appOrgSelected.forEach(function (appOrg) {
                appOrgSelectedMap[appOrg] = true;
            })
            selectedItems.forEach(function (id) {
                selectedItemMap[id] = true;
            })
            appOrgList.forEach(function (appOrg) {
                if (!appOrgSelectedMap[appOrg.value]) {
                    var list = appOrg.list;
                    var group = {
                        value: appOrg.value,
                        label: appOrg.label,
                        list: []
                    }
                    for (var i = 0; i < list.length; i++) {
                        var item = list[i];
                        if (selectedItemMap[item.value] === true) {
                            group.list.push({
                                value: item.value,
                                label: item.label
                            })
                        }
                    }
                    if (group.list.length) {
                        ret.push(group)
                    }
                }
            })
            appOrgList.forEach(function (appOrg) {
                if (appOrgSelectedMap[appOrg.value]) {
                    ret.push(appOrg)
                }
            })
            $("#selBusinessUnit").empty();
            var optionString = "";
            ret.forEach(function (group) {
                var optionGroup = "<optgroup label=" + group.label + ">";
                group.list.forEach(function (item) {
                    var realName = group.label + '----' + item.label
                    optionGroup += "<option title=" + realName + " value=" + item.value + ">" + realName + "</option>";
                });
                optionGroup += "</optgroup>";
                optionString += optionGroup;
            });
            $("#selBusinessUnit")
                .append(optionString)
                .selectpicker("refresh")
                .selectpicker("val", selectedItems);
            // 筛选出所有 selectedItems 对应的 appOrg
        },
        // 每次打开弹窗，初始化合作机构下拉框内容
        fetchAppOrgList: function (loadingIndex, callback) {
            if (Array.isArray(Page.appOrgList) && Page.appOrgList.length) {
                layer.close(loadingIndex);
                callback()
            } else {
                $.ajax({
                    type: 'GET',
                    url: ctx + '/sysmgt/allocatecase/wsxdAllocateGroup/getAppOrgList',
                    datatype: "json"
                }).done(function (res) {
                    layer.close(loadingIndex);
                    if (res.success && Array.isArray(res.list)) {
                        Page.appOrgList = res.list;
                        callback()
                    } else {
                        Common.alert(res.message);
                    }
                }).fail(function (err) {
                    layer.close(loadingIndex);
                    Common.alert(err.message);
                })
            }
        },
        getIdFromZh: function (zh) {
            return encodeURI(zh).replace(/%/g, '').toLowerCase()
        },
        getUniqueId: function (prefix, postfix) {
            prefix = checkIsNotEmpty(prefix) ? (Page.getIdFromZh(prefix) + '-') : "";
            postfix = checkIsNotEmpty(postfix) ? ('-' + Page.getIdFromZh(postfix)) : "";
            return (prefix + (new Date().getTime() * Math.random()).toString(16).replace(/\./g, "") + postfix);
        },
        filterCaseScope: function () {
            var custormerManager = $('#selCustomerManager').val();
            if (checkIsNotEmpty(custormerManager)) {
                custormerManager = Page.allCustomerManagers.filter(function (item) {
                    return custormerManager === item.value
                })
            } else {
                custormerManager = Page.allCustomerManagers
            }
            return custormerManager
        },
        handleCaseScopeUpdate: function () {
            var nameSelected = $("#selBusinessUnit").val();
            var custormerManagers = Page.allCustomerManagers;
            var selectedCustomerManagers = Page.filterCaseScope()
            var caseScopeData = Page.caseScopeData;
            var appOrgList = Page.appOrgList
            var selectedBsUnitGroup = [];
            var selectedItemMap = {};


            nameSelected = nameSelected === null ? [] : nameSelected;
            nameSelected.forEach(function (bs) {
                selectedItemMap[bs] = true;
            })
            appOrgList.forEach(function (appOrg) {
                var list = appOrg.list;
                var group = {
                    value: appOrg.value,
                    label: appOrg.label,
                    list: []
                }
                for (var i = 0; i < list.length; i++) {
                    var item = list[i];
                    if (selectedItemMap[item.value] === true) {
                        group.list.push({
                            value: item.value,
                            label: item.label
                        })
                    }
                }
                if (group.list.length) {
                    selectedBsUnitGroup.push(group)
                }
            })

            var restCaseScopeData = [];
            caseScopeData.forEach(function (cs) {
                var tmp = {
                    value: cs.value,
                    label: cs.label,
                    list: []
                };
                var list = cs.list;
                for (var i = 0; i < list.length; i++) {
                    var item = list[i];
                    if (item.checked === true) {
                        tmp.list.push(item)
                    }
                }
                if (tmp.list.length) {
                    restCaseScopeData.push(tmp)
                }
            })

            // merger
            var excludedData = []
            for (var i = 0; i < restCaseScopeData.length; i++) {
                var cur = restCaseScopeData[i];
                var target = arrayFind(selectedBsUnitGroup, function (item) {
                    return item.value === cur.value
                })
                if (target === null) {
                    excludedData.push(cur);
                } else {
                    var curList = cur.list;
                    var targetList = target.list;
                    var curListMap = {};
                    curList.forEach(function (item) {
                        curListMap[item.value] = item.cmValue;
                    });
                    targetList.forEach(function (item) {
                        if (curListMap[item.value] !== undefined) {
                            item.checked = true;
                            item.cmValue = curListMap[item.value]
                            curListMap[item.value] = undefined;
                        }
                    })
                    curList.forEach(function (item) {
                        if (curListMap[item.value] !== undefined) {
                            targetList.unshift(item)
                        }
                    });
                }
            }
            Array.prototype.unshift.apply(selectedBsUnitGroup, excludedData);
            //
            var selectedCustomerManagerMap = {};
            selectedCustomerManagers.forEach(function (cm) {
                selectedCustomerManagerMap[cm.value] = true;
            })
            var ret = [];
            Page.caseScopeData = selectedBsUnitGroup;
            selectedBsUnitGroup.forEach(function (appOrg) {
                var group = {
                    value: appOrg.value,
                    label: appOrg.label,
                    list: []
                }
                appOrg.list.forEach(function (item) {
                    custormerManagers.forEach(function (cm) {
                        var t = { cmValue: cm.value, cmLabel: cm.label, orgValue: appOrg.value, orgLabel: appOrg.label, buValue: item.value, buLabel: item.label }
                        t.value = t.buLabel + '----' + t.cmValue;
                        t.label = t.orgLabel + '----' + t.buLabel + '----' + t.cmLabel;
                        t.targetItem = item;
                        if (item.checked === true && item.cmValue === cm.value) {
                            t.checked = true;
                        }
                        if (selectedCustomerManagerMap[cm.value] !== true) {
                            t.display = false;
                        }
                        group.list.push(t)
                    });
                })
                if (group.list.length) {
                    ret.push(group)
                }
            })
            Page.caseScopeDataDisplay = ret;
            Page.showFilteredCaseScope();
            Page.showSelectedCaseScope();
        },

        /**
         * @param {Array} groups
         */
        showFilteredCaseScope: function () {
            var groups = Page.caseScopeDataDisplay;
            $("#filterdCaseScope").empty();
            groups.forEach(function (group) {
                var $pannel = $('<div class="panel"/>');
                var $panelheader = $('<div class="panel-heading" data-toggle="collapse">');
                var $panelBody = $('<div class="panel-collapse collapse in"><div class="panel-body checkbox"></div></div>');
                var uniqueGroupId = Page.getUniqueId("filterdCaseScope-" + group.value);
                var list = group.list;

                $panelheader.text(group.label).attr("href", '#' + uniqueGroupId);
                $panelBody.attr("id", uniqueGroupId);

                list.forEach(function (item) {
                    if (item.display === false) return;
                    var itemId = Page.getUniqueId("filterdCaseScope-item-" + item.value);
                    var $checkboxItem = $('<div class="checkbox-item"><input type="radio"/> <label><span></span></label> </div>');
                    $checkboxItem.find('input').attr({
                        id: itemId,
                        name: Page.getIdFromZh(item.buValue),
                        checked: item.checked
                    }).val(item.value).data('itemInfo', {
                        groupId: group.value,
                        groupName: group.label,
                        item: item,
                        targetItem: item.targetItem
                    })
                    $checkboxItem.find('label').attr('for', itemId);
                    $checkboxItem.find('label').find('span').text(item.label);
                    $panelBody.find(".panel-body").append($checkboxItem);
                });

                $pannel.append($panelheader).append($panelBody);
                $("#filterdCaseScope").append($pannel);
            });
        },
        showSelectedCaseScope: function () {
            $box = $('#boxSelectedCaseScope');
            var allCaseData = Page.caseScopeDataDisplay;
            var allCheckedItems = [];
            allCaseData.forEach(function (group) {
                var list = group.list;
                list.forEach(function (item) {
                    if (item.checked === true) {
                        allCheckedItems.push({
                            groupId: group.value,
                            groupName: group.label,
                            item: item,
                            targetItem: item.targetItem
                        })
                    }
                });
            });
            $box.empty();
            allCheckedItems.forEach(function (caseInfo) {
                var $li = $('<li class="search-choice"><span ></span > <a class="search-choice-close"></a> </li>')
                $li.find('span').text(caseInfo.item.label)
                $li.data('caseInfo', caseInfo)
                $box.append($li)
            });
        },
        filterRestUserGroup: function (selectedItems) {
            var userPreGroups = Page.userPreGroups;
            if (userPreGroups.length === 0 || !Array.isArray(selectedItems) || selectedItems.length === 0) {
                Page.userPreGroups = []
                return;
            }
            var ret = [];
            var selectedItemMap = {};
            selectedItems.forEach(function (userName) {
                selectedItemMap[userName] = true
            })
            for( var i = 0; i < userPreGroups.length; i++) {
                var currentGroup = userPreGroups[i];
                var tmpGroup = {
                    role: currentGroup.role,
                    name: currentGroup.name,
                    list: []
                }
                currentGroup.list.forEach(function (item) {
                    if (selectedItemMap[item.userName] === true) {
                        tmpGroup.list.push(item);
                        selectedItemMap[item.userName] = false;
                    }
                })
                if (tmpGroup.list.length) {
                    ret.push(tmpGroup)
                }
            }
            Page.userPreGroups = ret;
        },
        searchUserPreGroups: function (resultList, role,userName) {
            if (!Array.isArray(resultList) || resultList.length === 0) {
                return;
            }
            var userPreGroups = Page.userPreGroups;

            var targetIndex = -1;
            for (var i = 0; i < userPreGroups.length; i++) {
                var currentGroup = userPreGroups[i];
                if (currentGroup.role === role) {
                    targetIndex = i;
                }
            }
            if (targetIndex !== -1) {
                userPreGroups.splice(targetIndex, 1);
            }
            userPreGroups.push({
                role: role,
                name: userName,
                list: resultList
            });


            var emptyGroup = null;
            var otherUserNameMap = {};
            var emptyGroupIndex = null;
            for (var i = 0; i < userPreGroups.length; i++) {
                var currentGroup = userPreGroups[i];
                if (currentGroup.role === '' || currentGroup.role === undefined || currentGroup.role === null) {
                    emptyGroup = currentGroup
                    emptyGroupIndex = i;
                } else {
                    var list = currentGroup.list
                    list.forEach(function (item) {
                        otherUserNameMap[item.userName] = true;
                    })
                }
            }
            if (emptyGroup !== null) {
                var list = emptyGroup.list
                if (Array.isArray(list)) {
                    for (var i = 0; i < list.length; i++) {
                        var item = list[i];
                        if (otherUserNameMap[item.userName] === true) {
                            list.splice(i, 1);
                            i--;
                        }
                    }
                }
                if (!Array.isArray(list) || list.length === 0) {
                    userPreGroups.splice(emptyGroupIndex, 1);
                }
            }
        },
        init: function () {
            Page.initEvents();
            Page.initValidator();
            Page.initCaseScope();
        }
    };

jQuery(document).ready(function () {
    Page.init();
});
