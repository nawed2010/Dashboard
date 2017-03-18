'use strict';

var module = angular.module('mainApp');

module.filter("dateTimeFilter", function() {
	console.log("Filter in action");
	return function(date) {
		if (date) {
			var newDate= date.substring(0, 10);
			var d=new Date(newDate);
			//var temp = d.toDateString();
			return d.toDateString().substring(3, d.toString().length);
		} else {
			return date;
		}
	};
});