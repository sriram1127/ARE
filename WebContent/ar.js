$(document).ready(function() {
	$('#clearscreen').click(function() {
		alert("inn");
		window.location.reload();
	});
});

function parse() {
	$("#decisionattribute").find("option:gt(0)").remove();
	$("#stableattribute").empty();
	var attributeFilePath = document.getElementById('attributefile').files[0].name;
	jQuery.get(attributeFilePath, function(data) {
		var myvar = data.replace(/\s+/g, " ");
		attributes = myvar.split(" ");
		$('#attributes').val(attributes);
		decisionAttributeSelect = document.getElementById('decisionattribute');
		stableAttributeSelect = document.getElementById('stableattribute');
		for (var i = 0; i < attributes.length; i++) {
			var opt1 = document.createElement('option');
			var opt2 = document.createElement('option');
			opt1.value = i + '-' + attributes[i];
			opt1.innerHTML = i + '-' + attributes[i];
			opt2.value = i + '-' + attributes[i];
			opt2.innerHTML = i + '-' + attributes[i];
			decisionAttributeSelect.appendChild(opt1);
			stableAttributeSelect.appendChild(opt2);
		}

	});
	var dataFilePath = document.getElementById('datafile');
};

function selection() {
	var stableArray = attributes.slice();
	var da = jQuery("#decisionattribute option:selected").val();
	stableArray[stableArray.indexOf(da.substr(da.indexOf('-') + 1) + '')] = '';
	$("#stableattribute").empty();
	for (var i = 0; i < stableArray.length; i++) {
		if (stableArray[i] == '') {
			continue;
		}
		var opt2 = document.createElement('option');
		opt2.value = i + '-' + stableArray[i];
		opt2.innerHTML = i + '-' + stableArray[i];
		stableAttributeSelect.appendChild(opt2);
	}

}
