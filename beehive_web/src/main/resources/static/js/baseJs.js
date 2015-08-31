var urlPrefix = "/beehive_web/";
//ajax-post方法            
function ajax(url,data,fn) {
	if(data == null) {
		data = {};
	}
	jQuery.ajax({
		url:urlPrefix + url,
		type:"post",
		dataType:"json",
		data:data,
		success:fn,
		cache:false,
		error:function() {
			alert("网络连接异常，请联系开发者！");
		}
	});
}
//普通post表单
function post(url, data, method) {
    if (method == null) method = 'POST';
    if (data == null) data = {};
    url = urlPrefix + url;

    var form = $('<form>').attr({
        method: method,
        action: url
    }).css({
        display: 'none'
    });

    var addData = function (name, data) {
        if ($.isArray(data)) {
            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                addData(name + '[]', value);
            }
        } else if (typeof data === 'object') {
            for (var key in data) {
                if (data.hasOwnProperty(key)) {
                    addData(name + '[' + key + ']', data[key]);
                }
            }
        } else if (data != null) {
            form.append($('<input>').attr({
                type: 'hidden',
                name: String(name),
                value: String(data)
            }));
        }
    };

    for (var key in data) {
        if (data.hasOwnProperty(key)) {
            addData(key, data[key]);
        }
    }

    form.appendTo('body').submit();
}