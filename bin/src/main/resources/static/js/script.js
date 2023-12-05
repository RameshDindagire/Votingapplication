cansole.log("this is script file");

// const toggleSidebar = () => {
// 	if ($(".sidebar").is(":visible")) {
// 		//true
// 		//band karna hai
// 		$(".sidebar").css("display", "none");
// 		$(".content").css("margin-left", "0%");
// 	} else {
// 		//false
// 		//show karna hai
// 		$(".sidebar").css("display", "block");
// 		$(".content").css("margin-left", "20%");
// 	}
// };
$(".counter").each(function () {
  var $this = $(this),
    countTo = $this.attr("data-countto");
  countDuration = parseInt($this.attr("data-duration"));
  $({ counter: $this.text() }).animate(
    {
      counter: countTo
    },
    {
      duration: countDuration,
      easing: "linear",
      step: function () {
        $this.text(Math.floor(this.counter));
      },
      complete: function () {
        $this.text(this.counter);
      }
    }
  );
});


