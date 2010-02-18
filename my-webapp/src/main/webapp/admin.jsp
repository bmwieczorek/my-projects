<html>
<head>
<title>Status page</title>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type='text/javascript' src='dwr/interface/Admin.js'></script>
<script type="text/javascript">
function login(){
	username = dwr.util.getValue("username");
	Admin.welcomeUser(username, function(data){
		dwr.util.setValue("serverResp", data);
	});
}
</script>
</head>
<body>
Login
<br>
<form>
Username: <input type="text" id="username" />
<br/>
Password: <input type="password" id="password" />
</form>
<br/>
<button onclick="login()">Sign in</button>

<span id="serverResp"></span>
</body>
</html>