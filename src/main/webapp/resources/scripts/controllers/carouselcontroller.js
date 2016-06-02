/**
 * 
 */
console.log("this file was loaded: carousalcontroller.js");
angular.module("LuluCC").controller("carouselCtrl", function($scope){
	$scope.myInterval = 5000;
	$scope.noWrapSlides = true;
	var slies = $scope.slides = [
{
	"image":"resources/images/carousel/1.jpg",
	"text":"1",
	"id":1
},{
	"image":"resources/images/carousel/2.jpg",
	"text":"2",
	"id":2
},{
	"image":"resources/images/carousel/3.jpg",
	"text":"3",
	"id":3
},{
	"image":"resources/images/carousel/4.jpg",
	"text":"4",
	"id":4
},{
	"image":"resources/images/carousel/5.jpg",
	"text":"5",
	"id":5
}
	];

});