var bannerWidth = $('.mediabannerlist li').length * 100;
$('.mediabannerlist').width(bannerWidth);

//MEDIA BANNER SCROLLING
$('.scroller.right').mouseenter(function(){
	$('.banner-inner').scrollTo(bannerWidth, 1200);
});
$('.scroller.left').mouseenter(function(){
	$('.banner-inner').scrollTo(0, 1200);
});
$('.scroller').mouseleave(function(){
	$('.banner-inner').stop();
});