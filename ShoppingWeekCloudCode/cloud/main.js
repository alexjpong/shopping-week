
//Use Parse.Cloud.define to define as many cloud functions as you want.
//For example:
Parse.Cloud.define("hello", function(request, response) {
	response.success("Hello world!");
});


Parse.Cloud.define("coursesAtTime", function(request, response) {
	  var query = new Parse.Query("Course");
	  query.contains("meetings", request.params.day);
	  query.find({
	    success: function(results) {
	      for (var result in results)
	    },
	    error: function() {
	      response.error("movie lookup failed");
	    }
	  });
	});