/**
 * custom directive for the navbar
 */
angular.module("LuluCC")
	.directive("navbar", function(){
		
		return {
			restrict: 'E',
			templateUrl: "htmlfrags/navbar.html"
		};
	});
console.log("this file has loaded: navbardirective.js");