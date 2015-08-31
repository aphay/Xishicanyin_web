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
                    	<#if id==0>
                        	订单创建
                        <#else>
                        	订单修改
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
                                            <label>用户Id</label>
                                            <input id="userId" type="text" class="form-control" placeholder="Enter ..."/>
                                        </div>
                                        <div class="form-group">
                                            <label>总价</label>
                                            <input id="totalPrice" type="text" class="form-control" placeholder="Enter ..."/>
                                        </div>
										<div class="form-group">
                                            <label>备注</label>
                                            <textarea id="remark" class="form-control" rows="3"></textarea>
                                        </div>

                                        <!-- select -->
	                                    <div class="form-group">
	                                        <label>菜品</label>
	                                        <select id="dishesList" multiple class="form-control">
	                                        	<#list dishesList as r>
	                                        		<option value="${r.id}">${r.name}</option>
	                                        	</#list>
	                                        </select>
	                                    </div>
	                                    <div class="form-group">
							            	<button id="add" type="button" class="btn bg-olive">添加</button>
							            </div>
	                                    <div class="form-group">
	                                    	<label>已选择的菜品</label>
											<select id="dishes" multiple class="form-control">
											</select>
	                                    </div>
	                                    <div class="form-group">
							            	<button id="remove" type="button" class="btn bg-olive">移除</button>
							            </div>
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
                                            <label>付款方式</label>
                                            <select id="orderType" class="form-control">
                                            	<#list orderTypeList as r>
                                            		<option value="${r_index}">${r}</option>
                                            	</#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>订单状态</label>
                                            <select id="status" class="form-control">
                                            	<#list statusList as r>
                                            		<option value="${r_index}">${r}</option>
                                            	</#list>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>是否付款</label>
                                            <select id="hasPaid" class="form-control">
                                            	<#list hasPaidList as r>
                                            		<option value="${r_index}">${r}</option>
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
				var id = ${id};
				$("select#dishesList").val(0);
				if(id == 0) {
					$("select#restaurant").val(0);
					$("select#orderType").val(0);
					$("select#status").val(0);
					$("select#hasPaid").val(0);
				}
				else {
					ajax("web/order/orderdetail2",{orderId:id},function(data){
						if(data.ret != 0) {
							alert(data.msg);
							return;
						}
						else {
							var order = data.data.order;
							var dishes = data.data.dishes;
							$("input#userId").val(order.userId);
							$("input#totalPrice").val(order.totalPrice);
							$("textarea#remark").val(order.remarks);
							for(var i = 0;i < dishes.length;i++) {
								var d = dishes[i];
								var option = '<option value="'+d.id+'">'+d.name+":"+d.count+'</option>';
								$("select#dishes").append(option);
							}
							$("select#restaurant").val(order.restaurantId);
							$("select#orderType").val(order.orderType);
							$("select#status").val(order.status);
							$("select#hasPaid").val(order.hasPaid?1:0);
						}
					});
				}
				
				$("button#save").click(function(){
					var id = ${id};
					var userId = $("input#userId").val(),
						totalPrice = $("input#totalPrice").val(),
						remark = $("textarea#remark").val(),
						restaurant = $("select#restaurant").val(),
						orderType = $("select#orderType").val(),
						status = $("select#status").val(),
						hasPaid = $("select#hasPaid").val();
					
					var dishes = {};
					$("select#dishes option").each(function(){
						var id = $(this).val(),
							num = $(this).text().split(":")[1];
						dishes[id] = num;
					});
					var dishesStr = JSON.stringify(dishes).replace(/\"/g,"");
					
					if(userId=="") {
						alert("请输入用户ID。");
						return;
					}
					if(isNaN(userId) || userId <= 0) {
						alert("用户ID必须为数字，且大于0。");
						return;
					}
					if(totalPrice == "") {
						alert("请输入总价。");
						return;
					}
					if(isNaN(totalPrice) || totalPrice <= 0) {
						alert("总价必须为数字，且大于0。");
						return;
					}
					if(dishesStr == "{}") {
						alert("请添加菜品");
						return;
					}
					if(restaurant == 0) {
						alert("请选择店铺。");
						return;
					}
					if(id == 0) {
						ajax("web/order/createorder",{
							userId:userId,
							totalPrice:totalPrice,
							voucherId:0,
							remark:remark,
							restaurantId:restaurant,
							orderType:orderType,
							status:status,
							hasPaid:hasPaid,
							dishes:dishesStr
						},function(data){
							alert(data.msg);
						});
					}
					else {
						ajax("web/order/updateorder",{
							orderId:id,
							userId:userId,
							totalPrice:totalPrice,
							voucherId:0,
							remark:remark,
							restaurantId:restaurant,
							orderType:orderType,
							status:status,
							hasPaid:hasPaid,
							dishes:dishesStr
						},function(data){
							alert(data.msg);
						});
					}
				});
				
				$("button#add").click(function(){
					var id = $("select#dishesList").val();
					if(id == null) {
						alert("请选择要添加的菜品。");
						return;
					}
					var name = $("select#dishesList").find("option:selected").text();
					var item = $("select#dishes option[value='"+id+"']").text();
					if(item == "") {
						$("select#dishes").append('<option value="'+id+'">'+name+':1'+'</option>');
					}
					else {
						var itemStr = item.split(":");
						var num = parseInt(itemStr[1])+1;
						$("select#dishes option[value='"+id+"']").text(name+":"+num);
					}
				});
				
				$("button#remove").click(function(){
					if($("select#dishes").val() == null) {
						alert("请选择要移除的菜品。");
						return;
					}
					var item = $("select#dishes").find("option:selected");
					var id = item.val();
					var text = item.text();
					var str = text.split(":");
					if(str[1] == 1) {
						item.remove();;
					}
					else {
						var num = parseInt(str[1])-1;
						item.text(str[0]+":"+num);
					}
				});
            });
        </script>
	</body>
</html>