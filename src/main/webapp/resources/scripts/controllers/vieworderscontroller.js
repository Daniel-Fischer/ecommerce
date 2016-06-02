/**
 * Controller for individual user orders
 */
console.log("this file was loaded: vieworderscontroller.js");
angular.module("LuluCC").controller("viewOrdersCtrl", ['$order','$user', '$state', 'orderUrl', function($order, $user, $state, orderUrl){
	var self=this;
	var orderPromise;
	self.currentUser = $user.user();
	self.orders ='';
	if(self.currentUser.loggedin){
	orderPromise = $order.viewAll(self.currentUser,orderUrl);
	orderPromise.then(function(response){
		self.orders = response.data;
		console.log(self.cupcakes);
	});
	};
}]);
