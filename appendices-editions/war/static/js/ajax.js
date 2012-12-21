function fillSequence(url, jsonp) {
	
	/*
	 * if same domain (jsonp = false)
	 * if crossdomain is needed use jsonp (script with jsonp = true) or apache proxy
	 */

	$.ajax({
		url : url,
		dataType : (jsonp) ? 'jsonp' : 'json',
		cache : false,
		jsonp : 'jsonp',
		success : function(data) {
			if (data != null) {
				$.each(data, function(key, result) {
					if (result.length > 0) {
						$.each(result, function(index) {
							item = result[index];
							if (item.seqType == 'L') {
						    	sequenceLeft.push({ time: item.seqTime, type: item.type, value : item.value, title: item.title, author: item.author });
						    }
						    if (item.seqType == 'R') {
						    	sequenceRight.push({ time: item.seqTime, type: item.type, value : item.value, title: item.title, author: item.author });
						    }
						});
					}
				});
				playSequences();
				
			} else {
				alert("error");
			}
		}
	}).error(function() {
		alert("error");
	});
	
}

function launch(url) {
	
	$.ajax({
		url : url,
		dataType : 'html',
		cache : false,
		jsonp : 'jsonp',
		success : function(data) {
			if (data != null) {
				token = data;
				fillSequence('/play?s=' + data, false);
			} else {
				alert("error");
			}
		}
	}).error(function() {
		alert("error");
	});	

}

function playSequences() {
	
	$('#playLink').attr('href', '?s=' + token);
	
	// var func = function() {
    	display("right", sequenceRight[rightId].type, sequenceRight[rightId].value, sequenceRight[rightId].title, sequenceRight[rightId].author, rightId, sequenceRight[rightId].time);
	// };
	// window.setTimeout(func, 0);

	// var func = function() {
		display("left", sequenceLeft[leftId].type, sequenceLeft[leftId].value, sequenceLeft[leftId].title, sequenceLeft[leftId].author, leftId, sequenceLeft[leftId].time);
	// };
	// window.setTimeout(func, 0);
	
}



function display(containerId, type, value, title, author, id, time) {
	
	node = '';
	
	if (type == 'IMAGE') {
		node = '<img src="' + value + '" width="460px" />';
	}
	
	if (type == 'MUSIC') {
		audioId = containerId + "audio" + id;
		node = '<audio src="' + value + '" preload="auto" autoplay="autoplay" id="' + audioId + '"/>';
	}
	
	selector = '#' + containerId + ' div';
	
	$(selector).remove();
	$('<div>', {
		html : node
	}).appendTo('#' + containerId);
	
	if (type == 'MUSIC') {
	  	audiojs.events.ready(function() {
	  		selectorAudio = '#' + audioId;
	  		audiojs.create($(selectorAudio));
	    });
	}
	
	if (containerId == 'left') {
		if (sequenceLeft.length > leftId + 1) {
			leftId = id + 1;
			var func = function() {
				display("left", sequenceLeft[leftId].type, sequenceLeft[leftId].value, sequenceLeft[leftId].title, sequenceLeft[leftId].author, leftId, sequenceLeft[leftId].time);
			};
			window.setTimeout(func, time);
		} else {
			// leftId = 0; // don't restart
		}

	}
	if (containerId == 'right') {
		if (sequenceRight.length > rightId + 1) {
			rightId = id + 1;
			var func = function() {
		    	display("right", sequenceRight[rightId].type, sequenceRight[rightId].value, sequenceRight[rightId].title, sequenceRight[rightId].author, rightId, sequenceRight[rightId].time);
			};
			window.setTimeout(func, time);
		} else {
			// rightId = 0; // don't restart
		}

	}	
}

