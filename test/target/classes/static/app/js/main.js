var testApp = angular.module("testApp",["ngRoute"]);

testApp.controller("HomeCtrl", function($scope){
	$scope.message="Zadatak sa zavrsnog testa";
});

testApp.controller("SprintoviCtrl", function($scope, $http){
	
	var sprintoviUrl = "/api/sprintovi";
	
	var getSprintovi = function(){
		var promise = $http.get(sprintoviUrl);
		promise.then(
			function success(res){
				$scope.sprintovi = res.data;
			},
			function error(res){
				alert("Couldn't fetch sprintovi");
			}
		);
	}
	
	getSprintovi();
});

testApp.controller("StanjaCtrl", function($scope, $http){
	
	var stanjaUrl = "/api/stanja";
	
	var getStanja = function(){
		var promise = $http.get(stanjaUrl);
		promise.then(
			function success(res){
				$scope.stanja = res.data;
			},
			function error(res){
				alert("Couldn't fetch stanja");
			}
		);
	}
	
	getStanja();
});

testApp.controller("ZadaciCtrl", function($scope, $http, $location){
	
	var zadaciUrl = "/api/zadaci";
	var stanjaUrl = "/api/stanja";
	var sprintoviUrl = "/api/sprintovi";
	
	$scope.zadaci = [];
	$scope.stanja = [];
	$scope.sprintovi = [];
	
	$scope.novi = {};
	$scope.novi.ime = "";
	$scope.novi.zaduzeni = "";
	$scope.novi.bodovi = "";
	$scope.novi.stanjeId = "";
	$scope.novi.sprintId = "";
	
	$scope.sParams = {};
	$scope.sParams.ime = "";
	$scope.sParams.sprintId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	//TODO: dodati obeležja kojim se povezuje sa korisnikom i aktivnošću
	
	
	var getZadaci = function(){
		
		var config = {params:{}};
		
		if($scope.sParams.ime != ""){
			config.params.ime = $scope.sParams.ime;
		}
		
		if($scope.sParams.sprintId != ""){
			config.params.sprintId = $scope.sParams.sprintId;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(zadaciUrl, config);
		promise.then(
			function success(res){
				$scope.totalPages = res.headers("totalPages");
				$scope.zadaci = res.data;
			},
			function error(){
				alert("Couldn't fetch zadaci");
			}
		);
	}
	
	getZadaci();
	
	//TODO: Obezbediti prihvat korisnika i aktivnosti
	var getStanja = function(){
		var promise = $http.get(stanjaUrl);
		promise.then(
			function success(res){
				$scope.stanja = res.data;
			},
			function error(res){
				alert("Couldn't fetch stanja");
			}
		);
	}
	
	getStanja();
	
	
	
	var getSprintovi = function(){
		var promise = $http.get(sprintoviUrl);
		promise.then(
			function success(res){
				$scope.sprintovi = res.data;
			},
			function error(res){
				alert("Couldn't fetch sprintovi");
			}
		);
	}
	
	getSprintovi();
	
	
	
	
	$scope.doAdd = function(){
		
		$http.post(zadaciUrl, $scope.novi).then(
			function success(res){
				alert("Uspesno ste dodali zadatak.");
				getZadaci();
	
			},
			function error(){
				alert("Couldn't save the zadatak");
			}
		);
	}
	
	$scope.doClearAdd = function(){
		$scope.novi = {};
		$scope.novi.ime = "";
		$scope.novi.zaduzeni = "";
		$scope.novi.bodovi = "";
		$scope.novi.stanjeId = "";
		$scope.novi.sprintId = "";
		getZadaci();
	}
	
	$scope.goToEdit = function(id){
		$location.path("/zadaci/edit/" + id);
	}
	
	$scope.doDelete = function(id){
		var promise = $http.delete(zadaciUrl + "/" + id);
		promise.then(
			function success(){
				getZadaci();
			},
			function error(){
				alert("Couldn't delete the zadatak.");
			}
		);
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getZadaci();
	}
	
	$scope.doClear = function(){
		$scope.sParams = {};
		$scope.sParams.ime = "";
		$scope.sParams.sprintId = "";
		getZadaci();
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getZadaci();
	}
	
	$scope.doClear = function(){
		$scope.sParams = {};
		$scope.sParams.ime = "";
		$scope.sParams.sprintId = "";
		getZadaci();
	}
	
	$scope.doPredji = function(id){
		var promise = $http.post(zadaciUrl + "/" + id);
		promise.then(
			function success(){
				alert("Uspešno ste promenili stanje.");
				getZadaci();
			},
			function error(){
				alert("Neuspesna promena stanja.")
				getZadaci();
			}
		);
	}
});


testApp.controller("EditZadatakCtrl", function($scope, $http, $routeParams, $location){
	
	var zadatakUrl = "/api/zadaci/" + $routeParams.id;
	var stanjaUrl = "/api/stanja";
	var sprintoviUrl = "/api/sprintovi";
	
	$scope.izmeni = {};
	$scope.izmeni.ime = "";
	$scope.izmeni.zaduzeni = "";
	$scope.izmeni.bodovi = "";
	$scope.izmeni.stanjeId = "";
	$scope.izmeni.sprintId = "";	
	
	
	$scope.stanja = [];
	$scope.sprintovi = [];
	
	
	
	var getStanja = function(){
		$http.get(stanjaUrl).then(
			function success(res){
				$scope.stanja = res.data;
				getSprintovi();
			},
			function error(){
				alert("Couldn't fetch activities");
			}
		);
	}
	
	var getSprintovi = function(){
		return $http.get(sprintoviUrl).then(
			function success(res){
				$scope.sprintovi = res.data;
				getZadatak();
			},
			function error(){
				alert("Couldn't fetch users.");
			}
		);
	}
	
	var getZadatak = function(){
		$http.get(zadatakUrl).then(
			function success(res){
				$scope.izmeni = res.data;
			},
			function error(){
				alert("Couldn't fetch record.");
			}
		);
	}
	
	//TODO: Obezbediti redosled izvrsavanja!
	getStanja();
	
	
	
	
	$scope.doEdit = function(){
		$http.put(zadatakUrl, $scope.izmeni).then(
			function success(){
				$location.path("/zadaci");
			},
			function error(){
				alert("Something went wrong.");
			}
		);
	}
});




testApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/home.html',
			controller: "HomeCtrl"
		})
		.when('/sprintovi', {
			templateUrl : '/app/html/sprintovi.html'
		})
		.when('/stanja', {
			templateUrl : '/app/html/stanja.html'
		})
		.when('/zadaci', {
			templateUrl : '/app/html/zadaci.html'
		})
		.when('/zadaci/edit/:id', {
			templateUrl : '/app/html/edit-zadatak.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);