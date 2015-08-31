<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>订单管理 - Beehive餐饮后台管理系统</title>
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
			#orderInfo {
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
		
		<!-- 订单信息弹框 -->
		<div id="orderInfo">
			<h3>订单详细信息</h3>
			<ul>
				<li><div id="msgOrderId"/></li>
				<li><div id="msgDishes"/></li>
				<li><div id="msgTotalPrice"/></li>
				<li><div id="msgRemark"/></li>
				<li><div id="msgRestaurant"/></li>
				<li><div id="msgDistribution"/></li>
				<li><div id="msgTakeno"/></li>
				<li><div id="msgStatus"/></li>
				<li><div id="msgUserId"/></li>
				<li><div id="msgTel"/></li>
				<li><div id="msgOrderType"/></li>
				<li><div id="msgHasPaid"/></li>
				<li><div id="msgEnable"/></li>
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
                        <li class="active bg-green">
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
                        订单管理
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
			                                <button id="create" class="btn btn-success">创建订单</button>
		                                </div>
                           				<div class="col-xs-3">
                           					<div class="input-group">
		                                        <div class="input-group-addon">
		                                            <i class="fa fa-calendar"></i>
		                                        </div>	                                        
	                           				    <input id="time" type="text" class="form-control" data-inputmask="'alias': 'yyyy-mm-dd'" data-mask/>
		                                    </div>
                           				</div>
                           				<div class="col-xs-3">
                           					<div class="input-group">
		                                        <input id="tel" type="text" class="form-control" placeholder="手机号" />
		                                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
		                                    </div>
                           				</div>
                           				<div class="col-xs-3"></div>
                                	</div>
                                    <table id="table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>订单号</th>
                                                <th>创建时间</th>
                                                <th>更新时间</th>
                                                <th>操作员</th>
                                                <th>总价</th>
                                                <th>状态</th>
                                                <th>付款方式</th>
                                                <th>是否付款</th>
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
        <!-- InputMask -->
        <script src="js/plugins/input-mask/jquery.inputmask.js" type="text/javascript"></script>
        <script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js" type="text/javascript"></script>
        <script src="js/plugins/input-mask/jquery.inputmask.extensions.js" type="text/javascript"></script>
        <!-- LEAN MODAL -->
        <script src="js/plugins/leanModal/jquery.leanModal.min.js" type="text/javascript"></script>
        <!-- 基础公用方法 -->
        <script src="js/baseJs.js" type="text/javascript"></script>
        
        <!-- page script -->
        <script type="text/javascript">
            $(function() {
            	$("#personInfoButton").leanModal();
				//获取当前日期
            	var now = new Date();
            	var vYear = now.getFullYear();
				var vMon = now.getMonth() + 1;
				var vDay = now.getDate();
				var today = vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
            	$("input#time").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
            	$("input#time").val(today);
        		$("input#time").bind('keypress',function(event){
		            if(event.keyCode == "13") {
	        			var time = $(this).val();
	        			var tel = $("input#tel").val();
	        			if(time == "" || time.indexOf("d") != -1) {
	        				alert("请输入完整时间。");
	        				return;
	        			}
	        			refresh(time,tel,false);
		            }
		        });
		        $("input#tel").bind('keypress',function(event){
		            if(event.keyCode == "13") {
	        			var time = $("input#time").val();
	        			var tel = $(this).val();
	        			if(time == "" || time.indexOf("d") != -1) {
	        				alert("请输入完整时间。");
	        				return;
	        			}
	        			refresh(time,tel,false);
		            }
		        });
                $("button#create").click(function() {
                	post("orderEdit", {orderId:0});
                });
        		refresh(today,"",true);
            });
            
            function deleteRow(id) {
            	ajax("web/order/deleteorder",{orderId:id}, function(data){
            		if(data.ret != 0) {
            			alert(data.msg);
            			return;
            		}
            		$("tr#row"+id).remove();
            	});
            }
            
            function refresh(today,tel,isFirst) {
            	ajax("web/order/orderlist",{time:today,tel:tel},function(data) {
        			if(data.ret != 0) {
        				alert(data.msg);
        				return;
        			}
        			
        			$("#table tbody").empty();
    				var list = data.data;
    				for(var i = 0;i < list.length;i++) {
        				var updateButton = "<button id='update' orderId="+list[i].orderId+" class='btn btn-info'>修改</button>";
						var deleteButton = "<button id='delete' orderId="+list[i].orderId+" class='btn btn-danger'>删除</button>";
						var showButton = "<button id='show' href='#orderInfo' orderId="+list[i].orderId+" class='btn btn-info'>显示详情</button>";
						$("#table").append("" +
    						"<tr id='row"+list[i].orderId+"'>" +
    							"<td>" + list[i].orderId + "</td>" +
    							"<td>" + list[i].createTime + "</td>" +
    							"<td>" + list[i].updateTime + "</td>" +
    							"<td>" + list[i].operator + "</td>" +
    							"<td>" + list[i].totalPrice + "</td>" +
    							"<td>" + list[i].status + "</td>" +
    							"<td>" + list[i].orderType + "</td>" +
    							"<td>" + list[i].hasPaid + "</td>" +
    							"<td>" + updateButton + " " + deleteButton + " " + showButton + "</td>" +
    						"</tr>"
    					);
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
	                	var orderId = $(this).attr("orderId");
	                	post("orderEdit", {orderId:orderId});
	                });
            		$("button#delete").click(function() {
            			var orderId = $(this).attr("orderId");
            			deleteRow(orderId);
            		});
            		$("button#show").mouseover(function() {
            			$(this).leanModal();
            		});
            		$("button#show").click(function() {
            			var id = $(this).attr("orderId");
            			show(id);
            		});
        		});
            }
            
            function show(id) {
				ajax("web/order/orderdetail",{orderId:id}, function(data) {
					if(data.ret != 0) {
						alert(data.msg);
						return;
					}
					
					var order = data.data;
					$("#msgOrderId").html("订单号: "+order.orderId);
					$("#msgDishes").html("菜品: "+order.dishes);
					$("#msgTotalPrice").html("总价: "+order.totalPrice);
					$("#msgRemark").html("备注: "+order.remark);
					$("#msgRestaurant").html("店铺: "+order.restaurant);
					$("#msgDistribution").html("取餐点: "+order.distribution);
					$("#msgTakeno").html("取餐号: "+order.takeNo);
					$("#msgStatus").html("状态: "+order.status);
					$("#msgUserId").html("用户ID: "+order.userId);
					$("#msgTel").html("用户电话: "+order.tel);
					$("#msgOrderType").html("付款方式: "+order.orderType);
					$("#msgHasPaid").html("是否付款: "+order.hasPaid);
					$("#msgEnable").html("取消订单原因: "+order.cancelRemark);
				});
            }
        </script>
	</body>
</html>