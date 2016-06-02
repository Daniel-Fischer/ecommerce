/**
 * the main module for lulu's cupcakes
 */
function MethodNotImplementedException(message){
	this.message = message;
	this.name = "MethodNotImplementedException";
}

function RegisterUserException(message){
	this.message = message;
	this.name = "RegisterUserException";
}

console.log("this file was loaded: app.js");
angular.module("LuluCC", ['ui.router','ngAnimate','ui.bootstrap'])
	.constant("loginUrl", "http://localhost:8085/LuluCupcakeService/rest/login")
	.constant("orderUrl", "http://localhost:8085/LuluCupcakeService/rest/orders")
	.constant("oUrl", "http://localhost:8085/LuluCupcakeService/rest/order/")
	.constant("accountUrl","http://localhost:8085/LuluCupcakeService/rest/account")
	.constant("userinfoUrl","http://localhost:8085/LuluCupcakeService/rest/user/info")
	.constant("registerUrl", "http://localhost:8085/LuluCupcakeService/rest/register")
	.constant("ingredientUrl", "http://localhost:8085/LuluCupcakeService/rest/options")
	.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
		$urlRouterProvider.otherwise('/home');
		$stateProvider
			.state('home',{
				url: '/home',
				templateUrl: 'htmlfrags/home.html'
			})
			.state('login',{
				url: '/login',
				templateUrl: 'htmlfrags/login.html'
			})
			.state('register',{
				url: '/register',
				templateUrl: 'htmlfrags/register.html'
			})
			.state('account',{
				url:'/account',
				templateUrl: 'htmlfrags/account.html'
			})
			.state('orders', {
				url: '/orders',
				templateUrl: 'htmlfrags/orders.html'
			})
			.state('order', {
				url: '/order/{orderId}',
				templateUrl: 'htmlfrags/order.html',
				controller: function($stateParams,$state){
					this.orderId=$stateParams.orderId;
				},
				controllerAs:'order'
			})
			.state('neworder',{
				url: '/new',
				templateUrl: 'htmlfrags/neworder.html'
			})
			.state('mycart', {
				url: '/mycart',
				templateUrl: 'htmlfrags/mycart.html'
			})
			.state('completeorder',{
				url: '/complete',
				templateUrl: 'htmlfrags/confirmorder.html'
			});
	}]);