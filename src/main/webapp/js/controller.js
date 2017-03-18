//create the controller and inject Angular's $scope
var module = angular.module('mainApp');

module.controller('LoginController',

function($scope, $rootScope, $location, AuthenticationService) {
	// reset login status
	AuthenticationService.ClearCredentials();

	$scope.login = function() {
		$scope.dataLoading = true;
		AuthenticationService.Login($scope.username, $scope.password, function(
				response, responsecr, errResponse) {

			if (responsecr.success) {

				AuthenticationService.SetCredentials(response);

				$location.path('/home');
			} else {

				$scope.errorMessage = responsecr.message;
				$scope.dataLoading = false;
			}
		});
	};
});

module
		.controller(
				'forgetpasswordController',
				function($scope, $http, dataShare, $rootScope, $route,
						$location) {

					$scope.forgetPassword = function() {

						$scope.urlgenerated = "/whguserportal/services/users/login/forgot/"
						$scope.data = {
							"email" : $scope.email
						}

						console.log($scope.urlgenerated)
						console.log($scope.data)
						$http({
							url : $scope.urlgenerated,
							dataType : 'json',
							method : 'POST',
							data : $scope.data,
							headers : {
								"Content-Type" : "application/json"
							}

						})
								.success(function(response) {
									console.log("service successfully called");
									$location.path('/login');
									return response;
								})
								.error(
										function(errResponse) {
											$scope.error = true;
											$scope.forgotPasswordErrorMessage = 'Sorry we could not find you! Please enter a valid Email Address.';
											console.log(errResponse)
											console
													.error('Error while calling service');
										});

					}
				});



mainApp.controller('passwordController', function($scope, $http, dataShare,$rootScope,$route,$modal,$location) {
	selectLeftNav();
	$scope.setnew=false;
	//$rootScope.changeemail=false;
	$scope.changePassword=true;
	$scope.errorshow=false;
	$scope.takehome=function(){
		console.log("hopefully")
		$location.path('/login');

	}



	//console.log($rootScope.globals)
	$scope.email=$rootScope.globals.currentUser.username;

	/*$scope.changeEmail=function(){
		$scope.changeemail=true;
	}*/
	$scope.passwordModification=function(new_password,confirmnew_password){
		console.log($rootScope.globals.currentUser.username)
		console.log($scope.email)
		console.log(new_password)
		console.log(confirmnew_password)
		$scope.userupdate=function(new_password){
			$scope.urlgenerated="/dashboard/services/users/login/update/"
				$scope.data={
					"email":$scope.email,
					"id": $rootScope.globals.currentUser.id,
					"password": $scope.new_password,
					"name" : $rootScope.globals.currentUser.name
			}


			console.log($scope.urlgenerated)
			console.log($scope.data)
			$http({
				url:  $scope.urlgenerated,
				dataType: 'json',
				method: 'POST',
				data: $scope.data,
				headers: {
					"Content-Type": "application/json"
				}

			}).success(function(response){
				console.log("successfully password/emailid changed ");
				$scope.changePassword=false;
				return response;


			}).error(function(errResponse){
				console.log(errResponse)
				console.error('Error while editing user');
			});



		}

		if(new_password==confirmnew_password){
			$scope.userupdate(new_password);
		}
		else{
			$scope.errorshow=true;
			$scope.errordata="Password entered do not match each other"
		}
	}

})







