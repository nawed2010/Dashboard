// create the module and name it scotchApp
var mainApp = angular.module('mainApp', ['ngRoute','ngCookies','ui.bootstrap']);

// configure our routes
mainApp.config(function($routeProvider) {
	$routeProvider
      
	.when('/login', {
		controller: 'LoginController',
		templateUrl: 'pages/login.html',
		//hideMenus: true
	})

	// route for the home page
	.when('/home', {
		templateUrl : 'pages/home_overview.html',
		controller  : 'homeController',
		/*	factory    :'dataShare'*/
		/*	filter   : 'unique'*/
	})

	// route for the projects page
	.when('/projects', {
		templateUrl : 'pages/projects.html',
		controller  : 'projectController'
	})

	// route for the resource page
	.when('/resource', {
		templateUrl : 'pages/resource.html',
		controller  : 'resourceController'
	})

	.when('/dataUpload', {
		templateUrl : 'pages/dataUpload.html',
		controller  : 'dataUploadController' ,
	})  

	// route for the password page
	.when('/password', {
		templateUrl : 'pages/password.html',
		controller  : 'passwordController'
	})

	.when('/passwordRetrieve', {
		templateUrl : 'pages/passwordRetrieve.html',
		controller  : 'forgetpasswordController'
	})
});

mainApp.run(['$rootScope', '$location', '$cookieStore', '$http',
             function ($rootScope, $location, $cookieStore, $http) {
	// keep user logged in after page refresh
	$rootScope.globals = $cookieStore.get('globals') || {};
	if ($rootScope.globals.currentUser) {
		$http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
	}

	$rootScope.$on('$locationChangeStart', function (event, next, current) {
		// redirect to login page if not logged in
		if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
			if($location.path() == '/passwordRetrieve'){
				$location.path('/passwordRetrieve');
			}else{
				$location.path('/login');
			}
		}
	});
}]);