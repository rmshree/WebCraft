<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="resources/css/proj.css">
    <title>NittaCraft a.out Edition</title>
</head>
<body ng-app="app">
<div ng-include="'resources/scripts/controllers/nav/nav.html'"></div>
<main ng-view></main>

</body>

<%-- Angular injects --%>
<script src="resources/js/angular.min.js"></script>
<script src="resources/js/angular-route.min.js"></script>
<script src="resources/js/angular-resource.min.js"></script>
<%-- Angular injects --%>

<script src="resources/scripts/app.js"></script>

<%-- Controllers --%>
<script src="resources/scripts/controllers/landing/landing.js"></script>
<script src="resources/scripts/controllers/home/home.js"></script>
<script src="resources/scripts/controllers/about/about.js"></script>
<script src="resources/scripts/controllers/maps/maps.js"></script>
<script src="resources/scripts/controllers/rankings/rankings.js"></script>
<script src="resources/scripts/controllers/forums/forums.js"></script>
<script src="resources/scripts/controllers/nav/nav.js"></script>


<script src="resources/scripts/services/user.service.js"></script>


<%-- Google Fonts --%>
<link href="https://fonts.googleapis.com/css?family=Eagle+Lake" rel="stylesheet">

<%-- Controllers --%>