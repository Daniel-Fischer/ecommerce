/**
 * Controller for the register user form
 */
angular.module("LuluCC")
	.controller("Register", ['$login', '$user', '$state', 'registerUrl', function($login, $user, $state, registerUrl){
		var self = this;
		self._username;
		self._password;
		self._fname;
		self._lname;
		self._registering;
		
		self.username = function(username){
			return arguments.length ? (self._username = username) : self._username;
		};
		
		self.password = function(password){
			return arguments.length ? (self._password = password) : self._password;
		};
		
		self.firstName = function(firstName){
			return arguments.length ? (self._fname = firstName) : self._fname;
		};
		
		self.lastName = function(lastName){
			return arguments.length ? (self._lname = lastName) : self._lname;
		};
		
		self.doRegister = function(){
			var registerPromise;
			self._registering = true;
			registerPromise = $login.register(self._username, self._password, self._fname, self._lname, registerUrl);
			registerPromise.then(function(response){
				$user.user(response.data);
				self._registering = false;
				self._username = "";
				self._password = "";
				self._fname = "";
				self._lname = "";
				$state.go("home");
			}, function(error){
				self._registering = false;
				$login.error("Your account could not be created. That email address may already be in use.");
			});
		};
		
		self.hasError = function(){
			return $login.hasError();
		};
		
		self.error = function(){
			return $login.error();
		};
		
		self.resetError = function(){
			$login.resetError();
		}
		
		self.isRegistering = function(){
			return self._registering;
		};
	}]);
console.log("this file has been loaded: registerformcontroller.js");