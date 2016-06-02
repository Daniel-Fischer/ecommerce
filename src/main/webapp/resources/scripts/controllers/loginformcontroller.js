/**
 * Controller for the login controller
 */
angular.module("LuluCC").controller("Login", ['$login', '$user', '$state', 'loginUrl', 
                                                  function($login, $user, $state, loginUrl){
	var self = this;
	self._username;
	self._password;
	self.doLogin = function(){
		var loginPromise;
		loginPromise = $login.login(self._username, self._password, loginUrl)
		loginPromise.then(function(response){
			$user.user(response.data);
			self._username = "";
			self._password = "";
			$login.error("");
			$state.go('home');
		}, function(error){
			self._username = "";
			self._password = "";
			$login.error("Your username/password could not be be found.");
		});
	};
	
	self.hasError = function(){
		return $login.hasError();
	};
	
	self.error = function(){
		return $login.error();
	};
	
	self.password = function(password){
		return arguments.length ? (self._password = password) : self._password;
	};
	
	self.username = function(username){
		return arguments.length ? (self._username = username) : self._username;
	};
}]);
console.log("this file was loaded: loginformcontroller.js");