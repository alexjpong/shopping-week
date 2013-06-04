
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
			var resultlist = JSON.parse(results);
			for (var result in resultlist)
			{
				var searchTime = false;
				var s = result.["metings"].split(" ");
				for (word in s)
				{
					if (word.indexOf(request.params.day) !== -1) searchTime=true;
					if ()
					{
						toReturn.push(result);
						break;
					}
				}
			}
			response.success(JSON.stringify(toReturn))
			//response.success(results);
		},
		error: function() {
			response.error("movie lookup failed");
		}
	});
});