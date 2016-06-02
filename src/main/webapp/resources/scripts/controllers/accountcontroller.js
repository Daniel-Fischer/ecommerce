/**
 * This Controller is for modifying your account information
 */
console.log("this file was loaded: accountcontroller.js");
angular.module("LuluCC").controller("viewAccountCtrl", ['$user', 'accountUrl', function($user, accountUrl){
	var self=this;
	var accountPromise;
	self.currentUser = $user.user();
	self.newinfo =$user.user();
	self.checkPassword= "";
	self.error;
	self.success;
	console.log(self.newinfo);
	self.submit = function(){
		if (self.checkPassword == self.currentUser.password){
			self.error=undefined;
			self.newinfo.password= self.checkPassword;
			self.accountPromise = $user.saveUser(self.newinfo,accountUrl);
			self.accountPromise.then(function(){
				self.success="Account Information Updated Successfully"
			}),function(error){
				self.success=undefined;
				self.error = "Username Already Taken"
			};
			self.checkPassword="";
		}else{
			self.success=undefined;
			self.error ="Incorrect Password";
			self.checkPassword="";
		}
	}
}]);