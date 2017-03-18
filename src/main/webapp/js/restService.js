(function() {

	var module = angular.module('mainApp');

	module.factory("restServiceApiGet", function($http, $rootScope) {
		var userId = $rootScope.globals.currentUser.id;
		var urlGet = "services/resource/" + userId;

		var getUserProjectDetails = function(userName, password) {

			var user = $http.get(urlGet).success(function(response) {
				console.log("Success: ", response);
				//return response;

			}).error(function(response) {
				console.log("Error: ", response);
				//return response;
			});

			return user;

		};

		//For getting list of all accounts
		var getAllAccounts = function() {

			var accountsList = $http.get('services/account/').success(
					function(response) {
						console.log("List of accounts: ", response);
					}).error(function(errror) {
				console.log("Error getting list of accounts");
			});

			return accountsList;

		};

		//Get list of projects based on account selected
		var getProjects = function(id) {
			var projectList = $http.get('services/project/' + id);
			return projectList;
		};

		//Get list of modules based on project selected
		var getModules = function(id) {
			var modulesList = $http.get('services/module/' + id);
			return modulesList;
		};

		//Get list of releases based on module selected
		var getListOfReleases = function(id) {
			var releasesList = $http.get('services/release/' + id);
			return releasesList;
		};

		//Exposed methods
		return {
			getUserProjectDetails : getUserProjectDetails,
			getListOfAllAccounts : getAllAccounts,
			getListOfProjects : getProjects,
			getListOfModules : getModules,
			getListOfReleases : getListOfReleases,
		};

	});

	module.factory("restServiceApiPost", function($http, $rootScope) {

		var uploadFile = function(file) {

			var uploadUrl = "services/upload/risk";
			var fd = new FormData();
			fd.append('file', file);

			var risks=$http.post(uploadUrl, fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			})

			.success(function(response) {
				console.log('File uploaded successfully from service');
				//console.log(response);
				$rootScope.risk= response;
				console.log($rootScope.risk);
				
			})

			.error(function() {
				console.log('error in uploading file from service');
			});
			return risks;
		};

		//For Adding an account
		var addAccount = function(accountName) {
			var addAccountPromise = $http.post('services/account/add', {
				name : accountName
			}).success(function(response) {
				console.log("Add account service called: ", response);
			}).error(function() {
				console.log("Error in adding Account.");
			});

			return addAccountPromise;
		};
		//For Adding a Project
		var addProject = function(name, accountId) {
			var addProjectPromise = $http.post('services/project/add', {
				name : name,
				id : accountId
			}).success(function(response) {
				console.log("Add project service called: ", response);
			}).error(function() {
				console.log("Error in adding Project.");
			});

			return addProjectPromise;
		};
		//For Adding a Module
		var addModule = function(name, projectId) {
			var addModulePromise = $http.post('services/module/add', {
				name : name,
				id : projectId
			}).success(function(response) {
				console.log("Add module service called: ", response);
			}).error(function() {
				console.log("Error in adding Module.");
			});

			return addModulePromise;
		};
		//For Adding a Release
		var addRelease = function( name, release_actual_id, description, DDD_delivery, CUT_End_date, QA_start_date, QA_end_date, UAT_start_date, UAT_end_date, preview_Release, prod_Release, Type) {
			var addReleasePromise = $http.post('services/release/add',{
				//id : module_id,
				name : name,
				releaseid : release_actual_id,
				description : description,
				delivery : DDD_delivery,
				cutenddate : CUT_End_date,
				qastartdate : QA_start_date,
				qaenddate :  QA_end_date,
				uatstartdate : UAT_start_date,
				uatenddate : UAT_end_date,
			    previewRelease : preview_Release, 
			    prodRelease : prod_Release, 
			    //isdeleted : isdeleted,
			    type : Type
				
			}).success(function(response){
				console.log("Add release service called: ", response);
			}).error(function() {
				console.log("Error in adding Realease.");
			});	
			
			return addReleasePromise;
		};

		//Exposed Methods
		return {
			uploadExcelFile : uploadFile,
			addAccount : addAccount,
			addProject : addProject,
			addModule : addModule,
			addRelease : addRelease
		};

	});

}());