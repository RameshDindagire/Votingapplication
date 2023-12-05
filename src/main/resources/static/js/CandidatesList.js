function checkProgress() {
	if ($("input:radio[name*='selectedCandidateId']:checked").length != 0) {
		$('.submit-button').prop('disabled', false);
	} else {
		$('.submit-button').prop('disabled', true);
	}
}

$(function() {
	// Set the status once the doc loads.
	checkProgress();
	// Set it again when any of the radio buttons are clicked.
	$("input:radio[name*='selectedCandidateId']").on("click change", checkProgress);
	$('.submit-button').hover(
		function() {
			if ($(this).prop('disabled')) {
				$(this).css('cursor', 'not-allowed');
			}
		},
		function() {
			$(this).css('cursor', 'pointer');
		}
	);
});