/**
 * Controller for final confirmation and payment of the order
 */
angular.module("LuluCC").controller("FinishOrder",
		[ '$http', '$user', '$state', 'userinfoUrl', 'oUrl', '$scope', function($http, $user, $state, userinfoUrl, oUrl, $scope) {
			var self = this;
			self._pageInfo = {
				"stores" : [],
				"payments" : [],
				"shippingInfo" : [],
				"currStore" : {},
				"currShipping" : {},
				"currPayment" : {},
				"currType" : {}
			};

			self.init = function() {
				$http({
					"method" : 'POST',
					"url" : userinfoUrl,
					'data' : $user.user()
				}).then(function(response) {
					self._pageInfo.stores = response.data.stores;
					self._pageInfo.shippingInfo = response.data.shipping;
					self._pageInfo.payments = response.data.payment;
				});
			};

			self.submit = function() {
				var orderwrapper = {
					"order" : {
						"store" : self._pageInfo.currStore,
						"payment" : self._pageInfo.currPayment,
						"shipping" : self._pageInfo.currShipping,
						"type" : self._pageInfo.currType,
						"totalCost" : self.orderTotal()
					},
					"cupcakes": $user.user().shoppingCart
				};

				if (self.notAnonUser()) {
					orderwrapper.order.payment.email = $user.user().username;
					orderwrapper.order.payment.user = $user.user();
					orderwrapper.order.shipping.user = $user.user();
				}
				
				$http({
					'method': 'POST',
					'data': orderwrapper,
					'url': oUrl + "submit"
				}).then(function(response){
					$user.user().shoppingCart = [];
					$state.go('home');
				});
			};

			self.orderTotal = function() {
				var cupcakes = $user.user().shoppingCart;
				var total = 0.00;
				for (var i = 0; i < cupcakes.length; i++) {
					total += cupcakes[i].cost;
				}
				return total;
			};

			self.notAnonUser = function() {
				return $user.user().loggedin;
			};
			
			self.validOrder = function(){
				
				if($user.user().shoppingCart.length < 1){
					return false;
				}
				
				if(!self._pageInfo.currType.type){
					return false;
				}
				
				if(!validateStore()){
					return false;
				}
				
				if(!validateCC()){
					return false;
				}
				
				if(!validateEmail()){
					return false;
				}
				
				if(!validateAddress()){
					return false;
				}
				return true;
			};
			
			function validateCC(){
				var ccn = self._pageInfo.currPayment.ccn;
				var ccv = self._pageInfo.currPayment.ccv;
				
				if(!ccn || !ccv)
					return false;
				
				if(ccn.length < 16 || ccn.length > 16 || ccv.length < 3 || ccv.length > 3)
					return false;
				if(isNaN(ccn) || isNaN(ccv))
					return false;
				return true;
			}
			
			function validateStore(){
				return self._pageInfo.currStore.address;
			}
			
			function validateEmail(){
				if(self.notAnonUser()){
					return true;
				}else{
					return $scope.orderform.email.$valid;
				}
			}
			
			function validateAddress(){
				if(self._pageInfo.currType.type == "Pick Up")
					return true;
				
				if(!self._pageInfo.currShipping.address)
					return false;
				
					var str = self._pageInfo.currShipping.address.street;
					var cty = self._pageInfo.currShipping.address.city;
					var state = self._pageInfo.currShipping.address.state;
					var zip = self._pageInfo.currShipping.address.zip;
					
					if(!str || !cty || !state || !zip){
						return false
					}
					
					if(str.length < 6 || cty.length < 3 || state.length < 2 || zip.length < 5){
						return false;
					}
				return true;
			}

		} ]);
console.log("this file has been loaded: confirmordercontroller.js");