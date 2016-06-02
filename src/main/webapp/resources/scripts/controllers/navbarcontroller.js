/**
 *Controller for the navbar
 */
console.log("this file was loaded: navbarcontroller.js");
angular.module("LuluCC").controller("Navbar", ['$login', '$user', '$state', 'loginUrl',
                                               function($login, $user, $state, loginUrl){
	
	var self = this;
	self.doingLogin = false;
	self.username = "";
	self.password = "";
	
	self.toggleLoginForm = function(){
		var loginPromise;
		if(self.doingLogin){
			//use the login in service to login
			self.doLogin();
		}else{
			self.doingLogin = true;
		}
	};
	self.doLogin = function(){
		loginPromise = $login.login(self.username, self.password, loginUrl);
		loginPromise.then(function(response){
			$user.user(response.data);
			self.username = "";
			self.password = "";
			self.doingLogin = false;
		}, function(error){
			self.username = "";
			self.password = "";
			self.doingLogin = false;
			$login.error("Your username/password could not be found.");
			$state.go('login');
		});
	};
	
	self.doLogout = function(){
		self.needLogin = true;
		$user.user(null);
		$state.go("home");
	};
	
	self.isDoingLogin = function(){
		return self.doingLogin;
	};
	
	self.loginNeeded = function(){
		return $user.user().loggedin;
	};
	
	self.cartItems = function(){
		return ($user.user().shoppingCart.length > 0) ? $user.user().shoppingCart.length : "";
	};
	
}]);