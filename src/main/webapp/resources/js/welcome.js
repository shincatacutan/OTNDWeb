/**
 * 
 */

$(function() {
	getUser();
	
	$body = $("body");

	$(document).on({
	    ajaxStart: function() { $body.addClass("loading");    },
	     ajaxStop: function() { $body.removeClass("loading"); }    
	});
});

var getUser = function() {
	var network = new ActiveXObject("WScript.Network");
	var networkId = network.UserName;
//	var networkId = "scatacut";
	$("#empID").val(networkId)
	$("#userForm").submit();
}
