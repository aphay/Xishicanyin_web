<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>管理员管理 - Beehive餐饮后台管理系统</title>
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
	                        <li class="active bg-green">
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
                    	<#if id==0>
                        	管理员创建
                        <#else>
                        	管理员修改
                        </#if>
                    </h1>
                </section>
                
                <!-- Main content -->
                <section class="content">
                	<!-- TODO: 布置首页内容 -->
                	<div class="row">
                        <div class="col-xs-12">
                            <div class="box box-warning">
                                <div class="box-header">
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                    <form role="form">
                                        <!-- text input -->
                                        <div class="form-group">
                                            <label>登录名</label>
                                            <input id="loginName" type="text" class="form-control" placeholder="Enter ..."/>
                                        </div>
                                        <div class="form-group">
                                            <label>昵称</label>
                                            <input id="personName" type="text" class="form-control" placeholder="Enter ..."/>
                                        </div>
										<#if id==0>
											<div class="form-group">
	                                            <label>密码</label>
	                                            <input id="password" type="text" class="form-control" placeholder="Enter ..."/>
	                                        </div>
										</#if>

                                        <!-- select -->
                                        <div class="form-group">
                                            <label>店铺</label>
                                            <select id="restaurant" class="form-control">
                                            	<option value="0">-</option>
                                            	<#list restaurantList as r>
                                            		<option value="${r.id}">${r.name}</option>
                                            	</#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>角色</label>
                                            <select id="role" class="form-control">
                                            	<#list roleList as r>
                                            		<#if (r_index>0)>
	                                            		<option value="${r_index}">${r}</option>
	                                            	</#if>
                                            	</#list>
                                            </select>
                                        </div>
                                        <div class="footer">
						                    <button id="save" type="button" class="btn bg-olive">提交</button>
						                    <button type="reset" class="btn bg-orange">重置</button>
						                </div>
                                    </form>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- ./col -->
                    </div><!-- /.row -->
					<!-- 如果是修改，则可以修改密码 -->
                    <#if (id>0)>
	                	<div class="row">
	                        <div class="col-xs-12">
	                            <div class="box box-warning">
	                                <div class="box-header">
	                                </div><!-- /.box-header -->
	                                <div class="box-body">
	                                    <form role="form">
	                                        <!-- text input -->
	                                        <div class="form-group">
	                                            <label>新密码</label>
	                                            <input id="password2" type="text" class="form-control" placeholder="Enter ..."/>
	                                        </div>
	                                        <div class="footer">
							                    <button id="save2" type="button" class="btn bg-olive">提交</button>
							                    <button type="reset" class="btn bg-orange">重置</button>
							                </div>
	                                    </form>
	                                </div><!-- /.box-body -->
	                            </div><!-- /.box -->
	                        </div><!-- ./col -->
	                    </div><!-- /.row -->
                    </#if>
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
        	$("#personInfoButton").leanModal();
            $(function() {
				var adminId = ${id};
				if(adminId == 0) {
					$("select#restaurant").val(0);
					$("select#role").val(1);
				}
				else {
					ajax("web/admin/admindetail",{adminId:adminId},function(data){
						if(data.ret != 0) {
							alert(data.msg);
							return;
						}
						else {
							$("input#loginName").val(data.data.loginName);
							$("input#personName").val(data.data.personName);
							$("select#restaurant").val(data.data.restaurantId);
							$("select#role").val(data.data.level);
						}
					});
				}
				
				$("button#save").click(function(){
					var adminId = ${id};
					var loginName = $("input#loginName").val(),
						personName = $("input#personName").val(),
						restaurantId = $("select#restaurant").val(),
						level = $("select#role").val();
					var password = "";
					if(adminId==0) {
						password = $("input#password").val();
					}
					if(loginName == "") {
						alert("请输入登录名。");
						return
					}
					if(personName == "") {
						alert("请输入昵称。");
						return;
					}
					if(level < 2 && restaurantId == 0) {
						alert("请选择店铺。");
						return;
					}
					if(adminId == 0 && password == "") {
						alert("请输入密码。");
						return;
					}
					if(level >= 2) {
						$("select#restaurant").val(0);
					}
					if(adminId == 0) {
						ajax("web/admin/createadmin",{
							loginName:loginName,
							personName:personName,
							password:password,
							restaurantId:restaurantId,
							level:level
						},function(data){
							alert(data.msg);
						});
					}
					else {
						ajax("web/admin/updateadmin",{
							adminId:adminId,
							loginName:loginName,
							personName:personName,
							restaurantId:restaurantId,
							level:level
						},function(data){
							alert(data.msg);
						});
					}
				});
				
				$("button#save2").click(function(){
					var adminId = ${id};
					var password = $("input#password2").val();
					if(password == '') {
						alert("请输入新密码。");
						return;
					}
					ajax("web/admin/updateadminpassword", {adminId:adminId, password:password}, function(data){
						alert(data.msg);
					});
				});
            });
        </script>
	</body>
</html>