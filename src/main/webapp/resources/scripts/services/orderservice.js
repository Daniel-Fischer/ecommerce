/**
 * Service for viewing all orders/order by id/ and create order
 */
angular.module("LuluCC").service("$order", ['$http', function($http){
	var self = this;
	
	self.viewAll = function(user, url){
		return $http({
			'method': 'POST',
			'url': url,
			'data': {
						'id': user.id,
						'username':user.username
					}
		});
	};
}]);
console.log("this file was loaded: orderservice.js");