/**
 *provides login service to the app
 */
angular.module("LuluCC")
	.service("$login", ['$http', function($http){
	var self = this;
	self._error = "";
	
	self.login = function(username, password, url){
		return $http({
			'method': 'POST',
			'url': url,
			'data': {
						'username': username,
						'password': password
					}
		});
	};
	
	self.register = function(username, password, fname, lname, registerUrl){
		return $http({
			'method': 'POST',
			'url': registerUrl,
			'data': {
				'username': username,
				'password': password,
				'firstName': fname,
				'lastName': lname
			}
		});
	};
	
	self.hasError = function(){
		return(self._error != "");
	};
	
	self.error = function(error){
		return arguments.length ? (self._error = error) : self._error;
	};
	
	self.resetError = function(){
		self._error="";
	};
}]);
console.log("this file was loaded: loginservice.js");