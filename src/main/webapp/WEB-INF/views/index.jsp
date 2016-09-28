<head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="resources/css/proj.css">
    <title>Webcraft</title>
</head>
<body ng-app="app">
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
<script src="resources/scripts/controllers/forums/forums.js"></script>
<%-- Controllers --%>