module
		.controller(
				"homeController",
				function($scope, $log, restServiceApiGet) {
					var userDetailsFromRestApi = restServiceApiGet
							.getUserProjectDetails("", "");
					var user;
					userDetailsFromRestApi.then(function(successResponse) {
						console.log("Success controller: ", successResponse);
						//$scope.user = user = successResponse.data;
						//Temp
						$scope.user = user ={
								"reBoarded": false,
								"id": 1,
								"name": "Sayak Deb",
								"email": "sayak.deb@email.com",
								"password": null,
								"access": "Admin",
								"accessCode": 1,
								"highestRole": "Bussiness User",
								"adminId": 0,
								"deleted": false,
								"hasIncompletetasks": false,
								"accounts": [{
									"id": 1,
									"name": "WHG",
									"projects": [{
										"name": "App development",
										"id": 1,
										"modules": [{
											"id": 1,
											"name": "BWS",
											"releases": [{
												"id": 1,
												"release_actual_id": "6446",
												"name": "CUG",
												"description": "",
												"is_deleted": false,
												"type": "desktop",
												"role": 1,
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											}]
										}]
									},{
										"name": "LQ Project",
										"id": 6,
										"modules": [{
											"id": 4,
											"name": "LQ Module",
											"releases": [{
												"id": 3,
												"release_actual_id": "E445",
												"name": "RAAT",
												"description": "Hotels Id should be fetched at once",
												"is_deleted": false,
												"type": "mobile",
												"role": 4,
												"status":'red',
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											},{
												"id": 3,
												"release_actual_id": "E445",
												"name": "NVRAAT",
												"description": "Hotels Id should be fetched at once",
												"is_deleted": false,
												"type": "mobile",
												"role": 4,
												"status":'green',
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											}]
										},
										{
											"id": 4,
											"name": "LQ Module",
											"releases": [{
												"id": 3,
												"release_actual_id": "E445",
												"name": "RAAT",
												"description": "Hotels Id should be fetched at once",
												"is_deleted": false,
												"type": "mobile",
												"role": 4,
												"status":'red',
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											},{
												"id": 3,
												"release_actual_id": "E445",
												"name": "NVRAAT",
												"description": "Hotels Id should be fetched at once",
												"is_deleted": false,
												"type": "mobile",
												"role": 4,
												"status":'green',
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											}]
										}
										]
									}
									
									]
								},
								{
									"id": 2,
									"name": "Starwood",
									"projects": [{
										"name": "whg support",
										"id": 6,
										"modules": [{
											"id": 4,
											"name": "CRS",
											"releases": [{
												"id": 3,
												"release_actual_id": "E445",
												"name": "Endeca data fetch",
												"description": "Hotels Id should be fetched at once",
												"is_deleted": false,
												"type": "mobile",
												"role": 4,
												"cut_End_date": "2016-07-26 00:00:00.0",
												"ddd_delivery": "NA",
												"qa_end_date": "2016-10-16 00:00:00.0",
												"qa_start_date": "2016-09-16 00:00:00.0",
												"uat_end_date": "2016-10-30 00:00:00.0",
												"prod_Release": "2016-11-20 00:00:00.0",
												"preview_Release": "2016-11-16 00:00:00.0",
												"uat_start_date": "2016-10-26 00:00:00.0"
											}]
										}]
									}]
								}]
							};
						
						 $scope.accountSelectedObj = $scope.user.accounts[0];
						 $scope.refreshValues(false);
						
					},
							function(errorResponse) {
								console.error("Error from controller: ",
										errorResponse);
							});


					// When application loads up

					$scope.refreshValues = function(isUserInitiated) {

						console.log("Refresh function triggerd");

						if ($scope.accountSelectedObj) {
							
							// For selected account project list will get
							// defined
							accounts = user.accounts;
							for (var i = 0; i < accounts.length; i++) {
								if (accounts[i].id == $scope.accountSelectedObj.id) {
									$scope.projects = accounts[i].projects;
									break;
								}
							}
							if(!isUserInitiated){
								$scope.projectSelectedObj = $scope.projects[0];
							}
							// $scope.refreshValues();

							if ($scope.projectSelectedObj) {
								// For select project module list will get
								// defined
								for (var i = 0; i < $scope.projects.length; i++) {
									if ($scope.projects[i].id == $scope.projectSelectedObj.id) {
										$scope.modules = $scope.projects[i].modules;
										break;
									}
								}
								if(!isUserInitiated){
									$scope.moduleSelectedObj = $scope.modules[0];
								}
								
							}
							if ($scope.moduleSelectedObj) {
								// For selected module release list will get
								// defined
								for (var i = 0; i < $scope.modules.length; i++) {
									if ($scope.modules[i].id === $scope.moduleSelectedObj.id) {
										$scope.releases = $scope.modules[i].releases;
										break;
									}
								}
								 //Enable it if user needs complete automation
								 //$scope.releaseSelectedObj = $scope.releases[0];
							}
							if ($scope.releaseSelectedObj) {
								// For selected single release
								for (var i = 0; i < $scope.releases.length; i++) {
									if ($scope.releases[i].id === $scope.releaseSelectedObj.id) {
										$scope.release = $scope.releases[i];
										$log.log("Final Project :",
												$scope.releases[i]);
										if ($scope.release.status == "red") {
											$scope.release.class = "alert-danger";
											// angular.element('.releaseDetails').removeClass("panel-success");
											// angular.element('.releaseDetails').addClass("panel-danger");
										} else {
											$scope.release.class = "alert-success";
											if (angular.element(
													'.releaseDetails')
													.hasClass("alert-danger")) {
												angular.element(
														'.releaseDetails')
														.removeClass(
																"alert-danger");
											}

											// angular.element('.releaseDetails').addClass("panel-success");
										}
										break;
									}

								}

								$("#releaseModal").modal('show');
								$scope.releaseSelectedObj = false;
							}
						}
						;
					}
					
					//For resetting some values to null
					var reset = function(){
						//$scope.projects = null;
						$scope.modules = null;
						$scope.releases = null;
					}

				});

