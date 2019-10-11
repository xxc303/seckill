//存放主要交互逻辑js代码
//JavaScript模块化
var secKill = {
    //封装秒杀相关Ajax的url
    URL: {
        now: function () {
            return '/secKill/time/now';
        },
        exposer: function (secKillId) {
            return '/secKill/'+ secKillId + '/exposer';
        },
        execution: function (secKillId, md5) {
            return '/secKill/'+ secKillId + '/' + md5 + '/execution';
        }
    },

    // 验证手机号
    validatePhone: function(phone){
        if (phone && phone.length == 11 && !isNaN(phone)){
            return true;
        } else{
            return false;
        }
    },

    handleSecKillKill: function(secKillId, node){
        //获取秒杀地址 控制显示逻辑 执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(secKill.URL.exposer(secKillId), {}, function (result) {
            //在回调函数中执行交互流程
            if (result && result['success']){
                var exposer = result['data'];
                console.log(exposer);
                if (exposer['exposer']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = secKill.URL.execution(secKillId,md5);
                    console.log("killUrl:"+killUrl);
                    //绑定一次点击事件，降低服务器端的压力
                    $('#killBtn').one('click', function () {
                        //执行秒杀的操作
                        // 1.先禁用按钮
                        $(this).addClass('disable');
                        //2.发送秒杀请求执行秒杀
                        $.post(killUrl,{},function (result) {
                            console.log('result:'+result);
                            if (result && result['success']){
                                var killResult = result['data'];
                                console.log(killResult);
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">'+ stateInfo + '</span>');
                            }
                        });
                    });
                    node.show();
                } else {
                    //未开启秒杀
                    var now = exposer['now'];
                    var start = exposer['startTime'];
                    var end = exposer['endTime'];
                    //为防止计时结束，但未开始秒杀的情况，重新计算计时逻辑
                    secKill.countDown(secKillId,now,start,end);
                }
            }else {
                console.log('result:' + result);
            }
        })
    },

    countDown:function(secKillId, nowTime, startTime, endTime){
        var secKillBox = $('#secKill-box')
        // 时间判断
        if (nowTime > endTime){
            secKillBox.html('秒杀结束!');
        }else if (nowTime < startTime){
            //秒杀未开始，倒计时
            var killTime = new Date(startTime);
            secKillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                secKillBox.html(format)
                // 时间完成后回调事件
            }).on('finish.countdown',function () {
                //获取秒杀地址 控制显示逻辑 执行秒杀
                secKill.handleSecKillKill(secKillId, secKillBox);

            });
        }else {
            //秒杀开始，执行秒杀
            secKill.handleSecKillKill(secKillId, secKillBox);
        }
    },

    //详情页秒杀逻辑
    detail: {
        // 详情页初始化
        init: function (params) {
            // 手机验证和登录，计时交互
            // 规划交互流程
            // 在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            // 验证手机号
            if (!secKill.validatePhone(killPhone)){
                // 绑定phone
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true, //显示弹出层
                    backdrop: 'static', //禁止位置关闭
                    keyboard: false //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log(inputPhone);
                    if (secKill.validatePhone(inputPhone)){
                        // 电话写入cookie
                        $.cookie('killPhone',inputPhone,{expires: 7, path: '/secKill'});
                        //刷新页面
                        window.location.reload();
                    } else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }
            // 已经登录，Ajax计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var secKillId = params['secKillId'];
            $.get(secKill.URL.now(), {}, function (result) {
                if (result && result['success']){
                    var nowTime = result['data'];
                    //时间判断,计时交互
                    secKill.countDown(secKillId, nowTime, startTime, endTime);
                } else{
                    console.log('result:' + result)
                }
            })
        }
    }
}