INSERT INTO "SYS_EMAIL_TEMPLATE" VALUES (1, 'SECURITY', 'FORGOTPWD', 'Forgot Password', '
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title>ORO</title>
<style type="text/css">
.email-template * {
	font-family: Arial;
}

.email-template {
	margin: 0px auto;
	width: auto;
	max-width: 600px;
	width: 600px;
	font-family: Arial;
}

.email-header {
	padding: 8px 25px;
	background-color: #FFC503;
	font-family: Arial;
}

.email-logo {
	width: 50px;
	vertical-align: middle;
}

.email-app-name {
	font-size: 18px;
	padding-left: 0px;
	font-family: Arial;
}

.email-content {
	border: 1px solid #ddd;
	border-top: 0px solid #eee;
	text-align: justify;
	font-size: 15px;
	color: #333;
	font-family: Arial;
}

.email-title {
	font-size: 19px;
	font-weight: bold;
	color: #444;
	text-decoration: underline;
	text-align: left;
	font-family: Arial;
}

.email-buttons {
	font-family: Arial;
	color: #222;
}

.email-buttons a {
	color: #222;
	font-family: Arial;
	text-decoration: none;
}

.view-this {
	color: #222;
	font-size: 12px;
	font-family: Arial;
}

.view-this a {
	color: #222;
	font-size: 12px;
	font-family: Arial;
}
</style>
</head>

<body>
	<div class="email-template">
		<table class="email-header" width="100%" cellpadding="0"
			cellspacing="0" border="0" bgcolor="#FFC503">
			<tr>
				<td class="email-logo" width="70"><img width="50" height="50"
					src="${webAddress}/javax.faces.resource/images/favicon.png.xhtml?ln=primefaces-oro" /></td>
				<td class="email-app-name">Official Receiver''s Office</td>
			</tr>
		</table>
		<table class="email-content" cellpadding="20" cellspacing="0"
			border="0" bgcolor="#FFFFFF" style="width: 100%">
			<tr>
				<td><div class="email-title">Forgot Password</div> <br />
					<div>Dear ${engName}</div> <br /> Please change your password. <br />
					<br />
					<table class="email-buttons" cellpadding="15" cellspacing="0"
						border="0" bgcolor="#FFC503">
						<tr>
							<td><a
								href="${webAddress}/oro/admin/index/change-password.xhtml?token=${token}"
								target="_blank">Link to change password</a></td>
						</tr>
					</table> <br /> <br /></td>
			</tr>
		</table>
	</div>
</body>
</html>
');
