/**
 * helps display errors
 */
angular.module("LuluCC")
	.directive("error", ['$login', function($login){
		return{
			restrict: 'E',
			templateUrl: 'htmlfrags/error.html',
			controller: function(){
				var self = this;
				self._error;
				
				self.error = function(){
					return $login.error();
				}
			},
			controllerAs: 'errorCtrl'
		};
	}]);
console.log("this file has been loaded: errordirective.js");