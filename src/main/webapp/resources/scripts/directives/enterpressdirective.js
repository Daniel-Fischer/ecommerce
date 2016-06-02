/**
 * handles the keypress of the enter key
 */
angular.module("LuluCC")
	.directive("enterpress", function(){
		return function(scope, element, attrs){
			element.bind("keypress", function(event){
				if(event.which == 13){
					scope.$apply(function(){
						scope.$eval(attrs.enterpress);
					});
					event.preventDefault();
				}
			});
		};
	});
console.log("this file has been loaded: enterpressdirective.js");