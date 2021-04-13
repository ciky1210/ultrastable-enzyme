const publicFn = {
	/*
	* 时间戳转时间格式 不传返回当前时间
	*/ 
	dateFormat(num){
        if(!num){
            return this.dateFormat(new Date().getTime())
        }
		var num = parseInt(num);
		var Object = new Date(num*1000);
		var Year = String(Object.getFullYear());
		var Month = String(Object.getMonth() + 1);
		if(Month.length < 2) Month = '0' + Month;
		var Day = String(Object.getDate());
		if(Day.length < 2) Day = '0' + Day;
		var Hour = String(Object.getHours());
		if(Hour.length < 2) Hour = '0' + Hour;
		var Minute = String(Object.getMinutes());
		if(Minute.length < 2) Minute = '0' + Minute;
		var Second = String(Object.getSeconds());
		if(Second.length < 2) Second = '0' + Second;
		return(Year + '/' + Month + '/' + Day + ' ' + Hour + ':' + Minute + ':' + Second);
	},
	/**
	 * 时间转时间戳 不传返回当前时间戳
	 */
	Time2Timestamps(Number){
		if(Number == undefined){
			return new Date().getTime();
		}
		return new Date(Number).getTime();
	},
	/**
	 * 获取 localStorage 里的参数
	 */
	getStore(key){
		var value = localStorage.getItem(key);
        if (value) {
            try {
                var value_json = JSON.parse(value);
                if (typeof value_json === 'object') {
                    return value_json;
                } else if (typeof value_json === 'number') {
                    return value_json;
                }
            } catch(e) {
                return value;
            }
        } else {
            return false;
        }
	},
	/**
	 * 设置 localStorage 里的参数
	 */
	setStore(key, value){
		localStorage.setItem(key, JSON.stringify(value));
	},
	/**
	 * 删除 localStorage 里的参数
	 */
	removeStore: function(key) {
        localStorage.removeItem(key);
	},
	/**
	 * 获取 sessionStorage 里的参数
	 */
	getSession(key){
		var value = sessionStorage.getItem(key);
        if (value) {
            try {
                var value_json = JSON.parse(value);
                if (typeof value_json === 'object') {
                    return value_json;
                } else if (typeof value_json === 'number') {
                    return value_json;
                }
            } catch(e) {
                return value;
            }
        } else {
            return false;
        }
	},
	/**
	 * 设置 sessionStorage 里的参数
	 */
	setSession(key,value){
		sessionStorage.setItem(key, JSON.stringify(value));
	},
	/**
	 * 删除 sessionStorage 里的参数
	 */
	removeSession: function(key) {
        sessionStorage.removeItem(key);
	},
	/***
	 * 获取cookie
	 */
	getCookie(name) {
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg)){
			return unescape(arr[2]);
		}else{
			return null;
		}
	},
	/***
	 * 设置cookie
	 */
	setCookie(name,value) {
		var days = 1;
		var exp = new Date();
		exp.setTime(exp.getTime() + days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";path=/;expires=" + exp.toGMTString();
	},
	/***
	 * 删除cookie
	 */
	removeCookie(name) {
		var cval=this.readCookie(name);
		if(cval!=null){
			document.cookie= name + "=;path=/;expires="+(new Date(0)).toGMTString();
		}
	},
	/*超出长度...显示
	* test:文本内容
	* len:长度
	*/
	overFlowTest(test,len){
		if(test.length > len){
			return test.substring(0,len)+'...'
		}else{
			return test
		}
	},
	/**
	 * 获取地址栏参数
	 */
	getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if(r != null) return decodeURI(r[2]);
		return null; //返回参数值
	},
	/**
	 * 倒计时获取剩余多少时间
	 */
	dateCount(number){
		// 获取现在的时间
		var date = Date.parse(new Date());
		console.log(date,number,3123123)
		// 计算时会发生隐式转换，调用valueOf()方法，转化成时间戳的形式
		var days = (number - date)/1000/3600/24; 
		// 下面都是简单的数学计算 
		var day = Math.floor(days);
		var hours = (days - day)*24;
		var hour = Math.floor(hours);
		var minutes = (hours - hour)*60;
		var minute = Math.floor(minutes);
		var seconds = (minutes - minute)*60;
		var second = Math.floor(seconds);
		var back = day+'天'+hour+'小时'+minute+'分'+second+'秒';
		return back;
	},
	/***
	 * 冒泡排序 arr 数据 pargams参数，更具pargams排序
	 */
	bubbleSort(arr,pargams){  
		for(var i=0;i<arr.length-1;i++){  
			for(var j=i+1;j<arr.length;j++){ 
				if(arr[i][pargams] > arr[j][pargams]){//如果前面的数据比后面的大就交换  
					var temp=arr[i];  
					arr[i]=arr[j];  
					arr[j]=temp;  
				}  
			}  
		}   
		return arr;  
	},
	/**
	 * 验证手机号
	 */
	isPoneAvailable($poneInput) {
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
		if (!myreg.test($poneInput)) {
			return false;
		} else {
			return true;
		}
	},
	/**
	 * 验证身份证
	 */
	IdentityCodeValid(code) { 
		var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
		var tip = "";
		var pass= true;

		if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
			tip = "身份证号格式错误";
			pass = false;
		}

	   else if(!city[code.substr(0,2)]){
			tip = "地址编码错误";
			pass = false;
		}
		else{
			//18位身份证需要验证最后一位校验位
			if(code.length == 18){
				code = code.split('');
				//∑(ai×Wi)(mod 11)
				//加权因子
				var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
				//校验位
				var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
				var sum = 0;
				var ai = 0;
				var wi = 0;
				for (var i = 0; i < 17; i++)
				{
					ai = code[i];
					wi = factor[i];
					sum += ai * wi;
				}
				var last = parity[sum % 11];
				if(parity[sum % 11] != code[17]){
					tip = "校验位错误";
					pass =false;
				}
			}
		}
		return pass;
	},
	/**
	 * 更具身份证号，计算当前年龄
	 */
	getNowAge(idCard){
		return new Date().getFullYear() - parseInt(idCard.substring(6,10)) 
	},


	/**
	 * 验证手机号 有问题暂时
	 */
	isvalidatemobile(phone) {
		return
		let list = [];
		let result = true;
		let msg = '';
		var isPhone = /^0\d{2,3}-?\d{7,8}$/;
		//增加134 减少|1349[0-9]{7}，增加181,增加145，增加17[678]  
		var isMob = /^((\+?86)|(\(\+86\)))?(13[0123456789][0-9]{8}|15[012356789][0-9]{8}|18[012356789][0-9]{8}|14[57][0-9]{8}|17[3678][0-9]{8})$/;
		if (!validatenull(phone)) {
			if (phone.length == 11) {
				if (isPhone.test(phone)) {
					msg = '手机号码格式不正确';
				} else {
					result = false;
				}
			} else {
				msg = '手机号码长度不为11位';
			}
		} else {
			msg = '手机号码不能为空';
		}
		list.push(result);
		list.push(msg);
		return list;
	}
}

export {
    publicFn
}