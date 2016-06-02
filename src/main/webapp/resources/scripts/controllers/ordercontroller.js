/**
 * 
 */
console.log("this file was loaded: ordercontroller.js");
angular.module("LuluCC").controller("orderCtrl", ['$timeout','$stateParams','$user','$http','$state', 'oUrl', function($timeout, $stateParams,$user, $http, $state, oUrl){
	var self=this;
	var orderPromise;
	self.recentlyCancelled = false;
	self.isComplete;
	self.currentStatusPercent=25;
	self.cupcakes ='';
	self.currentUser = $user.user();
	orderPromise = $http({
		'method':'GET',
		'url': oUrl+$stateParams.orderId
	});
	var myTimeout;
	orderPromise.then(function(response){
		self.cupcakes = response.data;
		if (self.cupcakes[0].order.status =='Delivered' || self.cupcakes[0].order.status =='Picked Up'|| self.cupcakes[0].order.status =='Cancelled'){
			self.currentStatusPercent=100;
		}else{
			myTimeout = $timeout(self.checkComplete,15000);
		}
	});
	self.checkComplete = function(){
		if (self.cupcakes[0].order.status =='Delivered' || self.cupcakes[0].order.status =='Picked Up'|| self.cupcakes[0].order.status =='Cancelled'){
			self.currentStatusPercent=100;
		}else if (self.cupcakes[0].order.status=='Preparing'){
			self.currentStatusPercent=50;
			self.cupcakes[0].order.status='Baking'
			self.doUpdate();
			myTimeout= $timeout(self.checkComplete,15000);
		}else if (self.cupcakes[0].order.status=='Baking'&& self.cupcakes[0].order.type.type=='Delivery'){
			self.currentStatusPercent=75;
			self.cupcakes[0].order.status='Out For Delivery'
			self.doUpdate();
			myTimeout= $timeout(self.checkComplete,25000);
		}else if (self.cupcakes[0].order.status=='Baking'&& self.cupcakes[0].order.type.type=='Pick Up'){
			self.currentStatusPercent=75;
			self.cupcakes[0].order.status='Ready For Pickup'
			self.doUpdate();
			myTimeout= $timeout(self.checkComplete,25000);
		}else if (self.cupcakes[0].order.status=='Out For Delivery'&& self.cupcakes[0].order.type.type=='Delivery'){
			self.currentStatusPercent=100;
			self.cupcakes[0].order.status='Delivered'
			self.doUpdate();
		}else if (self.cupcakes[0].order.status=='Ready For Pickup'&& self.cupcakes[0].order.type.type=='Pick Up'){
			self.currentStatusPercent=100;
			self.cupcakes[0].order.status='Picked Up'
			self.doUpdate();
		}
	}
	self.doCancel = function(){
		self.cupcakes[0].order.status='Cancelled';
		self.currentStatusPercent=100;
		self.recentlyCancelled=true;
		self.doUpdate();
		};
	self.doUpdate = function(){
		$http({
			'method':'POST',
			'url': oUrl+$stateParams.orderId+"/update",	
			'data': self.cupcakes[0].order
		}).then(function(){
		});
	};
}]);