// This controller is used for data uploading.
module.controller("dataUploadController", function($scope, restServiceApiPost) {

	$scope.uploadFile = function() {
		var file = $scope.myFile;

		console.log('file is ');
		console.dir(file);
var risks={};
		if (!(file == undefined)) {
			if ((file.name.indexOf(".xlsx") > -1)) {
				if ((file.name.indexOf("risks (") > -1)
						|| (file.name.indexOf("defects (") > -1)
						|| (file.name.indexOf("risks") > -1)
						|| (file.name.indexOf("defects") > -1)) {
					//restServiceApiPost.uploadExcelFile(file);
					var fileName=( (file.name.indexOf("defects (") > -1)
						|| (file.name.indexOf("defects") > -1))? "defect":"risk";
					
					console.log("Template selected is of "+fileName+" type");
					var accountRisks = restServiceApiPost
					.uploadExcelFile(file);

					accountRisks.then(function(successResponse) {
						if (fileName=="risk"){
				console.log("List of risks : ",
						successResponse.data);
				
				risks = successResponse.data;
				$scope.fileType =fileName;
				$scope.risks = risks;
						}
						else if(fileName=="defect")
						{
							console.log("List of defects : ",
									successResponse.data);
							
							defects = successResponse.data;
							$scope.fileType =fileName;
							$scope.risks = defects;
						}
						}, function(errorResponse) {
				console.log("Error in getting list of risks/defects.");
			});

						console.log(risks.release_actual_id);
					
					$("#uploadModal").modal('show');
					
					
					//////////////////////////////////////////////////////////////////////////////////
					$scope.successMessage = "File uploaded successfully.";
					$scope.errorMessage = null;
				} else {
					$scope.successMessage = null;
					$scope.errorMessage = "Please upload a correct template.";
				}
			} else {
				$scope.successMessage = null;
				$scope.errorMessage = "Please upload a correct template.";
			}

		} else {
			console.log("Please select a file first.");
			$scope.errorMessage = "Please select a file first.";
		}

	};

});

