
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
			var toReturn;
//			for (var result in results)
//			{
//				var searchTime = false;
//				var s = result.get("meetings").split(" ");
//				for (word in s)
//				{
//					if (word.indexOf(request.params.day) !== -1) searchTime=true;
//				}
//			}
			response.success(results);
		},
		error: function() {
			response.error("movie lookup failed");
		}
	});
});