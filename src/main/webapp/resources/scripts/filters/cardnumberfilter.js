/**
 * 
 */
angular.module("LuluCC").filter('ccn', function() {
	return function(card) {
		if (card) {
			var output = '####-####-####-' + card.substring(12, 17);
			return output;
		}
	};
});