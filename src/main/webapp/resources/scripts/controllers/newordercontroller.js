/**
 * Controller for the new order view
 */
angular.module("LuluCC")
	.controller("NewOrder", ['$http', '$user', '$state', 'ingredientUrl', function($http, $user, $state, ingredientUrl){
		var self = this;
		self.accordion = {
				"oneAtATime": true
		};
		
		self.imgSrcPrefix = "resources/images/ingredients/";
		self.batterSrcPrefix = self.imgSrcPrefix + "batters/";
		self.cupSrcPrefix = self.imgSrcPrefix + "cups/";
		self.frostingSrcPrefix = self.imgSrcPrefix + "frostings/";
		self.sprinkleSrcPrefix = self.imgSrcPrefix + "sprinkles/";
		
		self.customChoices = {
				"batters": [],
				"frostings": [],
				"sprinkles": [],
				"cups": [],
				"batterChoice": {
					"id": 0,
					"src": "",
					"description": "",
					"costMod": 0.00
				},
				"frostingChoice": {
					"id": 0,
					"src": "",
					"description": "None",
					"costMod": 0.00
				},
				"sprinkleChoice": {
					"id": 0,
					"src": "",
					"description": "None",
					"costMod": 0.00
				},
				"cupChoice": {
					"id": 0,
					"src": "",
					"description": "",
					"costMod": 0.00
				},
				"qty": 6
		};
		
		var findCostMod = function(desc, arr){
			for(var i in arr){
				if(arr[i].description === desc){
					return arr[i].costMod;
				}
			}
			
		};
		
		self.orderSize = function(){
			return $user.user().shoppingCart.length;
		};
		
		self.initialize = function(){
			$http({
				'method': 'GET',
				'url': ingredientUrl,
			}).then(function(response){
				var ingreds = response.data;
				for(var i = 0; i < ingreds.length; i++){
					switch(ingreds[i].type){
					case "Cup":
						self.customChoices.cups.push(ingreds[i]);
						break;
					case "Sprinkle":
						self.customChoices.sprinkles.push(ingreds[i]);
						break;
					case "Batter":
						self.customChoices.batters.push(ingreds[i]);
						break;
					case "Frosting":
						self.customChoices.frostings.push(ingreds[i]);
						break;
					}
				}
			}, function(error){});
			
			self.batterSrc = function(desc){
				if(desc){
					desc = desc.replace(/ /g, "_").toLowerCase() + ".png";
					desc = self.batterSrcPrefix + desc;
					return desc;
				}else{
					return self.customChoices.batterChoice.src;
				}
			};
			
			self.cupSrc = function(desc){
				if(desc){
					desc = desc.replace(/ /g, "_").toLowerCase() + ".png";
					desc = self.cupSrcPrefix + desc;
					return desc;
				}else{
					return self.customChoices.cupChoice.src;
				}
			};
			
			self.sprinkleSrc = function(desc){
				if(desc){
					desc = desc.replace(/ /g, "_").toLowerCase() + ".png";
					desc = self.sprinkleSrcPrefix + desc;
					return desc;
				}else{
					return self.customChoices.sprinkleChoice.src;
				}
			};
			
			self.frostingSrc = function(desc){
				if(desc){
					desc = desc.replace(/ /g, "_").toLowerCase() + ".png";
					desc = self.frostingSrcPrefix + desc;
					return desc;
				}else{
					return self.customChoices.frostingChoice.src;
				}
			};
			
			self.cupcakes = function(){
				return $user.user.shoppingCart;
			};
			
			self.batterChoice = function(desc, id){
				if(desc){
					self.customChoices.batterChoice.id = id;
					self.customChoices.batterChoice.description = desc;
					self.customChoices.batterChoice.src = self.batterSrc(desc);
					self.customChoices.batterChoice.costMod = findCostMod(desc, self.customChoices.batters);
				}else{
					return self.customChoices.batterChoice;
				}
			};
			
			self.frostingChoice = function(desc, id){
				if(desc){
					self.customChoices.frostingChoice.id = id;
					self.customChoices.frostingChoice.description = desc;
					self.customChoices.frostingChoice.src = self.frostingSrc(desc);
					self.customChoices.frostingChoice.costMod = findCostMod(desc, self.customChoices.frostings);
				}else{
					return self.customChoices.frostingChoice;
				}
			};
			
			self.sprinkleChoice = function(desc, id){
				if(desc){
					self.customChoices.sprinkleChoice.id = id;
					self.customChoices.sprinkleChoice.description = desc;
					self.customChoices.sprinkleChoice.src = self.sprinkleSrc(desc);
					self.customChoices.sprinkleChoice.costMod = findCostMod(desc, self.customChoices.sprinkles);
				}else{
					return self.customChoices.sprinkleChoice;
				}
			};
			
			self.cupChoice = function(desc, id){
				if(desc){
					self.customChoices.cupChoice.id = id;
					self.customChoices.cupChoice.description = desc;
					self.customChoices.cupChoice.src = self.cupSrc(desc);
					self.customChoices.cupChoice.costMod = findCostMod(desc, self.customChoices.cups);
				}else{
					return self.customChoices.cupChoice;
				}
			};
			
			self.add = function(){
				var choices = self.customChoices;
				if(self.itemValid()){
					return;
				}else{
					if(self.customChoices.frostingChoice.id == 0){
						var frostings = self.customChoices.frostings;
						for(var i = 0; i < frostings.length; i++){
							if(frostings[i].description === "None"){
								self.customChoices.frostingChoice.id = frostings[i].id;
								break;
							}
						}
					}
					
					if(self.customChoices.sprinkleChoice.id == 0){
						var sprinkles = self.customChoices.sprinkles;
						for(var i = 0; i < sprinkles.length; i++){
							if(sprinkles[i].description === "None"){
								self.customChoices.sprinkleChoice.id = sprinkles[i].id;
								break;
							}
						}
					}
					
					var obj = {"batter": choices.batterChoice, "frosting": choices.frostingChoice, "sprinkle": choices.sprinkleChoice, 
								"cup": choices.cupChoice, "qty": choices.qty, "cost": self.cupcakeTotal()};
					$user.user().shoppingCart.push(obj);
					choices.batterChoice = {"id": 0, "src": "", "description": "", "costMod": 0.00};
					choices.frostingChoice = {"id": 0, "src": "", "description": "None", "costMod": 0.00};
					choices.sprinkleChoice = {"id": 0, "src": "", "description": "None", "costMod": 0.00};
					choices.cupChoice = {"id": 0, "src": "", "description": "", "costMod": 0.00};
					choices.qty = 6;
				}
			};
			
			self.itemValid = function(){
				var choices = self.customChoices;
				return (choices.batterChoice.description === "" || choices.cupChoice.description === "");
			};
			
			self.cupcakeTotal = function(){
				var qty = self.customChoices.qty;
				var oTotal = 0.00;
				oTotal += self.customChoices.batterChoice.costMod;
				oTotal += self.customChoices.frostingChoice.costMod;
				oTotal += self.customChoices.sprinkleChoice.costMod;
				oTotal += self.customChoices.cupChoice.costMod;
				oTotal *= qty;
				
				return oTotal;
				
			}
		};
	}]);
console.log("this file has been loaded: newordercontroller.js");