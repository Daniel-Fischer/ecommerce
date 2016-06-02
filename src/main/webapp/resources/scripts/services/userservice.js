/**
 *handles the user session
 */
angular.module("LuluCC").service("$user", ["$http", function($http){
	var self = this;
	self._user = {
		"loggedin":false,
		"shoppingCart": []
	};
	
	self.user = function(user){
		if(arguments.length){
			if(user){
				var tempSc = self._user.shoppingCart;
				self._user = user;
				self._user.loggedin=true;				
				self._user.shoppingCart = tempSc;
			}else{
				self._user = {};
				self._user.loggedin=false;
				self._user.shoppingCart = [];
			}
			
		}else{
			return self._user;
		}
	};
	
	self.saveUser=function(newinfo, url){
		return $http({
			'method': 'POST',
			'url': url,
			'data': newinfo
		});
	};
	
	self.removeUserItem = function(ind){
		self._user.shoppingCart.splice(ind, 1);
	};	
	
}]);
console.log("this file was loaded: userservice.js");