<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>菜品管理 - Beehive餐饮后台管理系统</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" type="text/css" />
		<!-- DATA TABLES -->
        <link href="css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />
        
        <!-- leanModal -->
		<style type="text/css">
	        #lean_overlay {
			    position: fixed;
			    z-index:100;
			    top: 0px;
			    left: 0px;
			    height:100%;
			    width:100%;
			    background: #000;
			    display: none;
			}
			#personInfo {
				width: 600px;
				padding: 30px;
				display:none;
			    background: #FFF;
				border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;
				box-shadow: 0px 0px 4px rgba(0,0,0,0.7); -webkit-box-shadow: 0 0 4px rgba(0,0,0,0.7); -moz-box-shadow: 0 0px 4px rgba(0,0,0,0.7);   
			}
			#dishesPictureInfo {
				width: 600px;
				padding: 30px;
				display:none;
			    background: #FFF;
				border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;
				box-shadow: 0px 0px 4px rgba(0,0,0,0.7); -webkit-box-shadow: 0 0 4px rgba(0,0,0,0.7); -moz-box-shadow: 0 0px 4px rgba(0,0,0,0.7);   
			}
		</style>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
	<body class="skin-blue">
		<!-- 个人信息弹框 -->
		<div id="personInfo">
			<h3>管理员个人信息</h3>
			<ul>
				<li>ID: ${adminId}</li>
				<li>名称: ${personName}</li>
				<li>角色: ${role}</li>
				<li>所属店铺: ${restaurantName!"-"}</li>
				<li>最后登录时间: ${lastLoginTime}</li>
			</ul>
		</div>
		
		<!-- 菜品图片弹框 -->
		<div id="dishesPictureInfo">
			<h3>菜品图片</h3>
			<div class="form-group">
                <label for="dishesPicture">上传图片</label>
                <input type="file" id="dishesPicture" name="picture"/>
            </div>
			<img id="dishes-picture" src="" style="width:100%;height:100%;"></img>
		</div>
	
	    <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="/beehive_web/portal" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Beehive控制后台
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>${personName}<i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                	<img src="img/avatar.png" class="img-circle" alt="User Image" />
                                    <p>
                                        ${personName}
                                        <small>最后登录时间：${lastLoginTime}</small>
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a id="personInfoButton" href="#personInfo" class="btn btn-default btn-flat"><i class="fa fa-info"></i>个人信息</a>
                                    </div>
                                    <div class="pull-right">
                                    	<form action="/beehive_web/logout" method="post">
                                        	<button type="submit" class="btn btn-default btn-flat"><i class="fa fa-sign-out"></i>登出</button>
                                        </form>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li>
                            <a href="/beehive_web/portal">
                                <i class="fa fa-home"></i> <span>欢迎页</span>
                            </a>
                        </li>
                        <#if (level>=2)>
	                        <li>
	                            <a href="/beehive_web/admin">
	                                <i class="fa fa-user"></i> <span>管理员管理</span>
	                            </a>
	                        </li>
	                    </#if>
                        <li class="active bg-green">
                            <a href="/beehive_web/dishes">
                                <i class="fa fa-book"></i> <span>菜品管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="/beehive_web/order">
                                <i class="fa fa-shopping-cart"></i> <span>订单管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="/beehive_web/restaurant">
                                <i class="fa fa-cutlery"></i> <span>店铺管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="/beehive_web/distribution">
                                <i class="fa fa-truck"></i> <span>取餐点管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="/beehive_web/user">
                                <i class="fa fa-users"></i> <span>用户管理</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
            	<!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        菜品管理
                    </h1>
                </section>
                
                <!-- Main content -->
                <section class="content">
                	<!-- TODO: 布置首页内容 -->
                	<div class="row">
                        <div class="col-xs-12">
                            <div class="box">
                                <div class="box-header"></div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <button id="create" class="btn btn-success">创建菜品</button>
                                    <table id="table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>菜品ID</th>
                                                <th>菜品名称</th>
                                                <th>类别</th>
                                                <th>价格(元)</th>
                                                <th>销量</th>
                                                <th>折扣</th>
                                                <th>操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- ./col -->
                    </div><!-- /.row -->
                </section>
            </aside>
        </div><!-- ./wrapper -->
		
        <!-- jQuery 2.0.2 -->
        <script src="http://apps.bdimg.com/libs/jquery/2.0.2/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DATA TABES SCRIPT -->
        <script src="js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="js/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
        <!-- LEAN MODAL -->
        <script src="js/plugins/leanModal/jquery.leanModal.min.js" type="text/javascript"></script>
        <!-- 基础公用方法 -->
        <script src="js/baseJs.js" type="text/javascript"></script>
        <!-- 上传图片插件 -->
        <script src="js/ajax-file-upload.js" type="text/javascript"></script>
        
        <!-- page script -->
        <script type="text/javascript">
            $(function() {
            	$("#personInfoButton").leanModal();
				ajax("web/dishes/disheslist",null,function(data) {
        			if(data.ret != 0) {
        				alert(data.msg);
        				return;
        			}
        			
    				var list = data.data;
    				for(var i = 0;i < list.length;i++) {
        				var updateButton = "<button id='update' dishesId="+list[i].id+" class='btn btn-info'>修改</button>";
        				var showButtonText = list[i].onShow?"下架":"上架";
						var showButton = "<button id='show-dishes' dishesId="+list[i].id+" onShow=" + list[i].onShow + " class='btn btn-info'>"+showButtonText+"</button>";
						var picButton = "<button id='pic-dishes' href='#dishesPictureInfo' dishesId="+list[i].id+" picPath='"+list[i].path+"' class='btn btn-info'>显示图片</button>";
						$("#table").append("" +
    						"<tr id='row"+list[i].id+"'>" +
    							"<td>" + list[i].id + "</td>" +
    							"<td>" + list[i].name + "</td>" +
    							"<td>" + list[i].type + "</td>" +
    							"<td>" + list[i].price + "</td>" +
    							"<td>" + list[i].sales + "</td>" +
    							"<td>" + list[i].discount + "</td>" +
    							"<td>" + updateButton + " " + showButton + " " + picButton + "</td>" +
    						"</tr>"
    					);
    					if(!list[i].onShow) {
    						$("#row"+list[i].id).addClass("bg-navy");
    					}
    				}
    				//渲染表格样式
	                $("#table").dataTable({
	                    "bPaginate": false,
	                    "bLengthChange": false,
	                    "bFilter": false,
	                    "bSort": true,
	                    "bInfo": false,
	                    "bAutoWidth": false
	                });
	                //绑定按钮事件
	                $("button#create").click(function() {
	                	post("dishesEdit", {dishesId:0});
	                });
	                $("button#update").click(function() {
	                	var dishesId = $(this).attr("dishesId");
	                	post("dishesEdit", {dishesId:dishesId});
	                });
            		$("button#show-dishes").click(function() {
            			var dishesId = $(this).attr("dishesId"),
            				onShow = $(this).attr("onShow");
        				show(dishesId, onShow=="true", $(this));
            		});
            		$("button#pic-dishes").mouseover(function() {
            			$(this).leanModal();
            		});
            		$("button#pic-dishes").click(function() {
            			var id = $(this).attr("dishesId");
            			var path = $(this).attr("picPath");
            			var button = $(this);
        				pic(id, path, button);
            		});
        		});
            });
            
            function show(id, onShow, button) {
            	if(onShow) {
	            	ajax("web/dishes/offshow",{dishesId:id}, function(data){
	            		if(data.ret != 0) {
	            			alert(data.msg);
	            			return;
	            		}
	            		$("tr#row"+id).addClass("bg-navy");
	            		button.html("上架");
	            		button.attr("onShow",false);
	            	});
            	}
            	else {
            		ajax("web/dishes/onshow",{dishesId:id}, function(data){
	            		if(data.ret != 0) {
	            			alert(data.msg);
	            			return;
	            		}
	            		$("tr#row"+id).removeClass("bg-navy");
	            		button.html("下架");
	            		button.attr("onShow",true);
	            	});
            	}
            }
            
            function pic(id, path, button) {
            	$("input#dishesPicture").val("");
            	$("button#upload-picture").attr("dishesId", id);
            	if(path != "/") {
            		$("img#dishes-picture").attr("src", urlPrefix+"."+path+"?"+new Date());
            		$("img#dishes-picture").show();
            	}
            	else {
            		$("img#dishes-picture").attr("src", "");
            		$("img#dishes-picture").hide();
            	}
            	
	            $("input#dishesPicture").AjaxFileUpload({
	            	action: urlPrefix+"web/dishes/uploadpicture",
	            	dishesId: id,
					onComplete: function(filename, response) {
						var data = response;
						if(data.ret != 0) {
							alert(data.msg);
							return;
						}
						var p = data.data;
						//更新对应按钮属性
						button.attr("picPath", p);
						//更新图片显示
						if(p != "/") {
		            		$("img#dishes-picture").attr("src", urlPrefix+"."+p+"?"+new Date());
		            		$("img#dishes-picture").show();
		            	}
		            	else {
		            		$("img#dishes-picture").attr("src", "");
		            		$("img#dishes-picture").hide();
		            	}
					}
	            });
            }
        </script>
	</body>
</html>