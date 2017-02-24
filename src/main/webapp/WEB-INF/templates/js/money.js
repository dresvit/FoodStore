function cal() {
	var sum = 0;
	for (var i = 1; i <= 9; i++) {
		var now = parseInt($('#pos' + i).val());
		if (isNaN(now)) now = 0;
		sum += parseFloat($('#price' + i).text()) * now;
	}
	if (!isNaN(sum)) {
		$('.money-pay').text(sum.toFixed(2) + " 元");
		$('.money-pay-hidden').val(sum.toFixed(2) + "元");
	}
}

$(function() {
	setInterval("cal()", 50);
	$("#submit-btn").click(cal());
});