// This controller is used for editing or adding a new project
module
		.controller(
				"projectController",
				function($scope, restServiceApiPost, restServiceApiGet) {

					// Adding or editing a project
					console.log("Adding or editing a project");
					// For reseting some values
					$scope.reset = function() {
						$scope.successMessage = "";
						$scope.errorMessage = "";
					};

					$scope.accountDisplay = "Select an Account";
					$scope.projectDisplay = "Select a Project";
					$scope.moduleDisplay = "Select a Module";
					$scope.releaseDisplay = "Select to edit a Release";

					var details = {};

					// For fetching all accounts details -- This call will
					// happen by default
					var fetchAccountsInfo = function() {
						var accountListRestApi = restServiceApiGet
								.getListOfAllAccounts();

						accountListRestApi.then(function(successResponse) {
							console.log("Success controller: ",
									successResponse.data);
							details.accountsList = successResponse.data;
							$scope.details = details;
						}, function(errorResponse) {
							console.log("Error controller: ", errorResponse);
						});

					};

					fetchAccountsInfo(); // By Default call

					$scope.accountSelect = function(id, name) {

						$scope.accountDisplay = name;
						$scope.accountId = id; // Used in Modal

						var projectsListRestApi = restServiceApiGet
								.getListOfProjects(id);

						projectsListRestApi.then(function(successResponse) {
							console.log("List of projects : ",
									successResponse.data);
							details.projectsList = successResponse.data;
							$scope.details = details;
							$scope.accountSelected = true;
						}, function(errorResponse) {
							console.log("Error in getting list of project.");
						});
					};

					$scope.projectSelect = function(id, name) {
						console.log("Selected Project Id: " + name);
						$scope.projectDisplay = name;
						$scope.projectId = id; // Used in Modal
						$scope.projectName = name; // Used in Modal

						var modulesListRestApi = restServiceApiGet
								.getListOfModules(id);

						modulesListRestApi.then(function(successResponse) {
							console.log("List of modules : ",
									successResponse.data);
							details.modulesList = successResponse.data;
							$scope.details = details;
							$scope.projectSelected = true;
						}, function(errorResponse) {
							console.log("Error in fetching module list.");
						});
					};

					$scope.moduleSelect = function(id, name) {
						console.log("Selected Project Id: " + name);
						$scope.moduleDisplay = name;
						$scope.moduleId = id; // Used in Modal

						var releasesListRestApi = restServiceApiGet
								.getListOfReleases(id);

						releasesListRestApi.then(function(successResponse) {
							console.log("List of releases : ",
									successResponse.data);
							details.releasesList = successResponse.data;
							$scope.details = details;
							$scope.moduleSelected = true;
						}, function(errorResponse) {
							console.log("Error in fetching release list.");
						});
					};

					$scope.releaseSelect = function(id, name, release,description) {
						console.log("Selected Release: ", release);
						$scope.releaseDisplay = name;
						details.release = release;
						$scope.details = details;
						console.log($scope.details);
						$scope.releaseSelected = true;
					};

					// POST OPERATIONS
					// For Adding account
					$scope.addAccount = function() {
						console.log("Add account! ", $scope.accountName);
						var addAccountPromise = restServiceApiPost
								.addAccount($scope.accountName);
						addAccountPromise
								.then(
										function(successResponse) {
											console
													.log(
															"Add account service successfully called: ",
															successResponse);

											if (successResponse.data.name
													&& successResponse.data.name === $scope.accountName) {
												$scope.errorMessage = "";
												$scope.accountName = null;
												$scope.successMessage = "Account Added Succesfully.";
												fetchAccountsInfo();

											} else {
												$scope.successMessage = "";
												$scope.errorMessage = "Account with this name already exists.";
											}
										},
										function(errorResponse) {
											$scope.successMessage = "";
											$scope.errorMessage = "Error in Adding Account. Please try after some time.";
											console.log(
													"Error in adding Accont: ",
													errorResponse);
										});
					};

					// For Adding Project
					$scope.addProject = function() {
						console.log("Add Project! ", $scope.projectName);
						var addProjectPromise = restServiceApiPost.addProject(
								$scope.projectName, $scope.accountId);
						addProjectPromise
								.then(
										function(successResponse) {
											console
													.log(
															"Add account service successfully called: ",
															successResponse);
											if (successResponse.data.name
													&& successResponse.data.name === $scope.projectName) {
												$scope.errorMessage = "";
												$scope.projectName = null;
												$scope.accountId = null;
												$scope.successMessage = "Project Added Succesfully.";
												// fetchAccountsInfo();
												// TODO
												// $scope.projectSelect($scope.projectId,
												// $scope.p)

											} else {
												$scope.successMessage = "";
												$scope.errorMessage = "Project with this name already exists.";
											}
										},
										function(errorResponse) {
											$scope.successMessage = "";
											$scope.errorMessage = "Error in Adding Project. Please try after some time.";
											console
													.log(
															"Error in adding Project: ",
															errorResponse);
										});
					};
					// For Adding Module
					$scope.addModule = function() {
						console.log("Add Module! ", $scope.moduleName);
						var addModulePromise = restServiceApiPost.addModule(
								$scope.moduleName, $scope.projectId);
						addModulePromise
								.then(
										function(successResponse) {
											console
													.log(
															"Add module service successfully called: ",
															successResponse);
											if (successResponse.data.name
													&& successResponse.data.name === $scope.moduleName) {
												$scope.errorMessage = "";

												$scope.successMessage = "Module Added Succesfully.";
												// fetchAccountsInfo();
												$scope.projectSelect(
														$scope.projectId,
														$scope.projectName);
												$scope.moduleName = null;
												// $scope.projectId = null;

											} else {
												$scope.successMessage = "";
												$scope.errorMessage = "Module with this name already exists.";
											}
										},
										function(errorResponse) {
											$scope.successMessage = "";
											$scope.errorMessage = "Error in Adding Module. Please try after some time.";
											console.log(
													"Error in adding Module: ",
													errorResponse);
										});
					};
					

				//});
				

       //For Adding Release

$scope.addRelease = function() {
	console.log("Add Release! ", $scope.releaseName);
	var addReleasePromise = restServiceApiPost.addRelease(
			$scope.releaseName , $scope.releaseId, $scope.description , $scope.Delivery, $scope.cutEndDate , $scope.qaStartDate, $scope.qaEndDate ,
			$scope.uatStartDate, $scope.uatEndDate , $scope.reviewRelase, $scope.productionRelease , $scope.type );
	addReleasePromise
			.then(
					function(successResponse) {
						console
								.log(
										"Add release service successfully called: ",
										successResponse);
						console.log( successResponse.name);
						console.log($scope.releaseName);
						
						if (successResponse.data.name
								&& successResponse.data.name === $scope.releaseName) {
							$scope.errorMessage = "";

							$scope.successMessage = "Release Added Succesfully.";
							// fetchAccountsInfo();
							$scope.moduleSelect(
									$scope.moduleId);
							$scope.releaseName = null;
							// $scope.projectId = null;

						} else {
							$scope.successMessage = "";
							$scope.errorMessage = "Release with this name already exists.";
						}
						
						
					},
					function(errorResponse) {
						$scope.successMessage = "";
						$scope.errorMessage = "Error in Adding Release. Please try after some time.";
						console.log("Error in adding Release: ",errorResponse);
					});
	
		};
		
		$scope.checkErr = function(qaStartDate,qaEndDate){
		    $scope.errMessage = '';
		    $scope.curDate = new Date();

		    if(qaStartDate < qaEndDate){
		      $scope.errMessage = 'End Date should be greate than start date';
		      return false;
		    }
		    if(qaStartDate < curDate){
		       $scope.errMessage = 'Start date should not be before today.';
		       return false;
		    }

		  };

});


// This controller is used for adding a resource
module.controller("resourceController", function($scope, restServiceApiPost) {

	// Adding a resource
	console.log("Adding a resource");

});



