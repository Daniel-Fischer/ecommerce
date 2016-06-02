/**
 * This is the controller for mycart.html
 */
angular.module("LuluCC")
	.controller("MyCart", ['oUrl', '$user', '$http', '$state', function(oUrl,$user, $http, $state){
		var self = this;
		
		
		self.cupcakes = function(){
			return $user.user().shoppingCart;
		};
		
		self.submit = function(){
			$state.go('completeorder');
		};
		
		self.remove = function(ind){
			$user.removeUserItem(ind);
		};
	}]);
console.log("this file has been loaded: mycart.js");