<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>店铺管理 - Beehive餐饮后台管理系统</title>
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
                        <li>
                            <a href="/beehive_web/dishes">
                                <i class="fa fa-book"></i> <span>菜品管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="/beehive_web/order">
                                <i class="fa fa-shopping-cart"></i> <span>订单管理</span>
                            </a>
                        </li>
                        <li class="active bg-green">
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
                        店铺管理
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
                                    <button id="create" class="btn btn-success">创建店铺</button>
                                    <table id="table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>店铺ID</th>
                                                <th>店铺名</th>
                                                <th>地址</th>
                                                <th>电话</th>
                                                <th>开业时间</th>
                                                <th>打烊时间</th>
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
        
        <!-- page script -->
        <script type="text/javascript">
            $(function() {
            	$("#personInfoButton").leanModal();
				ajax("web/restaurant/restaurantlist",null,function(data) {
        			if(data.ret != 0) {
        				alert(data.msg);
        				return;
        			}
        			
    				var list = data.data;
    				for(var i = 0;i < list.length;i++) {
        				var updateButton = "<button id='update' restaurantId="+list[i].id+" class='btn btn-info'>修改</button>";
						var deleteButton = "<button id='delete' restaurantId="+list[i].id+" class='btn btn-danger'>删除</button>";
						var showButtonText = list[i].enable?"失效":"生效";
						var showButton = "<button id='show-dishes' restaurantId="+list[i].id+" enable=" + list[i].enable + " class='btn btn-info'>"+showButtonText+"</button>";
						$("#table").append("" +
    						"<tr id='row"+list[i].id+"'>" +
    							"<td>" + list[i].id + "</td>" +
    							"<td>" + list[i].name + "</td>" +
    							"<td>" + list[i].address + "</td>" +
    							"<td>" + list[i].tel + "</td>" +
    							"<td>" + list[i].openTime + "</td>" +
								"<td>" + list[i].closeTime + "</td>" +
    							"<td>" + updateButton + " " + deleteButton + " " + showButton + "</td>" +
    						"</tr>"
    					);
    					if(!list[i].enable) {
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
	                	post("restaurantEdit", {restaurantId:0});
	                });
	                $("button#update").click(function() {
	                	var restaurantId = $(this).attr("restaurantId");
	                	post("restaurantEdit", {restaurantId:restaurantId});
	                });
            		$("button#delete").click(function() {
            			var restaurantId = $(this).attr("restaurantId");
            			deleteRow(restaurantId);
            		});
            		$("button#show-dishes").click(function() {
            			var id = $(this).attr("restaurantId"),
            				enable = $(this).attr("enable");
        				show(id, enable=="true", $(this));
            		});
        		});
            });
            
            function deleteRow(id) {
            	ajax("web/restaurant/removerestaurant",{restaurantId:id}, function(data){
            		if(data.ret != 0) {
            			alert(data.msg);
            			return;
            		}
            		$("tr#row"+id).remove();
            	});
            }
            
            function show(id, enable, button) {
            	if(enable) {
	            	ajax("web/restaurant/disable",{restaurantId:id}, function(data){
	            		if(data.ret != 0) {
	            			alert(data.msg);
	            			return;
	            		}
	            		$("tr#row"+id).addClass("bg-navy");
	            		button.html("生效");
	            		button.attr("enable",false);
	            	});
            	}
            	else {
            		ajax("web/restaurant/enable",{restaurantId:id}, function(data){
	            		if(data.ret != 0) {
	            			alert(data.msg);
	            			return;
	            		}
	            		$("tr#row"+id).removeClass("bg-navy");
	            		button.html("失效");
	            		button.attr("enable",true);
	            	});
            	}
            }
        </script>
	</body>
</html>