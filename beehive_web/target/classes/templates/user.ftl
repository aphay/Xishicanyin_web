<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>用户管理 - Beehive餐饮后台管理系统</title>
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
                        <li class="active bg-green">
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
                        用户管理
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
									<div class="row">
                                		<div class="col-xs-3">
		                                    <div class="input-group">
		                                        <input id="tel" type="text" class="form-control" placeholder="手机号" />
		                                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
		                                    </div>
	                                    </div>
                           				<div class="col-xs-3"></div>
                           				<div class="col-xs-3"></div>
                           				<div class="col-xs-3"></div>
                                	</div>
                                    <table id="table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>用户ID</th>
                                                <th>电话</th>
                                                <th>取餐点</th>
                                                <th>储值</th>
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
				refresh("");
        		
        		$("input#tel").bind('keypress',function(event){
		            if(event.keyCode == "13") {
	        			var tel = $(this).val();
	        			refresh(tel);
		            }
		        });
            });
            
            function refresh(tel) {
            	if(tel == "") {
	            	ajax("web/user/userlist",null,function(data) {
	        			listTable(data);
	        		});
            	}
            	else {
            		ajax("web/user/searchuser",{tel:tel},function(data) {
            			listTable(data);
            		});
            	}
            }
            
            function listTable(data) {
    			if(data.ret != 0) {
    				alert(data.msg);
    				return;
    			}
    			
    			$("#table tbody").empty();
				var list = data.data;
				for(var i = 0;i < list.length;i++) {
    				var updateButton = "<button id='update' userId="+list[i].id+" class='btn btn-info'>修改</button>";
    				var str = list[i].isBlack?"移除黑名单":"加入黑名单";
					var setBlackButton = "<button id='setBlack' userId="+list[i].id+" isBlack="+list[i].isBlack+" class='btn btn-info'>"+str+"</button>";
    				
					$("#table").append("" +
						"<tr id='row"+list[i].id+"'>" +
							"<td>" + list[i].id + "</td>" +
							"<td>" + list[i].tel + "</td>" +
							"<td>" + list[i].distribution + "</td>" +
							"<td>" + list[i].money + "</td>" +
							"<td>" + updateButton + " " + setBlackButton + "</td>" +
						"</tr>"
					);
					if(list[i].isBlack) {
						$("tr#row"+list[i].id).addClass("bg-navy");
					}
				}
				//渲染表格样式
                $("#table").dataTable({
                    "bPaginate": false,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": false,
                    "bAutoWidth": false,
                    "bRetrieve": true,
                    "bDestory": true
                });
                //绑定按钮事件
                $("button#update").click(function() {
                	var userId = $(this).attr("userId");
                	post("userEdit", {userId:userId});
                });
				$("button#setBlack").click(function() {
					var button = $(this);
        			var userId = button.attr("userId");
        			var isBlack = button.attr("isBlack")=="true"?false:true;
					ajax("web/user/setblack",{userId:userId,isBlack:isBlack}, function(data){
	            		if(data.ret != 0) {
	            			alert(data.msg);
	            			return;
	            		}
	            		if(data.data) {
		            		$("tr#row"+userId).addClass("bg-navy");
		            		button.html("移除黑名单");
		            		button.attr("isBlack",true);
	            		}
	            		else {
	            			$("tr#row"+userId).removeClass("bg-navy");
		            		button.html("加入黑名单");
		            		button.attr("isBlack",false);
	            		}
	            	});
        		});
            }
        </script>
	</body>
</html>