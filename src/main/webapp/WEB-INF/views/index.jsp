<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="resources/css/proj.css">
    <title>NittaCraft a.out Edition</title>
</head>
<body ng-app="app" style="background-color: #0f253c;">
    <div ng-include="'resources/scripts/controllers/nav/nav.html'"></div>
    <main ng-view></main>
</body>

<%-- Angular injects --%>
<script src="resources/js/angular.min.js"></script>
<script src="resources/js/angular-route.min.js"></script>
<script src="resources/js/angular-resource.min.js"></script>
<%-- Angular injects --%>

<script src="resources/scripts/app.js"></script>

<%-- Third party JS --%>
<script src="resources/js/ng-file-upload.min.js"></script>
<script src="resources/js/ng-device-detector.min.js"></script>
<script src="resources/js/re-tree.min.js"></script>
<script src="resources/js/html2canvas.js"></script>
<%--<script src="http://html2canvas.hertzen.com/build/html2canvas.js"></script>--%>
<%-- Third party JS --%>

<%-- Controllers --%>
<script src="resources/scripts/controllers/nav/nav.js"></script>
<script src="resources/scripts/controllers/home/home.js"></script>
<script src="resources/scripts/controllers/about/about.js"></script>
<script src="resources/scripts/controllers/maps/maps.js"></script>
<script src="resources/scripts/controllers/rankings/rankings.js"></script>
<script src="resources/scripts/controllers/forums/forums.js"></script>
<script src="resources/scripts/controllers/forums/forum.js"></script>
<script src="resources/scripts/controllers/profile/profile.js"></script>
<script src="resources/scripts/controllers/signup/signup.js"></script>
<script src="resources/scripts/controllers/settings/settings.js"></script>
<script src="resources/scripts/controllers/download/download.js"></script>
<script src="resources/scripts/controllers/signup/login.js"></script>
<script src="resources/scripts/controllers/signup/recover.js"></script>
<script src="resources/scripts/controllers/verify/verify.js"></script>
<script src="resources/scripts/controllers/match/match.js"></script>
<script src="resources/scripts/controllers/maps/map.js"></script>
<script src="resources/scripts/controllers/messages/message.js"></script>
<script src="resources/scripts/controllers/messages/compose.js"></script>




<%-- Controllers --%>

<%-- Google Fonts--%>
<link href="https://fonts.googleapis.com/css?family=Pontano+Sans|IM+Fell+Great+Primer+SC" rel="stylesheet">

<%-- Font Awesome --%>
<script src="https://use.fontawesome.com/d06f7b03ff.js"></script>

<%-- Services --%>
<script src="resources/scripts/services/user.service.js"></script>
<script src="resources/scripts/services/login.service.js"></script>
<script src="resources/scripts/services/forums.service.js"></script>
<script src="resources/scripts/services/map.service.js"></script>
<script src="resources/scripts/services/settings.service.js"></script>
<script src="resources/scripts/services/match.service.js"></script>
<script src="resources/scripts/services/message.service.js"></script>
<%-- Services --%>